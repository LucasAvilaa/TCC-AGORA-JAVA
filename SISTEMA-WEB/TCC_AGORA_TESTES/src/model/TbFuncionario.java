package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the TB_FUNCIONARIOS database table.
 * 
 */
@Entity
@Table(name="TB_FUNCIONARIOS")
@NamedQuery(name="TbFuncionario.findAll", query="SELECT t FROM TbFuncionario t")
public class TbFuncionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_FUNC")
	private String idFunc;

	@Column(name="CARGO")
	private String cargo;

	@Column(name="CPF")
	private String cpf;

	@Column(name="DATA_NASCIMENTO")
	private Timestamp dataNascimento;

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
	@OneToMany(mappedBy="tbFuncionario")
	private List<TbPrincipalPessoa> tbPrincipalPessoas;

	public TbFuncionario() {
	}

	public String getIdFunc() {
		return this.idFunc;
	}

	public void setIdFunc(String idFunc) {
		this.idFunc = idFunc;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Timestamp getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Timestamp dataNascimento) {
		this.dataNascimento = dataNascimento;
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
		tbPrincipalPessoa.setTbFuncionario(this);

		return tbPrincipalPessoa;
	}

	public TbPrincipalPessoa removeTbPrincipalPessoa(TbPrincipalPessoa tbPrincipalPessoa) {
		getTbPrincipalPessoas().remove(tbPrincipalPessoa);
		tbPrincipalPessoa.setTbFuncionario(null);

		return tbPrincipalPessoa;
	}

}