package com.devtime.job_register.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import com.devtime.job_register.R;
import com.devtime.job_register.enums.TabelaEnum;
import com.devtime.job_register.helper.DatabaseHelper;

public class RedeListActivity extends ListActivity {

	private DatabaseHelper databaseHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		databaseHelper = new DatabaseHelper(this);
		
		String[] de = {"id", "ssid", "ip", "tipo_desc"};
		int[] para = { R.id.id, R.id.ssid, R.id.ip, R.id.tipo_desc };
		
		SimpleAdapter adapter = new SimpleAdapter(this, listarRedes(), R.layout.lista_rede, de, para);
		setListAdapter(adapter);
	}
	
	private List<Map<String, Object>>  listarRedes(){
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		
		String sql = "SELECT _id, ssid, mac_address, ip, tipo_id, tipo_desc FROM " + TabelaEnum.REDE.getNome();
		
		Cursor cursor = db.rawQuery(sql, null);
		
		cursor.moveToFirst();
		
		List<Map<String, Object>> redes = new ArrayList<Map<String, Object>>();
		
		for(int i=0; i < cursor.getCount(); i++){
			Map<String, Object> item = new HashMap<String, Object>();
			
			item.put("id", cursor.getInt(0));
			item.put("ssid", cursor.getString(1));
			item.put("mac_address", cursor.getString(2));
			item.put("ip", cursor.getString(3));
			item.put("tipo_id", cursor.getInt(4));
			item.put("tipo_desc", cursor.getString(5));
			
			redes.add(item);
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return redes;
	}
}
