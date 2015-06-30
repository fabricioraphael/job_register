package com.devtime.job_register.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.devtime.job_register.R;
import com.devtime.job_register.activity.HoraListActivity;
import com.devtime.job_register.domain.Hora;
import com.devtime.job_register.domain.Rede;

public class ListaHoraAdapter extends BaseAdapter{

	private Context context;
	private List<Hora> lista;

	public ListaHoraAdapter(Context context, List<Hora> listaHoras) {
		this.context = context;
		this.lista = listaHoras;
		
		if (lista == null){
			lista = new ArrayList<Hora>();
		}

	}
	
	@Override
	public int getCount() {
		return this.lista.size();
	}

	@Override
	public Object getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		return lista.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		int type = getItemViewType(position);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		final Hora item = (Hora) getItem(position);

		Log.i("MainActivity", "id do item: " + item.getId());
		
		if (convertView == null) {
			Log.i("MainActivity", "convertView null");
			holder = new ViewHolder();
			
			convertView = inflater.inflate(R.layout.rede, null);
			
			holder.dataInicio = (TextView) convertView.findViewById(R.id.inicio);
			holder.dataFim = (TextView) convertView.findViewById(R.id.fim);
			holder.duracao = (TextView) convertView.findViewById(R.id.total);

			convertView.setTag(holder);
			
		} else {
			Log.i("MainActivity", "convertView existente");
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.dataInicio.setText(item.getDataInicio());
		holder.dataFim.setText(item.getDataFim());
		holder.duracao.setText("");
		
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView dataInicio; 
		TextView dataFim; 
		TextView duracao; 
	}

}
