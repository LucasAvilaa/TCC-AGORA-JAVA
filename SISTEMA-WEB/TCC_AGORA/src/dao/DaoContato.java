package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import model.TbContato;

public class DaoContato {

	Conexao con;
	public void crudContato(String acao, String cpf_cnpj, TbContato tel) throws Exception {
		con = new Conexao(); 
		PreparedStatement ps = null;
		
		if(acao.equals("I")) {
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_CONTATO I,?,?,?"); 
			ps.setString(1, cpf_cnpj);  
			ps.setString(2, tel.getNumero());
			ps.setString(3, tel.getEmail());
		}		
		else if(acao.equals("A")) { 			
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_CONTATO A,?,?,?");
			ps.setString(1, cpf_cnpj);  
			ps.setString(2, tel.getNumero());
			ps.setString(3, tel.getEmail());
		}else if(acao.equals("E")){ 
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_CONTATO E,?,NULL,NULL,NULL");  
			ps.setString(1, cpf_cnpj);
		} 
} 
	
	public TbContato contatoPorId(String cpf_cnpj) { 
			TbContato cont = new TbContato();
			try {
				con = new Conexao();
				PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_CONTATO  WHERE ID_GERAL_TEL = (SELECT DBO.PROCURA_ID_GERAL('?'))"); 
				ps.setString(1, cpf_cnpj);
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {  
					cont.setNumero(rs.getString("NUMERO"));
					cont.setEmail(rs.getString("EMAIL"));
				} 			
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}	 
			return cont;
} 
	
	public List<TbContato> listaContato() {
		List<TbContato> listaCont= new ArrayList<TbContato>();	
		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_CONTATO "); 
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				TbContato cont = new TbContato(); 			
				cont.setNumero(rs.getString("NUMERO"));
				cont.setEmail(rs.getString("EMAIL"));
				listaCont.add(cont);
			} 	
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	 
		return listaCont;
	}
}	