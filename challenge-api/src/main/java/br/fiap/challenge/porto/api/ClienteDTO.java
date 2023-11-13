package br.fiap.challenge.porto.api;

public class ClienteDTO {
	private String nome;
	private String email;
	private String cpf;

	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}	
	public String getCpf() {
		return cpf;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
