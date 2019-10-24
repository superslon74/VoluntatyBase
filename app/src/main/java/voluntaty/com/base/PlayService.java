package voluntaty.com.base;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;

public class PlayService 
    extends Service
    implements MediaPlayer.OnCompletionListener, MediaPlayer.OnInfoListener, MediaPlayer.OnErrorListener
{
    private static String TAG = "CallRecorder";

    
    public static final String EXTRA_FILENAME = "filename";

    private MediaPlayer player = null;
    private boolean isPlaying = false;
    private String recording = null;

    public void onCreate()
    {
        super.onCreate();
        player = new MediaPlayer();
        player.setOnCompletionListener(this);
        player.setOnInfoListener(this);
        player.setOnErrorListener(this);
       
    }

    public void onStart(Intent intent, int startId) {
       if (isPlaying) return;

        Context c = getApplicationContext();
        recording = intent.getStringExtra(EXTRA_FILENAME);

        if (recording == null) {
         
            return;
        }

      
        try {
            player.reset();
            player.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
            player.setDataSource(recording);
            player.setLooping(false);
            player.prepare();
                    
            player.start();

            isPlaying = true;
       
            //updateNotification(true);
        } catch (IOException e) {

            Toast t = Toast.makeText(getApplicationContext(), "PlayService was unable to start playing recording: " + e, Toast.LENGTH_LONG);
            t.show();
            return;
        } catch (Exception e) {
            Toast t = Toast.makeText(getApplicationContext(), "CallRecorder was unable to start playing recording: " + e, Toast.LENGTH_LONG);
            t.show();

        }

        return;
    }

    public void onDestroy()
    {
        if (null != player) {
          
            isPlaying = false;
            player.release();
        }

        //updateNotification(false);
        super.onDestroy();
    }


    // methods to handle binding the service

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

  
     public void onCompletion(MediaPlayer mp)
    {
         isPlaying = false;
    }

     public boolean onInfo(MediaPlayer mp, int what, int extra)
    {
          return false;
    }

      public boolean onError(MediaPlayer mp, int what, int extra) 
    {
        isPlaying = false;
        mp.reset();
        return true;
    }
}
