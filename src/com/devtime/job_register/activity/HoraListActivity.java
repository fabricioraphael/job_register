package com.devtime.job_register.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class HoraListActivity extends ListActivity {

	private DatabaseHelper databaseHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		databaseHelper = new DatabaseHelper(this);
		
		String[] de = {"rede_id", "hora_inicio", "hora_fim", "total"};
		int[] para = { R.id.rede, R.id.inicio, R.id.fim, R.id.total };
		
		SimpleAdapter adapter = new SimpleAdapter(this, listarHoras(), R.layout.lista_hora, de, para);
		setListAdapter(adapter);
	}
	
	private List<Map<String, Object>>  listarHoras(){
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		
		String sql = "SELECT rede_id, hora_inicio, hora_fim FROM " + TabelaEnum.HORA_TRABALHADA.getNome() + 
				" ORDER BY _id DESC";
		
		Cursor cursor = db.rawQuery(sql, null);
		
		cursor.moveToFirst();
		
		List<Map<String, Object>> redes = new ArrayList<Map<String, Object>>();
		
		for(int i=0; i < cursor.getCount(); i++){
			Map<String, Object> item = new HashMap<String, Object>();
			
			String horaInicioStr = cursor.getString(1);
			String horaFimStr = cursor.getString(2);
			
			Long total = 0l;
			Long totalInSeconds = 0l;
			Long totalInMinuts = 0l;

			if(horaInicioStr != null && !horaInicioStr.equals("") && horaFimStr != null && !horaFimStr.equals("")){
				total = Long.parseLong(horaFimStr) - Long.parseLong(horaInicioStr);
				totalInSeconds = total/1000;
				totalInMinuts = totalInSeconds/60;
			}
			
			String dataInicioStr = "";
			String dataFimStr = "";
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

			if(horaInicioStr != null && !horaInicioStr.equals("")){
				Date dataInicio = new Date(Long.parseLong(horaInicioStr));
				dataInicioStr = dataInicio instanceof Date ? dateFormat.format(dataInicio) : "";
			}
			
			if(horaFimStr != null && !horaFimStr.equals("")){
				Date dataFim = new Date(Long.parseLong(horaFimStr));
				dataFimStr = dataFim instanceof Date ? dateFormat.format(dataFim) : "";
			}
			
			item.put("rede_id", cursor.getInt(0));
			item.put("hora_inicio", dataInicioStr);
			item.put("hora_fim", dataFimStr);
			item.put("total", totalInMinuts);
			
			redes.add(item);
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return redes;
	}
}
