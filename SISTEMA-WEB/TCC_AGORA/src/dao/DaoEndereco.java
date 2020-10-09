package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import model.TbEndereco;

public class DaoEndereco {

	Conexao con;
	public boolean crudEndereco(String acao, String cpf_cnpj, TbEndereco end) throws Exception {
		con = new Conexao(); 
		PreparedStatement ps = null;
		System.out.println("CPF: " +cpf_cnpj);
		if(acao.equals("I")) {
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_ENDERECO I,?,?,?,?,?,?,?");  
			ps.setString(1, cpf_cnpj); 
			ps.setString(2,  end.getCep());
			ps.setString(3, end.getRua());
			ps.setInt(4, end.getNumero());
			ps.setString(5, end.getBairro());
			ps.setString(6, end.getEstado());
			ps.setString(7, end.getCidade());
			  
		}		
		else if(acao.equals("A")) { 			
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_ENDERECO A,?,?,?,?,?,?,?"); 
			ps.setString(1, cpf_cnpj); 
			ps.setString(2,  end.getCep());
			ps.setString(3, end.getRua());
			ps.setInt(4, end.getNumero());
			ps.setString(5, end.getBairro());
			ps.setString(6, end.getEstado());
			ps.setString(7, end.getCidade()); 
		}else if(acao.equals("E")){ 
			ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_ENDERECO E,?,NULL,NULL,NULL,NULL,NULL,NULL");  
			ps.setString(1, cpf_cnpj);
		} 
		if(ps.executeUpdate() > 0) {
			return true;
		}else {
			return false;
		}
}
	
	public TbEndereco enderecoPorId(String id) {
			TbEndereco end = new TbEndereco(); 
			try {
				con = new Conexao();
				PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_ENDERECO WHERE ID_GERAL_END = (SELECT DBO.PROCURA_ID_GERAL(?))"); 
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {  
					end.setCep(rs.getString("CEP"));
					end.setRua(rs.getString("RUA"));
					end.setNumero(rs.getInt("NUMERO"));
					end.setBairro(rs.getString("BAIRRO"));
					end.setEstado(rs.getString("ESTADO"));
					end.setCidade(rs.getString("CIDADE"));
				 
				} 
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}	 
			return end;
}
 
	public List<TbEndereco> listaEndereco() {
		List<TbEndereco> listaEnd= new ArrayList<TbEndereco>();	
		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_ENDERECO"); 
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				TbEndereco end = new TbEndereco(); 			
				end.setCep(rs.getString("CEP"));
				end.setRua(rs.getString("RUA"));
				end.setNumero(rs.getInt("NUMERO"));
				end.setBairro(rs.getString("BAIRRO"));
				end.setEstado(rs.getString("ESTADO"));
				end.setCidade(rs.getString("CIDADE"));
				listaEnd.add(end);
			} 	
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	 
		return listaEnd;
	}
}	