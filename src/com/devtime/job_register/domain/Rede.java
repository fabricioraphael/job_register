package com.devtime.job_register.domain;

public class Rede {

	private Integer id;
	private String macAddress;
	private String ip;
	private String ssid;
	private Integer tipoId;
	private String tipoDescricao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public Integer getTipoId() {
		return tipoId;
	}
	public void setTipoId(Integer tipoId) {
		this.tipoId = tipoId;
	}
	public String getTipoDescricao() {
		return tipoDescricao;
	}
	public void setTipoDescricao(String tipoDescricao) {
		this.tipoDescricao = tipoDescricao;
	}
	
	
}
