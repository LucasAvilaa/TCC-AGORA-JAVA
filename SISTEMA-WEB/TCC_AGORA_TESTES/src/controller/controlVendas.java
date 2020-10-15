package controller;

import java.math.BigDecimal;
import dao.daoVendas;
import model.TbComanda;
import model.TbContasReceber;
import model.TbListaProduto;
import model.TbProduto;

public class controlVendas {

		public boolean controlVenda(String acao, Integer idComanda, Integer quantidade, BigDecimal credito,BigDecimal debito, BigDecimal dinheiro,String metodoPagamento,Integer idProduto) throws Exception{
			TbListaProduto lista = new TbListaProduto();
			lista.setQuantidade(quantidade);
			
			TbProduto produto = new TbProduto();
			produto.setIdProduto(idProduto);
			
			TbContasReceber contas = new TbContasReceber();
			contas.setCredito(credito);
			contas.setDebito(debito);
			contas.setMetodoPagamento(metodoPagamento);
			contas.setDinheiro(dinheiro);
			
			TbComanda comanda = new TbComanda();
			comanda.setIdComanda(idComanda);
			
			daoVendas dao = new daoVendas();
						
			if(dao.crudVenda(acao, comanda, lista, contas, produto)) {
				return true;
			}else {
				return false;
			}
	  }
}
