package model;

import java.io.Serializable;
import javax.persistence.*;


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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_PAGAR")
	private int idPagar;

	//bi-directional many-to-one association to TbCompra
	@ManyToOne
	@JoinColumn(name="ID_VENDA")
	private TbCompra tbCompra;

	public TbContasPagar() {
	}

	public int getIdPagar() {
		return this.idPagar;
	}

	public void setIdPagar(int idPagar) {
		this.idPagar = idPagar;
	}

	public TbCompra getTbCompra() {
		return this.tbCompra;
	}

	public void setTbCompra(TbCompra tbCompra) {
		this.tbCompra = tbCompra;
	}

}