package br.com.portoseguro.teste;

import br.com.portoseguro.aplicacao.CadastrarCliente;
import br.com.portoseguro.dominio.Cliente;
import br.com.portoseguro.infraestrutura.RepositorioClienteMemoria;
import br.com.portoseguro.repositorio.RepositorioCliente;



public class TesteCadastrarCliente {

	public static void main(String[] args) {
		RepositorioCliente repositorio = new RepositorioClienteMemoria();
		CadastrarCliente casoDeUso = new CadastrarCliente(repositorio);
		
		Cliente clienteNaoExistente = repositorio.buscarPorCPF("134556");
		Cliente cliente = repositorio.buscarPorCPF("123.456.789-00");
		
		
		//Nao deve trazer o cliente que nao existe
		System.out.println(clienteNaoExistente);
		
		//Neste ponto, deve ser nulo
		System.out.println(cliente);
		casoDeUso.cadastrar("Livia", "livia@fiap.com.br", "123.456.789-00");
		casoDeUso.cadastrar("Livia", "livia@fiap.com.br", "123.456.789-25");
		
		cliente = repositorio.buscarPorCPF("123.456.789-00");
		
		//Neste ponto, deve existir
		System.out.println(cliente);
		
		for (Cliente cliente1 : casoDeUso.listarTodos()) {
			System.out.println(cliente1.getCpf() + " - " + cliente1.getIdCliente());
		}
		

	}

}
