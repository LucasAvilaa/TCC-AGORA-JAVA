package controller;

import dao.daoCompra;
import model.TbCompra;
import model.TbCompraProduto;
import model.TbProduto;

public class controlCompra {
	
	public boolean controlCriarCompra() throws Exception {
		daoCompra dao = new daoCompra();
		
		if(dao.criarCompra()) {
			return true;
		}else {
			return false;
		}
	}

	public boolean controlConfirmaCompra(Integer idCompra) throws Exception {
		TbCompra model = new TbCompra();
		model.setIdCompra(idCompra); 
		
		daoCompra dao = new daoCompra();	
		
		if(dao.confirmaCompra(model)) {
			return true;
		}else {
			return false;
		}
	}

	public boolean controlCompraItens(String acao, Integer quantidade, TbProduto idproduto, TbCompra idCompra) throws Exception {
		TbCompraProduto model = new TbCompraProduto();
		model.setQuantidade(quantidade);
		model.setTbProduto(idproduto);
		model.setTbCompra(idCompra);		
		
		daoCompra dao = new daoCompra();		
		
		if(dao.crudCompraItens(acao, model)) {
			return true;
		}else {
			return false;
		}
	}
}
