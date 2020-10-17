package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TB_CONTAS_RECEBER database table.
 * 
 */
@ManagedBean
@Entity
@Table(name="TB_CONTAS_RECEBER")
@NamedQuery(name="TbContasReceber.findAll", query="SELECT t FROM TbContasReceber t")
public class TbContasReceber implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_RECEBER")
	private int idReceber;

	@Column(name="CREDITO")
	private BigDecimal credito;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_COMPRA")
	private Date dataCompra;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_PREVISTA_RECEBER")
	private Date dataPrevistaReceber;

	@Column(name="DEBITO")
	private BigDecimal debito;

	@Column(name="DINHEIRO")
	private BigDecimal dinheiro;

	@Column(name="METODO_PAGAMENTO")
	private String metodoPagamento;

	//bi-directional many-to-one association to TbComanda
	@ManyToOne
	@JoinColumn(name="ID_COMANDA_RECEBER")
	private TbComanda tbComanda;

	public TbContasReceber() {
	}

	public int getIdReceber() {
		return this.idReceber;
	}

	public void setIdReceber(int idReceber) {
		this.idReceber = idReceber;
	}

	public BigDecimal getCredito() {
		return this.credito;
	}

	public void setCredito(BigDecimal credito) {
		this.credito = credito;
	}

	public Date getDataCompra() {
		return this.dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Date getDataPrevistaReceber() {
		return this.dataPrevistaReceber;
	}

	public void setDataPrevistaReceber(Date dataPrevistaReceber) {
		this.dataPrevistaReceber = dataPrevistaReceber;
	}

	public BigDecimal getDebito() {
		return this.debito;
	}

	public void setDebito(BigDecimal debito) {
		this.debito = debito;
	}

	public BigDecimal getDinheiro() {
		return this.dinheiro;
	}

	public void setDinheiro(BigDecimal dinheiro) {
		this.dinheiro = dinheiro;
	}

	public String getMetodoPagamento() {
		return this.metodoPagamento;
	}

	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public TbComanda getTbComanda() {
		return this.tbComanda;
	}

	public void setTbComanda(TbComanda tbComanda) {
		this.tbComanda = tbComanda;
	}

}