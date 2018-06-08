package br.com.Farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.Farmacia.conexao.Conexao;
import br.com.Farmacia.domain.Fornecedores;
import br.com.Farmacia.domain.Produto;

public class produtoDao {
    //Inserir produto
	public void inserir(Produto p) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("INSERT INTO produto (descricao_produto, quantidade_produto, preco_produto, fornecedor_idfornecedor)VALUES(?,?,?,?)");
			stmt.setString(1, p.getDescricao());
			stmt.setInt(2, p.getQtd());
			stmt.setDouble(3, p.getPreco());
			stmt.setInt(4, p.getFornecedores().getId());

			stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException("ERRO AO INSERIR :(", ex);
		} finally {
			Conexao.closeConnection(con, stmt);
		}
	}
	
	
	
	//Atualizar produto
	public void update(Produto p) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("UPDATE produto SET descricao_produto=?, quantidade_produto=?, preco_produto=?, fornecedor_idfornecedor=? WHERE idproduto=?");
			stmt.setString(1, p.getDescricao());
			stmt.setInt(2, p.getQtd());
			stmt.setDouble(3, p.getPreco());
			stmt.setInt(4, p.getFornecedores().getId());
			stmt.setInt(5, p.getId());

			stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException("ERRO AO ATUALIZAR! :(", ex);
		} finally {
			Conexao.closeConnection(con, stmt);
		}
	}
	
	//excluir produto
	
	public void excluir(Produto p) throws SQLException {
		Connection con = Conexao.getConnection();
		PreparedStatement stmt= null;
	try {
		stmt = con.prepareStatement("DELETE FROM produto WHERE idproduto=?");
		stmt.setInt(1, p.getId());
		
		stmt.executeUpdate();
	}catch(SQLException ex) {
		throw new SQLException("ERRO AO DELETAR :(");
	}finally {
		Conexao.closeConnection(con, stmt);
	}
	}
	
	// Buscar
		public Produto buscaPorCodigo(Produto p) throws SQLException {
			Connection con = Conexao.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;

			stmt = con.prepareStatement("SELECT * FROM produto WHERE idproduto=?");
			stmt.setInt(1, p.getId());

			rs = stmt.executeQuery();

			Produto retorno = null;

			if (rs.next()) {
				retorno = new Produto();
				retorno.setId(rs.getInt("idproduto"));
				retorno.setDescricao(rs.getString("descricao_produto"));
				retorno.setQtd(rs.getInt("quantidade_produto"));
				retorno.setPreco(rs.getDouble("preco_produto"));
				
				
			}
			return retorno;

		}
		
		// listar
		public ArrayList<Produto> listar() throws SQLException {
			Connection con = Conexao.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs = null;

			stmt = con.prepareStatement("SELECT p.idproduto, p.descricao_produto, p.quantidade_produto, p.preco_produto, f.idfornecedor, f.descricao_fornecedor FROM produto p INNER JOIN fornecedor f ON f.idfornecedor=p.fornecedor_idfornecedor");
					

			rs = stmt.executeQuery();

			ArrayList<Produto> list = new ArrayList<Produto>();
          
			while (rs.next()) {
				Fornecedores f = new Fornecedores();
				
				f.setId(rs.getInt("f.idfornecedor"));
				f.setDescricao(rs.getString("f.descricao_fornecedor"));
				
				Produto p = new Produto();
				
				p.setId(rs.getInt("p.idproduto"));
			    p.setDescricao(rs.getString("p.descricao_produto"));
			    p.setQtd(rs.getInt("p.quantidade_produto"));
			    p.setPreco(rs.getDouble("p.preco_produto"));
			   
		        p.setFornecedores(f);
			    

				list.add(p);
			}

			return list;

		}
		
		
		
		
	
	
	
	//Metodo principal
	public static void main(String[] args) throws SQLException {
		
	try {	
		
	
		produtoDao dao = new produtoDao();
		ArrayList<Produto> lista=dao.listar();
		
		for(Produto p: lista) {
			System.out.println("CODIGO produto:"+p.getId());
			System.out.println("Descricao Produto"+p.getDescricao());
		    System.out.println("QTD"+p.getQtd());
		    System.out.println("PRECO:"+p.getPreco());
		    System.out.println("IDFORN"+p.getFornecedores().getId());
		    System.out.println("DESCRICAOFORN"+p.getFornecedores().getDescricao());
		    System.out.println("   ");
		}
		
		
	}catch(SQLException ex) {
		throw new SQLException("ERRO", ex);
	}
	
}
}
