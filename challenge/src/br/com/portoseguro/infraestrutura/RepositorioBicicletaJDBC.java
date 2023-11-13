package br.com.portoseguro.infraestrutura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portoseguro.conexao.Conexao;
import br.com.portoseguro.dominio.Bicicleta;
import br.com.portoseguro.dominio.CPF;
import br.com.portoseguro.dominio.Cliente;
import br.com.portoseguro.repositorio.RepositorioBicicleta;


public class RepositorioBicicletaJDBC implements RepositorioBicicleta{
	
	private final String SQL_CONSULTAR_BICICLETAULTIMOID= "SELECT MAX(IDBICICLETA) AS ID FROM T_BICICLETA";
	private final String SQL_CONSULTAR_BICICLETA= "SELECT * FROM T_BICICLETA WHERE NUMERODESERIE=?";
	private final String SQL_CONSULTAR_BICICLETACLIENTE= "SELECT idcliente,numerodeserie,ano,marca,modelo,valor,idbicicleta FROM T_BICICLETA WHERE NUMERODESERIE=? AND IDCLIENTE=?";
	private final String SQL_CONSULTAR_CLIENTEID= "SELECT * FROM T_CLIENTE WHERE IDCLIENTE=?";
	private final String SQL_INSERIR_BICICLETA= "INSERT INTO T_BICICLETA ( idCliente, numeroDeSerie, ano, marca, modelo, valor, idBicicleta ) values ( ?, ?, ?, ?, ?, ?, ?)";
	private final String SQL_LISTAR_BICICLETA= "SELECT * FROM T_BICICLETA";
	
	private PreparedStatement pst = null;
	
	@Override
	public int buscarUltimoID() {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(SQL_CONSULTAR_BICICLETAULTIMOID);
			
			try (ResultSet registros = pst.executeQuery()) {						
				while(registros.next()) {
					int idBicileta = registros.getInt("ID");
					pst.close();
					Conexao.fecharCnx();
					return idBicileta;
				}
				pst.close();
				Conexao.fecharCnx();
				return 0;
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
			Conexao.fecharCnx();
		}
		return 0;
	}
	@Override
	public Bicicleta buscarPorNumeroDeSerie(String numeroDeSerie) {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(SQL_CONSULTAR_BICICLETA);
			pst.setString(1, numeroDeSerie);

			try (ResultSet registros = pst.executeQuery()) {						
				while(registros.next()) {
					int idCliente = registros.getInt("idCliente");
					Cliente cliente = buscarClienteID(idCliente);
					String numeroDeSerieBanco = registros.getString("numeroDeSerie");
					int ano = registros.getInt("ano");
					String marca = registros.getString("marca");
					String modelo = registros.getString("modelo");
					double valor = registros.getDouble("valor");
					int idBicicleta = registros.getInt("idBicicleta");
					pst.close();
					Conexao.fecharCnx();
					return new Bicicleta(cliente, numeroDeSerieBanco, 
							ano, marca, modelo, valor, idBicicleta);
				}
				pst.close();
				Conexao.fecharCnx();
				return null;
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
			Conexao.fecharCnx();
		}
		return null;
	}


	@Override
	public Bicicleta buscarPorNumeroDeSerie(String numeroDeSerie, int idCliente) {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(SQL_CONSULTAR_BICICLETACLIENTE);
			pst.setString(1, numeroDeSerie);
			pst.setInt(2, idCliente);

			try (ResultSet registros = pst.executeQuery()) {						
				while(registros.next()) {
					int idClienteBanco = registros.getInt("idcliente");
					Cliente cliente = buscarClienteID(idClienteBanco);
					int ano = registros.getInt("ano");
					String numeroDeSerieBanco = registros.getString("NUMERODESERIE");
					
					String marca = registros.getString("marca");
					String modelo = registros.getString("modelo");
					double valor = registros.getDouble("valor");
					int idBicicleta = registros.getInt("idBicicleta");
					pst.close();
					Conexao.fecharCnx();
					return new Bicicleta(cliente, numeroDeSerieBanco, 
							ano, marca, modelo, valor, idBicicleta);
				}
				pst.close();
				Conexao.fecharCnx();
				return null;
			} catch (SQLException e) {
				System.out.println("Erro ao executar o Statment " + e.toString());
				Conexao.fecharCnx();
			}			
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
			Conexao.fecharCnx();
		}
		return null;
	}

	@Override
	public void salvar(Bicicleta novaBicicletas) {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(SQL_INSERIR_BICICLETA);
			pst.setInt(1, novaBicicletas.getCliente().getIdCliente());
			pst.setString(2, novaBicicletas.getNumeroDeSerie());
			pst.setInt(3, novaBicicletas.getAno());
			pst.setString(4, novaBicicletas.getMarca());
			pst.setString(5, novaBicicletas.getModelo());
			pst.setDouble(6, novaBicicletas.getValor());
			pst.setInt(7, novaBicicletas.getIdBicicleta());
			
			pst.execute();
			pst.close();
			Conexao.fecharCnx();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
			Conexao.fecharCnx();
		}
	}
		

	@Override
	public List<Bicicleta> listarTodos() {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(SQL_LISTAR_BICICLETA);
			
			List<Bicicleta> lista = new ArrayList<>();
			
			try (ResultSet registros = pst.executeQuery()) {
				while(registros.next()) {
					int idCliente = registros.getInt("idCliente");
					Cliente cliente = buscarClienteID(idCliente);
					Bicicleta bicicleta = new Bicicleta(cliente,
							registros.getString("numeroDeSerie"),
							registros.getInt("ano"),
							registros.getString("marca"),
							registros.getString("modelo"),
							registros.getDouble("valor"),
							registros.getInt("idBicicleta"));
					
					lista.add(bicicleta);
					
				}
				pst.close();
				Conexao.fecharCnx();
				return lista;
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
			Conexao.fecharCnx();
		}
		return null;
	}
	
	public Cliente buscarClienteID(int idCliente) {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(SQL_CONSULTAR_CLIENTEID);
			pst.setInt(1, idCliente);
			
			try (ResultSet registros = pst.executeQuery()) {						
				while(registros.next()) {
					String cpf = registros.getString("cpf");
					String nome = registros.getString("nome");
					String email = registros.getString("email");
					int idClienteBanco = registros.getInt("idCliente");
					pst.close();
					
					return new Cliente(nome, email, new CPF (cpf), idClienteBanco);
				}
				pst.close();
				
				return null;
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
			Conexao.fecharCnx();
		}
		return null;
	}

}
