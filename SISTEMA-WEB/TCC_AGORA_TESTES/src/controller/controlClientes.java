package controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.daoClientes;
import model.TbCliente;


public class controlClientes {

	public boolean controlCliente(String acao, String nome, String sobrenome,String rg,String cpf, Timestamp dtNasc, String sexo) throws Exception {
		TbCliente model = new TbCliente();
		model.setNome(nome);
		model.setSobrenome(sobrenome);
		model.setRg(rg);
		model.setCpf(cpf);
		model.setDtNasc(dtNasc);
		model.setSexo(sexo);
		
		daoClientes dao = new daoClientes();
		if(dao.crudClientes(acao, model)) {
			return true;
			
		}else {
			return false;
		}		
	}
	
	/**
	 *  VERIFICAR SE ESTA CERTO
	 *
	 */
	
	public static List<String[]> getClientes(String cpf){
		 ArrayList<String[]> cliente = new ArrayList<String[]>();
		 
		 ArrayList<TbCliente> model = new ArrayList<TbCliente>();
		 
		 for(int i = 0; i < model.size(); i++ ) {
			 String a[] = new String[4]; 
			 a[0]=  model.get(i).getIdCli();
			 a[1]=  model.get(i).getNome();
			 a[2]=  model.get(i).getSobrenome();
			 a[3]=  model.get(i).getRg();
			 a[4]=  model.get(i).getCpf();
			 a[5]=  model.get(i).getDtNasc().toString();
			 a[6]=  model.get(i).getSexo();
			 cliente.add(a);
		 }
		return cliente;
	}	
}