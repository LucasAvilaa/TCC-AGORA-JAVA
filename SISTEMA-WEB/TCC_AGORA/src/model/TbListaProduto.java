package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TB_LISTA_PRODUTOS database table.
 * 
 */
@Entity
@Table(name="TB_LISTA_PRODUTOS")
@NamedQuery(name="TbListaProduto.findAll", query="SELECT t FROM TbListaProduto t")
public class TbListaProduto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_COMPRA")
	private int idCompra;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_COMPRA")
	private Date dataCompra;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_SAIDA")
	private Date dataSaida;

	@Column(name="QUANTIDADE")
	private int quantidade;

	//bi-directional many-to-one association to TbComanda
	@ManyToOne
	@JoinColumn(name="ID_COMANDA_LISTA")
	private TbComanda tbComanda;

	//bi-directional many-to-one association to TbProduto
	@ManyToOne
	@JoinColumn(name="ID_PROD_LISTA")
	private TbProduto tbProduto;

	public TbListaProduto() {
	}

	public int getIdCompra() {
		return this.idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public Date getDataCompra() {
		return this.dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Date getDataSaida() {
		return this.dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public TbComanda getTbComanda() {
		return this.tbComanda;
	}

	public void setTbComanda(TbComanda tbComanda) {
		this.tbComanda = tbComanda;
	}

	public TbProduto getTbProduto() {
		return this.tbProduto;
	}

	public void setTbProduto(TbProduto tbProduto) {
		this.tbProduto = tbProduto;
	}

}