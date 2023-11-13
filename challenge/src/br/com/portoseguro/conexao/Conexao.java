package br.com.portoseguro.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Conexao {
	
	//private static final String driver_class = "org.hsqldb.jdbc.JDBCDriver";
	private static final String driver_class = "oracle.jdbc.driver.OracleDriver";
	private static Connection cnx = null;
	private static String usuario = "rm99892";
	private static String senha = "231004";
	private static final String URL = "jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL";
	//private static String PathBase = "C:\\projetoseclipse\\challenge\\dados";
	//private static final String URL = "jdbc:hsqldb:file:" + PathBase + ";shutdown=true;hsqldb.write_delay=false;";

	public static Connection conectar() {
		
		if(cnx == null) {
			try {
				Class.forName(driver_class);
				
				cnx = DriverManager.getConnection(URL, usuario, senha);
					
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnx;
	}
	
	public static void fecharCnx() {
	    try {
	        cnx.close();
	        cnx = null;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
	}
	
	public static int cBoolToInt(boolean b) {
		return (b?1:0);
	}
	public static boolean cIntToBool(int b) {
		return (b==1);
	}
}
