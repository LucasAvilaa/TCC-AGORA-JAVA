package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dao.DaoFornecedor;


/**
 * The persistent class for the TB_FORNECEDORES database table.
 * 
 */
@Entity
@Table(name="TB_FORNECEDORES")
@NamedQuery(name="TbFornecedore.findAll", query="SELECT t FROM TbFornecedore t")
public class TbFornecedore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_FORN")
	private String idForn;

	@Column(name="ATIVO")
	private boolean ativo;

	@Column(name="CNPJ")
	private String cnpj;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Column(name="RAZAO_SOCIAL")
	private String razaoSocial;

	//bi-directional many-to-one association to TbPrincipalPessoa
	@OneToMany(mappedBy="tbFornecedore")
	private List<TbPrincipalPessoa> tbPrincipalPessoas;

	//bi-directional many-to-one association to TbProduto
	@OneToMany(mappedBy="tbFornecedore")
	private List<TbProduto> tbProdutos;

	private List<SelectItem> fornecedores;

	public List<SelectItem> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<SelectItem> fornecedores) {
		this.fornecedores = fornecedores;
	}

	
	public TbFornecedore() {
	}

	public String getIdForn() {
		return this.idForn;
	}

	public void setIdForn(String idForn) {
		this.idForn = idForn;
	}

	public boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public List<TbPrincipalPessoa> getTbPrincipalPessoas() {
		return this.tbPrincipalPessoas;
	}

	public void setTbPrincipalPessoas(List<TbPrincipalPessoa> tbPrincipalPessoas) {
		this.tbPrincipalPessoas = tbPrincipalPessoas;
	}

	public TbPrincipalPessoa addTbPrincipalPessoa(TbPrincipalPessoa tbPrincipalPessoa) {
		getTbPrincipalPessoas().add(tbPrincipalPessoa);
		tbPrincipalPessoa.setTbFornecedore(this);

		return tbPrincipalPessoa;
	}

	public TbPrincipalPessoa removeTbPrincipalPessoa(TbPrincipalPessoa tbPrincipalPessoa) {
		getTbPrincipalPessoas().remove(tbPrincipalPessoa);
		tbPrincipalPessoa.setTbFornecedore(null);

		return tbPrincipalPessoa;
	}

	public List<TbProduto> getTbProdutos() {
		return this.tbProdutos;
	}

	public void setTbProdutos(List<TbProduto> tbProdutos) {
		this.tbProdutos = tbProdutos;
	}

	public TbProduto addTbProduto(TbProduto tbProduto) {
		getTbProdutos().add(tbProduto);
		tbProduto.setTbFornecedore(this);

		return tbProduto;
	}

	public TbProduto removeTbProduto(TbProduto tbProduto) {
		getTbProdutos().remove(tbProduto);
		tbProduto.setTbFornecedore(null);

		return tbProduto;
	}
	
	@PostConstruct
	public void listar() {
		DaoFornecedor fornecedor = new DaoFornecedor(); 
			setFornecedores(fornecedor.getListaFornecedores());  
	}

}