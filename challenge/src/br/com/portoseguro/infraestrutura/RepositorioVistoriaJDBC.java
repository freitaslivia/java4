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
import br.com.portoseguro.dominio.Vistoria;
import br.com.portoseguro.repositorio.RepositorioVistoria;

public class RepositorioVistoriaJDBC implements RepositorioVistoria{
	
	private final String SQL_CONSULTAR_VISTORIAULTIMOID= "SELECT MAX(IDVISTORIA) AS ID FROM T_VISTORIA";
	private final String SQL_INSERIR_VISTORIA= "INSERT INTO T_VISTORIA ( renovacao, idCliente, idBicicleta, coleta1, coleta2, coleta3, coleta4, coleta5, idVistoria ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String SQL_LISTAR_VISTORIA= "SELECT * FROM T_VISTORIA";
	private final String SQL_CONSULTAR_VISTORIA_CPF= "SELECT * FROM T_VISTORIA WHERE idCliente in (select idCliente from t_cliente where CPF=?)";
	private final String SQL_CONSULTAR_CLIENTEID= "SELECT * FROM T_CLIENTE WHERE IDCLIENTE=?";
	private final String SQL_CONSULTAR_BICICLETAID= "SELECT * FROM T_BICICLETA WHERE IDBICICLETA=?";
	//boolean renovacao, String coleta1,
	//String coleta2, String coleta3, String coleta4, 
	//String coleta5, String cpf, String numeroDeSerie
	
	private PreparedStatement pst = null;
	
	@Override
	public int buscarUltimoID() {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(SQL_CONSULTAR_VISTORIAULTIMOID);
			
			try (ResultSet registros = pst.executeQuery()) {						
				while(registros.next()) {
					int idVistoria = registros.getInt("ID");
					pst.close();
					Conexao.fecharCnx();
					return idVistoria;
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

	//cadastrar
	//boolean renovacao, String coleta1,
	//String coleta2, String coleta3, String coleta4, 
	//String coleta5, String cpf, String numeroDeSerie
	
	//construtor
	//boolean renovacao, Cliente cliente, Bicicleta bicicleta, String coleta1,
	//String coleta2, String coleta3, String coleta4, String coleta5, int idVistoria
	@Override
	public void salvar(Vistoria novaVistoria) {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(SQL_INSERIR_VISTORIA);
			pst.setBoolean(1, novaVistoria.getRenovacao());
			pst.setInt(2, novaVistoria.getCliente().getIdCliente());
			pst.setInt(3, novaVistoria.getBicicleta().getIdBicicleta());
			pst.setString(4, novaVistoria.getColeta1());
			pst.setString(5, novaVistoria.getColeta2());
			pst.setString(6, novaVistoria.getColeta3());
			pst.setString(7, novaVistoria.getColeta4());
			pst.setString(8, novaVistoria.getColeta5());
			pst.setInt(9, novaVistoria.getIdVistoria());
			
			pst.execute();
			pst.close();
			Conexao.fecharCnx();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
			Conexao.fecharCnx();
		}
	}
		

	@Override
	public List<Vistoria> listarTodos() {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(SQL_LISTAR_VISTORIA);
			
			List<Vistoria> lista = new ArrayList<>();
			
			try (ResultSet registros = pst.executeQuery()) {
				while(registros.next()) {
					int idCliente = registros.getInt("idCliente");
					Cliente cliente = buscarClienteID(idCliente);
					int idBicicleta = registros.getInt("idBicicleta");
					Bicicleta bicicleta = buscarBicicletaID(idBicicleta);
					Vistoria vistoria = new Vistoria(
							Conexao.cIntToBool(registros.getInt("renovacao")),
							cliente,
							bicicleta,
							registros.getString("coleta1"),
							registros.getString("coleta2"),
							registros.getString("coleta3"),
							registros.getString("coleta4"),
							registros.getString("coleta5"),
							registros.getInt("idVistoria"));
					
					lista.add(vistoria);
					
				}
				pst.close();
				Conexao.fecharCnx();
				return lista;
			}
			catch (SQLException e) {
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
	public List<Vistoria> buscarPorCPF(String cpf) {
		Connection conn = Conexao.conectar();
		List<Vistoria> lista = new ArrayList<>();
		try {
			pst = conn.prepareStatement(SQL_CONSULTAR_VISTORIA_CPF);
			pst.setString(1, cpf);
			
			try (ResultSet registros = pst.executeQuery()) {						
				while(registros.next()) {
					int idCliente = registros.getInt("idCliente");
					Cliente cliente = buscarClienteID(idCliente);
					int idBicicleta = registros.getInt("idBicicleta");
					Bicicleta bicicleta = buscarBicicletaID(idBicicleta);
					Vistoria vistoria = new Vistoria(
							Conexao.cIntToBool(registros.getInt("renovacao")),
							cliente,
							bicicleta,
							registros.getString("coleta1"),
							registros.getString("coleta2"),
							registros.getString("coleta3"),
							registros.getString("coleta4"),
							registros.getString("coleta5"),
							registros.getInt("idVistoria"));
					
					lista.add(vistoria);
					
				}
				pst.close();
				Conexao.fecharCnx();
				return lista;
			}
			catch (SQLException e) {
				System.out.println("Erro ao executar o Statment " + e.toString());
				Conexao.fecharCnx();
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
	
	public Bicicleta buscarBicicletaID(int idBicicleta) {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(SQL_CONSULTAR_BICICLETAID);
			pst.setInt(1, idBicicleta);
			
			try (ResultSet registros = pst.executeQuery()) {						
				while(registros.next()) {
					
					int idCliente = registros.getInt("idCliente");
					Cliente cliente = buscarClienteID(idCliente);
					String numeroDeSerie = registros.getString("numeroDeSerie");
					int ano = registros.getInt("ano");
					String marca = registros.getString("marca");
					String modelo = registros.getString("modelo");
					double valor = registros.getDouble("valor");
					int idBicicletaBanco = registros.getInt("idBicicleta");
			
					pst.close();
					
					return new Bicicleta(cliente, numeroDeSerie, 
							ano, marca, modelo, valor, idBicicletaBanco);
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
