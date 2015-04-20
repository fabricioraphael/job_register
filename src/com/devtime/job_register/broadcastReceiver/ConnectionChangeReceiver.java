package com.devtime.job_register.broadcastReceiver;

import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.util.Log;
import android.widget.Toast;

import com.devtime.job_register.TimeControl;
import com.devtime.job_register.domain.Rede;
import com.devtime.job_register.helper.DatabaseHelper;
import com.devtime.job_register.service.HoraTrabalhadaService;
import com.devtime.job_register.service.RedeService;
import com.devtime.job_register.util.ApplicationJobRegister;

public class ConnectionChangeReceiver extends BroadcastReceiver {
	
	private static final Long MINUT_IN_MILESECONDS = 60000l;
	
	private Long lastCheck;
	private Rede lastNetwork;
	
	private DatabaseHelper database;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("MainActivity", "==========================================");
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();

		if (activeNetInfo == null) {
			return;
		}
		
		lastCheck = TimeControl.getLastCheck();
		lastNetwork = TimeControl.getLastNetwork();

		WifiInfo wifi = ApplicationJobRegister.getNetworkWifi(context);
		
		if(lastNetwork != null){
			Log.i("MainActivity", "Atual: " + wifi.getSSID() + ". Ultima: " + lastNetwork.getSsid());
		
			if(wifi.getMacAddress().equals(lastNetwork.getMacAddress()) && 
					wifi.getSSID().equals(lastNetwork.getSsid())){
				Log.i("MainActivity", "Mesma rede ja conectada foi detectada, segue a vida...");
				return;
			}
		}

		if(lastCheck != null){
			Long now = new Date().getTime();
			
			long diference = now - lastCheck; 
			
			if(diference < MINUT_IN_MILESECONDS){
				Log.i("MainActivity", "Diferença: " + diference + ". Menor que 1 minuto, segue a vida...");
				return;
			}else{
				Log.i("MainActivity", "Diferença: " + diference + ". Maior que 1 minuto. IDENTIFICA A NOVA REDE. ");
			}
		}else{
			Log.i("MainActivity", "PRIMEIRA CONEXAO IDENTIFICADA");
		}
		
		Rede rede = new Rede();
		rede.setIp(wifi.getIpAddress() + "");
		rede.setMacAddress(wifi.getMacAddress());
		rede.setSsid(wifi.getSSID());
		rede.setTipoId(activeNetInfo.getType());
		rede.setTipoDescricao(activeNetInfo.getTypeName());
		
		Log.i("MainActivity", ">> TYPE : " + activeNetInfo.getTypeName());
		Log.i("MainActivity", ">> MacAddress: " + wifi.getMacAddress());
		Log.i("MainActivity", ">> SSID: " + wifi.getSSID());
		Log.i("MainActivity", ">> IP: " + wifi.getIpAddress());
		Log.i("MainActivity", ">> NETWORD ID: " + wifi.getNetworkId());
		
		if(wifi != null){
			database = new DatabaseHelper(context);
			
			RedeService redeService = new RedeService(database);
			int redeId = (int) redeService.salvarRede(rede);
			rede.setId(redeId);
			
			HoraTrabalhadaService horaService = new HoraTrabalhadaService(database);
			horaService.registrarInicioConexao(rede, lastNetwork);
			
			TimeControl.updateLastNetwork(rede);
			Toast.makeText(context, wifi.getSSID() +  "\n" + activeNetInfo.getTypeName(), Toast.LENGTH_SHORT).show();
			
			database.close();
		}
		
		TimeControl.updateLastCheck();
	}
	
}