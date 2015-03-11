package com.devtime.job_register.util;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class ApplicationJobRegister extends Application{

	
	public static String getNetworkSSID(Context context) {
		String networkSSID = "";
		
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivityManager != null) {
				NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

				if (networkInfo != null) {
					WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
					WifiInfo wifiInfo = wifiManager.getConnectionInfo();
					networkSSID = wifiInfo.getSSID();
					
					Log.i("MainActivity", "NetworkSSID: " + networkSSID);
					
					return networkSSID;
				}
			}
		} catch (Exception e) {
			Log.e("MainActivity", "[Exception] checkNetworkConnection" + e.toString());
			return null;
		}

		return null;
	}
}
