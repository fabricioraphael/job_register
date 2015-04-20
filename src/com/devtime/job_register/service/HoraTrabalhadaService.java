package com.devtime.job_register.service;

import java.util.Date;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.devtime.job_register.domain.Rede;
import com.devtime.job_register.enums.TabelaEnum;
import com.devtime.job_register.helper.DatabaseHelper;

public class HoraTrabalhadaService {

	private DatabaseHelper databaseHelper;
	
	public HoraTrabalhadaService(DatabaseHelper databaseHelper){
		this.databaseHelper = databaseHelper;
	}
	
	public void registrarInicioConexao(Rede redeAtual, Rede ultimaRede){
		this.salvaHoraInicio(redeAtual);
		
		if(ultimaRede != null){
			this.salvaHoraFim(ultimaRede);
		}
	}
	
	private void salvaHoraInicio(Rede redeConectada){
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("hora_inicio", new Date().getTime());
		values.put("rede_id", redeConectada.getId());
		
		Log.i("MainActivity", "Salvando Hora Inicio: " + values.get("hora_inicio") + ", para rede id: " + redeConectada.getId());
		
		db.insert(TabelaEnum.HORA_TRABALHADA.getNome(), null, values);
	}
	
	private void salvaHoraFim(Rede redeDesconectada){
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		
		String sql = "SELECT _id FROM " + TabelaEnum.HORA_TRABALHADA.getNome()  + " WHERE rede_id = ? and hora_fim IS NULL LIMIT 1";
		
		Cursor cursor = db.rawQuery(sql, new String[]{redeDesconectada.getId() + ""});
		
		cursor.moveToFirst();
		
		int horaId = cursor.getInt(0);
		
		ContentValues values = new ContentValues();
		values.put("hora_fim", new Date().getTime());
		
		Log.i("MainActivity", "Salvando Hora Fim: " + values.get("hora_fim") + ", para rede id: " + redeDesconectada.getId());
		
		db.update(TabelaEnum.HORA_TRABALHADA.getNome(), values, "_id=?", new String[]{horaId + ""});
	}
}
