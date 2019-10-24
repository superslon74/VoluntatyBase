package voluntaty.com.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.TypedValue;

import com.voluntary.database.DataHelper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
/* e-mail: superslon74@gmail.com
 * skype: superslon74
 * шаманский геннадий александрович
 */

public class RepeatingAlarmServiceAudioOneInsert extends BroadcastReceiver {

    public static final String DEFAULT_STORAGE_LOCATION = "/sdcard/audiorecorder";

    private DataHelper dh;

    String login = null;
    String email = null;
    String password = null;
    String URL_HTTP = null;
    ArrayList<Integer> list = null;
    String EMAIL = null;
    long MIN_TIME_BW_UPDATES2 =300000;

    public static boolean isNetworkConnectedOrConnecting(Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public void onReceive(Context context, Intent intent) {
        if (isNetworkConnectedOrConnecting(context.getApplicationContext())) {

            File dir = new File(DEFAULT_STORAGE_LOCATION);

            if (!dir.exists()) {
                try {
                    dir.mkdirs();
                } catch (Exception e) {
                }
            } else {
                if (!dir.canWrite()) {
                }
            }

            someTask(context);
        }
    }


    void someTask(Context context) {

        dh = null;
        dh = new DataHelper(context);

        String serviceaudioone;
        serviceaudioone = null;

        serviceaudioone = dh.selectServicesms("1");
        dh = null;
        if(serviceaudioone.length()>0) {

            dh = new DataHelper(context);
            URL_HTTP = dh.selectUrl_Http();
            login = dh.selectLogin();
            email = dh.selectEmail();
            password = dh.selectPassword();
            dh = null;

            File sdPath = Environment.getExternalStorageDirectory();
            sdPath = new File(sdPath.getAbsolutePath() + "/audiorecorder");
            String filename = null;
            //---------------------------------------------------------------------------------------
            if (sdPath.exists()) {
                File[] images = sdPath.listFiles();
                for (int i = 0; i < images.length; i++) {
                    String strt = null;
                    strt = "" + images[i];
                    String[] s = strt.split("/");
                    int dlina = s.length;
                    strt = null;
                    filename = s[dlina - 1];

                    String[] s2 = filename.split("\\.");

                    String nameaudiofile=null;
                    dh =null;
                    dh= new DataHelper(context);

                    String http_url=null;
                    String email=null;
                    http_url=dh.selectUrl_Http();
                    email=dh.selectEmail();

                    nameaudiofile=dh.selectNameaudiofile(filename);
                    int dlinaaudio=nameaudiofile.length();
                    if(dlinaaudio<1){
                        dh.InsertNameaudiofile(filename);
                 //       dh.insertIdNameFile(""+number);
                        new SendFile().execute("" + sdPath, filename,email,http_url);

                    }

                    http_url=null;
                    email=null;
                    dh =null;

                    nameaudiofile=null;
                    strt = null;
                    filename = null;

                }

            }



        }
        serviceaudioone = null;

    }



    public class SendFile extends AsyncTask<String, String, String> {

        private String str2=null;
        HttpURLConnection conn;

        @Override
        protected String doInBackground(String... params)
        {

            str2=params[1];
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            try {

                String emailmodify=null;
                emailmodify=params[2].replaceAll("\\.", "_");
                emailmodify=emailmodify.replaceAll("@", "_");

                URL url = new URL(params[3]+"/user_"+emailmodify+"/audiophonetwo/upload.php");

                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("Content-Type","multipart/form-data;boundary=" + boundary);

                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                TypedValue value = new TypedValue();
                //  activity.getResources().getValue(params[0], value, true);
                dos.writeBytes("Content-Disposition: post-data; name=fileToUpload;filename=" + params[1] + "" + lineEnd);
                dos.writeBytes(lineEnd);


                File sdFile = new File(params[0],params[1]);
                InputStream inputStream = new FileInputStream(sdFile);

                int bytesAvailable = inputStream.available();
                int maxBufferSize = 1000;
                byte[] buffer = new byte[bytesAvailable];

                int bytesRead = inputStream.read(buffer, 0, bytesAvailable);

                while (bytesRead > 0) {
                    dos.write(buffer, 0, bytesAvailable);
                    bytesAvailable = inputStream.available();
                    bytesAvailable = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = inputStream.read(buffer, 0, bytesAvailable);
                }

                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                inputStream.close();
                dos.flush();
                dos.close();
            }
            catch (Exception ex) {
                //    return false;
            }


            try {
                BufferedInputStream responseStream = new BufferedInputStream(conn.getInputStream());
                BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));
                String line = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = responseStreamReader.readLine()) != null)
                {
                    stringBuilder.append(line).append("\n");
                }
                responseStreamReader.close();

                String response = stringBuilder.toString();
                conn.disconnect();

            } catch (Exception ex) {
                //return false;
            }

            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

    }







}