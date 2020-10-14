package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TB_CONTATO database table.
 * 
 */
@Entity
@Table(name="TB_CONTATO")
@NamedQuery(name="TbContato.findAll", query="SELECT t FROM TbContato t")
public class TbContato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_CONTATO")
	private int idContato;

	@Column(name="EMAIL")
	private String email;

	@Column(name="NUMERO")
	private String numero;

	//bi-directional many-to-one association to TbPrincipalPessoa
	@ManyToOne
	@JoinColumn(name="ID_GERAL_TEL")
	private TbPrincipalPessoa tbPrincipalPessoa;

	public TbContato() {
	}

	public int getIdContato() {
		return this.idContato;
	}

	public void setIdContato(int idContato) {
		this.idContato = idContato;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TbPrincipalPessoa getTbPrincipalPessoa() {
		return this.tbPrincipalPessoa;
	}

	public void setTbPrincipalPessoa(TbPrincipalPessoa tbPrincipalPessoa) {
		this.tbPrincipalPessoa = tbPrincipalPessoa;
	}

}