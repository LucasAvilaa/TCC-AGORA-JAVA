package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import factory.Conexao;
import model.TbFornecedore;

public class daoFornecedores {
		
	Conexao con;
	public boolean crudEstab(String acao,TbFornecedore tbforn) throws Exception {
		con = new Conexao();
		String sql = "EXEC PROC_CRUD_FORNECEDORES ?,?,?";
		PreparedStatement ps = con.getConexao().prepareStatement(sql);
		ps.setString(1, acao);
		ps.setString(2, tbforn.getCnpj());
		ps.setString(3, tbforn.getRazaoSocial()); 
		if(ps.executeUpdate()>0) {
			ps.close();
			return true;
		}else {
			return false;
		}
	}
	
	public TbFornecedore buscaFornecedor() throws Exception {
		con = new Conexao();
		TbFornecedore  fornecedores = null;
		String sql = "SELECT * FROM TB_FORNECEDORES";
		PreparedStatement ps = con.getConexao().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			fornecedores = new TbFornecedore(); 
			
			fornecedores.setIdForn(rs.getString("ID_FORN"));
			fornecedores.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));
			fornecedores.setCnpj(rs.getString("CNPJ"));	
		}
		return fornecedores;		
	}
}
