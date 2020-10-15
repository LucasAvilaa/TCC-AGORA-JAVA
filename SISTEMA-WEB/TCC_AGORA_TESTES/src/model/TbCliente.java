package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the TB_CLIENTES database table.
 * 
 */ 
public class TbCliente implements Serializable {
	private static final long serialVersionUID = 1L;
 
 
	private String idCli;

 	private String cpf;

	 
	private Timestamp dtNasc;

 
	private String nome;
 
	private String rg;
 
	private String sexo;
 
	private String sobrenome;

	//bi-directional many-to-one association to TbLogin
 
	private TbLogin tbLogin;

	//bi-directional many-to-one association to TbPrincipalPessoa
 
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

	public Timestamp getDtNasc() {
		return this.dtNasc;
	}

	public void setDtNasc(Timestamp dtNasc) {
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