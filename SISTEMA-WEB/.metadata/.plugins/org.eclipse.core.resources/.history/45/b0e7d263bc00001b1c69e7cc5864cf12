package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TB_CONTAS_PAGAR database table.
 * 
 */
@Entity
@Table(name="TB_CONTAS_PAGAR")
@NamedQuery(name="TbContasPagar.findAll", query="SELECT t FROM TbContasPagar t")
public class TbContasPagar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PAGAR")
	private int idPagar;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_VENCIMENTO")
	private Date dataVencimento;

	//bi-directional many-to-one association to TbCompra
	@ManyToOne
	@JoinColumn(name="ID_COMPRA")
	private TbCompra tbCompra;

	public TbContasPagar() {
	}

	public int getIdPagar() {
		return this.idPagar;
	}

	public void setIdPagar(int idPagar) {
		this.idPagar = idPagar;
	}

	public Date getDataVencimento() {
		return this.dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public TbCompra getTbCompra() {
		return this.tbCompra;
	}

	public void setTbCompra(TbCompra tbCompra) {
		this.tbCompra = tbCompra;
	}

}