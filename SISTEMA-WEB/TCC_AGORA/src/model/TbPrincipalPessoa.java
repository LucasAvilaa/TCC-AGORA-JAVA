package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TB_PRINCIPAL_PESSOA database table.
 * 
 */
@Entity
@Table(name="TB_PRINCIPAL_PESSOA")
@NamedQuery(name="TbPrincipalPessoa.findAll", query="SELECT t FROM TbPrincipalPessoa t")
public class TbPrincipalPessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_GERAL")
	private int idGeral;

	//bi-directional many-to-one association to TbContato
	@OneToMany(mappedBy="tbPrincipalPessoa")
	private List<TbContato> tbContatos;

	//bi-directional many-to-one association to TbEndereco
	@OneToMany(mappedBy="tbPrincipalPessoa")
	private List<TbEndereco> tbEnderecos;

	//bi-directional many-to-one association to TbCliente
	@ManyToOne
	@JoinColumn(name="ID_CLI")
	private TbCliente tbCliente;

	//bi-directional many-to-one association to TbEstabelecimento
	@ManyToOne
	@JoinColumn(name="ID_ESTAB")
	private TbEstabelecimento tbEstabelecimento;

	//bi-directional many-to-one association to TbFornecedore
	@ManyToOne
	@JoinColumn(name="ID_FORN")
	private TbFornecedore tbFornecedore;

	//bi-directional many-to-one association to TbFuncionario
	@ManyToOne
	@JoinColumn(name="ID_FUNC")
	private TbFuncionario tbFuncionario;

	public TbPrincipalPessoa() {
	}

	public int getIdGeral() {
		return this.idGeral;
	}

	public void setIdGeral(int idGeral) {
		this.idGeral = idGeral;
	}

	public List<TbContato> getTbContatos() {
		return this.tbContatos;
	}

	public void setTbContatos(List<TbContato> tbContatos) {
		this.tbContatos = tbContatos;
	}

	public TbContato addTbContato(TbContato tbContato) {
		getTbContatos().add(tbContato);
		tbContato.setTbPrincipalPessoa(this);

		return tbContato;
	}

	public TbContato removeTbContato(TbContato tbContato) {
		getTbContatos().remove(tbContato);
		tbContato.setTbPrincipalPessoa(null);

		return tbContato;
	}

	public List<TbEndereco> getTbEnderecos() {
		return this.tbEnderecos;
	}

	public void setTbEnderecos(List<TbEndereco> tbEnderecos) {
		this.tbEnderecos = tbEnderecos;
	}

	public TbEndereco addTbEndereco(TbEndereco tbEndereco) {
		getTbEnderecos().add(tbEndereco);
		tbEndereco.setTbPrincipalPessoa(this);

		return tbEndereco;
	}

	public TbEndereco removeTbEndereco(TbEndereco tbEndereco) {
		getTbEnderecos().remove(tbEndereco);
		tbEndereco.setTbPrincipalPessoa(null);

		return tbEndereco;
	}

	public TbCliente getTbCliente() {
		return this.tbCliente;
	}

	public void setTbCliente(TbCliente tbCliente) {
		this.tbCliente = tbCliente;
	}

	public TbEstabelecimento getTbEstabelecimento() {
		return this.tbEstabelecimento;
	}

	public void setTbEstabelecimento(TbEstabelecimento tbEstabelecimento) {
		this.tbEstabelecimento = tbEstabelecimento;
	}

	public TbFornecedore getTbFornecedore() {
		return this.tbFornecedore;
	}

	public void setTbFornecedore(TbFornecedore tbFornecedore) {
		this.tbFornecedore = tbFornecedore;
	}

	public TbFuncionario getTbFuncionario() {
		return this.tbFuncionario;
	}

	public void setTbFuncionario(TbFuncionario tbFuncionario) {
		this.tbFuncionario = tbFuncionario;
	}

}