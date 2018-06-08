package br.com.Farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.Farmacia.conexao.Conexao;
import br.com.Farmacia.domain.Fornecedores;

public class fornecedoresDao {
	// Inserir
	public void inserir(Fornecedores f) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("INSERT INTO fornecedor (descricao_fornecedor)VALUES(?)");
			stmt.setString(1, f.getDescricao());

			stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException("ERRO AO INSERIR :(", ex);
		} finally {
			Conexao.closeConnection(con, stmt);
		}
	}

	// Atualizar
	public void update(Fornecedores f) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("UPDATE fornecedor SET descricao_fornecedor=? WHERE idfornecedor=?");
			stmt.setString(1, f.getDescricao());
			stmt.setInt(2, f.getId());

			stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException("ERRO AO ATUALIZAR! :(", ex);
		} finally {
			Conexao.closeConnection(con, stmt);
		}
	}

	// excluir
	public void deletar(Fornecedores f) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("DELETE FROM fornecedor WHERE idfornecedor=?");
			stmt.setInt(1, f.getId());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException("ERRO AO EXCLUIR :(", ex);
		} finally {
			Conexao.closeConnection(con, stmt);
		}
	}

	// Buscar
	public Fornecedores buscaPorCodigo(Fornecedores f) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		stmt = con.prepareStatement("SELECT idfornecedor, descricao_fornecedor FROM fornecedor WHERE idfornecedor=?");
		stmt.setInt(1, f.getId());

		rs = stmt.executeQuery();

		Fornecedores retorno = null;

		if (rs.next()) {
			retorno = new Fornecedores();
			retorno.setId(rs.getInt("idfornecedor"));
			retorno.setDescricao(rs.getString("descricao_fornecedor"));
		}
		return retorno;

	}

	// listar
	public ArrayList<Fornecedores> listar() throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		stmt = con.prepareStatement("SELECT idfornecedor, descricao_fornecedor FROM fornecedor ORDER BY descricao_fornecedor ASC");
				

		rs = stmt.executeQuery();

		ArrayList<Fornecedores> list = new ArrayList<Fornecedores>();

		while (rs.next()) {
			Fornecedores f = new Fornecedores();
			f.setId(rs.getInt("idfornecedor"));
			f.setDescricao(rs.getString("descricao_fornecedor"));

			list.add(f);
		}

		return list;

	}

	// Buscar pela descrição
	public ArrayList<Fornecedores> buscaDesc(Fornecedores f) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		stmt = con.prepareStatement("SELECT * FROM fornecedor WHERE descricao_fornecedor LIKE ?");
		stmt.setString(1, "%"+f.getDescricao()+"%");
		rs = stmt.executeQuery();
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		
		if (rs.next()) {
			Fornecedores f1 = new Fornecedores();
			f1.setId(rs.getInt("idfornecedor"));
			f1.setDescricao(rs.getString("descricao_fornecedor"));
			lista.add(f1);
		}

		return lista;
	}

	


}
