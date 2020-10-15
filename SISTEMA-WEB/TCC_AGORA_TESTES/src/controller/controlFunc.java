package controller;

import java.sql.Timestamp;

import dao.daoFuncionarios;
import model.TbFuncionario;

public class controlFunc {
	
	public boolean controlFunci( String acao, String cargo, String cpf, Timestamp dataNascimento, String nome,  String sobrenome, String rg) throws Exception {
		TbFuncionario model = new TbFuncionario();
		model.setCargo(cargo);
		model.setCpf(cpf);
		model.setDataNascimento(dataNascimento);
		model.setNome(nome);
		model.setRg(rg);
		model.setSobrenome(sobrenome);
		
		daoFuncionarios dao = new daoFuncionarios();
		
		if(dao.crudFuncionario(acao,model)) {
			return true;
		}else {
			return false;
		}
	}
}
