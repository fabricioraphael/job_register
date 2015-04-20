package com.devtime.job_register.enums;

public enum TabelaEnum {

	REDE("rede"),
	HORA_TRABALHADA("hora_trabalhada");
	
	String nome;
	
	private TabelaEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
