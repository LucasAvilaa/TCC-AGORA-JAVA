package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import factory.Conexao;
import model.TbCompra;
import model.TbCompraProduto;
import model.TbFornecedore;
import model.TbProduto;

public class DaoCompra {

	Conexao con;

	public boolean crudCompra(String acao, TbCompraProduto compralista, TbCompra compra, TbProduto produto)	throws Exception {
		con = new Conexao();
		PreparedStatement ps = null;

		// INCLUIR COMPRA
		if (acao.equals("IC")) {
			ps = con.getConexao().prepareStatement("EXEC PROC_COMPRA_ESTAB IC,NULL,NULL,NULL");
		}
		// EXCLUIR COMPRA
		else if (acao.equals("EC")) {
			ps = con.getConexao().prepareStatement("EXEC PROC_COMPRA_ESTAB EC,?,NULL,NULL");
			ps.setInt(1, compra.getIdCompra());
		}
		// FINALIZAR/CONCLUIR COMPRA
		if (acao.equals("C")) {
			ps = con.getConexao().prepareStatement("EXEC PROC_COMPRA_ESTAB C,?,NULL,NULL");
			ps.setInt(1, compra.getIdCompra());
		}
		if (ps.executeUpdate() > 0) {
			ps.close();
			return true;
		} else {
			return false;
		}
	}

	public boolean crudCompraItens(String acao, TbCompraProduto compralista, TbCompra compra, TbProduto produto)throws Exception {
		con = new Conexao();
		PreparedStatement ps = null;

		if (acao.equals("II")) {
			ps = con.getConexao().prepareStatement("EXEC PROC_COMPRA_ESTAB II,?,?,?");
			ps.setInt(1, compra.getIdCompra());
			ps.setInt(2, produto.getIdProduto());
			ps.setInt(3, compralista.getQuantidade());
		} else if (acao.equals("AI")) {
			ps = con.getConexao().prepareStatement("EXEC PROC_COMPRA_ESTAB AI,?,?,?");
			ps.setInt(1, compra.getIdCompra());
			ps.setInt(2, produto.getIdProduto());
			ps.setInt(3, compralista.getQuantidade());
		} else if (acao.equals("EI")) {
			ps = con.getConexao().prepareStatement("EXEC PROC_COMPRA_ESTAB EI,?,?,NULL");
			ps.setInt(1, compra.getIdCompra());
			ps.setInt(2, produto.getIdProduto());
		}
		if (ps.executeUpdate() > 0) {
			ps.close();
			return true;
		} else {
			return false;
		}
	}

	public TbCompra CompraPorId(TbCompra id) {
		TbCompra compra = new TbCompra();
		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM TB_COMPRAS WHERE ID_COMPRA = ?");
			ps.setInt(1, id.getIdCompra());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				compra.setIdCompra(rs.getInt("ID_COMPRA"));
				if (rs.getString("STATUS").equals("F")) {
					compra.setStatus("FINALIZADO");
				} 
				else if (rs.getString("STATUS").equals("P")){
					compra.setStatus("CONFIRMADA");
				}else {
					compra.setStatus("COTAÇÃO");
				}
				compra.setDataCriada(rs.getTimestamp("DATA_CRIADA"));
				compra.setDataFinalizada(rs.getTimestamp("DATA_FINALIZADA"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return compra;
	}

	public TbCompra CompraGerada() {
		TbCompra compra = new TbCompra();
		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao()
					.prepareStatement("SELECT TOP 1 *  FROM TB_COMPRAS ORDER BY ID_COMPRA DESC");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				compra.setIdCompra(rs.getInt("ID_COMPRA"));
				if (rs.getString("STATUS").equals("F")) {
					compra.setStatus("FINALIZADO");
				} 
				else if (rs.getString("STATUS").equals("P")){
					compra.setStatus("CONFIRMADA");
				}else {
					compra.setStatus("COTAÇÃO");
				}
				compra.setDataCriada(rs.getTimestamp("DATA_CRIADA"));
				compra.setDataFinalizada(rs.getTimestamp("DATA_FINALIZADA"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return compra;
	} 

	public TbCompraProduto itensPorId(TbCompra compra, TbProduto produtoId) {
		TbCompraProduto listaItens = new TbCompraProduto();

		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao()
					.prepareStatement("SELECT * FROM VW_COMPRA WHERE ID_COMPRA = ? AND ID_PRODUTO = ?");
			ps.setInt(1, compra.getIdCompra());
			ps.setInt(2, produtoId.getIdProduto());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) { 
				TbProduto produto = new TbProduto();
				TbFornecedore fornecedor = new TbFornecedore();

				listaItens.setQuantidade(rs.getInt("QUANTIDADE"));

				produto.setNomeProduto(rs.getString("NOME_PRODUTO"));
				produto.setValorUniCompra(rs.getBigDecimal("VALOR_UNI_COMPRA"));
				produto.setIdProduto(rs.getInt("ID_PRODUTO"));
				listaItens.setSubtotal(rs.getBigDecimal("SUBTOTAL")); 
				fornecedor.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));
				produto.setTbFornecedore(fornecedor);
				listaItens.setTbProduto(produto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaItens;
	}

	public List<TbCompraProduto> itensPorCompra(TbCompra compra) {
		List<TbCompraProduto> listaItens = new ArrayList<TbCompraProduto>();

		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM VW_COMPRA WHERE ID_COMPRA = ?");
			ps.setInt(1, compra.getIdCompra());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				TbCompraProduto itens = new TbCompraProduto();
				TbProduto produto = new TbProduto();
				TbFornecedore fornecedor = new TbFornecedore();

				itens.setQuantidade(rs.getInt("QUANTIDADE")); 
				produto.setNomeProduto(rs.getString("NOME_PRODUTO"));
				produto.setValorUniCompra(rs.getBigDecimal("VALOR_UNI_COMPRA"));
				produto.setIdProduto(rs.getInt("ID_PRODUTO")); 
				itens.setSubtotal(rs.getBigDecimal("SUBTOTAL")); 
				fornecedor.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));
				produto.setTbFornecedore(fornecedor);
				itens.setTbProduto(produto);

				listaItens.add(itens);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaItens;
	}

	public List<TbCompra> listaCompra() {
		List<TbCompra> listaCompra = new ArrayList<TbCompra>();

		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao().prepareStatement("SELECT * FROM VW_COMPRA_TOTAL ORDER BY ID_COMPRA DESC");
			ResultSet rs = ps.executeQuery();
		 
			while (rs.next()) {
				TbCompra compra = new TbCompra();
				compra.setIdCompra(rs.getInt("ID_COMPRA")); 
				if (rs.getString("STATUS").equals("F")) {
					compra.setStatus("FINALIZADO");
				} 
				else if (rs.getString("STATUS").equals("P")){
					compra.setStatus("CONFIRMADA");
				}else {
					compra.setStatus("COTAÇÃO");
				}
				compra.setDataCriada(rs.getTimestamp("DATA_CRIADA"));
				compra.setDataFinalizada(rs.getTimestamp("DATA_FINALIZADA")); 
				compra.setTotal(rs.getBigDecimal("TOTAL"));
				listaCompra.add(compra);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCompra;
	}
	
	public void confirmaCompra(TbCompra compra) {
		try {
			con = new Conexao();
			String sql = "UPTimestamp TB_COMPRAS SET STATUS = 'P' WHERE ID_COMPRA = ?";
			PreparedStatement ps = con.getConexao().prepareStatement(sql);
			ps.setInt(1, compra.getIdCompra());
			ps.executeUpdate();
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
		
	}
	
	public TbCompraProduto valorTotalIndividual(TbCompra compra) {
		TbCompraProduto listaItens = new TbCompraProduto();

		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao()
					.prepareStatement("SELECT SUM(SUBTOTAL) AS TOTAL FROM VW_COMPRA WHERE ID_COMPRA = ?");
			ps.setInt(1, compra.getIdCompra()); 
			ResultSet rs = ps.executeQuery();

			while (rs.next()) { 
				listaItens.setTotal(rs.getBigDecimal("TOTAL"));   
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaItens;
	} 
	
	public List<TbCompra>valorTotal() {
		List<TbCompra> lista = new ArrayList<TbCompra>();  
		try {
			con = new Conexao();
			PreparedStatement ps = con.getConexao().prepareStatement(""); 
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {  
				TbCompra compra = new TbCompra(); 
				compra.setTotal(rs.getBigDecimal("TOTAL"));    
				lista.add(compra); 
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	} 
	
	
}