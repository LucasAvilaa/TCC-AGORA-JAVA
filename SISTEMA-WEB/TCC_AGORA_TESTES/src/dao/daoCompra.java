package dao;

import java.sql.PreparedStatement;
import factory.Conexao;
import model.TbCompra;
import model.TbCompraProduto;

public class daoCompra {
	
	Conexao con;
	public boolean criarCompra() throws Exception {
		con = new Conexao();
		String sql = "INSERT INTO TB_COMPRAS VALUES('P')";
		PreparedStatement ps = con.getConexao().prepareStatement(sql);
		if(ps.executeUpdate()>0) {
			ps.close();
			return true;
		}else {
			return false;
		}
	}
	
	public boolean confirmaCompra(TbCompra tbcompras) throws Exception {
		con = new Conexao();
		String sql = "UPDATE TB_COMPRAS SET STATUS = 'C' WHERE ID_COMPRA = ?";
		PreparedStatement ps = con.getConexao().prepareStatement(sql);
		ps.setInt(1, tbcompras.getIdCompra());
		if(ps.executeUpdate()>0) {
			ps.close();
			return true;
		}else {
			return false;
		}
	}	
	
	public boolean crudCompraItens(String acao, TbCompraProduto tbcompra) throws Exception {
		con = new Conexao();
		String sql = "EXEC PROC_COMPRA_ESTAB ?,?,?,?";
		PreparedStatement ps = con.getConexao().prepareStatement(sql);
		ps.setString(1,acao);
		ps.setInt(2, tbcompra.getTbProduto().getIdProduto());
		ps.setInt(3, tbcompra.getTbCompra().getIdCompra());
		ps.setInt(4, tbcompra.getQuantidade());
		
		if(ps.executeUpdate()>0) {
			ps.close();
			return true;
		}else {
			return true;
		}
	} 
}
