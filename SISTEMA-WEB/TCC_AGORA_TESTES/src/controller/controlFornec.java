package controller;

import dao.daoFornecedores;
import model.TbFornecedore;

public class controlFornec {
	
	public boolean controlFornece(String acao, String cnpj, String razaoSocial) throws Exception {
		TbFornecedore model = new TbFornecedore();
		model.setCnpj(cnpj);
		model.setRazaoSocial(razaoSocial);
		
		daoFornecedores dao = new daoFornecedores();
		if(dao.crudEstab(acao,model)) {
			return true;
		}else {
			return false;
		}
	}
}
