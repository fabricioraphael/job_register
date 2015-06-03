package com.devtime.job_register.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.devtime.job_register.R;
import com.devtime.job_register.domain.Rede;

public class ListaRedeAdapter extends BaseAdapter{

	private Context context;
	private List<Rede> lista;

	public ListaRedeAdapter(Context context, List<Rede> listaRedes) {
		this.context = context;
		this.lista = listaRedes;
		
		if (lista == null){
			lista = new ArrayList<Rede>();
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
		
		final Rede item = (Rede) getItem(position);

		Log.i("MainActivity", "id do item: " + item.getId());
		
		if (convertView == null) {
			Log.i("MainActivity", "convertView null");
			holder = new ViewHolder();
			
			convertView = inflater.inflate(R.layout.lista_rede, null);
			
			holder.redeId = (TextView) convertView.findViewById(R.id.redeId);
			holder.nome = (TextView) convertView.findViewById(R.id.ssid);
			holder.ip = (TextView) convertView.findViewById(R.id.ip);
			holder.tipo = (TextView) convertView.findViewById(R.id.tipo_desc);

			convertView.setTag(holder);
			
			
			
		} else {
			Log.i("MainActivity", "convertView existente");
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.redeId.setText(item.getId() + "");
		holder.nome.setText(item.getSsid() + "");
		holder.ip.setText(item.getIp() + "");
		holder.tipo.setText(item.getTipoDescricao() + "");
		
		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				CharSequence itens[] = {
//						getString(R.string.ver_horas),
//						getString(R.string.remover_rede)
//				};
				
				final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
							case DialogInterface.BUTTON_POSITIVE:
								Log.i("MainActivity", "clicou sim");
								break;
	
							case DialogInterface.BUTTON_NEGATIVE:
								Log.i("MainActivity", "clicou nao");
								
								break;
							}
					}
				};
				
				builder.setTitle(R.string.opcoes);
				
				builder.setMessage("Rede clicada: " + item.getSsid());
				
				builder.setPositiveButton("Sim", dialogClickListener);
				builder.setNegativeButton("Não", dialogClickListener);
				builder.show();
			}
		});
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView redeId;// tab_frag_view_infos_statemente_output_plate
		TextView nome; // tab_frag_view_infos_statemente_input_item_value
		TextView ip; // tab_frag_view_infos_statemente_input_item_date
		TextView tipo; // tab_frag_view_infos_statemente_input_item_time
	}

}
