package br.com.portoseguro.dominio;

public class Pessoa {
	private String nome;
	private CPF cpf;
	private String email;
	
	public Pessoa(String nome, String email, CPF cpf) {
		
		if (nome == null || nome.isBlank()) {
			throw new RuntimeException("Nome deve ser informado");
		}
		
		if (email == null) {
			throw new RuntimeException("Email deve ser informado");
		}
		
		if (cpf == null) {
			throw new RuntimeException("CPF deve ser informado");
		}
		
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
	}
	
	public String getCpf() {
		return cpf.toString();
	}
	
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

}
