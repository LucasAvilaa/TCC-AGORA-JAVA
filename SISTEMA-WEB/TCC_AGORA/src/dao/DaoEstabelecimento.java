package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import model.TbEstabelecimento;

public class DaoEstabelecimento {
	Conexao con;
	public boolean crudEstabelecimento(String acao, TbEstabelecimento estab) throws Exception {
		con = new Conexao(); 
		PreparedStatement ps = null;
		 
		if(acao.equals("I")) { 
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_ESTABELECIMENTOS I,NULL,?,?");
			ps.setString(1, estab.getCnpj());
			ps.setString(2, estab.getRazaoSocial()); 
		}				
		else if(acao.equals("A")) { 			 
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_ESTABELECIMENTOS A,?,?,?");
			ps.setString(1, estab.getIdEstab());
			ps.setString(2, estab.getCnpj());
			ps.setString(3, estab.getRazaoSocial()); 
		}
		else if(acao.equals("E")){ 
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_ESTABELECIMENTOS E,?,NULL,NULL");   
			ps.setString(1, estab.getIdEstab()); 
		} 
		if(ps.executeUpdate()>0) { 
			ps.close();
			return true;			
		}else {
			return false;
		}
}
	
	public TbEstabelecimento estabelecimentoPorId(TbEstabelecimento id) {
			TbEstabelecimento estab = new TbEstabelecimento();
			try {
				con = new Conexao();
				PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_ESTABELECIMENTOS WHERE ID_ESTAB = ?"); 
				ps.setString(1, id.getIdEstab());
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) { 
					estab.setIdEstab(rs.getString("ID_ESTAB"));
					estab.setCnpj(rs.getString("CNPJ"));
					estab.setRazaoSocial(rs.getString("RAZAO_SOCIAL")); 							 
				} 			
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}	 
			return estab;
		}
	
	public List<TbEstabelecimento> listaEstabelecimento() {
		List<TbEstabelecimento> listaEstab = new ArrayList<TbEstabelecimento>();	
		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_ESTABELECIMENTOS ORDER BY RAZAO_SOCIAL ASC"); 
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				TbEstabelecimento estab = new TbEstabelecimento(); 				
				estab.setIdEstab(rs.getString("ID_ESTAB"));
				estab.setCnpj(rs.getString("CNPJ"));
				estab.setRazaoSocial(rs.getString("RAZAO_SOCIAL")); 							
				listaEstab.add(estab);
			} 	
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	 
		return listaEstab;
	}
}