package dao;

import java.sql.PreparedStatement;

import factory.Conexao;
import model.TbComanda;
import model.TbContasReceber;
import model.TbListaProduto;
import model.TbProduto;

public class daoVendas {
	Conexao con;
	public boolean crudVenda(String acao, TbComanda comanda, TbListaProduto lista, TbContasReceber receber,TbProduto produto) throws Exception{
		con = new Conexao();
		String sql = "EXEC PROC_VENDA_CLIENTE '?',?,?,?,?,?,?,?";
		PreparedStatement ps = con.getConexao().prepareStatement(sql);
		ps.setString(1, acao);
		ps.setInt(2, comanda.getIdComanda());
		ps.setInt(3, produto.getIdProduto());
		ps.setInt(3, lista.getQuantidade());
		ps.setString(4, receber.getMetodoPagamento());
		ps.setBigDecimal(5, receber.getDinheiro());
		ps.setBigDecimal(6, receber.getDebito());
		ps.setBigDecimal(7, receber.getCredito());
		
		if(ps.executeUpdate()>0) {
			ps.close();
			return true;
		}else {
			return false;
		}
	}
}  