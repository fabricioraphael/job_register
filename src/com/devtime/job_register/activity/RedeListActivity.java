package com.devtime.job_register.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;

import com.devtime.job_register.R;
import com.devtime.job_register.enums.TabelaEnum;
import com.devtime.job_register.helper.DatabaseHelper;

public class RedeListActivity extends ListActivity implements OnItemClickListener, OnClickListener {

	private DatabaseHelper databaseHelper;
	private AlertDialog alertDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		databaseHelper = new DatabaseHelper(this);
		
		String[] de = {"id", "ssid", "ip", "tipo_desc"};
		int[] para = { R.id.id, R.id.ssid, R.id.ip, R.id.tipo_desc };
		
		SimpleAdapter adapter = new SimpleAdapter(this, listarRedes(), R.layout.lista_rede, de, para);
		setListAdapter(adapter);
		
		Log.i("MainActivity", "pre criar");
		
		this.alertDialog = criaAlertDialog();
		
		Log.i("MainActivity", "pos criar");
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
	
	
	private AlertDialog criaAlertDialog(){
		Log.i("MainActivity", "Entrou no criar alert dialog");
		
		CharSequence itens[] = {
				getString(R.string.ver_horas),
				getString(R.string.remover_rede)
		};
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.opcoes);
		builder.setItems(itens, this);
		
		return builder.create();
	}

	@Override
	public void onClick(DialogInterface dialog, int item) {
		switch (item) {
			case 0:
				Toast.makeText(this, "Ver Horas selecionado", Toast.LENGTH_LONG);
				break;
	
			case 1:
				Toast.makeText(this, "Remover selecionado", Toast.LENGTH_LONG);
				break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
		Log.i("MainActivity", "position: " + position);
		alertDialog.show();
	}

}
