package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import factory.Conexao;
import model.TbFornecedore;
import model.TbProduto;

public class DaoProduto {
	 
	private Conexao con;
 	
	public boolean crudProduto(String acao,TbProduto prod ) throws Exception {
			con = new Conexao(); 
			PreparedStatement ps = null;			
			
			if(acao.equals("I")) {
				ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_PRODUTOS I,NULL,?,?,?,?,?,?"); 
				ps.setString(1, prod.getTbFornecedore().getIdForn()); 
				ps.setString(2, prod.getNomeProduto());
				ps.setString(3, prod.getDescricaoProduto());
				ps.setString(4, prod.getCategoria());
				ps.setBigDecimal(5, prod.getValorUniCompra());
				ps.setBigDecimal(6,  prod.getValorUniVenda());  
				 
			}				
			else if(acao.equals("A")) { 			
				ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_PRODUTOS A,?,?,?,?,?,?,?");
				ps.setInt(1, prod.getIdProduto());	
				ps.setString(2, prod.getTbFornecedore().getIdForn());							
				ps.setString(3, prod.getNomeProduto());
				ps.setString(4, prod.getDescricaoProduto());
				ps.setString(5, prod.getCategoria());
				ps.setBigDecimal(6, prod.getValorUniCompra());
				ps.setBigDecimal(7,  prod.getValorUniVenda()); 
			//	ps.setBoolean(7, prod.getAtivo());
			}
			else if(acao.equals("E")){ 
				ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_PRODUTOS E,?,NULL,NULL,NULL,NULL,NULL,NULL");  
				ps.setInt(1, prod.getIdProduto());
			} 
			if(ps.executeUpdate()>0) { 
				ps.close();
				return true;			
			}else {
				return false;
			}
}
		
	public TbProduto produtoPorId(TbProduto id) {
				TbProduto produto = new TbProduto();
				TbFornecedore fornecedor = new TbFornecedore();
				try {
					con = new Conexao();
					PreparedStatement ps = con.getConexao().prepareStatement("SELECT *, (SELECT RAZAO_SOCIAL FROM TB_FORNECEDORES"
							+ "												 WHERE ID_FORN = (SELECT ID_FORN_PROD FROM TB_PRODUTOS"
							+ "												 WHERE ID_PRODUTO = ?)) AS RAZAO_SOCIAL  FROM TB_PRODUTOS"
							+ "												 WHERE ID_PRODUTO = ?"); 
					ps.setInt(1, id.getIdProduto());
					ps.setInt(2, id.getIdProduto());
			 
					ResultSet rs = ps.executeQuery();
					
					while (rs.next()) { 
						
						fornecedor.setIdForn(rs.getString("ID_FORN_PROD"));
						fornecedor.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));
						produto.setTbFornecedore(fornecedor);
						
						produto.setIdProduto(rs.getInt("ID_PRODUTO"));
						produto.setNomeProduto(rs.getString("NOME_PRODUTO"));
						produto.setDescricaoProduto(rs.getString("DESCRICAO_PRODUTO"));
						produto.setCategoria(rs.getString("CATEGORIA"));
						produto.setValorUniCompra(rs.getBigDecimal("VALOR_UNI_COMPRA"));
						produto.setValorUniVenda(rs.getBigDecimal("VALOR_UNI_VENDA"));
						produto.setDataCadastro(rs.getDate("DATA_CADASTRO"));
			//			produto.setAtivo(rs.getBoolean("ATIVO"));
					}
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ERRO AO BUSCAR PRODUTO POR ID ");
				}	 
				
				return produto;
			}
		
	public List<TbProduto> listaProdutos() {
			List<TbProduto> listaprod = new ArrayList<TbProduto>();	
			
			try {
				con = new Conexao();
				PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_PRODUTOS"); 
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) { 
					TbProduto produto = new TbProduto();
					TbFornecedore fornecedor = new TbFornecedore();
					
					fornecedor.setIdForn(rs.getString("ID_FORN_PROD"));
					produto.setTbFornecedore(fornecedor);
					
					produto.setIdProduto(rs.getInt("ID_PRODUTO"));
					produto.setNomeProduto(rs.getString("NOME_PRODUTO"));
					produto.setDescricaoProduto(rs.getString("DESCRICAO_PRODUTO"));
					produto.setCategoria(rs.getString("CATEGORIA"));
					produto.setValorUniCompra(rs.getBigDecimal("VALOR_UNI_COMPRA"));
					produto.setValorUniVenda(rs.getBigDecimal("VALOR_UNI_VENDA"));
					produto.setDataCadastro(rs.getDate("DATA_CADASTRO"));	
		//			produto.setAtivo(rs.getBoolean("ATIVO"));	
					listaprod.add(produto);
				} 	
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ERRO AO BUSCAR A LISTA PRODUTO ");
			}	 
			return listaprod;
		}	
	
	public List<SelectItem> lista(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		
		try {
			con = new Conexao();
			PreparedStatement ps =  con.getConexao().prepareStatement("SELECT ID_PRODUTO, NOME_PRODUTO FROM TB_PRODUTOS");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next() ) {
				lista.add(new SelectItem(rs.getInt("ID_PRODUTO"),rs.getString("NOME_PRODUTO")));
			} 
		} catch (Exception e) {			 
			e.printStackTrace();
		} 
		return lista; 
	} 
}