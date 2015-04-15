package com.devtime.job_register.util;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class ApplicationJobRegister extends Application{
	
	public static WifiInfo getNetworkWifi(Context context) {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			
			if (connectivityManager != null) {
				NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

				if (networkInfo != null) {
					WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
					WifiInfo wifiInfo = wifiManager.getConnectionInfo();
					
					return wifiInfo;
				}
			}
		} catch (Exception e) {
			Log.e("ApplicationJobRegister", "[Exception] checkNetworkConnection" + e.toString());
			return null;
		}

		return null;
	}
}
