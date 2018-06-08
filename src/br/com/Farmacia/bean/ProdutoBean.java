package br.com.Farmacia.bean;


import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.Farmacia.dao.fornecedoresDao;
import br.com.Farmacia.dao.produtoDao;
import br.com.Farmacia.domain.Fornecedores;
import br.com.Farmacia.domain.Produto;
import br.com.Farmacia.util.JSFUtil;

@ManagedBean(name="MBprodutos")
@ViewScoped
public class ProdutoBean {

	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFiltrados;
	private Produto produto;
	private ArrayList<Fornecedores>comboFornecedores;
	
	

	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}

	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public ArrayList<Produto> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public ArrayList<Produto> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}
	
	@PostConstruct
	public void prepararItens() {
		
		
		try {
			produtoDao dao = new produtoDao();
			itens=dao.listar();
			
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemError("ex.getMessagem()");
			e.printStackTrace();
		}
		
	}
	//Metodo para inserir um novo fornecedor
	public void prepararNovo() {
	
		try {
		    produto = new Produto();
			fornecedoresDao dao = new fornecedoresDao();
			comboFornecedores=dao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemError("ex.getMessage()");
			e.printStackTrace();
		}
		
		
	}
	
	public void novo() {
		
		
		try {
			produtoDao dao = new produtoDao();
			dao.inserir(produto);
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Cadastrado Com Sucesso!!");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemError("ex.getMessage()");
			e.printStackTrace();
		}
	}
	
	
	
	//Metodo para Excluir
	public void excluir() {
		try {
			produtoDao dao = new produtoDao();
			dao.excluir(produto);
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Excluído com Sucesso :)");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemError("ex.getMessage()");
			e.printStackTrace();
		}
	}
	
	public void prepararEditar() {
		
		try {
		    produto = new Produto();
			fornecedoresDao dao = new fornecedoresDao();
			
			comboFornecedores=dao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemError("ex.getMessage()");
			e.printStackTrace();
		}
		
		
	}
	
	
	//Metodo para Editar
	public void editar() {
		try {
			produtoDao dao = new produtoDao();
			dao.update(produto);
			itens = dao.listar();
		
			JSFUtil.adicionarMensagemSucesso("Editado com Sucesso :)");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemError("ex.getMessage()");
			e.printStackTrace();
		}
	}
}
