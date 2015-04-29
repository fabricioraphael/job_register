package com.devtime.job_register.helper;

import com.devtime.job_register.enums.TabelaEnum;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "db_job_register";
	private static int VERSION = 4;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.initDatabase(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TabelaEnum.HORA_TRABALHADA.getNome());
		db.execSQL("DROP TABLE IF EXISTS " + TabelaEnum.REDE.getNome());
		
		this.initDatabase(db);
	}
	
	/**
	 * Metodo responsavel pela criacao do banco e suas tabelas
	 * @param db
	 */
	private void initDatabase(SQLiteDatabase db){
		String sql = "CREATE TABLE hora_trabalhada (_id INTEGER PRIMARY KEY,"
				+ "hora_inicio DATE NOT NULL, "
				+ "hora_inicio_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
				+ "hora_fim DATE NULL, "
				+ "hora_fim_time TIMESTAMP NULL, "
				+ "rede_id INTEGER NOT NULL, "
				+ "usuario_id INTEGER NULL); ";
		
		db.execSQL(sql);
		
		String sql2 = "CREATE TABLE rede (_id INTEGER PRIMARY KEY, "
				+ "mac_address TEXT, "
				+ "ip TEXT,"
				+ "ssid TEXT, "
				+ "data_primeira_conexao DATE, "
				+ "tipo_id INTEGER, "
				+ "tipo_desc TEXT, "
				+ "CONSTRAINT idx_unq_red UNIQUE (ssid, mac_address, tipo_id));";
		
		db.execSQL(sql2);
	}

}
