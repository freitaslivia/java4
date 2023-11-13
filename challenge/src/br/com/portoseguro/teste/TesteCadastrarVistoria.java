package br.com.portoseguro.teste;

import java.util.List;

import br.com.portoseguro.aplicacao.CadastrarBicicleta;
import br.com.portoseguro.aplicacao.CadastrarCliente;
import br.com.portoseguro.aplicacao.CadastrarVistoria;
import br.com.portoseguro.dominio.Vistoria;
import br.com.portoseguro.infraestrutura.RepositorioBicicletaMemoria;
import br.com.portoseguro.infraestrutura.RepositorioClienteMemoria;
import br.com.portoseguro.infraestrutura.RepositorioVistoriaMemoria;
import br.com.portoseguro.repositorio.RepositorioBicicleta;
import br.com.portoseguro.repositorio.RepositorioCliente;
import br.com.portoseguro.repositorio.RepositorioVistoria;


public class TesteCadastrarVistoria {

	public static void main(String[] args) {
		
		RepositorioVistoria repositorioVistoria = new RepositorioVistoriaMemoria();
		RepositorioCliente repositorioCliente = new RepositorioClienteMemoria();
		RepositorioBicicleta repositorioBicicleta = new RepositorioBicicletaMemoria();
		
		CadastrarVistoria casoDeUsoVistoria = new CadastrarVistoria(repositorioVistoria, repositorioBicicleta, repositorioCliente);
		CadastrarCliente casoDeUsoCliente = new CadastrarCliente(repositorioCliente);
		CadastrarBicicleta casoDeUsoBicicleta = new CadastrarBicicleta(repositorioBicicleta, repositorioCliente);
		
		List<Vistoria> vistorias = repositorioVistoria.buscarPorCPF("123.456.789-00");
		System.out.println("Qtde de vistorias encontradas para o cpf:" + vistorias.size());
		
		casoDeUsoCliente.cadastrar("livia", "livia@fiap.com.br", "123.456.789-00");
		
		casoDeUsoBicicleta.cadastrar("123456789", 2000, "caloi", "ceci", 10000,"123.456.789-00");
		
		casoDeUsoVistoria.cadastrar(false, "x1","x2","x3","x4", "x5", "123.456.789-00", "123456789");

		
		casoDeUsoBicicleta.cadastrar("023456789", 2000, "caloi", "ceci", 10000,"123.456.789-00");
		
		casoDeUsoVistoria.cadastrar(false, "x1","x2","x3","x4", "x5", "123.456.789-00", "023456789");

		casoDeUsoCliente.cadastrar("Livia", "livia@fiap.com.br", "123.456.789-01");
		
		casoDeUsoBicicleta.cadastrar("223456789", 2000, "caloi", "ceci", 10000,"123.456.789-01");
		
		casoDeUsoVistoria.cadastrar(false, "x1","x2","x3","x4", "x5", "123.456.789-01", "223456789");

		

		vistorias = repositorioVistoria.buscarPorCPF("123.456.789-00");
		
		//Neste ponto, deve existir
		System.out.println("Qtde de vistorias encontradas para o cpf:" + vistorias.size());
		
		
		System.out.println("Listar todas as vistorias encontradas para o cpf");
		for (Vistoria vistoria1 : vistorias) {
			System.out.println("Vistoria: " + vistoria1.getIdVistoria() + " - Bicicleta: " + vistoria1.getBicicleta().getNumeroDeSerie() + " - Cliente: " + vistoria1.getCliente().getNome());
		}
		

		vistorias = repositorioVistoria.buscarPorCPF("123.456.789-01");
		
		//Neste ponto, deve existir
		System.out.println("Qtde de vistorias encontradas para o cpf:" + vistorias.size());
		
		
		System.out.println("Listar todas as vistorias encontradas para o cpf ");
		for (Vistoria vistoria1 : vistorias) {
			System.out.println("Vistoria: " + vistoria1.getIdVistoria() + " - Bicicleta: " + vistoria1.getBicicleta().getNumeroDeSerie() + " - Cliente: " + vistoria1.getCliente().getNome());
		}
				
		
		
		System.out.println("Listar todas as vistorias encontradas");
		
		for (Vistoria vistoria1 : casoDeUsoVistoria.listarTodos()) {
			System.out.println("Vistoria: " + vistoria1.getIdVistoria() + " - Bicicleta: " + vistoria1.getBicicleta().getNumeroDeSerie() + " - Cliente: " + vistoria1.getCliente().getNome());
		}
		

	}

}
