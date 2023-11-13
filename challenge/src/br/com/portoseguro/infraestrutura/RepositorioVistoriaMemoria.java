package br.com.portoseguro.infraestrutura;

import java.util.ArrayList;
import java.util.List;

import br.com.portoseguro.dominio.Vistoria;
import br.com.portoseguro.repositorio.RepositorioVistoria;

public class RepositorioVistoriaMemoria implements RepositorioVistoria {
	
	private List<Vistoria> vistorias = new ArrayList<>();
	
	@Override
	public int buscarUltimoID() {
		if (vistorias.size()== 0) {
			return 0;
		}
		var ultimoItem = vistorias.get(vistorias.size()-1);
		return ultimoItem.getIdVistoria();
	}

	@Override
	public void salvar(Vistoria novaVistoria) {
		this.vistorias.add(novaVistoria);
	}

	@Override
	public List<Vistoria> listarTodos() {
		return vistorias;
	}
	
	@Override
	public List<Vistoria> buscarPorCPF(String cpf) {
		
		List<Vistoria> retorno = new ArrayList<>();
		
		for (Vistoria vistoria : vistorias) {
			if (vistoria.getCliente().getCpf().toString().equals(cpf)) {
				retorno.add(vistoria);
			}	
		}
	
		return retorno;
	}

}



