package br.com.portoseguro.repositorio;

import java.util.List;

import br.com.portoseguro.dominio.Vistoria;

public interface RepositorioVistoria {

	public int buscarUltimoID();

	public void salvar(Vistoria novaVistoria);

	public List<Vistoria> listarTodos();

	public List<Vistoria> buscarPorCPF(String cpf);

}
