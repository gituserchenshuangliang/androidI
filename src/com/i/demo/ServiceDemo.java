package com.i.demo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
/**
 * 安卓服务
 * @author Cherry
 * @date  2019年6月17日
 */
public class ServiceDemo extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		Log.d("TAG","onCreate");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("TAG","onStartCommand");
		new Thread(new Runnable() {
			@Override
			public void run() {
				Log.d("TAG","run");
			}
		}).start();
		long s =  SystemClock.elapsedRealtime() + 5*1000;
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		Intent i = new Intent(this,ServiceBroadCast.class);
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
		am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, s, pi);
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		Log.d("TAG","onDestroy");
	}
}
