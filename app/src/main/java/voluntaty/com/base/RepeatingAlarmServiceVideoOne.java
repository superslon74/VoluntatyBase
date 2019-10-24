package voluntaty.com.base;

/* e-mail: superslon74@gmail.com
 * skype: superslon74
 * шаманский геннадий александровичь
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;

import com.voluntary.database.DataHelper;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class RepeatingAlarmServiceVideoOne extends BroadcastReceiver {

    private DataHelper dh;
    SendFile sendfile=null;

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
    void someTask(Context context){


        File sdPath = Environment.getExternalStorageDirectory();
        sdPath = new File(sdPath.getAbsolutePath() + "/DCIM/Camera");
        String filename=null;
        //---------------------------------------------------------------------------------------
        if(sdPath.exists()){
            File[] images = sdPath.listFiles();
            for(int i=0;i<images.length;i++){
                String strt=null;
                strt=""+images[i];
                String[] s =strt.split("/");
                int dlina=s.length;

                filename=s[dlina-1];

                String[] s2 =filename.split("\\.");

                //       Toast.makeText(getApplicationContext()," mp4 "+s2[1], Toast.LENGTH_LONG).show();
                if(s2[1].equals("mp4"))
                {
               /*     String namevidiofile=null;

                    namevidiofile=dh.selectFileNameVideo(filename);
                    int dlinavidio=namevidiofile.length();
                    //    		  Toast.makeText(getApplicationContext()," else "+s2[1], Toast.LENGTH_LONG).show();
                    if(dlinavidio<1) {
                        //  		  Toast.makeText(getApplicationContext()," mp4 "+strt+" ??? "+filename+"  ???", Toast.LENGTH_LONG).show();
               */         sendfile = new SendFile();
                        sendfile.ValueContect(context);
                        sendfile.execute(strt, filename);
             /*           dh.insertFileNameVideo(filename);
                    }
                    namevidiofile=null;
           */     }
                else
                {
                    //		  Toast.makeText(getApplicationContext()," else "+s2[1], Toast.LENGTH_LONG).show();

                }
                strt=null;
                filename=null;
            }

        }

    }

    public void ftpConn(String hostAddress, String log, String password,String pathFilename,String fileName,String email){
        FTPClient fClient = new FTPClient();

        String emailmodify=null;
        emailmodify=email.replaceAll("\\.", "_");
        emailmodify=emailmodify.replaceAll("@", "_");

       try {
            fClient.connect(hostAddress);
            fClient.login(log, password);
            //	  fClient.connect("ftp.voluntaty.esy.es");
            //	  fClient.login("u638105038", "1234567890");
            //	  dh.InsertFTP("ftp.voluntaty.esy.es", "u638105038", "1234567890");
            fClient.enterLocalPassiveMode();
            fClient.setFileType(FTP.BINARY_FILE_TYPE);

            File firstLocalFile = new File(pathFilename);
            String firstRemoteFile = "/public_html/user_"+emailmodify+"/video/"+fileName;
            InputStream inputStream = new FileInputStream(firstLocalFile);

            fClient.storeFile(firstRemoteFile,inputStream);
            inputStream.close();

        } catch (IOException ex) {
            System.err.println(ex);
        }

        finally {
            try {
                if (fClient.isConnected()) {
                    fClient.logout();
                    fClient.disconnect();
                }
            } catch (IOException ex) {
                //        ex.printStackTrace();
            }
        }

    }


    public class SendFile extends AsyncTask<String, String, String> {

        private String str2=null;
        HttpURLConnection conn;
        String strt=null;
        String filename=null;

        Context context;

        void ValueContect(Context contextA){
            context=contextA;
        }

        @Override
        protected String doInBackground(String... params)
        {

            strt=params[0];
            filename=params[1];
//            ftpConn("ftp.voluntaty.esy.es", "u638105038", "1234567890",strt,filename,"superslon74@gmail.com");
            dh =null;
            dh= new DataHelper(context);
      //      String ftp=null;
    //        String email=null;
            String http=null;

  //          ftp=dh.selectFTP();
//            email=dh.selectEmail();
            http=dh.selectUrl_Http();
            dh =null;

            try {
                DefaultHttpClient hc = new DefaultHttpClient();
                HttpGet postMethod = new HttpGet(http+"/index.php/api/ftpaccount");
                postMethod.setHeader("Content-Type", "text/plain; charset=utf-8");
                postMethod.setHeader("Expect", "100-continue");

                HttpResponse response = hc.execute(postMethod);
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);
                str2=content.toString().trim();

            } catch (Exception e) {
                System.out.println("Exp=" + e);
            }

  /*          String[] res = str2.split("\\|");

            ftpConn(res[0],res[1],res[2],strt,filename,email);

*/

            http=null;
 /*           email=null;
            ftp=null;

*/
            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
     //      	Toast.makeText(context.getApplicationContext()," ????? "+ str2, Toast.LENGTH_LONG).show();


            dh =null;
            dh= new DataHelper(context);
         //   String ftp=null;
            String email=null;
            String http=null;

       //    ftp=dh.selectFTP();
            email=dh.selectEmail();
        //    http=dh.selectUrl_Http();
            dh =null;

           String[] res = str2.split("\\,");
        //   ftpConn(res[0],res[1],res[2],strt,filename,email);

      //      ftpConn("ftp.voluntaty.esy.es", "u638105038", "1234567890",strt,filename,"superslon74@gmail.com");
       //     dh.InsertFTP("ftp.voluntaty.esy.es","u638105038","1234567890");

            new SendFileTwo().execute(res[0], res[1], res[2], strt, filename, email);

        //	Toast.makeText(context.getApplicationContext()," ????? "+ res[0]+" "+res[1]+" "+res[2]+" "+strt+" "+filename+" "+email, Toast.LENGTH_LONG).show();

            http=null;
            email=null;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();



        }
    }





    public class SendFileTwo extends AsyncTask<String, String, String> {

       @Override
        protected String doInBackground(String... params)
        {

        ftpConn(params[0],params[1],params[2],params[3],params[4],params[5]);
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