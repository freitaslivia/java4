package br.com.portoseguro.teste;

import br.com.portoseguro.aplicacao.CadastrarBicicleta;
import br.com.portoseguro.aplicacao.CadastrarCliente;
import br.com.portoseguro.dominio.Bicicleta;
import br.com.portoseguro.infraestrutura.RepositorioBicicletaMemoria;
import br.com.portoseguro.infraestrutura.RepositorioClienteMemoria;
import br.com.portoseguro.repositorio.RepositorioBicicleta;
import br.com.portoseguro.repositorio.RepositorioCliente;


public class TesteCadastrarBicicleta{
	
	public static void main(String[] args) {
		
		RepositorioCliente repositorioCliente = new RepositorioClienteMemoria();
		RepositorioBicicleta repositorioBicicleta = new RepositorioBicicletaMemoria();
		
		CadastrarCliente casoDeUsoCliente = new CadastrarCliente(repositorioCliente);
		CadastrarBicicleta casoDeUsoBicicleta = new CadastrarBicicleta(repositorioBicicleta, repositorioCliente);
		
		Bicicleta bicicleta = repositorioBicicleta.buscarPorNumeroDeSerie("12345678");
		
		
		//Nao deve trazer uma bike que nao existe
		System.out.println(bicicleta);
		
		casoDeUsoCliente.cadastrar("Livia", "livia@fiap.com.br", "123.456.789-00");
		//String numeroDeSerie, int ano, String marca, String modelo, double valor, String cpf
		casoDeUsoBicicleta.cadastrar("123456789", 2000, "caloi", "ceci", 10000,"123.456.789-00");
		
		bicicleta = repositorioBicicleta.buscarPorNumeroDeSerie("123456789");
		
		//Neste ponto, deve existir
		System.out.println(bicicleta);
		
		for (Bicicleta bicicleta1 : casoDeUsoBicicleta.listarTodos()) {
			System.out.println(bicicleta1.getNumeroDeSerie() + " - " + bicicleta1.getIdBicicleta() + " - " + bicicleta1.getCliente().getNome());
		}
		

   }

  }
