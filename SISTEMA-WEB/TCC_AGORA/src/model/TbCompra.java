package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TB_COMPRAS database table.
 * 
 */
@Entity
@Table(name="TB_COMPRAS")
@NamedQuery(name="TbCompra.findAll", query="SELECT t FROM TbCompra t")
public class TbCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_COMPRA")
	private int idCompra;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_CRIADA")
	private Date dataCriada;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_FINALIZADA")
	private Date dataFinalizada;

	@Column(name="STATUS")
	private String status;

	//bi-directional many-to-one association to TbCompraProduto
	@OneToMany(mappedBy="tbCompra")
	private List<TbCompraProduto> tbCompraProdutos;

	//bi-directional many-to-one association to TbContasPagar
	@OneToMany(mappedBy="tbCompra")
	private List<TbContasPagar> tbContasPagars;

	public TbCompra() { 
	}

	public int getIdCompra() {
		return this.idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public Date getDataCriada() {
		return this.dataCriada;
	}

	public void setDataCriada(Date dataCriada) {
		this.dataCriada = dataCriada;
	}

	public Date getDataFinalizada() {
		return this.dataFinalizada;
	}

	public void setDataFinalizada(Date dataFinalizada) {
		this.dataFinalizada = dataFinalizada;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<TbCompraProduto> getTbCompraProdutos() {
		return this.tbCompraProdutos;
	}

	public void setTbCompraProdutos(List<TbCompraProduto> tbCompraProdutos) {
		this.tbCompraProdutos = tbCompraProdutos;
	}

	public TbCompraProduto addTbCompraProduto(TbCompraProduto tbCompraProduto) {
		getTbCompraProdutos().add(tbCompraProduto);
		tbCompraProduto.setTbCompra(this);

		return tbCompraProduto;
	}

	public TbCompraProduto removeTbCompraProduto(TbCompraProduto tbCompraProduto) {
		getTbCompraProdutos().remove(tbCompraProduto);
		tbCompraProduto.setTbCompra(null);

		return tbCompraProduto;
	}

	public List<TbContasPagar> getTbContasPagars() {
		return this.tbContasPagars;
	}

	public void setTbContasPagars(List<TbContasPagar> tbContasPagars) {
		this.tbContasPagars = tbContasPagars;
	}

	public TbContasPagar addTbContasPagar(TbContasPagar tbContasPagar) {
		getTbContasPagars().add(tbContasPagar);
		tbContasPagar.setTbCompra(this);

		return tbContasPagar;
	}

	public TbContasPagar removeTbContasPagar(TbContasPagar tbContasPagar) {
		getTbContasPagars().remove(tbContasPagar);
		tbContasPagar.setTbCompra(null);

		return tbContasPagar;
	}

}