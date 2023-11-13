package br.fiap.challenge.porto.api;

public class VistoriaDTO {
	private boolean renovacao;
	private String cpf;
	private String numeroDeSerie;
	private String coleta1;
	private String coleta2;
	private String coleta3;
	private String coleta4;
	private String coleta5;
	
	public boolean isRenovacao() {
		return renovacao;
	}
	public String getCpf() {
		return cpf;
	}
	public String getColeta1() {
		return coleta1;
	}
	public String getColeta2() {
		return coleta2;
	}
	public String getColeta3() {
		return coleta3;
	}
	public String getColeta4() {
		return coleta4;
	}
	public String getColeta5() {
		return coleta5;
	}
	public void setRenovacao(boolean renovacao) {
		this.renovacao = renovacao;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setColeta1(String coleta1) {
		this.coleta1 = coleta1;
	}
	public void setColeta2(String coleta2) {
		this.coleta2 = coleta2;
	}
	public void setColeta3(String coleta3) {
		this.coleta3 = coleta3;
	}
	public void setColeta4(String coleta4) {
		this.coleta4 = coleta4;
	}
	public void setColeta5(String coleta5) {
		this.coleta5 = coleta5;
	}
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}
	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	
	
	
}
