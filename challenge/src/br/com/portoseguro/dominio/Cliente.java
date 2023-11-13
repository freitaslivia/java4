package br.com.portoseguro.dominio;

import java.util.Date;

public class Cliente extends Pessoa{
	private int idCliente;
	private Date dataCadastro;
	private boolean cadastroAtivo;
	
	public Cliente(String nome, String email, CPF cpf, int idCliente) {
		super(nome, email, cpf);
		
		this.idCliente = idCliente;
		this.dataCadastro = new Date();
		this.cadastroAtivo = true;
	}
	
	public Cliente(String nome, String email, CPF cpf, int idCliente, Date dataCadastro, boolean cadastroAtivo) {
		super(nome, email, cpf);
		
		this.idCliente = idCliente;
		this.dataCadastro = dataCadastro;
		this.cadastroAtivo = cadastroAtivo;
	}

	public int getIdCliente() {
		return idCliente;
	}
	

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public boolean isCadastroAtivo() {
		return cadastroAtivo;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	
	@Override
	public String toString() {
		return "Cliente [nome=" + this.getNome() + ", email=" + this.getEmail() + ", cpf=" + this.getCpf().toString() + "]";
	}

	
}
