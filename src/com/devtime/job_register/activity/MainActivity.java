package com.devtime.job_register.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.devtime.job_register.R;
import com.devtime.job_register.helper.DatabaseHelper;

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

	public void selecionarOpcao(View view){
	    switch (view.getId()) {
	     	case R.id.ver_redes_btn:
	     		startActivity(new Intent(this, RedeListActivity.class));
	     		break;
	     	case R.id.ver_horas_btn:
	     		startActivity(new Intent(this, HoraListActivity.class));
	     		break;
	     	default:
	     		TextView textView = (TextView) view;
	    		String opcao = "Opção: "+ textView.getText().toString();
	    		Toast.makeText(this, opcao, Toast.LENGTH_LONG).show();
	    }
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
