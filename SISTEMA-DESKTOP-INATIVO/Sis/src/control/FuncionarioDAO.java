package control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;

public class FuncionarioDAO {
	Conexao con;

	/**
	 * Inserir
	 *  
	 */
		
	public boolean incluir(Funcionario objFunc) throws Exception {
		con = new Conexao();
		
		String SQL = "INSERT INTO TB_FUNCIONARIOS (ID,NOME,RG,CPF,CARGO,DT_NASC,SEXO) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps = con.getConexao().prepareStatement(SQL);
		ps.setInt(1,objFunc.getId());
		ps.setString(2, objFunc.getNome());
		ps.setString(3, objFunc.getRG());
		ps.setString(4, objFunc.getCPF());
		ps.setString(5, objFunc.getCargo());
		ps.setDate(6, objFunc.getDtNasc());
		ps.setString(7, objFunc.getSexo());
		ps.executeUpdate();
			
			
		String Endereco = "INSERT INTO TB_ENDERECO (CEP, RUA, NUMERO, BAIRRO, ESTADO, CIDADE,ID_FUNCIONARIO) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement end = con.getConexao().prepareStatement(Endereco);
		end.setString(1, objFunc.getCEP());
		end.setString(2, objFunc.getRua());
		end.setInt(3, objFunc.getNum());
		end.setString(4, objFunc.getBairro());
		end.setString(5, objFunc.getUF());
		end.setString(6, objFunc.getCidade());	
		end.setInt(7, objFunc.getId());
		end.executeUpdate();
					
		String Login = "INSERT INTO TB_LOGIN (USUARIO,SENHA,ID_FUNCIONARIO) VALUES(?,?,?)";
		PreparedStatement log = con.getConexao().prepareStatement(Login);
		log.setString(1, objFunc.getUsuario());
		log.setString(2, objFunc.getSenha());
		log.setInt(3, objFunc.getId());
		log.executeUpdate();
		
		String Cel = "INSERT INTO TB_CONTATO (NUMERO, ATIVO,ID_FUNCIONARIO) VALUES(?,?,?)";
		PreparedStatement cel= con.getConexao().prepareStatement(Cel);
		cel.setString(1, objFunc.getCel());
		cel.setInt(2, 1);
		cel.setInt(3, objFunc.getId());
		cel.executeUpdate();			
		return true;		 
	} 	
	
	/**
	 * Alterar
	 *  
	 */	
	public boolean alterar(Funcionario objFunc) throws Exception {
			con = new Conexao();
			
			String SQL = "UPDATE TB_FUNCIONARIOS SET NOME = ? ,RG= ? ,CPF= ? ,CARGO = ? ,DT_NASC = ?,SEXO = ?  WHERE ID= ?";
			PreparedStatement ps = con.getConexao().prepareStatement(SQL);
			
			ps.setString(1, objFunc.getNome());
			ps.setString(2, objFunc.getRG());
			ps.setString(3, objFunc.getCPF());
			ps.setString(4, objFunc.getCargo());
			ps.setDate(5, objFunc.getDtNasc());
			ps.setString(6, objFunc.getSexo());
			ps.setInt(7,objFunc.getId());						 			
			ps.executeUpdate();
			
			String Endereco = "UPDATE TB_ENDERECO SET CEP= ?, RUA= ?, NUMERO= ?, BAIRRO= ?, ESTADO= ? , CIDADE= ?  WHERE ID_FUNCIONARIO = ?";
			PreparedStatement end = con.getConexao().prepareStatement(Endereco);
			end.setString(1, objFunc.getCEP());
			end.setString(2, objFunc.getRua());
			end.setInt(3, objFunc.getNum());
			end.setString(4, objFunc.getBairro());
			end.setString(5, objFunc.getUF());
			end.setString(6, objFunc.getCidade());	
			end.setInt(7, objFunc.getId());
			end.executeUpdate();
			
			String Login = "UPDATE TB_LOGIN SET USUARIO = ?, SENHA= ? WHERE ID_FUNCIONARIO =? ";
			PreparedStatement log = con.getConexao().prepareStatement(Login);
			log.setString(1, objFunc.getUsuario());
			log.setString(2, objFunc.getSenha());
			log.setInt(3, objFunc.getId());
			log.executeUpdate();
			
			String Cel = "UPDATE TB_CONTATO SET NUMERO = ? , ATIVO = ? WHERE ID_FUNCIONARIO = ?";
			PreparedStatement cel= con.getConexao().prepareStatement(Cel);
			cel.setString(1, objFunc.getCel());
			cel.setInt(2, 1);
			cel.setInt(3, objFunc.getId());
			cel.executeUpdate();
						
			return true;
}
		
		/**
		 * Excluir
		 *  
		 */
 
		public boolean excluir(int id) throws Exception {
			con = new Conexao();
			String url = "DELETE FROM TB_ENDERECO WHERE ID_FUNCIONARIO =?";
			PreparedStatement ps = con.getConexao().prepareStatement(url);
			ps.setInt(1, id);
			ps.executeUpdate();
			
			String cnn = "DELETE FROM TB_CONTATO WHERE ID_FUNCIONARIO =?";
			PreparedStatement cn = con.getConexao().prepareStatement(cnn);
			cn.setInt(1, id);
			cn.executeUpdate();
			
			String log = "DELETE FROM TB_LOGIN WHERE ID_FUNCIONARIO =?";
			PreparedStatement lg = con.getConexao().prepareStatement(log);
			lg.setInt(1,id);
			lg.executeUpdate();
			 
			String inf = "DELETE FROM TB_FUNCIONARIOS WHERE ID =?";
			PreparedStatement fun = con.getConexao().prepareStatement(inf);
			fun.setInt(1,id);
			fun.executeUpdate();
			
			return true;
}
 
		/**
		 * PESQUISAR
		 *  
		 */
		
		public Funcionario pesquisar(int id) throws Exception {
			con = new Conexao();
			Funcionario objFunc = null;
			PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM VW_FUNCIONARIO_COMPLETO WHERE ID = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				objFunc = new Funcionario();
				objFunc.setNome(rs.getString("NOME"));	
				 objFunc.setRG(rs.getString("RG"));
				 objFunc.setCPF(rs.getString("CPF"));
				 objFunc.setCargo(rs.getString("CARGO"));
				 objFunc.setDtNasc(rs.getDate("DT_NASC"));	 
				 objFunc.setSexo(rs.getString("SEXO"));				 
				 objFunc.setCel(rs.getString("CELULAR"));
				 objFunc.setUsuario(rs.getString("USUARIO"));
				 objFunc.setSenha(rs.getString("SENHA"));
				 objFunc.setCEP(rs.getString("CEP"));
				 objFunc.setRua(rs.getString("RUA"));
				 objFunc.setBairro(rs.getString("BAIRRO"));
				 objFunc.setNum(rs.getInt("NUMERO"));
				 objFunc.setUF(rs.getString("ESTADO"));
				 objFunc.setCidade(rs.getString("CIDADE"));
			}
				return objFunc;
		}
			
		/**		
		 * 		INSERIR DADOS NA TABELA
		 */		
				
		public List<Funcionario> read() throws Exception{
			con = new Conexao();
			
			List<Funcionario> funcionario = new ArrayList<>();
			
			PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_FUNCIONARIOS");
			ResultSet rs = ps.executeQuery();
			
			try {
			while(rs.next()){
				
				Funcionario fun = new Funcionario();
				
				fun.setId(rs.getInt("ID"));/// ALTERAR ESSA VARIAVEL
				fun.setNome(rs.getString("NOME"));
				fun.setCPF(rs.getString("CPF"));
				fun.setRG(rs.getString("RG"));
				fun.setCargo(rs.getString("CARGO"));	
				funcionario.add(fun);		
			}
		}catch(Exception e){
			System.out.println("Erro:" + e.getMessage());
			} 			
			return funcionario;
		}
}
	





	
