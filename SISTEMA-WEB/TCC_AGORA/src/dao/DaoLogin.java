package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import factory.Conexao;
import model.TbFuncionario;
import model.TbLogin;

public class DaoLogin {
	Conexao con; 
 	 
	public boolean crudLogin(String acao, TbLogin log) throws Exception {
		con = new Conexao();
		PreparedStatement ps = null;
		
		if(acao.equals("I")) {
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_LOGIN I,?,?");
			ps.setString(1, log.getUsuario());
			ps.setString(2, log.getSenha());		
			
		}				
		else if(acao.equals("A")) { 			
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_LOGIN A,?,?"); 
			System.out.println("SENHA QUE CHEGOU NO DAO: " + log.getSenha());
			ps.setString(1, log.getUsuario());
			ps.setString(2, log.getSenha());		
			
		}
		else if(acao.equals("E")){ 
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_LOGIN E,?,NULL");
			ps.setString(1, log.getUsuario());	
		} 
		
		if(ps.executeUpdate()>0) {		
			ps.close();
			return true;		
		}else {
				return false;
			  }
	}
	public boolean validaUsuario(TbLogin login) { 
		try {
			con = new Conexao();  
			TbLogin usuario = new TbLogin(); 	
			PreparedStatement ps = con.getConexao().prepareStatement("SELECT USUARIO FROM TB_LOGIN WHERE USUARIO = ?");
			ps.setString(1, login.getUsuario());
			ResultSet rs =  ps.executeQuery();
			
			while(rs.next()) {
				usuario.setUsuario(rs.getString("USUARIO"));
				System.out.println("USUARIO SELECIONADO NO BANCO: " + rs.getString("USUARIO").toString());
			} 
			return true;
			
		} catch (Exception e) {   
			e.printStackTrace();
			return false;
		}  
	} 
	
	public boolean validaLogin(TbLogin log) throws Exception{
		con = new Conexao();  
		TbLogin senha = new TbLogin();
		PreparedStatement ps  = con.getConexao().prepareStatement("SELECT SENHA FROM TB_LOGIN WHERE USUARIO = ?");
		ps.setString(1, log.getUsuario());
		ResultSet rs =  ps.executeQuery();
		
		while(rs.next()) {   
			senha.setSenha(rs.getString("SENHA"));	 
		}
		ps.close();
		if(senha.getSenha() == null) { 
			return false;
		}else {
			if(log.getSenha().equals(senha.getSenha())) {
				return true; 
			} 
			else {
				return false;
			} 
		} 
	}
	
	public TbLogin loginFuncionario(TbFuncionario funcionario) {
		TbLogin login = null;
		try {
			con = new Conexao();
			String url = "SELECT * FROM TB_LOGIN WHERE ID_LOGIN = (SELECT ID_LOGIN FROM TB_FUNCIONARIOS WHERE ID_FUNC = ?)";
			PreparedStatement ps = con.getConexao().prepareStatement(url);
			ps.setString(1, funcionario.getIdFunc());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				login = new TbLogin();
				login.setIdLogin(rs.getInt("ID_LOGIN"));
				login.setUsuario(rs.getString("USUARIO"));
				login.setSenha(rs.getString("SENHA"));
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return login;
	}
	
	public boolean alterarSenha(TbLogin login, TbFuncionario funcionario) { 
		try {
			con = new Conexao(); 
			PreparedStatement ps = con.getConexao().prepareStatement("EXEC PROC_ALTERA_LOGIN ?,?,?");
			ps.setString(1, funcionario.getCpf());
			ps.setString(2, login.getUsuario());
			ps.setString(3, login.getSenha()); 
			
			if(ps.executeUpdate() > 0) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) { 
			e.printStackTrace();
			return false;
		}
		
	}
}	
