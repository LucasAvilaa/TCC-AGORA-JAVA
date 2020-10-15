package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TB_HIERARQUIA database table.
 * 
 */
@Entity
@Table(name="TB_HIERARQUIA")
@NamedQuery(name="TbHierarquia.findAll", query="SELECT t FROM TbHierarquia t")
public class TbHierarquia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_HIERARQUIA")
	private int idHierarquia;

	@Column(name="CARGO")
	private String cargo;

	//bi-directional many-to-one association to TbLogin
	@OneToMany(mappedBy="tbHierarquia")
	private List<TbLogin> tbLogins;

	public TbHierarquia() {
	}

	public int getIdHierarquia() {
		return this.idHierarquia;
	}

	public void setIdHierarquia(int idHierarquia) {
		this.idHierarquia = idHierarquia;
	}

	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public List<TbLogin> getTbLogins() {
		return this.tbLogins;
	}

	public void setTbLogins(List<TbLogin> tbLogins) {
		this.tbLogins = tbLogins;
	}

	public TbLogin addTbLogin(TbLogin tbLogin) {
		getTbLogins().add(tbLogin);
		tbLogin.setTbHierarquia(this);

		return tbLogin;
	}

	public TbLogin removeTbLogin(TbLogin tbLogin) {
		getTbLogins().remove(tbLogin);
		tbLogin.setTbHierarquia(null);

		return tbLogin;
	}

}