package br.com.portoseguro.aplicacao;

import java.util.List;

import br.com.portoseguro.dominio.Bicicleta;
import br.com.portoseguro.dominio.Cliente;
import br.com.portoseguro.dominio.Vistoria;
import br.com.portoseguro.repositorio.RepositorioBicicleta;
import br.com.portoseguro.repositorio.RepositorioCliente;
import br.com.portoseguro.repositorio.RepositorioVistoria;

public class CadastrarVistoria {
	private RepositorioVistoria repositorio;
	private RepositorioBicicleta repositorioBicicleta;
	private RepositorioCliente repositorioCliente;
	
	
	public CadastrarVistoria(RepositorioVistoria repositorio, RepositorioBicicleta repositorioBicicleta, RepositorioCliente repositorioCliente) {
		this.repositorio = repositorio;
		this.repositorioBicicleta = repositorioBicicleta;
		this.repositorioCliente = repositorioCliente;
	}
	
	public void cadastrar(boolean renovacao, String coleta1,
			String coleta2, String coleta3, String coleta4, String coleta5, String cpf, String numeroDeSerie) {
		Cliente clienteExistente = repositorioCliente.buscarPorCPF(cpf);
		
		
		if (clienteExistente == null) {
			throw new IllegalArgumentException(
					"Este cliente não está cadastrado");
		}
		
		Bicicleta bicicletaExistente = repositorioBicicleta.buscarPorNumeroDeSerie(numeroDeSerie, clienteExistente.getIdCliente());
		
		if (bicicletaExistente == null) {
			throw new IllegalArgumentException(
					"Esta bicicleta não está cadastrada");
		}
		
		int ultimoID = repositorio.buscarUltimoID();
		
		Vistoria novaVistoria = new Vistoria(renovacao, clienteExistente, 
				bicicletaExistente ,coleta1, coleta2, coleta3, coleta4, 
				coleta5, ultimoID + 1);
		this.repositorio.salvar(novaVistoria);
	}
	
	public List<Vistoria> listarTodos(){
		return repositorio.listarTodos();
	}
}


