package com.devtime.job_register;

import java.util.Date;

import android.net.wifi.WifiInfo;
import android.util.Log;

import com.devtime.job_register.domain.Rede;
import com.devtime.job_register.service.RedeService;

public class TimeControl {

	private static Long lastCheck = null;
	
	public static Long getLastCheck(){
		return lastCheck;
	}
	
	public static void updateLastCheck(){
		Long now = new Date().getTime();
		lastCheck = now;
		
		Log.i("MainActivity", "LastCheck updated: " + lastCheck);
	}
	
}
