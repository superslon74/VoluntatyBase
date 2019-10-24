package voluntaty.com.base;

/* e-mail: superslon74@gmail.com 
 * skype: superslon74
 * шаманский геннадий александровичь
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ServiceContactReseiver extends BroadcastReceiver{
	static public final String Action_A = "voluntaty.com.base.MY_STARTUP_SERVICE";
	static public final long SEC_60 = 60000;
	@Override
    public void onReceive(Context context, Intent intent) {
    /*
	if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {	
	//для Service
	Intent serviceIntent = new Intent(context,ServiceContact.class);
	context.startService(serviceIntent);
		  }
	*/

		Intent iStartService = new Intent(context, ServiceContact.class);
		context.startService(iStartService);

		// запускаем активацию приемника по сигналу
		// если устройство спит, при этом сами его не будем
		// для теста частоту вызова берем в 1.5 минуты
		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent i = new Intent(context, ServiceContactReseiver.class);

		PendingIntent pending = PendingIntent.getBroadcast(context, 0, i,
				PendingIntent.FLAG_CANCEL_CURRENT);
		long t = System.currentTimeMillis() + SEC_60*1;
		long time_update = SEC_60*1;
		alarmManager.setRepeating(AlarmManager.RTC, t, time_update, pending);

	}

}