package br.com.portoseguro.repositorio;

import java.util.List;
import br.com.portoseguro.dominio.Bicicleta;


public interface RepositorioBicicleta {
	
	public int buscarUltimoID();

	public Bicicleta buscarPorNumeroDeSerie(String numeroDeSerie);
	
	public Bicicleta buscarPorNumeroDeSerie(String numeroDeSerie, int idCliente);

	public void salvar(Bicicleta novaBicicletas);

	public List<Bicicleta> listarTodos();

}
