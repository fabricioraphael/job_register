package com.devtime.job_register;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this;
		
//		IntentFilter intentFilter = new IntentFilter();
//		// intentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
//		intentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
//		registerReceiver(broadcastReceiver, intentFilter);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private boolean isConnectedViaWifi() {
		// ConnectivityManager connectivityManager = (ConnectivityManager)
		// mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		// NetworkInfo mWifi =
		// connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		// return mWifi.isConnected();
		return false;
	}
	
	private String getNetworkSSID() {
		String networkStatus = "disconnected";
		int netType = 0;
		
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivityManager != null) {
				NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

				if (networkInfo != null) {
					netType = networkInfo.getType();
					
					
					WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
					WifiInfo wifiInfo = wifiManager.getConnectionInfo();
					String ssid = wifiInfo.getSSID();

					Log.i("MainActivity", "ssid: " + ssid);
					
					Log.i("MainActivity", "connetion is available netType: " + netType);
				} else {
					Log.i("MainActivity", "connetion is  not available");
					return networkStatus;
				}

				// if(networkInfo.isAvailable()){ // Old one
				if (networkInfo.isAvailable() && networkInfo.isConnected()) { // New
																				// change
																				// added
																				// here
					if (netType == ConnectivityManager.TYPE_WIFI) {
					} else if (netType == ConnectivityManager.TYPE_MOBILE) {
					}
				}
			}
		} catch (Exception e) {
			Log.e("MainActivity", "[Exception] checkNetworkConnection" + e.toString());
			return networkStatus;
		}

		return networkStatus;
	}

	private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
//			final String action = intent.getAction();
//
//			if (action.equals(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION)) {
//				Log.i("MainActivity", "SUPPLICANT_STATE_CHANGED_ACTION...");
//				
//				String status = getNetworkSSID();
//				
////				NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
////
////				if (info != null) {
////					if (info.isConnected()) {
////						// Do your work.
////
////						// e.g. To check the Network Name or other info:
////						WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
////						WifiInfo wifiInfo = wifiManager.getConnectionInfo();
////						String ssid = wifiInfo.getSSID();
////
////						Log.i("MainActivity", "ssid: " + ssid);
////					}
////				}
//			}

//			if (action.equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)) {
//
//				Log.i("MainActivity", "isMindFucker: " + isMindFucker());
//
//				NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
//
//				if (info != null) {
//					if (info.isConnected()) {
//						// Do your work.
//
//						// e.g. To check the Network Name or other info:
//						WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//						WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//						String ssid = wifiInfo.getSSID();
//
//						Log.i("MainActivity", "ssid: " + ssid);
//					}
//				}
//
//				if (intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false)) {
//					// wifi is enabled
//
//					Log.i("MainActivity", "wifi is enabled: ");
//				} else {
//					// wifi is disabled
//
//					Log.i("MainActivity", "wifi is disabled: ");
//				}
//			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
