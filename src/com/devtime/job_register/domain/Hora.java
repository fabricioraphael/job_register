package com.devtime.job_register.domain;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Hora {

	private Integer id;
	private String dataInicio;
	private String dataFim;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	
	public String getDataInicioFormatada(){
		if(dataInicio == null || dataInicio.equals("")){
			return "";
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return dateFormat.format(new Date(Long.parseLong(dataInicio)));
	}
	
	public String getDataFimFormatada(){
		if(dataFim == null || dataFim.equals("")){
			return "";
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return dateFormat.format(new Date(Long.parseLong(dataFim)));
	}
	
	
}
