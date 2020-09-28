package controller;

import dao.daoEstabelecimento;
import model.TbEstabelecimento;

public class controlEstab {
	
	public boolean controEstabe(String acao, String cnpj, String razaoSocial) throws Exception {
		TbEstabelecimento model = new TbEstabelecimento();
		model.setCnpj(cnpj);
		model.setRazaoSocial(razaoSocial);
		
		daoEstabelecimento dao = new daoEstabelecimento();
		if(dao.crudEstab(acao,model)) {
			return true;
		}else {
			return false;
		}
	}
}
