package com.yyh.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yyh.fork.NativeRuntime;
import com.yyh.utils.FileUtils;

import a.daemonprocess.R;

@SuppressWarnings("unchecked")
public class MainActivity extends Activity {

	Button btnstart, btnend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences sp=this.getSharedPreferences("Preferences",MODE_PRIVATE);
		setContentView(R.layout.activity_main);
		initService();
	}

	private void initService() {
		btnstart = (Button) findViewById(R.id.btn_start);
		btnend = (Button) findViewById(R.id.btn_end);

		Toast.makeText(this, NativeRuntime.getInstance().stringFromJNI(), Toast.LENGTH_LONG).show();
		String executable = "libhelper.so";
		String aliasfile = "helper";
		String parafind = "/data/data/" + getPackageName() + "/" + aliasfile;
		String retx = "false";
		NativeRuntime.getInstance().RunExecutable(getPackageName(), executable, aliasfile, getPackageName() + "/com.yyh.service.HostMonitor");

		btnstart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.e("点击了启动按钮","dsfddf");
				new Thread(new Runnable() {

					@Override
					public void run() {
						Log.e("开始通过底层启动","service");
						try {
							Log.e("服务类的路径是 ",getPackageName() + "/com.yyh.service.HostMonitor");
							Thread.sleep(3 * 1000);
							NativeRuntime.getInstance().startService(getPackageName() + "/com.yyh.service.HostMonitor", FileUtils.createRootPath());
						} catch (Exception e) {
							Log.e("启动子进程","异常");
							e.printStackTrace();
						}
					}
				}).start();
			}
		});

		btnend.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					NativeRuntime.getInstance().stopService();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
	}
}
