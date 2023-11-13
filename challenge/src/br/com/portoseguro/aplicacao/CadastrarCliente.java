package br.com.portoseguro.aplicacao;

import java.util.List;


import br.com.portoseguro.dominio.CPF;
import br.com.portoseguro.dominio.Cliente;
import br.com.portoseguro.repositorio.RepositorioCliente;

public class CadastrarCliente{
	private RepositorioCliente repositorio;
	
	public CadastrarCliente(RepositorioCliente repositorio) {
		this.repositorio = repositorio;
	}
	
	public void cadastrar(String nome, String email, String cpf) {
		Cliente clienteExistente = repositorio.buscarPorCPF(cpf);
		
		int ultimoID = repositorio.buscarUltimoID();
		
		if (clienteExistente != null) {
			throw new IllegalArgumentException(
					"Já existe um cliente com o cpf informado");
		}
		
		Cliente novoCliente = new Cliente(nome, email, new CPF(cpf), ultimoID + 1);
		this.repositorio.salvar(novoCliente);
	}
	
	public List<Cliente> listarTodos(){
		return repositorio.listarTodos();
	}
	

}