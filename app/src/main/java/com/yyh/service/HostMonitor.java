package com.yyh.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import javax.security.auth.Destroyable;

public class HostMonitor extends Service {

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e("daemon_java", "HostMonitor: onCreate! I can not be Killed!");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int t=0;
		Log.e("daemon_java",t + "HostMonitor: onStartCommand! I can not be Killed!");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
