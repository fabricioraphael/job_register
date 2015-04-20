package com.devtime.job_register.service;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.devtime.job_register.domain.Rede;
import com.devtime.job_register.enums.TabelaEnum;
import com.devtime.job_register.helper.DatabaseHelper;

public class RedeService {

	DatabaseHelper databaseHelper;
	
	public RedeService(DatabaseHelper databaseHelper){
		this.databaseHelper = databaseHelper;
	}
	
	public long salvarRede(Rede rede){
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("mac_address", rede.getMacAddress());
		values.put("ip", rede.getIp());
		values.put("ssid", rede.getSsid());
		values.put("tipo_id", rede.getTipoId());
		values.put("tipo_desc", rede.getTipoDescricao());
		
		return db.insert(TabelaEnum.REDE.getNome(), null, values);
	}
}
