package br.com.portoseguro.dominio;

import java.util.Date;

public class Vistoria {
	private int idVistoria;
	private Date dataVistoria;
	private boolean renovacao;
	private Cliente cliente;
	private Bicicleta bicicleta;
	private String status;
	private String coleta1;
	private String coleta2;
	private String coleta3;
	private String coleta4;
	private String coleta5;
	
	public Vistoria(boolean renovacao, Cliente cliente, Bicicleta bicicleta, String coleta1,
			String coleta2, String coleta3, String coleta4, String coleta5, int idVistoria) {
		
	
		if (coleta1 == null ) {
			throw new RuntimeException("É obrigatório enviar a foto do número de serie");
		}
		
		if (coleta2 == null ) {
			throw new RuntimeException("É obrigatório enviar a foto");
		}
		
		if (coleta3== null) {
			throw new RuntimeException("É obrigatório enviar a foto");
		}
		
		if (coleta4 == null) {
			throw new RuntimeException("É obrigatório enviar a foto");
		}
		
		if (coleta5 == null) {
			throw new RuntimeException("É obrigatório enviar a foto");
	
		}
		
		this.renovacao = renovacao;
		this.cliente = cliente;
		this.bicicleta = bicicleta;
		this.status = "Em analise";
		this.coleta1 = coleta1;
		this.coleta2 = coleta2;
		this.coleta3 = coleta3;
		this.coleta4 = coleta4;
		this.coleta5 = coleta5;
		this.dataVistoria = new Date();
		this.idVistoria = idVistoria;
	}

	public int getIdVistoria() {
		return idVistoria;
	}

	public Cliente getCliente() {
		return this.cliente;
	}
	
	public Bicicleta getBicicleta() {
		return this.bicicleta;
	}

	public Date getDataVistoria() {
		return dataVistoria;
	}

	public void setDataVistoria(Date dataVistoria) {
		this.dataVistoria = dataVistoria;
	}

	public boolean getRenovacao() {
		return renovacao;
	}

	public void setRenovacao(boolean renovacao) {
		this.renovacao = renovacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getColeta1() {
		return coleta1;
	}

	public void setColeta1(String coleta1) {
		this.coleta1 = coleta1;
	}

	public String getColeta2() {
		return coleta2;
	}

	public void setColeta2(String coleta2) {
		this.coleta2 = coleta2;
	}

	public String getColeta3() {
		return coleta3;
	}

	public void setColeta3(String coleta3) {
		this.coleta3 = coleta3;
	}

	public String getColeta4() {
		return coleta4;
	}

	public void setColeta4(String coleta4) {
		this.coleta4 = coleta4;
	}

	public String getColeta5() {
		return coleta5;
	}

	public void setColeta5(String coleta5) {
		this.coleta5 = coleta5;
	}

	public void setIdVistoria(int idVistoria) {
		this.idVistoria = idVistoria;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setBicicleta(Bicicleta bicicleta) {
		this.bicicleta = bicicleta;
	}
	
	@Override
	public String toString() {
		return "Bicicleta [Id Vistoria=" + this.getIdVistoria() +  "CPF=" + this.cliente.getCpf() + ", Data da Vistoria=" + this.getDataVistoria() + ", Numero de Serie=" + this.bicicleta.getNumeroDeSerie() + ", Coleta1=" + this.getColeta1() + ", Coleta2=" + this.getColeta2() + ", Coleta3=" + this.getColeta3() + ", Coleta4=" + this.getColeta4() +  ", Coleta5=" + this.getColeta5() +", ID Cliente=" + this.cliente.getIdCliente() + "]";
	}
	
}
