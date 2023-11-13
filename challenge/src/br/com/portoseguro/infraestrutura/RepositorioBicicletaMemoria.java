package br.com.portoseguro.infraestrutura;

import java.util.ArrayList;
import java.util.List;
import br.com.portoseguro.dominio.Bicicleta;
import br.com.portoseguro.repositorio.RepositorioBicicleta;


public class RepositorioBicicletaMemoria implements RepositorioBicicleta {
	
	private List<Bicicleta> bicicletas = new ArrayList<>();
	
	@Override
	public int buscarUltimoID() {
		if (bicicletas.size()== 0) {
			return 0;
		}
		var ultimoItem = bicicletas.get(bicicletas.size()-1);
		return ultimoItem.getIdBicicleta();
	}


	
	@Override
	public Bicicleta buscarPorNumeroDeSerie(String numeroDeSerie) {
		
		for (Bicicleta bicicleta : bicicletas) {
			if (bicicleta.getNumeroDeSerie().toString().equals(numeroDeSerie)) {
				return bicicleta;
			}	
		}
		
		return null;
	}
	
	@Override
	public Bicicleta buscarPorNumeroDeSerie(String numeroDeSerie, int idCliente) {
		
		for (Bicicleta bicicleta : bicicletas) {
			if (bicicleta.getNumeroDeSerie().toString().equals(numeroDeSerie)) {
				if (bicicleta.getCliente().getIdCliente() == idCliente) {
					return bicicleta;
				}
			}	
		}
		
		return null;
	}

	@Override
	public void salvar(Bicicleta novaBicicleta) {
		this.bicicletas.add(novaBicicleta);
	}

	@Override
	public List<Bicicleta> listarTodos() {
		return bicicletas;
	}

}
