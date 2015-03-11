package com.devtime.job_register.broadcastReceiver;

import com.devtime.job_register.util.ApplicationJobRegister;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class ConnectionChangeReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		
//		NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		
		if (activeNetInfo != null) {
			
			Log.i("MainActivity", "Active Network Type : " + activeNetInfo.getTypeName());
			
			String ssid = ApplicationJobRegister.getNetworkSSID(context);
			
			if(ssid != null)
				Toast.makeText(context, "Type: " + activeNetInfo.getTypeName()  +  " \nSSID: " + ssid, Toast.LENGTH_SHORT).show();	
		}

//		if (mobNetInfo != null) {
//			Toast.makeText(context, "Mobile Network Type : " + mobNetInfo.getTypeName(), Toast.LENGTH_SHORT).show();
//			Log.i("MainActivity", "Mobile Network Type : " + mobNetInfo.getTypeName());
//		}
	}
}