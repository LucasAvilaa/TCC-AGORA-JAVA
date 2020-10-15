package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import model.TbFuncionario;
import model.TbLogin;

public class daoFuncionarios {
	Conexao con;
	
	public boolean crudFuncionario(String acao,TbFuncionario tbfuncionario) throws Exception {
		con = new Conexao();
		String sql = "EXEC PROC_CRUD_FUNCIONARIOS ?,?,?,?,?,?,?,?";
		PreparedStatement ps = con.getConexao().prepareStatement(sql);
		ps.setString(1, acao);
		ps.setString(2, tbfuncionario.getNome());
		ps.setString(3, tbfuncionario.getSobrenome());
		ps.setString(4, tbfuncionario.getRg());
		ps.setString(5, tbfuncionario.getCpf());
		ps.setTimestamp(6, tbfuncionario.getDataNascimento());
		ps.setString(7, tbfuncionario.getSexo());
		ps.setString(8, tbfuncionario.getCargo());
		
		if(ps.executeUpdate()>0) {
			ps.close();
			return true;
		}else {
			return false;
		}
	}		 

	public TbFuncionario buscarFuncionario() throws Exception {
		con = new Conexao();
		TbFuncionario  funcionario = null;
		String sql = "SELECT * FROM TB_FUNCIONARIOS";
		PreparedStatement ps = con.getConexao().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			funcionario = new TbFuncionario();
			TbLogin login = new TbLogin(); 
			
			funcionario.setIdFunc(rs.getNString("ID_FUNC"));
			funcionario.setNome(rs.getString("NOME"));
			funcionario.setSobrenome(rs.getString("SOBRENOME"));
			funcionario.setRg(rs.getString("RG"));
			funcionario.setCpf(rs.getString("CPF"));
			funcionario.setDataNascimento(rs.getTimestamp("DT_NASC"));
			funcionario.setSexo(rs.getString("SEXO"));
			funcionario.setCargo(rs.getString("CARGO"));
			login.setIdLogin(rs.getInt("ID_LOGIN"));			
		}
		return funcionario;		
	}
	
	
	
	/**		
	 * 		INSERIR DADOS NA TABELA
	 * 		VERIFICAR SE ESTA CERTO
	 */		
			
	public List<TbFuncionario> read() throws Exception{
		con = new Conexao();
		
		List<TbFuncionario> funcionario = new ArrayList<>();
		
		PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_FUNCIONARIOS");
		ResultSet rs = ps.executeQuery();
		
		try {
		while(rs.next()){
			
			TbFuncionario fun = new TbFuncionario();
			
			fun.setIdFunc(rs.getString("ID_FUNC"));
			fun.setNome(rs.getString("NOME"));
			fun.setCpf(rs.getString("CPF"));
			fun.setRg(rs.getString("RG"));
			fun.setCargo(rs.getString("CARGO"));	
			funcionario.add(fun);		
		}
	}catch(Exception e){
		System.out.println("Erro:" + e.getMessage());
		} 			
		return funcionario;
	}
	
}
