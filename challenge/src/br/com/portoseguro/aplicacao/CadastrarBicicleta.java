package br.com.portoseguro.aplicacao;

import java.util.List;

import br.com.portoseguro.dominio.Bicicleta;
import br.com.portoseguro.dominio.Cliente;
import br.com.portoseguro.repositorio.RepositorioBicicleta;
import br.com.portoseguro.repositorio.RepositorioCliente;


public class CadastrarBicicleta {
	private RepositorioBicicleta repositorio;
	private RepositorioCliente repositorioCliente;
	
	public CadastrarBicicleta(RepositorioBicicleta repositorio, RepositorioCliente repositorioCliente) {
		this.repositorio = repositorio;
		this.repositorioCliente = repositorioCliente;
	}
	
	public void cadastrar(String numeroDeSerie, int ano, String marca, String modelo, double valor, String cpf) {
		Cliente clienteExistente = repositorioCliente.buscarPorCPF(cpf);
		
		if (clienteExistente == null) {
			throw new IllegalArgumentException(
					"Este cliente não está cadastrado");
		}
		
		Bicicleta bicicletaExistente = repositorio.buscarPorNumeroDeSerie(numeroDeSerie);
		
		
		if (bicicletaExistente != null) {
			throw new IllegalArgumentException(
					"Já existe uma bicicleta com o numero de serie informado");
		}
		
		int ultimoID = repositorio.buscarUltimoID();
		
		Bicicleta novaBicicleta = new Bicicleta(clienteExistente, numeroDeSerie, ano, marca,modelo, valor, ultimoID + 1);
		this.repositorio.salvar(novaBicicleta);
	}
	
	public List<Bicicleta> listarTodos(){
		return repositorio.listarTodos();
	}
}
