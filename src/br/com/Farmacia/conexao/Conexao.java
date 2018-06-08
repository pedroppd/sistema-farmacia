package br.com.Farmacia.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {

	
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/farmacia", "root", "");
		} catch (ClassNotFoundException e) {
			throw new SQLException("ERRO AO SE CONECTAR", e);
		}
	}
	
	public static void closeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt) {
		Conexao.closeConnection(con);
		
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
		Conexao.closeConnection(con, stmt);
		
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
}
