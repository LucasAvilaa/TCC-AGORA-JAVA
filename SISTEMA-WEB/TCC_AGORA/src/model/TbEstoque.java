package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dao.DaoEstoque;


/**
 * The persistent class for the TB_ESTOQUE database table.
 * 
 */
@ManagedBean
@Entity
@Table(name="TB_ESTOQUE")
@NamedQuery(name="TbEstoque.findAll", query="SELECT t FROM TbEstoque t")
public class TbEstoque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_ESTOQUE")
	private int idEstoque;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ENTRADA")
	private Date dataEntrada;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_VENCIMENTO")
	private Date dataVencimento;

	@Column(name="QUANTIDADE")
	private int quantidade;

	//bi-directional many-to-one association to TbProduto
	@ManyToOne
	@JoinColumn(name="ID_PROD_ESTOQ")
	private TbProduto tbProduto;
	
	private List<SelectItem> lista;

	public List<SelectItem> getLista() {
		return lista;
	}

	public void setLista(List<SelectItem> lista) {
		this.lista = lista;
	}

	public TbEstoque() {
	}

	public int getIdEstoque() {
		return this.idEstoque;
	}

	public void setIdEstoque(int idEstoque) {
		this.idEstoque = idEstoque;
	}

	public Date getDataEntrada() {
		return this.dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataVencimento() {
		return this.dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
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

	@PostConstruct
	public void produtoEmEstoque() { 
		try {
			DaoEstoque estoque = new DaoEstoque();
			setLista(estoque.lista());
		} catch (Exception e) { 
			e.printStackTrace();
			System.out.println("ERRO AO LISTA PRODUTO NO ESTOQUE");
		}
	}
}