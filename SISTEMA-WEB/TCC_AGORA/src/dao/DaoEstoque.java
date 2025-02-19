package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import factory.Conexao; 
import model.TbEstoque;
import model.TbProduto;

public class DaoEstoque {

	Conexao con;
	public boolean crudEstoque(String acao,TbEstoque estoque) throws Exception {
		con = new Conexao(); 
		PreparedStatement ps = null;
		
		if(acao.equals("I")) {
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_ESTOQUE I,NULL,?,?,?"); 
			ps.setInt(1, estoque.getTbProduto().getIdProduto());
			ps.setInt(2, estoque.getQuantidade()); 
			ps.setDate(3, new java.sql.Date(estoque.getDataVencimento().getTime())); 
		}		
		else if(acao.equals("A")) { 			
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_ESTOQUE A,?,?,?,?");
			ps.setInt(1, estoque.getIdEstoque());
			ps.setInt(2, estoque.getTbProduto().getIdProduto());
			ps.setInt(3, estoque.getQuantidade()); 
			ps.setDate(4, new java.sql.Date(estoque.getDataVencimento().getTime())); 
		}else if(acao.equals("E")){ 
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_ESTOQUE E,?,NULL,NULL,NULL");  
			ps.setInt(1, estoque.getIdEstoque()); 
		} 
		if(ps.executeUpdate()>0) { 
			ps.close();
			return true;			
		}else {
			return false;
		}
}
	
	public TbEstoque estoquePorId(TbEstoque id) throws Exception {
		TbEstoque estoque = new TbEstoque(); 
		con = new Conexao();
		PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM VW_ESTOQUE WHERE ID_ESTOQUE = ?"); 
		ps.setInt(1, id.getIdEstoque());
		try {
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				estoque = new TbEstoque(); 
				TbProduto produto = new TbProduto();
				
				produto.setIdProduto(rs.getInt("ID_PRODUTO"));
				produto.setNomeProduto(rs.getString("NOME_PRODUTO"));
				produto.setCategoria(rs.getString("CATEGORIA"));
				estoque.setTbProduto(produto);
				System.out.println("QUANTIDADE REAL NO ESTOQUE");
				estoque.setIdEstoque(rs.getInt("ID_ESTOQUE"));
				estoque.setQuantidade(rs.getInt("QUANTIDADE"));
				estoque.setDataEntrada(rs.getTimestamp("DATA_ENTRADA"));
				estoque.setDataVencimento(rs.getTimestamp("DATA_VENCIMENTO")); 
				} 			 
		} catch (Exception e) {
			e.printStackTrace();
		}	
		ps.close();
		return estoque;		 
}
	
	public TbEstoque quantidadePorProduto(TbProduto produto) throws Exception {
		TbEstoque estoque = null; 
		con = new Conexao();
		PreparedStatement ps = con.getConexao().prepareStatement("SELECT QUANTIDADE FROM VW_ESTOQUE WHERE ID_PRODUTO = ?"); 
		ps.setInt(1, produto.getIdProduto());
		try {
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				estoque = new TbEstoque();  
				estoque.setQuantidade(rs.getInt("QUANTIDADE")); 
				} 			 
		} catch (Exception e) {
			e.printStackTrace();
		}	
		ps.close();
		return estoque;		 
}
	
	public List<TbEstoque> listaEstoque() throws Exception {
		List<TbEstoque> listaEstoque = new ArrayList<TbEstoque>(); 
		con = new Conexao();
		PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM VW_ESTOQUE ORDER BY NOME_PRODUTO ASC"); 
		
		try {
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				TbEstoque estoque = new TbEstoque(); 
				TbProduto produto = new TbProduto();
				
				produto.setIdProduto(rs.getInt("ID_PRODUTO"));
				produto.setNomeProduto(rs.getString("NOME_PRODUTO"));
				produto.setCategoria(rs.getString("CATEGORIA"));
				estoque.setTbProduto(produto);
								
				estoque.setIdEstoque(rs.getInt("ID_ESTOQUE"));
				estoque.setQuantidade(rs.getInt("QUANTIDADE"));
				estoque.setDataEntrada(rs.getTimestamp("DATA_ENTRADA"));
				estoque.setDataVencimento(rs.getTimestamp("DATA_VENCIMENTO"));
				
				listaEstoque.add(estoque); 
		} 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}	
		ps.close();
		return listaEstoque;		
	}
	
	public List<SelectItem> lista() throws Exception { 
		List<SelectItem> emEstoque = new ArrayList<SelectItem>();
		con = new Conexao();
		PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM VW_ESTOQUE WHERE VALOR_VENDA IS NOT NULL AND QUANTIDADE > 0 AND VALOR_VENDA > 0 ORDER BY NOME_PRODUTO ASC"); 
		
		try {
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) { 
				emEstoque.add(new SelectItem(rs.getInt("ID_PRODUTO"),rs.getString("NOME_PRODUTO"))); 
		} 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}	
		ps.close();
		return emEstoque;		
	}
	
	
}	