package br.fiap.challenge.porto.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import br.com.portoseguro.aplicacao.CadastrarBicicleta;
import br.com.portoseguro.aplicacao.CadastrarCliente;
import br.com.portoseguro.dominio.Bicicleta;
import br.com.portoseguro.infraestrutura.RepositorioBicicletaJDBC;
import br.com.portoseguro.infraestrutura.RepositorioClienteJDBC;
import br.com.portoseguro.repositorio.RepositorioBicicleta;
import br.com.portoseguro.repositorio.RepositorioCliente;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("bicicletas")
@Produces("application/json")
@Consumes("application/json")
public class BicicletasApi {
	private RepositorioCliente repositorioClientes;
	private RepositorioBicicleta repositorioBicicletas;
	private CadastrarCliente cadastrarCliente;
	private CadastrarBicicleta cadastrarBicicleta;

	public BicicletasApi() {

		repositorioBicicletas = new RepositorioBicicletaJDBC();
		repositorioClientes = new RepositorioClienteJDBC();
		cadastrarCliente = new CadastrarCliente(repositorioClientes);
		cadastrarBicicleta = new CadastrarBicicleta(repositorioBicicletas, repositorioClientes);
	}

	//
	/*
	 * Para testar o post coloque esse json:
	 * 
	 * {"numeroDeSerie": "777","ano": 2020, "marca": "c", "modelo": "x",
	 * "valor":5000, "cpf": "111.111.111.11" }
	 * 
	 * na url coloca: http://localhost:8080/bicicletas/
	 * 
	 */
	@POST
	public Response cadastrarNovaBicicleta(BicicletaDTO bicicleta) throws URISyntaxException {

		try {
			this.cadastrarBicicleta.cadastrar(bicicleta.getNumeroDeSerie(), bicicleta.getAno(), bicicleta.getMarca(),
					bicicleta.getModelo(), bicicleta.getValor(), bicicleta.getCpf());
			return this.obterBicicletaPorNumeroDeSerie(bicicleta.getNumeroDeSerie());

		} catch (Exception ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}

	/*
	 * Para testar o get coloque essa url:
	 * 
	 * http://localhost:8080/bicicletas/123
	 * 
	 * tem que colocar um numeroDeserie existente :) caso esse nao exista cria
	 * primeiro um no post.
	 */
	@GET
	@Path("{numeroDeSerie}")
	public Response obterBicicletaPorNumeroDeSerie(@PathParam("numeroDeSerie") String numeroDeSerie) {
		Bicicleta bicicleta = this.repositorioBicicletas.buscarPorNumeroDeSerie(numeroDeSerie);
		if (bicicleta != null) {
			return Response.ok(bicicleta).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Bicicleta não encontrado").build();
		}
	}

	/*
	 * @GET
	 * 
	 * @Path("{numeroDeSerie}") public Response
	 * obterBicletaNumeroDeSerie(@PathParam("numeroDeSerie") String numeroDeSerie,
	 * int idCliente) { //Cliente cliente = cliente.getIdCliente().toString();
	 * Bicicleta bicicleta =
	 * this.repositorioBicicletas.buscarPorNumeroDeSerie(numeroDeSerie, idCliente);
	 * if (bicicleta != null) { return Response.ok(bicicleta.toString()).build(); }
	 * else { return
	 * Response.status(Response.Status.NOT_FOUND).entity("Bicicleta não encontrado")
	 * .build(); } }
	 */

	/*
	 * Para testar o get coloque essa url:
	 * 
	 * http://localhost:8080/bicicletas/listatodos
	 */
	@GET
	@Path("listatodos")
	public Response listarTodasBicicletas() {
		List<Bicicleta> lista = this.cadastrarBicicleta.listarTodos();
		return Response.ok(lista).build();
	}
}
