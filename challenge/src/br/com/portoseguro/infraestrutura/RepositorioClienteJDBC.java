package br.com.portoseguro.infraestrutura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.portoseguro.dominio.CPF;
import br.com.portoseguro.dominio.Cliente;
import br.com.portoseguro.repositorio.RepositorioCliente;
import br.com.portoseguro.conexao.Conexao;

public class RepositorioClienteJDBC implements RepositorioCliente{
	
	private final String SQL_CONSULTAR_CLIENTEULTIMOID= "SELECT MAX(IDCLIENTE) AS ID FROM T_CLIENTE";
	private final String SQL_CONSULTAR_CLIENTE= "SELECT * FROM T_CLIENTE WHERE CPF=?";
	private final String SQL_INSERIR_CLIENTE= "INSERT INTO T_CLIENTE ( IDCLIENTE, NOME, EMAIL, CPF, CADASTROATIVO, DATACADASTRO ) values ( ?, ?, ?, ?, ?, CURRENT_DATE)";
	private final String SQL_LISTAR_CLIENTE= "SELECT * FROM T_CLIENTE";
	private final String SQL_CONSULTAR_CLIENTEID= "SELECT * FROM T_CLIENTE WHERE IDCLIENTE=?";
	
	private PreparedStatement pst = null;
	
	public Cliente buscarPorCPF(String cpf) {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(SQL_CONSULTAR_CLIENTE);
			pst.setString(1, cpf);
			
			try (ResultSet registros = pst.executeQuery()) {						
				while(registros.next()) {
					String cpfBanco = registros.getString("cpf");
					String nome = registros.getString("nome");
					String email = registros.getString("email");
					int idCliente = registros.getInt("idCliente");
					pst.close();
					Conexao.fecharCnx();
					return new Cliente(nome, email, new CPF (cpfBanco), idCliente);
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
	public int buscarUltimoID() {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(SQL_CONSULTAR_CLIENTEULTIMOID);
			
			try (ResultSet registros = pst.executeQuery()) {						
				while(registros.next()) {
					int idCliente = registros.getInt("ID");
					pst.close();
					Conexao.fecharCnx();
					return idCliente;
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
	public void salvar(Cliente novoCliente) {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(SQL_INSERIR_CLIENTE);
			pst.setInt(1, novoCliente.getIdCliente());
			pst.setString(2, novoCliente.getNome());
			pst.setString(3, novoCliente.getEmail());
			pst.setString(4, novoCliente.getCpf().toString());
			pst.setInt(5, Conexao.cBoolToInt(novoCliente.isCadastroAtivo()));
			
			
			System.out.println(novoCliente.getIdCliente());
			System.out.println(novoCliente.getNome());
			System.out.println(novoCliente.getEmail());
			System.out.println(novoCliente.getCpf().toString());
			System.out.println(Conexao.cBoolToInt(novoCliente.isCadastroAtivo()));
			
			
			
			boolean ret = pst.execute();
			pst.close();
			Conexao.fecharCnx();
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
			Conexao.fecharCnx();
		}
	}

	@Override
	public List<Cliente> listarTodos() {
		Connection conn = Conexao.conectar();
		try {
			pst = conn.prepareStatement(SQL_LISTAR_CLIENTE);
			
			List<Cliente> lista = new ArrayList<>();
			
			try (ResultSet registros = pst.executeQuery()) {
				while(registros.next()) {
					Cliente cliente = new Cliente(registros.getString("nome") , 
							registros.getString("email"), new CPF(registros.getString("CPF")), 
							registros.getInt("idCliente"), registros.getDate("dataCadastro"), 
							registros.getBoolean("cadastroAtivo"));
					
					lista.add(cliente);
					
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
	
	
	public Cliente buscarID(int idCliente) {
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
					Conexao.fecharCnx();
					return new Cliente(nome, email, new CPF (cpf), idClienteBanco);
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
}
