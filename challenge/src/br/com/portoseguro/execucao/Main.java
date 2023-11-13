package br.com.portoseguro.execucao;

import java.util.List;
import java.util.Scanner;

import br.com.portoseguro.aplicacao.CadastrarBicicleta;
import br.com.portoseguro.aplicacao.CadastrarCliente;
import br.com.portoseguro.aplicacao.CadastrarVistoria;
import br.com.portoseguro.dominio.Bicicleta;
import br.com.portoseguro.dominio.Cliente;
import br.com.portoseguro.dominio.Vistoria;
import br.com.portoseguro.infraestrutura.RepositorioBicicletaJDBC;
import br.com.portoseguro.infraestrutura.RepositorioBicicletaMemoria;
import br.com.portoseguro.infraestrutura.RepositorioClienteJDBC;
import br.com.portoseguro.infraestrutura.RepositorioClienteMemoria;
import br.com.portoseguro.infraestrutura.RepositorioVistoriaJDBC;
import br.com.portoseguro.infraestrutura.RepositorioVistoriaMemoria;
import br.com.portoseguro.repositorio.RepositorioBicicleta;
import br.com.portoseguro.repositorio.RepositorioCliente;
import br.com.portoseguro.repositorio.RepositorioVistoria;



public class Main {

	public static void main(String[] args) {
		
		RepositorioVistoria repositorioVistoria = new RepositorioVistoriaJDBC();
		RepositorioCliente repositorioCliente = new RepositorioClienteJDBC();
		RepositorioBicicleta repositorioBicicleta = new RepositorioBicicletaJDBC();
		
		CadastrarVistoria cadastrarVistoria = new CadastrarVistoria(repositorioVistoria, repositorioBicicleta, repositorioCliente);
		CadastrarCliente cadastarCliente = new CadastrarCliente(repositorioCliente);
		CadastrarBicicleta cadastrarBicicleta = new CadastrarBicicleta(repositorioBicicleta, repositorioCliente);
		
		Scanner scanner = new Scanner(System.in);
		
		while (true){

			System.out.print("##--Teste Estrutura de Menu--##\n\n");
			System.out.print("|---------------------------------------|\n");
			System.out.print("| Opção 1 - Nova Vistoria     			|\n");
			System.out.print("| Opção 2 - Listar Vistorias por CPF		|\n");
			System.out.print("| Opção 3 - Listar Vistorias    			|\n");
			System.out.print("| Opção 9 - Sair              			|\n");
			System.out.print("|---------------------------------------|\n");
			System.out.print("Digite uma opção: ");

			int opcao = scanner.nextInt();

			if(opcao == 1) {
				
				// coletar CPF
				System.out.println("Digite cpf: ");
				String cpf = scanner.next();
				
				// Verificar se o CPF ja existe
				Cliente cliente = repositorioCliente.buscarPorCPF(cpf);
			
				// se o cpf náo existe coletar nome, email, sexo, nascimento
				if(cliente == null) {
					System.out.println("CPF não encontrado, faça um cadastro");
					System.out.println("Digite nome: ");
					String nome = scanner.next();
					System.out.println("Digite email: ");
					String email = scanner.next();
					//System.out.println("Digite sexo: ");
					//String sexo = scanner.next();
					//System.out.println("Digite nascimento: ");
					//String nascimento = scanner.next();
					
					// salvar cliente
					cadastarCliente.cadastrar(nome, email, cpf);
					cliente = repositorioCliente.buscarPorCPF(cpf);
				}
			

				// coletar numerodeserie
				System.out.println("Digite numero de serie: ");
				String numeroDeSerie = scanner.next();
				// verificar se o numero de serie ja existe para o cliente
				Bicicleta bicicleta = repositorioBicicleta.buscarPorNumeroDeSerie(numeroDeSerie, cliente.getIdCliente());
				// se o numero de serie nao existe coletar informacoes da bike marca, modelo, ano, numerodeserie, valor
				if(bicicleta == null) {
					System.out.println("Numero de serie não encontrado, faça um cadastro");
					System.out.println("Digite marca: ");
					String marca = scanner.next();
					System.out.println("Digite modelo: ");
					String modelo = scanner.next();
					System.out.println("Digite ano: ");
					int ano = scanner.nextInt();
					System.out.println("Digite valor: ");
					double valor = scanner.nextDouble();
					
					// salvar bike
					cadastrarBicicleta.cadastrar(numeroDeSerie, ano, marca, modelo, valor, cpf);
					bicicleta = repositorioBicicleta.buscarPorNumeroDeSerie(numeroDeSerie, cliente.getIdCliente());
				}
				
				// coletar informacoes de vistoria: renovacao, coleta1 ate coleta5
				System.out.println("Preencha os dados da vistoria");
				System.out.println("É renovação(S)ou (N)? ");
				String sRenovacao = scanner.next();
				boolean renovacao = false; 
				if(sRenovacao.equals("S")) {
					renovacao = true; 
				}
				System.out.println("Foto1: ");
				String coleta1 = scanner.next();
				System.out.println("Foto2: ");
				String coleta2 = scanner.next();
				System.out.println("Foto3: ");
				String coleta3 = scanner.next();
				System.out.println("Foto4: ");
				String coleta4 = scanner.next();
				System.out.println("Foto5: ");
				String coleta5 = scanner.next();
				
				// gravar vistoria
				cadastrarVistoria.cadastrar(renovacao, coleta1, coleta2, coleta3, coleta4, coleta5, cpf, numeroDeSerie);

				// finalizar opcao 1
				System.out.println("Vistoria cadastrada");
			}
			else if(opcao == 2) {
				System.out.println("Digite cpf: ");
				String cpf = scanner.next();
				
				List<Vistoria> vistorias = repositorioVistoria.buscarPorCPF(cpf);
				
				System.out.println("Listar todas as vistorias encontradas para o cpf");
				for (Vistoria vistoria1 : vistorias) {
					System.out.println("Vistoria: " + vistoria1.getIdVistoria() + " - Bicicleta: " + vistoria1.getBicicleta().getNumeroDeSerie() + " - Cliente: " + vistoria1.getCliente().getNome());
				}
				
			}
			
			else if(opcao == 3) {
				
				List<Vistoria> vistorias = repositorioVistoria.listarTodos();
				
				System.out.println("Listar todas as vistorias encontradas");
				for (Vistoria vistoria1 : vistorias) {
					System.out.println("Vistoria: " + vistoria1.getIdVistoria() + " - Bicicleta: " + vistoria1.getBicicleta().getNumeroDeSerie() + " - Cliente: " + vistoria1.getCliente().getNome());
				}
				
			}
			else if(opcao == 9){
				System.out.println("Obrigado, volte sempre!!");
				break;
			}
			

		}

	}

}
