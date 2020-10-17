package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import factory.Conexao;
import model.TbContato;
import model.TbEndereco;
import model.TbPrincipalPessoa;  

public class daoEnderecoContato {
	Conexao con;
	
	public boolean crudEndCont(String acao, TbEndereco end, TbContato tel, String cpf) throws Exception {
		con = new Conexao();
		String sql = "EXEC PROC_CRUD_ENDERECO_CONTATO ?,?,?,?,?,?,?,?,?,?,?";
		PreparedStatement ps = con.getConexao().prepareStatement(sql);
		// ARRUMAR A VARIAVEL AÇÃO
		ps.setString(1, acao);	
		ps.setString(2, cpf); 
		ps.setString(3,  end.getCep());
		ps.setString(4, end.getRua());
		ps.setInt(5, end.getNumero());
		ps.setString(6, end.getBairro());
		ps.setString(7, end.getEstado());
		ps.setString(8, end.getCidade());
		ps.setString(9, tel.getNumero());
		ps.setString(10, tel.getEmail());
		ps.setBoolean(11, tel.getNumeroAtivo());		 
		if(ps.executeUpdate() > 0) {
			ps.close();
			return true;	
		}
		else {
			return false;
		} 
	}
	
	public TbEndereco buscarEndereco() throws Exception {
		con = new Conexao();
		TbEndereco  endereco  = null;
		String sql = "SELECT * FROM TB_ENDERECO";
		PreparedStatement ps = con.getConexao().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			endereco = new TbEndereco(); 
			TbPrincipalPessoa id_geral = new TbPrincipalPessoa();
			endereco.setBairro(rs.getString("BAIRRO"));
			endereco.setCep(rs.getString("CEP"));
			endereco.setCidade(rs.getString("CIDADE"));
			endereco.setEstado(rs.getString("ESTADO"));
			endereco.setNumero(rs.getInt("NUMERO"));
			endereco.setRua(rs.getString("RUA"));
			id_geral.setIdGeral(rs.getInt("ID_GERAL_END")); 
		}
		return endereco;		
	}
	
	public TbContato buscarContato() throws Exception {
		con = new Conexao();
		TbContato contato = null;
		String sql ="SELECT * FROM TB_CONTATO";
		PreparedStatement ps = con.getConexao().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			contato = new TbContato();
			TbPrincipalPessoa id_geral = new TbPrincipalPessoa();
			contato.setEmail(rs.getString("EMAIL"));
			contato.setNumero(rs.getString("NUMERO"));
			contato.setNumeroAtivo(rs.getBoolean("NUMERO_ATIVO"));
			id_geral.setIdGeral(rs.getInt("ID_GERAL_TEL")); 
			
		}
			return contato;
	}
}
