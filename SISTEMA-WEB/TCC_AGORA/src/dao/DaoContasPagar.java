package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import model.TbCompra;
import model.TbContasPagar;

public class DaoContasPagar {

	Conexao con;
	public boolean crudContaPagar(String acao, TbContasPagar pagar, TbCompra compra) throws Exception {
		con = new Conexao(); 
		PreparedStatement ps = null;
		
		if(acao.equals("I")) {
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_CONTAS_PAGAR I,NULL,?,?"); 
			ps.setInt(1, compra.getIdCompra());
			ps.setString(2, pagar.getCategoria());
			ps.setDate(3, new java.sql.Date(pagar.getDataVencimento().getTime()));	
		}		
		else if(acao.equals("A")) { 			
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_CONTAS_PAGAR A,?,?,?");
			ps.setInt(1, pagar.getIdPagar());
			ps.setInt(2, compra.getIdCompra());
			ps.setString(3, pagar.getCategoria());
			ps.setDate(4, new java.sql.Date(pagar.getDataVencimento().getTime()));	
		}else if(acao.equals("E")){ 
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_CONTAS_PAGAR E,?,NULL,NULL");  
			ps.setInt(1, pagar.getIdPagar()); 
		}	
		if(ps.executeUpdate()>0) { 
			ps.close();
			return true;			
		}else {
			return false;
		}
	 }
	
	public TbContasPagar ContaPagarPorId(TbContasPagar pagar) { 
			TbContasPagar contas = null;
			try {
				con = new Conexao();
				PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_CONTAS_PAGAR WHERE ID_PAGAR = ?"); 
				ps.setInt(1, pagar.getIdPagar());
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) { 
					TbCompra compra = new TbCompra(); 
					 contas = new TbContasPagar();
					 compra.setDataCriada(rs.getDate("DATA_CRIADA"));	
					 compra.setIdCompra(rs.getInt("ID_COMPRA"));
					 contas.setDataVencimento(rs.getDate("DATA_VENCIMENTO"));  
					 contas.setIdPagar(rs.getInt("ID_PAGAR"));
					 contas.setCategoria(rs.getString("CATEGORIA"));
					 contas.setTbCompra(compra); 	 
				} 			
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}	 
			return contas;
		}
	
	public List<TbContasPagar> listaContaPagar() {
		List<TbContasPagar> ListaContaPagar = new ArrayList<TbContasPagar>();	 
		
		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM VW_CONTAS_PAGAR"); 
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) { 
				TbCompra compra = new TbCompra(); 
				TbContasPagar contas = new TbContasPagar();
				 compra.setDataCriada(rs.getDate("DATA_CRIADA"));	
				 compra.setIdCompra(rs.getInt("ID_COMPRA"));
				 contas.setDataVencimento(rs.getDate("DATA_VENCIMENTO")); 
				 contas.setIdPagar(rs.getInt("ID_PAGAR"));
				 contas.setCategoria(rs.getString("CATEGORIA"));
				 contas.setTbCompra(compra); 
				 
				 ListaContaPagar.add(contas);
				 
			} 		
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	 
		return ListaContaPagar;
	}
}	