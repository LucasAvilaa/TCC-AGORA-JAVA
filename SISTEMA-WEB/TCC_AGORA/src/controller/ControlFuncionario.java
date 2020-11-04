package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoContato;
import dao.DaoEndereco;
import dao.DaoFuncionario;
import dao.DaoLogin;
import model.TbContato;
import model.TbEndereco;
import model.TbFuncionario;
import model.TbLogin;

/**
 * Servlet implementation class Controlfuncionario2
 */
@WebServlet(name = "funcionarios", urlPatterns = { "/ControlFuncionario" })
public class ControlFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String tabela = "/TabelaFuncionario.jsp";
	private static String criar_editar = "/CadastroFuncionario.jsp";
	private DaoFuncionario Dao;
	private DaoLogin DaoLogin;
	private DaoEndereco End;
	private DaoContato Cont;
	private String cpf = null;
	private String acao = "I";
	private String idFunc = null;
	private TbLogin login = new TbLogin();
	private TbFuncionario funcionario = new TbFuncionario();
	private TbEndereco endereco = new TbEndereco();
	private TbContato contato = new TbContato();

	public ControlFuncionario() {
		super();
		DaoLogin = new DaoLogin();
		Dao = new DaoFuncionario();
		End = new DaoEndereco();
		Cont = new DaoContato();
		login = new TbLogin();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		String idFun = request.getParameter("idFunc");
		cpf = request.getParameter("cod");

		if (idFun != null) {
			idFunc = String.valueOf(idFun);
			funcionario.setIdFunc(idFunc);
		}
		
		String sessao = (String) request.getSession().getAttribute("usuario"); 
		if(sessao != null) {     
			if(sessao.toString() != null) { 
				}
			}
		else {
			forward = "Login.xhtml";
		} 

		if (action.equalsIgnoreCase("Tabela")) {    
					try {
						request.setAttribute("funcionario", Dao.listaFuncionario());
						request.setAttribute("endereco", End.listaEndereco());
						request.setAttribute("contato", Cont.listaContato());
						forward = tabela;
					} catch (Exception e) {
						e.printStackTrace();
					}  
		} 
		else if (action.equalsIgnoreCase("Delete")) {
			try {
				acao = "E";
				if (End.crudEndereco(acao, cpf, endereco)) {
					System.out.println("ENDEREÇO DELETADO COM SUCESSO");
				}
				if (Cont.crudContato(acao, cpf, contato)) {
					System.out.println("CONTADO DELETADO COM SUCESSO");
				}
				if (Dao.crudFuncionario(acao, funcionario)) {
					System.out.println("FUNCIONARIO DELETADO COM SUCESSO");
				}
				if(DaoLogin.crudLogin(acao, login)) {
					System.out.println("USUARIO EXCLUIDO COM SUCESSO");
					System.out.println("______________________________________");
				}
				request.setAttribute("funcionario", Dao.listaFuncionario());
				request.setAttribute("endereco", End.listaEndereco());
				request.setAttribute("contato", Cont.listaContato());
				forward = tabela;

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("Edit")) {
			request.setAttribute("user", DaoLogin.loginFuncionario(funcionario));
			request.setAttribute("funcionario", Dao.funcionarioPorId(funcionario));
			request.setAttribute("endereco", End.enderecoPorId(cpf));
			request.setAttribute("contato", Cont.contatoPorId(cpf));
			request.setAttribute("ativo", Dao.funcionarioPorId(funcionario));
			System.out.println("_____________________________________");
			System.out.println("ID FUNCIONARIO ALTERANDO " + funcionario.getIdFunc());
			System.out.println("CPF FUNCIONARIO ALTERANDO " + funcionario.getCpf());
			acao = "A";
			forward = criar_editar;
		} else {
			forward = criar_editar;
			request.setAttribute("ativo", Dao.funcionarioPorId(funcionario));
			acao = "I";
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		cpf = request.getParameter("cpf");

		funcionario.setNome(request.getParameter("nome"));
		funcionario.setSobrenome(request.getParameter("sobrenome"));
		funcionario.setRg(request.getParameter("rg"));
		funcionario.setCpf(request.getParameter("cpf"));

		if (request.getParameter("sexo").equals("M") || request.getParameter("sexo").equals("MASCULINO")) {
			funcionario.setSexo("M");
		} else {
			funcionario.setSexo("F");
		}

		funcionario.setCargo(request.getParameter("cargo"));
		endereco.setCep(request.getParameter("cep"));
		endereco.setRua(request.getParameter("rua"));

		if(request.getParameter("idLogin") != null) {
			System.out.println("ID LOGIN " + request.getParameter("idLogin")); 
			login.setIdLogin(Integer.parseInt(request.getParameter("idLogin")));
		}
		 
			login.setUsuario(request.getParameter("login"));
		  
		if (request.getParameter("numero") != "") {
			endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
		}

		endereco.setBairro(request.getParameter("bairro"));
		endereco.setEstado(request.getParameter("estado"));
		endereco.setCidade(request.getParameter("cidade"));

		contato.setEmail(request.getParameter("emailContato"));
		contato.setNumero(request.getParameter("celular"));


		Date data = null;
		try {
			DateFormat dataCru = new SimpleDateFormat("yyyy-mm-dd");
			Date date = dataCru.parse(request.getParameter("data"));

			DateFormat dataConv = new SimpleDateFormat("dd-mm-yyyy");
			String date2 = dataConv.format(date);

			data = new SimpleDateFormat("dd-MM-yyyy").parse(date2);
			funcionario.setDtNasc(data);

		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("ERRO NA CONVERSÃO DA DATA");
		}
		try {
			System.out.println("AÇÃO: " + acao);  
			if(!request.getParameter("senha").equals("**************") || acao == "E") {
				login.setSenha(request.getParameter("senha")); 
				if(DaoLogin.crudLogin(acao, login)) {
					System.out.println("USUARIO CRIADO COM SUCESSO");

				}else {
					System.out.println("ERRO AO CRIAR USUARIO");
				}	
			}
			else {
				System.out.println("A SENHA NÃO FOI DEFINIDA");
			}
			if (Dao.crudFuncionario(acao, funcionario)) {
				System.out.println("FUNCIONARIO INSERIDO COM SUCESSO");
			} else {
				System.out.println("ERRO AO INSERIR FUNCIONARIO");
			} 
			if (End.crudEndereco(acao, cpf, endereco)) {
				System.out.println("ENDERECO INSERIDO COM SUCESSO");
			} else {
				System.out.println("ERRO AO INSERIR ENDERECO");
			}
			if (Cont.crudContato(acao, cpf, contato)) {
				System.out.println("CONTATO INSERIDO COM SUCESSO");
				System.out.println("_____________________________________");
			} else {
				System.out.println("ERRO AO INSERIR CONTATO");
				System.out.println("_____________________________________");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO TRY/CATCH - ERRO AO INSERIR FUNCIONARIO");
			System.out.println("_____________________________________");
		}
		response.sendRedirect("ControlFuncionario?action=Tabela");
	}
}