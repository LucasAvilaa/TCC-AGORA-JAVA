package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import model.TbFuncionario;

public class DaoFuncionario {
	
	private TbFuncionario funcionario;
	private	Conexao con;
	
	public DaoFuncionario() {
		super();
	}
	
	public boolean crudFuncionario(String acao,TbFuncionario func) throws Exception {
			con = new Conexao(); 
			PreparedStatement ps = null;
			
			if(acao.equals("I")) {
				ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_FUNCIONARIOS I,NULL,?,?,?,?,?,?,?"); 
				ps.setString(1, func.getNome());
				ps.setString(2, func.getSobrenome());
				ps.setString(3, func.getRg());
				ps.setString(4, func.getCpf());
				ps.setDate(5, new java.sql.Date(func.getDtNasc().getTime()));
				ps.setString(6, func.getSexo()); 
				ps.setString(7, func.getCargo());
			}				
			else if(acao.equals("A")) { 			
				ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_FUNCIONARIOS A,?,?,?,?,?,?,?,?");
				ps.setString(1, func.getIdFunc());
				ps.setString(2, func.getNome());
				ps.setString(3, func.getSobrenome());
				ps.setString(4, func.getRg());
				ps.setString(5, func.getCpf());
				ps.setDate(6, new java.sql.Date(func.getDtNasc().getTime()));
				ps.setString(7, func.getSexo());
				ps.setString(8, func.getCargo());
			}
			else if(acao.equals("E")){ 
				ps = con.getConexao().prepareStatement("EXEC PROC_CRUD_FUNCIONARIOS E,?,NULL,NULL,NULL,NULL,NULL,NULL,NULL");  
				ps.setString(1, func.getIdFunc());
			} 
			if(ps.executeUpdate()>0) { 
				ps.close();
				return true;			
			}else {
				return false;
			}
}
	
	public TbFuncionario funcionarioPorId(TbFuncionario id) {
				funcionario = new TbFuncionario();
				try {
					con = new Conexao();
					PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_FUNCIONARIOS WHERE ID_FUNC=?"); 
					ps.setString(1, id.getIdFunc());
					ResultSet rs = ps.executeQuery();
					
					while (rs.next()) { 
						funcionario.setIdFunc(rs.getString("ID_FUNC"));
						funcionario.setNome(rs.getString("NOME"));
						funcionario.setSobrenome(rs.getString("SOBRENOME"));
						funcionario.setRg(rs.getString("RG"));
						funcionario.setCpf(rs.getString("CPF"));
						funcionario.setCargo(rs.getString("CARGO"));
						funcionario.setDtNasc(rs.getDate("DT_NASC"));
						if(rs.getString("SEXO").equals("M")) {
							funcionario.setSexo("MASCULINO");	
						}else {
							funcionario.setSexo("FEMININO");
						}  
					} 	
					ps.close();
				} catch (Exception e) {
					e.printStackTrace();
				}	 
				return funcionario;
			}
	
	public List<TbFuncionario> listaFuncionario() {
			List<TbFuncionario> listafunc = new ArrayList<TbFuncionario>();	
			try {
				con = new Conexao();
				PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_FUNCIONARIOS"); 
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					TbFuncionario funcionario = new TbFuncionario(); 
					
					funcionario.setIdFunc(rs.getString("ID_FUNC"));
					funcionario.setNome(rs.getString("NOME"));
					funcionario.setSobrenome(rs.getString("SOBRENOME"));
					funcionario.setRg(rs.getString("RG"));
					funcionario.setCpf(rs.getString("CPF"));
					funcionario.setCargo(rs.getString("CARGO"));
					funcionario.setDtNasc(rs.getDate("DT_NASC"));
					funcionario.setSexo(rs.getString("SEXO"));
								
					listafunc.add(funcionario);
				} 	
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}	 
			return listafunc;
		}
	
}
