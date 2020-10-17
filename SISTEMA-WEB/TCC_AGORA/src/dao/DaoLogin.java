package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import factory.Conexao;
import model.TbLogin;

public class DaoLogin {
	Conexao con; 
 	 
	public boolean crudLogin(String acao, TbLogin log) throws Exception {
		con = new Conexao();
		PreparedStatement ps = null;
		
		if(acao.equals("I")) {
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_LOGIN I,NULL,?,?");
			ps.setString(1, log.getUsuario());
			ps.setString(2, log.getSenha());		
			
		}				
		else if(acao.equals("A")) { 			
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_LOGIN A,?,?,?");
			ps.setInt(1, log.getIdLogin());
			ps.setString(2, log.getUsuario());
			ps.setString(3, log.getSenha());		
			
		}
		else if(acao.equals("E")){ 
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_LOGIN E,?,NULL,NULL");
			ps.setString(1, acao); 	
		} 
		
		if(ps.executeUpdate()>0) {		
			ps.close();
			return true;		
		}else {
				return false;
			  }
	}
	
	public boolean validaLogin(TbLogin log) throws Exception{
		con = new Conexao();  
		TbLogin senha = null;
		PreparedStatement ps  = con.getConexao().prepareStatement("SELECT SENHA FROM TB_LOGIN WHERE USUARIO = ?");
		ps.setString(1, log.getUsuario());
		ResultSet rs =  ps.executeQuery();
		
		while(rs.next()) { 
			senha = new TbLogin();
			senha.setSenha(rs.getString("SENHA"));		
		}
		ps.close();
		if(log.getSenha().equals(senha.getSenha())) {
			return true;
		}
		else {
			return false;
		}
	}
}	
