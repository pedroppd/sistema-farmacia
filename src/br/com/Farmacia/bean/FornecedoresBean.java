package br.com.Farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.Farmacia.dao.fornecedoresDao;
import br.com.Farmacia.domain.Fornecedores;
import br.com.Farmacia.util.JSFUtil;

@ManagedBean(name="MBfornecedores")
@ViewScoped
public class FornecedoresBean {
	private ArrayList<Fornecedores> itens;
	private ArrayList<Fornecedores> itensFiltrados;
	private Fornecedores fornecedor;

	public Fornecedores getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedores fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public ArrayList<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public ArrayList<Fornecedores> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Fornecedores> itens) {
		this.itens = itens;
	}
	
	@PostConstruct
	public void prepararItens() {
		
		
		try {
			fornecedoresDao dao = new fornecedoresDao();
			itens=dao.listar();
			
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemError("ex.getMessagem()");
			e.printStackTrace();
		}
		
	}
	//Metodo para inserir um novo fornecedor
	public void prepararNovo() {
		 fornecedor = new Fornecedores();
		
	}
	
	public void novo() {
		
		
		try {
			fornecedoresDao dao = new fornecedoresDao();
			dao.inserir(fornecedor);
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
			fornecedoresDao dao = new fornecedoresDao();
			dao.deletar(fornecedor);
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Excluído com Sucesso :)");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemError("ex.getMessage()");
			e.printStackTrace();
		}
	}
	
	
	//Metodo para Editar
	public void editar() {
		try {
			fornecedoresDao dao = new fornecedoresDao();
			dao.update(fornecedor);
			itens = dao.listar();
		
			JSFUtil.adicionarMensagemSucesso("Editado com Sucesso :)");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemError("ex.getMessage()");
			e.printStackTrace();
		}
	}
	
	

}
