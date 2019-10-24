package voluntaty.com.base;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import java.security.KeyPairGenerator;
//import java.security.KeyPair;
//import java.security.Key;


public class RecordService 
    extends Service
    implements MediaRecorder.OnInfoListener, MediaRecorder.OnErrorListener
{
    private static final String TAG = "CallRecorder";

    public static final String DEFAULT_STORAGE_LOCATION = "/sdcard/callrecorder";
    private static final int RECORDING_NOTIFICATION_ID = 1;

    private MediaRecorder recorder = null;
    private boolean isRecording = false;
    private File recording = null;;

 
    private File makeOutputFile (SharedPreferences prefs)
    {
        File dir = new File(DEFAULT_STORAGE_LOCATION);

        if (!dir.exists()) {
            try {
                dir.mkdirs();
            } catch (Exception e) {
                 return null;
            }
        } else {
            if (!dir.canWrite()) {
                return null;
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SS");
        String prefix = sdf.format(new Date());

        int audiosource =1;
        prefix += "-channel" + audiosource + "-";

        String suffix = "";
        int audioformat = 1;
        switch (audioformat) {
        case MediaRecorder.OutputFormat.THREE_GPP:
            suffix = ".3gpp";
            break;
        case MediaRecorder.OutputFormat.MPEG_4:
            suffix = ".mpg";
            break;
        case MediaRecorder.OutputFormat.RAW_AMR:
            suffix = ".amr";
            break;
        }

        try {
            return File.createTempFile(prefix, suffix, dir);
        } catch (IOException e) {
            return null;
        }
    }

    public void onCreate()
    {
        super.onCreate();
        recorder = new MediaRecorder();
    }

    public void onStart(Intent intent, int startId) {
        if (isRecording) return;

        Context c = getApplicationContext();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);

        Boolean shouldRecord =true;
        if (!shouldRecord) {
       
            return;
        }

        int audiosource =1; 
        int audioformat =1; 

        recording = makeOutputFile(prefs);
        if (recording == null) {
            recorder = null;
            return; //return 0;
        }

        try {
           
            recorder.reset();
            recorder.setAudioSource(audiosource);
            recorder.setOutputFormat(audioformat);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            recorder.setOutputFile(recording.getAbsolutePath());
            recorder.setOnInfoListener(this);
            recorder.setOnErrorListener(this);
            
            try {
                recorder.prepare();
            } catch (IOException e) {
                recorder = null;
                return;
                }

            recorder.start();
            isRecording = true;
            updateNotification(true);
        } catch (Exception e) {
            recorder = null;
        }

        return; 
    }

    public void onDestroy()
    {
        super.onDestroy();

        if (null != recorder) {
            isRecording = false;
            recorder.release();
         }

        updateNotification(false);
    }


     public IBinder onBind(Intent intent)
    {
        return null;
    }

    public boolean onUnbind(Intent intent)
    {
        return false;
    }

    public void onRebind(Intent intent)
    {
    }


    private void updateNotification(Boolean status)
    {
        Context c = getApplicationContext();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);

        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);

        if (status) {
        //    int icon = R.drawable.rec;
   /*         CharSequence tickerText = "Recording call from channel " + 1;
            long when = System.currentTimeMillis();
            
   //         Notification notification = new Notification(icon, tickerText, when);
            
            Context context = getApplicationContext();
            CharSequence contentTitle = "CallRecorder Status";
            CharSequence contentText = "Recording call from channel...";
            Intent notificationIntent = new Intent(this, RecordService.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
            
            notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
            mNotificationManager.notify(RECORDING_NOTIFICATION_ID, notification);
     */   } else {
            mNotificationManager.cancel(RECORDING_NOTIFICATION_ID);
        }
    }

     public void onInfo(MediaRecorder mr, int what, int extra)
    {
            isRecording = false;
    }

    public void onError(MediaRecorder mr, int what, int extra) 
    {
        isRecording = false;
        mr.release();
    }
}
