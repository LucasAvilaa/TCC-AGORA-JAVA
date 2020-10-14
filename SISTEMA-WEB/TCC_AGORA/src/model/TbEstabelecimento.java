package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TB_ESTABELECIMENTOS database table.
 * 
 */
@Entity
@Table(name="TB_ESTABELECIMENTOS")
@NamedQuery(name="TbEstabelecimento.findAll", query="SELECT t FROM TbEstabelecimento t")
public class TbEstabelecimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ESTAB")
	private String idEstab;

	@Column(name="CNPJ")
	private String cnpj;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_CADASTRO")
	private Date dataCadastro;

	@Column(name="RAZAO_SOCIAL")
	private String razaoSocial;

	//bi-directional many-to-one association to TbPrincipalPessoa
	@OneToMany(mappedBy="tbEstabelecimento")
	private List<TbPrincipalPessoa> tbPrincipalPessoas;

	public TbEstabelecimento() {
	}

	public String getIdEstab() {
		return this.idEstab;
	}

	public void setIdEstab(String idEstab) {
		this.idEstab = idEstab;
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
		tbPrincipalPessoa.setTbEstabelecimento(this);

		return tbPrincipalPessoa;
	}

	public TbPrincipalPessoa removeTbPrincipalPessoa(TbPrincipalPessoa tbPrincipalPessoa) {
		getTbPrincipalPessoas().remove(tbPrincipalPessoa);
		tbPrincipalPessoa.setTbEstabelecimento(null);

		return tbPrincipalPessoa;
	}

}