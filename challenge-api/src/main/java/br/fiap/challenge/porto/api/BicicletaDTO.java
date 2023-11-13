package br.fiap.challenge.porto.api;

public class BicicletaDTO {
		private int ano;
		private String marca;
		private String modelo;
		private double valor;
		private String numeroDeSerie;
		private String cpf;

		public int getAno() {
			return ano;
		}

		public void setAno(int ano) {
			this.ano = ano;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public void setModelo(String modelo) {
			this.modelo = modelo;
		}

		public void setValor(double valor) {
			this.valor = valor;
		}

		public void setNumeroDeSerie(String numeroDeSerie) {
			this.numeroDeSerie = numeroDeSerie;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
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

		public String getNumeroDeSerie() {
			return numeroDeSerie;
		}

		public String getCpf() {
			return cpf;
		}


}


