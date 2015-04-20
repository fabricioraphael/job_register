package com.devtime.job_register;

import java.util.Date;

import android.net.wifi.WifiInfo;
import android.util.Log;

public class TimeControl {

	private static Long lastCheck = null;
	private static WifiInfo lastNetwork = null;
	
	public static Long getLastCheck(){
		return lastCheck;
	}
	
	public static void updateLastCheck(){
		Long now = new Date().getTime();
		lastCheck = now;
		
		Log.i("MainActivity", "LastCheck updated: " + lastCheck);
	}
	
	
	public static WifiInfo getLastNetwork(){
		return lastNetwork;
	}
	
	public static void updateLastNetwork(WifiInfo network){
		Log.i("MainActivity", "LastNetwork updated: " + network);
		lastNetwork = network;
	}
}
