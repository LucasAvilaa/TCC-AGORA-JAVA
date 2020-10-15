package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import factory.Conexao;
import model.TbCliente;
import model.TbLogin;

public class daoClientes {

	Conexao con;
	public boolean crudClientes(String acao,TbCliente tbclientes) throws Exception {
		con = new Conexao();
		String sql = "EXEC PROC_CRUD_CLIENTE ?,?,?,?,?,?,?";
		PreparedStatement ps = con.getConexao().prepareStatement(sql);
		ps.setString(1, acao);
		ps.setString(2, tbclientes.getNome());
		ps.setString(3, tbclientes.getSobrenome());
		ps.setString(4, tbclientes.getRg());
		ps.setString(5, tbclientes.getCpf());
		ps.setTimestamp(6,  tbclientes.getDtNasc());
		ps.setString(7, tbclientes.getSexo()); 
		
		if(ps.executeUpdate()>0) {
			ps.close();
			return true;
			
		}else {
			ps.close();
			return false;
		}
		
	}
	
	public TbCliente buscarCliente(TbCliente tbcliente) throws Exception {
		con = new Conexao();
		TbCliente  cliente = null;
		String sql = "SELECT * FROM TB_CLIENTES WHERE CPF = ?";
		PreparedStatement ps = con.getConexao().prepareStatement(sql);
		ps.setString(1, tbcliente.getCpf());
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			cliente = new TbCliente();
			TbLogin login = new TbLogin(); 
			
			cliente.setIdCli("ID_CLI");
			cliente.setNome(rs.getString("NOME"));
			cliente.setSobrenome(rs.getString("SOBRENOME"));
			cliente.setRg(rs.getString("RG"));
			cliente.setCpf(rs.getString("CPF"));
			cliente.setDtNasc(rs.getTimestamp("DT_NASC"));
			cliente.setSexo(rs.getString("SEXO"));
			login.setIdLogin(rs.getInt("ID_LOGIN"));			
		}
		return cliente;		
	}
}
	