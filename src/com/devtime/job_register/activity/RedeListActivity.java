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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.devtime.job_register.R;
import com.devtime.job_register.adapter.ListaRedeAdapter;
import com.devtime.job_register.domain.Rede;
import com.devtime.job_register.enums.TabelaEnum;
import com.devtime.job_register.helper.DatabaseHelper;

public class RedeListActivity extends ListActivity implements OnItemClickListener, OnClickListener {

	private DatabaseHelper databaseHelper;
	private AlertDialog alertDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		databaseHelper = new DatabaseHelper(this);
		
		ListaRedeAdapter adapter = new ListaRedeAdapter(this, listarRedes());
		setListAdapter(adapter);
		
		//SimpleAdapter adapter = new SimpleAdapter(this, listarRedes(), R.layout.lista_rede, de, para);
		
		Log.i("MainActivity", "pre criar");
		
		this.alertDialog = criaAlertDialog();
		
		Log.i("MainActivity", "pos criar");
	}
	
	private List<Rede> listarRedes(){
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		
		String sql = "SELECT _id, ssid, mac_address, ip, tipo_id, tipo_desc FROM " + TabelaEnum.REDE.getNome();
		
		Cursor cursor = db.rawQuery(sql, null);
		
		cursor.moveToFirst();
		
		List<Rede> redes = new ArrayList<Rede>();
		
		for(int i=0; i < cursor.getCount(); i++){
			Rede rede = new Rede();
			rede.setId(cursor.getInt(0));
			rede.setSsid(cursor.getString(1));
			rede.setMacAddress(cursor.getString(2));
			rede.setIp(cursor.getString(3));
			rede.setTipoId(cursor.getInt(4));
			rede.setTipoDescricao(cursor.getString(5));
			
			redes.add(rede);
			
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
