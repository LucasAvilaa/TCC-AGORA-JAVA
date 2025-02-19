package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TB_CLIENTES database table.
 * 
 */
@Entity
@Table(name="TB_CLIENTES")
@NamedQuery(name="TbCliente.findAll", query="SELECT t FROM TbCliente t")
public class TbCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_CLI")
	private String idCli; 

	@Column(name="CPF")
	private String cpf;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_NASC")
	private Date dtNasc;

	@Column(name="NOME")
	private String nome;

	@Column(name="RG")
	private String rg;

	@Column(name="SEXO")
	private String sexo;

	@Column(name="SOBRENOME")
	private String sobrenome;

	//bi-directional many-to-one association to TbLogin
	@ManyToOne
	@JoinColumn(name="ID_LOGIN")
	private TbLogin tbLogin;

	//bi-directional many-to-one association to TbPrincipalPessoa
	@OneToMany(mappedBy="tbCliente")
	private List<TbPrincipalPessoa> tbPrincipalPessoas;

	public TbCliente() {
	}

	public String getIdCli() {
		return this.idCli;
	}

	public void setIdCli(String idCli) {
		this.idCli = idCli;
	} 

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDtNasc() {
		return this.dtNasc;
	}

	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return this.rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSobrenome() {
		return this.sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public TbLogin getTbLogin() {
		return this.tbLogin;
	}

	public void setTbLogin(TbLogin tbLogin) {
		this.tbLogin = tbLogin;
	}

	public List<TbPrincipalPessoa> getTbPrincipalPessoas() {
		return this.tbPrincipalPessoas;
	}

	public void setTbPrincipalPessoas(List<TbPrincipalPessoa> tbPrincipalPessoas) {
		this.tbPrincipalPessoas = tbPrincipalPessoas;
	}

	public TbPrincipalPessoa addTbPrincipalPessoa(TbPrincipalPessoa tbPrincipalPessoa) {
		getTbPrincipalPessoas().add(tbPrincipalPessoa);
		tbPrincipalPessoa.setTbCliente(this);

		return tbPrincipalPessoa;
	}

	public TbPrincipalPessoa removeTbPrincipalPessoa(TbPrincipalPessoa tbPrincipalPessoa) {
		getTbPrincipalPessoas().remove(tbPrincipalPessoa);
		tbPrincipalPessoa.setTbCliente(null);

		return tbPrincipalPessoa;
	}

}