package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TB_LOGIN database table.
 * 
 */
@Entity
@Table(name="TB_LOGIN")
@NamedQuery(name="TbLogin.findAll", query="SELECT t FROM TbLogin t")
public class TbLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_LOGIN")
	private int idLogin;

	@Column(name="SENHA")
	private String senha;

	@Column(name="USUARIO")
	private String usuario;

	//bi-directional many-to-one association to TbCliente
	@OneToMany(mappedBy="tbLogin")
	private List<TbCliente> tbClientes;

	//bi-directional many-to-one association to TbFuncionario
	@OneToMany(mappedBy="tbLogin")
	private List<TbFuncionario> tbFuncionarios;

	//bi-directional many-to-one association to TbHierarquia
	@ManyToOne
	@JoinColumn(name="ID_HIERARQUIA")
	private TbHierarquia tbHierarquia;

	public TbLogin() {
	}

	public int getIdLogin() {
		return this.idLogin;
	}

	public void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<TbCliente> getTbClientes() {
		return this.tbClientes;
	}

	public void setTbClientes(List<TbCliente> tbClientes) {
		this.tbClientes = tbClientes;
	}

	public TbCliente addTbCliente(TbCliente tbCliente) {
		getTbClientes().add(tbCliente);
		tbCliente.setTbLogin(this);

		return tbCliente;
	}

	public TbCliente removeTbCliente(TbCliente tbCliente) {
		getTbClientes().remove(tbCliente);
		tbCliente.setTbLogin(null);

		return tbCliente;
	}

	public List<TbFuncionario> getTbFuncionarios() {
		return this.tbFuncionarios;
	}

	public void setTbFuncionarios(List<TbFuncionario> tbFuncionarios) {
		this.tbFuncionarios = tbFuncionarios;
	}

	public TbFuncionario addTbFuncionario(TbFuncionario tbFuncionario) {
		getTbFuncionarios().add(tbFuncionario);
		tbFuncionario.setTbLogin(this);

		return tbFuncionario;
	}

	public TbFuncionario removeTbFuncionario(TbFuncionario tbFuncionario) {
		getTbFuncionarios().remove(tbFuncionario);
		tbFuncionario.setTbLogin(null);

		return tbFuncionario;
	}

	public TbHierarquia getTbHierarquia() {
		return this.tbHierarquia;
	}

	public void setTbHierarquia(TbHierarquia tbHierarquia) {
		this.tbHierarquia = tbHierarquia;
	}

}