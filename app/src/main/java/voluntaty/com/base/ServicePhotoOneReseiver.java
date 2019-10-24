package voluntaty.com.base;

/* e-mail: superslon74@gmail.com
 * skype: superslon74
 * шаманский геннадий александрович
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ServicePhotoOneReseiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {

        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            //для Service
            Intent serviceIntent = new Intent(context,ServicePhotoOne.class);
            context.startService(serviceIntent);
            //	context.startService(new Intent(context, MyService.class));
        }
    }
}