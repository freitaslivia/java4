package br.com.portoseguro.conexao;

import java.sql.Connection;

public class TesteCnx {

	public static void main(String[] args) {
		
		Connection c = Conexao.conectar();
		
		if(c == null)
		{
			System.out.println("Não Conectou");
		}else {
			System.out.println("Conectou");
		}
	}

}
