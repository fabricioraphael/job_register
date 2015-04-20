package com.devtime.job_register.activity;

import com.devtime.job_register.R;
import com.devtime.job_register.R.id;
import com.devtime.job_register.R.layout;
import com.devtime.job_register.R.menu;
import com.devtime.job_register.helper.DatabaseHelper;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private Context context;
	SharedPreferences preferencias;
	DatabaseHelper database;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		
		database = new DatabaseHelper(context);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		
		if (id == R.id.action_settings) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		database.close();
		super.onDestroy();
	}
}
