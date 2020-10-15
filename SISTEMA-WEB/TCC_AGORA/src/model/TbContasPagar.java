package model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TB_CONTAS_PAGAR database table.
 * 
 */
@ManagedBean
@Entity
@Table(name="TB_CONTAS_PAGAR")
@NamedQuery(name="TbContasPagar.findAll", query="SELECT t FROM TbContasPagar t")
public class TbContasPagar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CATEGORIA")
	private String categoria;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="VALOR_PAGAR")
	private BigDecimal valorPagar;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_VENCIMENTO")
	private Date dataVencimento;

	@Column(name="ID_COMPRA")
	private int idCompra;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_PAGAR")
	private int idPagar;

	public TbContasPagar() {
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(BigDecimal valorPagar) {
		this.valorPagar = valorPagar;
	}


	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Date getDataVencimento() {
		return this.dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public int getIdCompra() {
		return this.idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public int getIdPagar() {
		return this.idPagar;
	}

	public void setIdPagar(int idPagar) {
		this.idPagar = idPagar;
	}

}