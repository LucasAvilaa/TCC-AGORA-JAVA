package model;

import java.io.Serializable;
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

import dao.DaoHierarquia;


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

	private List<SelectItem> hierarquia;
	
	 
	public List<SelectItem> getHierarquia() {
		return hierarquia;
	}

	public void setHierarquia(List<SelectItem> hierarquia) {
		this.hierarquia = hierarquia;
	}

	
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
	
	@PostConstruct
	public void listar() {		
		try {
			DaoHierarquia hierarquia = new DaoHierarquia();		 
			 setHierarquia(hierarquia.getListaHierarquia());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO AO LISTA HIERARQUIA");
		}
	}  

}