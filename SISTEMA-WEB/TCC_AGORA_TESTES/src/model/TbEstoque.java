package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the TB_ESTOQUE database table.
 * 
 */
@Entity
@Table(name="TB_ESTOQUE")
@NamedQuery(name="TbEstoque.findAll", query="SELECT t FROM TbEstoque t")
public class TbEstoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_ESTOQUE")
	private int idEstoque;

	@Column(name="DATA_ENTRADA")
	private Timestamp dataEntrada;

	@Column(name="DATE_VENCIMENTO")
	private Timestamp dateVencimento;

	@Column(name="QUANTIDADE")
	private int quantidade;

	//bi-directional many-to-one association to TbProduto
	@ManyToOne
	@JoinColumn(name="ID_PROD_ESTOQ")
	private TbProduto tbProduto;

	public TbEstoque() {
	}

	public int getIdEstoque() {
		return this.idEstoque;
	}

	public void setIdEstoque(int idEstoque) {
		this.idEstoque = idEstoque;
	}

	public Timestamp getDataEntrada() {
		return this.dataEntrada;
	}

	public void setDataEntrada(Timestamp dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Timestamp getDateVencimento() {
		return this.dateVencimento;
	}

	public void setDateVencimento(Timestamp dateVencimento) {
		this.dateVencimento = dateVencimento;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public TbProduto getTbProduto() {
		return this.tbProduto;
	}

	public void setTbProduto(TbProduto tbProduto) {
		this.tbProduto = tbProduto;
	}

}