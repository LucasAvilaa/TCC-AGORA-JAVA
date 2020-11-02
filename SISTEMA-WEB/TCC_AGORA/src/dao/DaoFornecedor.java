package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import factory.Conexao;
import model.TbEndereco;
import model.TbFornecedore;

public class DaoFornecedor {
	
	private TbFornecedore fornecedor;
	private Conexao con;
	
	public DaoFornecedor(){
		super();	
	}	
	
	public boolean crudFornecedor(String acao, TbFornecedore forn) throws Exception {
			con = new Conexao(); 
			PreparedStatement ps = null;
			 
			if(acao.equals("I")) { 
				ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_FORNECEDORES I,NULL,?,?");
				ps.setString(1, forn.getCnpj());
				ps.setString(2, forn.getRazaoSocial());
			}				
			else if(acao.equals("A")) { 			
				ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_FORNECEDORES A,?,?,?");
				ps.setString(1, forn.getIdForn());
				ps.setString(2, forn.getCnpj());
				ps.setString(3, forn.getRazaoSocial());
			}
			else if(acao.equals("E")){ 
				ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_FORNECEDORES E,?,NULL,NULL");  
				ps.setString(1, forn.getIdForn());
			} 
			if(ps.executeUpdate()>0) { 
				ps.close();
				return true;			
			}else {
				return false;
			}
}
		
	public TbFornecedore fornecedorPorId(TbFornecedore fornecedo) {
			fornecedor = new TbFornecedore();
			try {
				con = new Conexao();
				PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_FORNECEDORES WHERE ID_FORN = ?"); 
				ps.setString(1, fornecedo.getIdForn());
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) { 
					fornecedor.setIdForn(rs.getString("ID_FORN"));
					fornecedor.setCnpj(rs.getString("CNPJ"));
					fornecedor.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));							 
				} 	 
			} catch (Exception e) {
				e.printStackTrace();
			}	 
			return fornecedor;
		}
		
	public List<TbFornecedore> listaFornecedor() {
			List<TbFornecedore> listaforn = new ArrayList<TbFornecedore>();	
			try {
				con = new Conexao();
				PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_FORNECEDORES ORDER BY RAZAO_SOCIAL ASC");  
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					TbEndereco endereco = new TbEndereco();
					TbFornecedore forn = new TbFornecedore(); 
					
					endereco.setCidade(rs.getString("CIDADE"));
					endereco.setEstado(rs.getString("ESTADO")); 
					forn.setIdForn(rs.getString("ID_FORN"));
					forn.setCnpj(rs.getString("CNPJ"));
					forn.setRazaoSocial(rs.getString("RAZAO_SOCIAL")); 
								
					listaforn.add(forn);
				}  
			} catch (Exception e) {
				e.printStackTrace();
			}	 
			return listaforn;
		}	
	 
	public List<SelectItem> getListaFornecedores()  { 
		List<SelectItem> fornecedores = new ArrayList<SelectItem>(); 
		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao().prepareStatement("SELECT ID_FORN, RAZAO_SOCIAL FROM TB_FORNECEDORES");  
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) { 
				fornecedores.add(new SelectItem(rs.getString("ID_FORN"),rs.getString("RAZAO_SOCIAL")));
			}  
		} catch (Exception e) {
			e.printStackTrace();
		}	 
		return fornecedores;
	} 
}