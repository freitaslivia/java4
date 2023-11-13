package br.com.portoseguro.dominio;

public class Bicicleta {
	private int idBicicleta;
	private int ano;
	private String marca;
	private String modelo;
	private double valor;
	private String numeroDeSerie;
	private Cliente cliente;
	
	public Bicicleta(Cliente cliente, String numeroDeSerie, int ano, String marca, String modelo, double valor, int idBicicleta) 
	{
		
		if (marca == null || marca.isBlank()) {
			throw new RuntimeException("Nome deve ser informado");
		}
		
		if (modelo == null || marca.isBlank()) {
			throw new RuntimeException("O modelo deve ser informado");
		}
		
		if (numeroDeSerie == null) {
			throw new RuntimeException("O número de serie deve ser informado");
		}
		
		if (ano == 0) {
			throw new RuntimeException("O ano de fabricacao deve ser informado");
		}
		
		if (valor == 0) {
			throw new RuntimeException("O valor da bicicleta deve ser informado");
		}
		
		this.marca = marca;
		this.modelo = modelo;
		this.numeroDeSerie = numeroDeSerie;
		this.ano = ano;
		this.valor = valor;	
		this.cliente = cliente;
		this.idBicicleta = idBicicleta;
		
		
	}

	public int getAno() {
		return ano;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public double getValor() {
		return valor;
	}

	public int getIdBicicleta() {
		return idBicicleta;
	}

	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public Cliente getCliente() {
		return cliente;
	}	
	
	@Override
	public String toString() {
		return "Bicicleta [ID=" + this.getIdBicicleta() + ", Numero de Serie=" + this.numeroDeSerie + ", Marca=" + this.marca + ", Numero de Serie=" + this.numeroDeSerie + ", Modelo=" + this.numeroDeSerie +", valor=" + this.valor + "]";
	}
}

