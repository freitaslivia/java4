package br.com.portoseguro.repositorio;

import java.util.List;

import br.com.portoseguro.dominio.Cliente;

public interface RepositorioCliente {
	
	public int buscarUltimoID();

	public Cliente buscarPorCPF(String cpf);

	public void salvar(Cliente novoCliente);

	public List<Cliente> listarTodos();
}
