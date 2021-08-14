package model;

import java.sql.Date; 

public class Funcionario {
	int Id;
	int Num;
	Date DtNasc;
	String Nome;
	String CPF;
	String RG;	
	String Cargo;
	String Sexo;
	String Cel;
	String Usuario;
	String Senha;
	String CEP;
	String Rua;
	String Bairro;
	String UF;
	String Cidade;
	
	public Funcionario() {
		super();
	}

	public Funcionario(int id, String nome, String cPF, String rG, Date dtNasc, String cargo, String sexo,
			String cel, String usuario, String senha, String cEP, String rua, String bairro, String uF, String cidade,
			int num) {
		super();
		Id = id;
		Nome = nome;
		CPF = cPF;
		RG = rG;
		DtNasc = dtNasc;
		Cargo = cargo;
		Sexo = sexo;
		Cel = cel;
		Usuario = usuario;
		Senha = senha;
		CEP = cEP;
		Rua = rua;
		Bairro = bairro;
		UF = uF;
		Cidade = cidade;
		Num = num;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public Date getDtNasc() {
		return DtNasc;
	}

	public void setDtNasc(Date dataFormatada) {
		DtNasc = dataFormatada;
	}

	public String getCargo() {
		return Cargo;
	}

	public void setCargo(String cargo) {
		Cargo = cargo;
	}

	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		Sexo = sexo;
	}

	public String getCel() {
		return Cel;
	}

	public void setCel(String cel) {
		Cel = cel;
	}

	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getRua() {
		return Rua;
	}

	public void setRua(String rua) {
		Rua = rua;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public int getNum() {
		return Num;
	}

	public void setNum(int num) {
		Num = num;
	}
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
}	