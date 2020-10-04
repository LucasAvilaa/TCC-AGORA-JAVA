package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TB_PRODUTOS database table.
 * 
 */
@Entity
@Table(name="TB_PRODUTOS")
@NamedQuery(name="TbProduto.findAll", query="SELECT t FROM TbProduto t")
public class TbProduto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PRODUTO")
	private int idProduto;

	@Column(name="CATEGORIA")
	private String categoria;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Column(name="DESCRICAO_PRODUTO")
	private String descricaoProduto;

	@Column(name="NOME_PRODUTO")
	private String nomeProduto;

	@Column(name="VALOR_UNI_COMPRA")
	private BigDecimal valorUniCompra;

	@Column(name="VALOR_UNI_VENDA")
	private BigDecimal valorUniVenda;

	//bi-directional many-to-one association to TbCompraProduto
	@OneToMany(mappedBy="tbProduto")
	private List<TbCompraProduto> tbCompraProdutos;

	//bi-directional many-to-one association to TbEstoque
	@OneToMany(mappedBy="tbProduto")
	private List<TbEstoque> tbEstoques;

	//bi-directional many-to-one association to TbListaProduto
	@OneToMany(mappedBy="tbProduto")
	private List<TbListaProduto> tbListaProdutos;

	//bi-directional many-to-one association to TbFornecedore
	@ManyToOne
	@JoinColumn(name="ID_FORN_PROD")
	private TbFornecedore tbFornecedore;

	public TbProduto() {
	}

	public int getIdProduto() {
		return this.idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getDescricaoProduto() {
		return this.descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public String getNomeProduto() {
		return this.nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public BigDecimal getValorUniCompra() {
		return this.valorUniCompra;
	}

	public void setValorUniCompra(BigDecimal valorUniCompra) {
		this.valorUniCompra = valorUniCompra;
	}

	public BigDecimal getValorUniVenda() {
		return this.valorUniVenda;
	}

	public void setValorUniVenda(BigDecimal valorUniVenda) {
		this.valorUniVenda = valorUniVenda;
	}

	public List<TbCompraProduto> getTbCompraProdutos() {
		return this.tbCompraProdutos;
	}

	public void setTbCompraProdutos(List<TbCompraProduto> tbCompraProdutos) {
		this.tbCompraProdutos = tbCompraProdutos;
	}

	public TbCompraProduto addTbCompraProduto(TbCompraProduto tbCompraProduto) {
		getTbCompraProdutos().add(tbCompraProduto);
		tbCompraProduto.setTbProduto(this);

		return tbCompraProduto;
	}

	public TbCompraProduto removeTbCompraProduto(TbCompraProduto tbCompraProduto) {
		getTbCompraProdutos().remove(tbCompraProduto);
		tbCompraProduto.setTbProduto(null);

		return tbCompraProduto;
	}

	public List<TbEstoque> getTbEstoques() {
		return this.tbEstoques;
	}

	public void setTbEstoques(List<TbEstoque> tbEstoques) {
		this.tbEstoques = tbEstoques;
	}

	public TbEstoque addTbEstoque(TbEstoque tbEstoque) {
		getTbEstoques().add(tbEstoque);
		tbEstoque.setTbProduto(this);

		return tbEstoque;
	}

	public TbEstoque removeTbEstoque(TbEstoque tbEstoque) {
		getTbEstoques().remove(tbEstoque);
		tbEstoque.setTbProduto(null);

		return tbEstoque;
	}

	public List<TbListaProduto> getTbListaProdutos() {
		return this.tbListaProdutos;
	}

	public void setTbListaProdutos(List<TbListaProduto> tbListaProdutos) {
		this.tbListaProdutos = tbListaProdutos;
	}

	public TbListaProduto addTbListaProduto(TbListaProduto tbListaProduto) {
		getTbListaProdutos().add(tbListaProduto);
		tbListaProduto.setTbProduto(this);

		return tbListaProduto;
	}

	public TbListaProduto removeTbListaProduto(TbListaProduto tbListaProduto) {
		getTbListaProdutos().remove(tbListaProduto);
		tbListaProduto.setTbProduto(null);

		return tbListaProduto;
	}

	public TbFornecedore getTbFornecedore() {
		return this.tbFornecedore;
	}

	public void setTbFornecedore(TbFornecedore tbFornecedore) {
		this.tbFornecedore = tbFornecedore;
	}

}