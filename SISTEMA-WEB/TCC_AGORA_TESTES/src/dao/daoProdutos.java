package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import factory.Conexao;
import model.TbProduto;

public class daoProdutos {
	
	Conexao con;
	public boolean crudProdutos(String acao,TbProduto tbprodutos) throws Exception {
		con = new Conexao();
		String sql = "EXEC PROC_CRUD_PRODUTOS ?,?,?,?,?,?,?,?";
		PreparedStatement ps = con.getConexao().prepareStatement(sql);
		ps.setString(1, acao);
		ps.setInt(2, tbprodutos.getIdProduto());
		ps.setString(3, tbprodutos.getNomeProduto());
		ps.setString(4,  tbprodutos.getDescricaoProduto());
		ps.setString(5, tbprodutos.getCategoria());
		ps.setBigDecimal(6, tbprodutos.getValorUniCompra());
		ps.setBigDecimal(7, tbprodutos.getValorUniVenda());
		ps.setTimestamp(8, tbprodutos.getDataCadastro());
		
		if(ps.executeUpdate()>0) {
			ps.close();
			return true;
		}else {
			return false;
		} 
	}
	
	public TbProduto buscarProdutos() throws Exception {
		con = new Conexao();
		TbProduto  produto = null;
		String sql = "SELECT * FROM TB_PRODUTOS";
		PreparedStatement ps = con.getConexao().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			produto = new TbProduto(); 
			
			produto.setIdProduto(rs.getInt("ID_PRODUTO"));
			produto.setIdProduto(rs.getInt("ID_FORN_PROD"));
			produto.setNomeProduto(rs.getString("NOME_PRODUTO"));
			produto.setDescricaoProduto(rs.getString("DESCRICAO_PRODUTO"));
			produto.setCategoria(rs.getString("CATEGORIA"));
			produto.setValorUniCompra(rs.getBigDecimal("VALOR_UNI_COMPRA"));
			produto.setValorUniVenda(rs.getBigDecimal("VALOR_UNI_VENDA"));
			produto.setDataCadastro(rs.getTimestamp("DATA_CADASTRO"));			
			 			
		}
		return produto;		
	}
}
