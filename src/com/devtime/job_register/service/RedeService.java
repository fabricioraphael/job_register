package com.devtime.job_register.service;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.devtime.job_register.domain.Rede;
import com.devtime.job_register.enums.TabelaEnum;
import com.devtime.job_register.helper.DatabaseHelper;

public class RedeService {

	private DatabaseHelper databaseHelper;
	
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
		
		long redeId = this.getRedeId(rede.getSsid(), rede.getMacAddress(), rede.getTipoId());;
		
		Log.i("MainActivity", "redeId: " + redeId);
		
		if(redeId > 0){
			return redeId;
		} else{
			return db.insert(TabelaEnum.REDE.getNome(), null, values);
		}
		
	}
	
	public long getRedeId(String ssid, String macAddress, int tipoId){
		Log.i("MainActivity", "Recuperando rede já existente: " + ssid);
		
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		
		String sql = "SELECT _id FROM " + TabelaEnum.REDE.getNome() + 
				  " WHERE ssid = ? and "
				+ " mac_address = ? and "
				+ " tipo_id = ?" ;
		
		Cursor cursor = db.rawQuery(sql, new String[]{ssid, macAddress, tipoId + ""});
		
		cursor.moveToFirst();
		
		if(cursor.getCount() > 0){
			return cursor.getLong(0);
		}

		return 0;
	}
	
	public Rede getLastNetworkConnected(){
		String sql = "SELECT r._id, r.ssid, r.mac_address, r.tipo_id, h._id FROM " + TabelaEnum.HORA_TRABALHADA + " h " +
				" INNER JOIN " + TabelaEnum.REDE + " r " +
				" ON (r._id = h.rede_id) " + 
				" WHERE h.hora_fim IS NULL " + 
				" ORDER BY h._id DESC LIMIT 1";
		
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(sql, new String[]{});
		
		cursor.moveToFirst();
		
		if(cursor.getCount() > 0){
			Rede rede = new Rede();
			rede.setId(cursor.getInt(0));
			rede.setSsid(cursor.getString(1));
			rede.setMacAddress(cursor.getString(2));
			rede.setTipoId(cursor.getInt(3));

			Log.i("MainActivity", "Ultima rede reperada: " + rede.getId() + " - " + rede.getSsid());
			Log.i("MainActivity", "Hora trabalhada id: " + cursor.getInt(4));
			
			return rede;
		}
		
		return null;
	}
}
