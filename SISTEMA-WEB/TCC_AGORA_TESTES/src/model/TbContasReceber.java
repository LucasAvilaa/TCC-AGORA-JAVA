package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the TB_CONTAS_RECEBER database table.
 * 
 */
@Entity
@Table(name="TB_CONTAS_RECEBER")
@NamedQuery(name="TbContasReceber.findAll", query="SELECT t FROM TbContasReceber t")
public class TbContasReceber implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_RECEBER")
	private int idReceber;

	@Column(name="CREDITO")
	private BigDecimal credito;

	@Column(name="DATA_COMPRA")
	private Timestamp dataCompra;

	@Column(name="DATA_PRIVISTA_RECEBER")
	private Timestamp dataPrivistaReceber;

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

	public Timestamp getDataCompra() {
		return this.dataCompra;
	}

	public void setDataCompra(Timestamp dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Timestamp getDataPrivistaReceber() {
		return this.dataPrivistaReceber;
	}

	public void setDataPrivistaReceber(Timestamp dataPrivistaReceber) {
		this.dataPrivistaReceber = dataPrivistaReceber;
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