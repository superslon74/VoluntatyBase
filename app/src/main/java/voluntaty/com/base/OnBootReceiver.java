package voluntaty.com.base;

/* e-mail: superslon74@gmail.com 
 * skype: superslon74
 * шаманский геннадий александрович
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OnBootReceiver extends BroadcastReceiver {
     @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent serviceLauncher = new Intent(context, MyService.class);
            context.startService(serviceLauncher);
       //     Log.v(this.getClass().getName(), "Service loaded while device boot.");
        }





    }
}  