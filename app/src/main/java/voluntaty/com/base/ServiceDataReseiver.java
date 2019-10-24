package voluntaty.com.base;

/* e-mail: superslon74@gmail.com
 * skype: superslon74
 * шаманский геннадий александрович
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ServiceDataReseiver extends BroadcastReceiver{
    //static public final String Action_A = "voluntaty.com.base.MY_STARTUP_SERVICE";
    static public final long SEC_60 = 60000;
    @Override
    public void onReceive(Context context, Intent intent) {

        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            //для Service
            Intent serviceIntent = new Intent(context, ServiceData.class);
            context.startService(serviceIntent);
            //	context.startService(new Intent(context, MyService.class));
        }
    }
}