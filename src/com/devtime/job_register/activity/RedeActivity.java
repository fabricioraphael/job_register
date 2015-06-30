package com.devtime.job_register.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.devtime.job_register.R;
import com.devtime.job_register.adapter.ListaHoraAdapter;
import com.devtime.job_register.domain.Hora;
import com.devtime.job_register.enums.TabelaEnum;
import com.devtime.job_register.helper.DatabaseHelper;

public class RedeActivity extends ListActivity implements OnItemClickListener, OnClickListener {

	private DatabaseHelper helper;
	private String id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		id = getIntent().getStringExtra("rede_id");
		
		Log.i("MainActivity", "Exibindo tela com horas da rede " + id);
		
		helper = new DatabaseHelper(this);
		
		ListaHoraAdapter adapter = new ListaHoraAdapter(this, recuperaHorasByRede(id));
		setListAdapter(adapter);
	}
	
	private List<Hora> recuperaHorasByRede(String redeId) {
		SQLiteDatabase db = helper.getReadableDatabase();

		String sql = "SELECT rede_id, hora_inicio, hora_fim FROM " + TabelaEnum.HORA_TRABALHADA.getNome() + 
				" WHERE rede_id = ? ORDER BY _id DESC";
		
		Cursor cursor = db.rawQuery(sql, new String[]{redeId});
		cursor.moveToFirst();

		List<Hora> listaHoras = new ArrayList<Hora>();
		
		for(int i=0; i < cursor.getCount(); i++){
			Hora hora = new Hora();
			hora.setId(cursor.getInt(0));
			hora.setDataInicio(cursor.getString(1));
			hora.setDataFim(cursor.getString(2));
			
			listaHoras.add(hora);
			
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return listaHoras;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}
