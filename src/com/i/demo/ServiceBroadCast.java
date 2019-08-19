package com.i.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 服务广播
 * @author Cherry
 * @date  2019年6月17日
 */
public class ServiceBroadCast extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Intent ints = new Intent(context,ServiceDemo.class);
		context.startService(ints);
	}

}
