package br.fiap.challenge.porto.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import br.com.portoseguro.aplicacao.CadastrarCliente;
import br.com.portoseguro.dominio.Cliente;
import br.com.portoseguro.infraestrutura.RepositorioClienteJDBC;
import br.com.portoseguro.repositorio.RepositorioCliente;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientesApi {
	private RepositorioCliente repositorioClientes;
	private CadastrarCliente cadastrarCliente;

	public ClientesApi() {

		repositorioClientes = new RepositorioClienteJDBC();
		cadastrarCliente = new CadastrarCliente(repositorioClientes);
	}

	/*
	 Para testar o get coloque essa url:
	
	 http://localhost:8080/clientes/111.111.111.11
	 
	 tem que colocar um cpf existente :)
	 caso esse nao exista cria primeiro um no post.
	*/
	@GET
	@Path("{cpf}")
	public Response obterClientePorCpf(@PathParam("cpf") String cpf) {
		Cliente cliente = this.repositorioClientes.buscarPorCPF(cpf);
		if (cliente != null) {
			return Response.ok(cliente).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado").build();
		}
	}

	/*
	 Para testar o post coloque esse json:
	{"nome":"x","email":"xxx","cpf":"888.999.999-99"}
	
	na url coloca: http://localhost:8080/clientes/
	
	*/
	@POST
	public Response cadastrarNovoCliente(ClienteDTO cliente) throws URISyntaxException {
		try {
			
			this.cadastrarCliente.cadastrar(cliente.getNome(), cliente.getEmail(),
					cliente.getCpf());
			return this.obterClientePorCpf(cliente.getCpf());
			
		} catch (Exception ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

	/*
	 Para testar o get coloque essa url:
	
	 http://localhost:8080/clientes/listatodos
	*/
	@GET
	@Path("listatodos")
	public Response listarTodosClientes() {
		List<Cliente> lista = this.cadastrarCliente.listarTodos();
		return Response.ok(lista).build();
	}

}