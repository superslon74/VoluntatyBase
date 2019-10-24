package com.voluntary.record.audio.phone;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import voluntaty.com.base.RecordService;

public class PhoneListener extends PhoneStateListener
{
    private Context context;

    public PhoneListener(Context c) {
      
        context = c;
    }

    public void onCallStateChanged (int state, String incomingNumber)
    {
        switch (state) {
        case TelephonyManager.CALL_STATE_IDLE:
          
            Boolean stopped = context.stopService(new Intent(context, RecordService.class));
          
            break;
        case TelephonyManager.CALL_STATE_RINGING:
            
            break;
        case TelephonyManager.CALL_STATE_OFFHOOK:
           
            Intent callIntent = new Intent(context, RecordService.class);
            ComponentName name = context.startService(callIntent);
            if (null == name) {
              
            } else {
               
            }
            break;
        }
    }
}
