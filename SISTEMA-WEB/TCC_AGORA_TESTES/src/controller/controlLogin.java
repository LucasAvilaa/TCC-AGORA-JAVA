package controller;
 
import dao.daoLogin;
import model.TbLogin;
 
public class controlLogin{ 
	
	public boolean validaLogin(String usuario, String senha) throws Exception{
		TbLogin model = new TbLogin();
		model.setUsuario(usuario); 
		
		daoLogin dao = new daoLogin();		
		dao.validaLogin(model); 
		
		if(model.getSenha().equals(senha)) {
			return true;
		}
		else {
			return false;
		}
	}		
	
	public boolean crudLogin(String acao, String usuario, String senha) throws Exception {
		TbLogin model = new TbLogin();
		model.setUsuario(usuario); 
		model.setSenha(senha);
		
		daoLogin dao = new daoLogin();		
 	
		if(dao.crudLogin(acao, model)) {
			return true;
		}
		else {
			return false;
		}
	}

}
 
	 
