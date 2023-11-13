package br.com.portoseguro.infraestrutura;

import java.util.ArrayList;
import java.util.List;

import br.com.portoseguro.dominio.Cliente;
import br.com.portoseguro.repositorio.RepositorioCliente;

public class RepositorioClienteMemoria implements RepositorioCliente {
	
	private List<Cliente> clientes = new ArrayList<>();
	
	@Override
	public int buscarUltimoID() {
		if (clientes.size()== 0) {
			return 0;
		}
		var ultimoItem = clientes.get(clientes.size()-1);
		return ultimoItem.getIdCliente();
	}


	
	@Override
	public Cliente buscarPorCPF(String cpf) {
		
		for (Cliente cliente : clientes) {
			if (cliente.getCpf().toString().equals(cpf)) {
				return cliente;
			}	
		}
		
		return null;
	}

	@Override
	public void salvar(Cliente novoCliente) {
		this.clientes.add(novoCliente);
	}

	@Override
	public List<Cliente> listarTodos() {
		return clientes;
	}

}
