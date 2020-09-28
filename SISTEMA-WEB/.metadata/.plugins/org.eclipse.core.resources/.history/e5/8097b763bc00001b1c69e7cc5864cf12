package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TB_COMANDA database table.
 * 
 */
@Entity
@Table(name="TB_COMANDA")
@NamedQuery(name="TbComanda.findAll", query="SELECT t FROM TbComanda t")
public class TbComanda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_COMANDA")
	private int idComanda;

	@Column(name="STATUS_COMANDA")
	private String statusComanda;

	//bi-directional many-to-one association to TbContasReceber
	@OneToMany(mappedBy="tbComanda")
	private List<TbContasReceber> tbContasRecebers;

	//bi-directional many-to-one association to TbListaProduto
	@OneToMany(mappedBy="tbComanda")
	private List<TbListaProduto> tbListaProdutos;

	public TbComanda() {
	}

	public int getIdComanda() {
		return this.idComanda;
	}

	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}

	public String getStatusComanda() {
		return this.statusComanda;
	}

	public void setStatusComanda(String statusComanda) {
		this.statusComanda = statusComanda;
	}

	public List<TbContasReceber> getTbContasRecebers() {
		return this.tbContasRecebers;
	}

	public void setTbContasRecebers(List<TbContasReceber> tbContasRecebers) {
		this.tbContasRecebers = tbContasRecebers;
	}

	public TbContasReceber addTbContasReceber(TbContasReceber tbContasReceber) {
		getTbContasRecebers().add(tbContasReceber);
		tbContasReceber.setTbComanda(this);

		return tbContasReceber;
	}

	public TbContasReceber removeTbContasReceber(TbContasReceber tbContasReceber) {
		getTbContasRecebers().remove(tbContasReceber);
		tbContasReceber.setTbComanda(null);

		return tbContasReceber;
	}

	public List<TbListaProduto> getTbListaProdutos() {
		return this.tbListaProdutos;
	}

	public void setTbListaProdutos(List<TbListaProduto> tbListaProdutos) {
		this.tbListaProdutos = tbListaProdutos;
	}

	public TbListaProduto addTbListaProduto(TbListaProduto tbListaProduto) {
		getTbListaProdutos().add(tbListaProduto);
		tbListaProduto.setTbComanda(this);

		return tbListaProduto;
	}

	public TbListaProduto removeTbListaProduto(TbListaProduto tbListaProduto) {
		getTbListaProdutos().remove(tbListaProduto);
		tbListaProduto.setTbComanda(null);

		return tbListaProduto;
	}

}