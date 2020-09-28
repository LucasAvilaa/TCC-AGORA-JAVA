package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import factory.Conexao; 
import model.TbEstoque;
import model.TbProduto;

public class DaoEstoque {

	Conexao con;
	public boolean crudEstoque(String acao,TbEstoque estoque) throws Exception {
		con = new Conexao(); 
		PreparedStatement ps = null;
		
		if(acao.equals("I")) {
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_ESTOQUE I,NULL,?,?,?,?"); 
			ps.setInt(1, estoque.getTbProduto().getIdProduto() );
			ps.setInt(2, estoque.getQuantidade());
			ps.setDate(3,new java.sql.Date(estoque.getDataEntrada().getTime()));
			ps.setDate(4, new java.sql.Date(estoque.getDataVencimento().getTime())); 
		}		
		else if(acao.equals("A")) { 			
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_ESTOQUE A,?,?,?,?,?");
			ps.setInt(1, estoque.getIdEstoque());
			ps.setInt(2, estoque.getTbProduto().getIdProduto() );
			ps.setInt(3, estoque.getQuantidade());
			ps.setDate(4,new java.sql.Date(estoque.getDataEntrada().getTime()));
			ps.setDate(5, new java.sql.Date(estoque.getDataVencimento().getTime())); 
		}else if(acao.equals("E")){ 
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_ESTOQUE E,?,NULL,NULL,NULL,NULL");  
			ps.setInt(1, estoque.getIdEstoque()); 
		} 
		if(ps.executeUpdate()>0) { 
			ps.close();
			return true;			
		}else {
			return false;
		}
}
	
	public TbEstoque estoquePorId(TbEstoque id) {
		TbEstoque cliente = new TbEstoque();
		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_ESTOQUE WHERE ID_ESTOQUE = ?"); 
			ps.setInt(1, id.getIdEstoque());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) { 
				 							 
			} 	
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	 
		return cliente;
}
	
	public List<TbEstoque> listaEstoque() {
		List<TbEstoque> listaEstoque = new ArrayList<TbEstoque>();	
		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_ESTOQUE"); 
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				TbEstoque estoque = new TbEstoque(); 
				TbProduto produto = new TbProduto();
				
				estoque.setIdEstoque(rs.getInt("ID_ESTOQUE"));
				estoque.setQuantidade(rs.getInt("QUANTIDADE"));
				estoque.setDataEntrada(rs.getDate("DATA_ENTRADA"));
				estoque.setDataVencimento(rs.getDate("DATA_VENCIMENTO"));
				produto.setIdProduto(rs.getInt("ID_PROD_ESTOQ"));				
		} 
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	 
		return listaEstoque;
	}
}	