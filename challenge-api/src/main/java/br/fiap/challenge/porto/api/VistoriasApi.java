package br.fiap.challenge.porto.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import br.com.portoseguro.aplicacao.CadastrarBicicleta;
import br.com.portoseguro.aplicacao.CadastrarCliente;
import br.com.portoseguro.aplicacao.CadastrarVistoria;
import br.com.portoseguro.dominio.Bicicleta;
import br.com.portoseguro.dominio.Vistoria;
import br.com.portoseguro.infraestrutura.RepositorioBicicletaJDBC;
import br.com.portoseguro.infraestrutura.RepositorioClienteJDBC;
import br.com.portoseguro.infraestrutura.RepositorioVistoriaJDBC;
import br.com.portoseguro.repositorio.RepositorioBicicleta;
import br.com.portoseguro.repositorio.RepositorioCliente;
import br.com.portoseguro.repositorio.RepositorioVistoria;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("vistorias")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VistoriasApi {
	private RepositorioCliente repositorioClientes;
	private RepositorioBicicleta repositorioBicicletas;
	private RepositorioVistoria repositorioVistoria;
	private CadastrarCliente cadastrarCliente;
	private CadastrarBicicleta cadastrarBicicleta;
	private CadastrarVistoria cadastrarVistoria;


	
	public VistoriasApi() {		
		
		repositorioBicicletas = new RepositorioBicicletaJDBC();
		repositorioClientes = new RepositorioClienteJDBC();
		repositorioVistoria = new RepositorioVistoriaJDBC();
		cadastrarCliente =  new CadastrarCliente(repositorioClientes);
		cadastrarBicicleta =  new CadastrarBicicleta(repositorioBicicletas, repositorioClientes);
		cadastrarVistoria = new CadastrarVistoria(repositorioVistoria, repositorioBicicletas, repositorioClientes);
	}
	
	/*
	 Para testar o post coloque esse json:
	{"renovacao":false,"coleta1": "x", "coleta2": "x", "coleta3": "x", "coleta4":"x", "coleta5" : "x", "cpf": "111.111.111.11", "numeroDeSerie": "123"}
	
	na url coloca: http://localhost:8080/clientes/
	
	*/
	@POST
	public Response cadastrarNovaVistoria(VistoriaDTO vistoria) throws URISyntaxException {
		
		try {
			this.cadastrarVistoria.cadastrar(vistoria.isRenovacao(), vistoria.getColeta1(), vistoria.getColeta2(), 
					vistoria.getColeta3(), vistoria.getColeta4(), vistoria.getColeta5(),
					vistoria.getCpf(), vistoria.getNumeroDeSerie());
			return Response.created(new URI("vistorias/" + vistoria.getCpf())).build();
			
		} catch(Exception ex) {
			return Response.serverError().entity(ex.getMessage()).build();
		}
	}
	
	
	/*
	 Para testar o get coloque essa url:
	
	 http://localhost:8080/vistorias/1111.111.111.11
	*/
	
    @GET
    @Path("{cpf}")
    public Response obterVistoriaPorCpf(@PathParam("cpf") String cpf) {
        List<Vistoria> vistoria = this.repositorioVistoria.buscarPorCPF(cpf);
        if (vistoria != null) {
            return Response.ok(vistoria).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Vistoria não encontrada").build();
        }
    }
 
	/*
	 Para testar o get coloque essa url:
	
	 http://localhost:8080/vistorias/listatodos
	*/  
	@GET
	@Path("listatodos")
	public Response listarTodasVistorias() {
		List<Vistoria> lista = this.cadastrarVistoria.listarTodos();
		return Response.ok(lista).build();
	}

}
