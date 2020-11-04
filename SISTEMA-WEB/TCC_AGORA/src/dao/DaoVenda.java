package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import model.TbComanda;
import model.TbContasReceber;
import model.TbListaProduto;
import model.TbProduto;

public class DaoVenda {

	Conexao con;	
	public boolean crudVenda(String acao,TbComanda comanda, TbListaProduto lista, TbContasReceber receber,TbProduto produto) throws Exception {
		con = new Conexao(); 
		PreparedStatement ps = null; 
		
		if(acao.equals("I")) {
			ps = con.getConexao().prepareStatement("EXEC PROC_VENDA_ESTAB I,?,?,?,NULL,NULL,NULL,NULL"); 
			ps.setInt(1, comanda.getIdComanda());
			ps.setInt(2, produto.getIdProduto());			
			ps.setInt(3, lista.getQuantidade());	 
		}	
		
		else if(acao.equals("A")) {
			ps = con.getConexao().prepareStatement("EXEC PROC_VENDA_ESTAB A,?,?,?,NULL,NULL,NULL,NULL");
			ps.setInt(1, comanda.getIdComanda());
			ps.setInt(2, produto.getIdProduto());			
			ps.setInt(3, lista.getQuantidade());		 
		}	
		
		else if(acao.equals("E")) { 			
			ps = con.getConexao().prepareStatement("EXEC PROC_VENDA_ESTAB E,?,?,NULL,NULL,NULL,NULL,NULL");
			ps.setInt(1, comanda.getIdComanda());
			ps.setInt(2, produto.getIdProduto());
			
		}else if(acao.equals("P")){ 
			ps = con.getConexao().prepareStatement("EXEC PROC_VENDA_ESTAB P,?,NULL,NULL,?,?,?,?");  
			ps.setInt(1, comanda.getIdComanda()); 
			ps.setString(2, receber.getMetodoPagamento());
			ps.setBigDecimal(3, receber.getDinheiro());
			ps.setBigDecimal(4, receber.getDebito());
			ps.setBigDecimal(5, receber.getCredito());
		} 
		if(ps.executeUpdate()>0) { 
			ps.close();
			return true;			
		}else {
			return false;
		}
}

	public List<TbListaProduto> listaProdutoPorComanda(TbComanda comanda) {
		List<TbListaProduto> listaProduto = new ArrayList<TbListaProduto>(); 
			try {
				con = new Conexao();
				PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM VW_VENDA WHERE ID_COMANDA_LISTA = ?"); 
				ps.setInt(1, comanda.getIdComanda());
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) { 
					TbListaProduto lista = new TbListaProduto();
					TbComanda comand = new TbComanda();
					TbProduto produto = new TbProduto();
					lista.setDataCompra(rs.getDate("DATA_COMPRA"));
					lista.setDataSaida(rs.getDate("DATA_SAIDA"));
					lista.setQuantidade(rs.getInt("QUANTIDADE"));
					lista.setIdCompra(rs.getInt("ID_COMPRA")); 
					lista.setSubtotal(rs.getBigDecimal("SUBTOTAL"));
					comand.setStatusComanda(rs.getString("STATUS_COMANDA"));
					comand.setIdComanda(rs.getInt("ID_COMANDA_LISTA"));
					produto.setNomeProduto(rs.getString("NOME_PRODUTO"));
					produto.setValorUniVenda(rs.getBigDecimal("VALOR_UNI_VENDA"));
					produto.setIdProduto(rs.getInt("ID_PROD_LISTA"));
					lista.setTbProduto(produto);
					lista.setTbComanda(comand);		
					listaProduto.add(lista);
				} 	 
			} catch (Exception e) {
				e.printStackTrace();
			}	 
			return listaProduto;
		}
	  
	public TbListaProduto produtoPorId(TbComanda comanda, TbProduto idProduto) {
		 
		TbListaProduto lista = new TbListaProduto();
		TbComanda comand = new TbComanda();
		TbProduto produto = new TbProduto();
		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM VW_VENDA WHERE ID_COMANDA_LISTA = ? AND ID_PROD_LISTA = ?"); 
			ps.setInt(1, comanda.getIdComanda());
			ps.setInt(2,idProduto.getIdProduto());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) { 
				lista.setDataCompra(rs.getDate("DATA_COMPRA"));
				lista.setDataSaida(rs.getDate("DATA_SAIDA"));
				lista.setQuantidade(rs.getInt("QUANTIDADE"));
				lista.setIdCompra(rs.getInt("ID_COMPRA")); 
				lista.setSubtotal(rs.getBigDecimal("SUBTOTAL"));
				comand.setStatusComanda(rs.getString("STATUS_COMANDA"));
				comand.setIdComanda(rs.getInt("ID_COMANDA_LISTA"));
				produto.setNomeProduto(rs.getString("NOME_PRODUTO"));
				produto.setValorUniVenda(rs.getBigDecimal("VALOR_UNI_VENDA"));
				produto.setIdProduto(rs.getInt("ID_PROD_LISTA"));
				lista.setTbProduto(produto);
				lista.setTbComanda(comand);		 
			} 	 
		} catch (Exception e) {
			e.printStackTrace();
		}	 
		return lista;
	}
	
 public TbListaProduto dataAberta(TbComanda comanda) {
		TbListaProduto listaProduto = new TbListaProduto(); 
		try {
				con = new Conexao();
				PreparedStatement ps = con.getConexao().prepareStatement("SELECT DATA_COMPRA FROM VW_VENDA WHERE ID_COMANDA_LISTA = ? AND DATA_COMPRA IS NOT NULL"); 
				ps.setInt(1, comanda.getIdComanda());
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {   
					listaProduto.setDataCompra(rs.getDate("DATA_COMPRA"));  
				} 	 
		} catch (Exception e) {
			e.printStackTrace();
		}	 
		System.out.println("DATA ENCONTRADA: " + listaProduto.getDataCompra());
		return listaProduto;
	}
 	
	public TbListaProduto valorTotal(TbComanda comanda) {
		TbListaProduto listaItens = new TbListaProduto();

		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao()
					.prepareStatement("SELECT SUM(SUBTOTAL) AS TOTAL FROM VW_VENDA WHERE ID_COMANDA_LISTA = ?");
			ps.setInt(1, comanda.getIdComanda()); 
			ResultSet rs = ps.executeQuery();

			while (rs.next()) { 
				listaItens.setTotal(rs.getBigDecimal("TOTAL"));   
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaItens;
	}
	
	public TbComanda status(TbComanda comanda) {
		TbComanda status = new TbComanda();

		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao()
					.prepareStatement("SELECT STATUS_COMANDA FROM TB_COMANDA WHERE ID_COMANDA = ?");
			ps.setInt(1, comanda.getIdComanda()); 
			ResultSet rs = ps.executeQuery();

			while (rs.next()) { 
				if(rs.getString("STATUS_COMANDA").equals("A")) {
					status.setStatusComanda("ABERTA");   
				}
				else if(rs.getString("STATUS_COMANDA").equals("D")) {
					status.setStatusComanda("FECHADA");   
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}	