package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import factory.Conexao;
import model.TbLogin;

public class daoLogin {
	Conexao con; 
 	 
	public boolean crudLogin(String acao, TbLogin model) throws Exception {
		con = new Conexao();
		
		String SQL = "EXEC PROC_CRUD_LOGIN ?,?,?";
		PreparedStatement ps = con.getConexao().prepareStatement(SQL);
		ps.setString(1, acao);
		ps.setString(2, model.getUsuario());
		ps.setString(3, model.getSenha());		
		
		if(ps.executeUpdate()>0) {		
			ps.close();
			return true;		
		}else {
				return false;
			  }
	}
	
	public TbLogin validaLogin(TbLogin model) throws Exception{
		con = new Conexao();  
		String sql = "SELECT SENHA FROM TB_LOGIN WHERE USUARIO = ?";
		PreparedStatement ps  = con.getConexao().prepareStatement(sql);
		ps.setString(1, model.getUsuario());
		ResultSet rs =  ps.executeQuery();
		
		while(rs.next()) { 
			model.setSenha(rs.getString("SENHA"));		
		}
		ps.close();
		return model;
	}
}	
