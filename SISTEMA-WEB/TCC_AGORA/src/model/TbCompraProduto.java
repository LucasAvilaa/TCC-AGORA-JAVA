package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TB_COMPRA_PRODUTO database table.
 * 
 */
@Entity
@Table(name="TB_COMPRA_PRODUTO")
@NamedQuery(name="TbCompraProduto.findAll", query="SELECT t FROM TbCompraProduto t")
public class TbCompraProduto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private int id;

	@Column(name="QUANTIDADE")
	private int quantidade;

	//bi-directional many-to-one association to TbCompra
	@ManyToOne
	@JoinColumn(name="ID_COMPRA_PRODUTO")
	private TbCompra tbCompra;

	//bi-directional many-to-one association to TbProduto
	@ManyToOne
	@JoinColumn(name="ID_PRODUTO")
	private TbProduto tbProduto;

	public TbCompraProduto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public TbCompra getTbCompra() {
		return this.tbCompra;
	}

	public void setTbCompra(TbCompra tbCompra) {
		this.tbCompra = tbCompra;
	}

	public TbProduto getTbProduto() {
		return this.tbProduto;
	}

	public void setTbProduto(TbProduto tbProduto) {
		this.tbProduto = tbProduto;
	}

}