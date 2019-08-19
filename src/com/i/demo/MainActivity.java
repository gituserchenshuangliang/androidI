package com.i.demo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
/**
 * 安卓线程
 * 在主线程当中创建一个 Handler 对象，并重写
 * handleMessage()方法。然后当子线程中需要进行 UI 操作时，就创建一个 Message 对象，并
 * 通过 Handler 将这条消息发送出去。之后这条消息会被添加到 MessageQueue 的队列中等待
 * 被处理，而 Looper 则会一直尝试从 MessageQueue 中取出待处理消息，最后分发回 Handler
 * 的 handleMessage()方法中。由于 Handler是在主线程中创建的，所以此时 handleMessage()方
 * 法中的代码也会在主线程中运行，于是我们在这里就可以安心地进行 UI 操作了。
 * @author Cherry
 * @date  2019年6月17日
 */
@SuppressLint("HandlerLeak")
public class MainActivity extends BaseActivity {
	private static final int UPDATE_TEXT = 1;
	private TextView tv;
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_TEXT:
				tv.setText("Cherry Chen");
				break;
			default:
				break;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.createListenButton(R.id.A);
		this.createListenButton(R.id.B);
		this.createListenButton(R.id.C);
		tv = (TextView) findViewById(R.id.T);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.A:
			//Toast.makeText(this, "Clicked",Toast.LENGTH_LONG).show();
			new Thread(new Runnable() {
				@Override
				public void run() {
					Message msg = new Message();
					msg.what = UPDATE_TEXT;
					handler.sendMessage(msg);
				}
			}).start();
			break;
		case R.id.B:
			Intent start = new Intent(this,ServiceDemo.class);
			startService(start);
			break;
		case R.id.C:
			Intent stop = new Intent(this,ServiceDemo.class);
			stopService(stop);
			break;
		default:
			break;
		}
	}
	
	/**
	 * AsyncTask异步任务,参数介绍
 		1. Params 在执行 AsyncTask 时需要传入的参数，可用于在后台任务中使用。
		2. Progress 后台任务执行时，如果需要在界面上显示当前的进度，则使用这里指定的泛型作为进度单位。
		3. Result 当任务执行完毕后，如果需要对结果进行返回，则使用这里指定的泛型作为返回值类型。
	 * @author Cherry
	 * @date  2019年6月17日
	 */
	class DownloadTask extends AsyncTask<Void, Integer, Boolean>{
		/*
		 * 这个方法会在后台任务开始执行之前调用，用于进行一些界面上的初始化操作，
		 * 比如显示一个进度条对话框等。
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		/*
		 * 这个方法中的所有代码都会在子线程中运行，我们应该在这里去处理所有的耗时任务。
		 */
		@Override
		protected Boolean doInBackground(Void... params) {
			return null;
		}
		
		/*
		 * 当在后台任务中调用了 publishProgress(Progress...)方法后，这个方法就会很快被调
		 * 用，方法中携带的参数就是在后台任务中传递过来的。
		 */
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}
		
		/*
		 * 当后台任务执行完毕并通过 return 语句进行返回时，这个方法就很快会被调用。
		 */
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
		}
	}
}
