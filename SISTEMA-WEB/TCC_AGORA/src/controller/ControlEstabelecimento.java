package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DaoContato;
import dao.DaoEndereco;
import dao.DaoEstabelecimento;
import model.TbContato;
import model.TbEndereco;
import model.TbEstabelecimento;

/**
 * Servlet implementation class Controlestabelecimento2
 */
@WebServlet(name = "estabelecimento", urlPatterns = { "/ControlEstabelecimento" })
public class ControlEstabelecimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String tabela = "/TabelaEstabelecimento.jsp";
	private static String criar_editar = "/CadastroEstabelecimento.jsp";
	private DaoEstabelecimento Dao;
	private DaoEndereco End;
	private DaoContato Cont;
	private String cnpj = null;
	private String acao = "I";
	private String idEstab = null;
	private TbEstabelecimento estabelecimento = new TbEstabelecimento();
	private TbEndereco endereco = new TbEndereco();
	private TbContato contato = new TbContato();

	public ControlEstabelecimento() {
		super();
		Dao = new DaoEstabelecimento();
		End = new DaoEndereco();
		Cont = new DaoContato();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		String idEsta = request.getParameter("idEstab");
		cnpj = request.getParameter("cod");

		if (idEsta != null) {
			idEstab = String.valueOf(idEsta);
			estabelecimento.setIdEstab(idEstab);
		}
		if (action.equalsIgnoreCase("Tabela")) { 
			String sessao = (String) request.getSession().getAttribute("usuario"); 
			if(sessao != null) {     
				if(sessao.toString() != null) {   
				try {
					request.setAttribute("estabelecimento", Dao.listaEstabelecimento());
					request.setAttribute("endereco", End.listaEndereco());
					request.setAttribute("contato", Cont.listaContato());
					forward = tabela;
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
			}else {
				forward = "Login.xhtml";
			} 
			
		} else if (action.equalsIgnoreCase("Delete")) {
			try {
				acao = "E";
				if (End.crudEndereco(acao, cnpj, endereco)) {
					System.out.println("ENDEREÇO DELETADO COM SUCESSO");
				}
				if (Cont.crudContato(acao, cnpj, contato)) {
					System.out.println("CONTADO DELETADO COM SUCESSO");
				}
				if (Dao.crudEstabelecimento(acao, estabelecimento)) {
					System.out.println("ESTABELECIMENTO DELETADO COM SUCESSO");
					System.out.println("______________________________________");
				}
				request.setAttribute("estabelecimento", Dao.listaEstabelecimento());
				request.setAttribute("endereco", End.listaEndereco());
				request.setAttribute("contato", Cont.listaContato());
				forward = tabela;

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("Edit")) {
			request.setAttribute("estabelecimento", Dao.estabelecimentoPorId(estabelecimento));
			request.setAttribute("endereco", End.enderecoPorId(cnpj));
			request.setAttribute("contato", Cont.contatoPorId(cnpj));
			System.out.println("_____________________________________");
			System.out.println("ID ESTABELECIMENTO ALTERANDO " + estabelecimento.getIdEstab());
			System.out.println("CNPJ ESTABELECIMENTO ALTERANDO " + cnpj);
			acao = "A";
			forward = criar_editar;
		} else {
			forward = criar_editar;
			acao = "I";
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		if (acao.equals("I")) {
			cnpj = request.getParameter("cnpj");
		}

		estabelecimento.setRazaoSocial(request.getParameter("razao-social"));
		estabelecimento.setCnpj(request.getParameter("cnpj"));

		if (request.getParameter("numero") != "") {
			endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
		}

		if (request.getParameter("cep") != "") {
			endereco.setCep(request.getParameter("cep"));
		}
		endereco.setCep(request.getParameter("cep"));
		endereco.setRua(request.getParameter("rua"));
		endereco.setBairro(request.getParameter("bairro"));
		endereco.setEstado(request.getParameter("estado"));
		endereco.setCidade(request.getParameter("cidade"));

		contato.setEmail(request.getParameter("email"));
		contato.setNumero(request.getParameter("celular"));

		try {
			System.out.println("AÇÃO: " + acao);
			if (Dao.crudEstabelecimento(acao, estabelecimento)) {
				System.out.println("ESTABELECIMENTO INSERIDO COM SUCESSO");
			} else {
				System.out.println("ERRO AO INSERIR ESTABELECIMENTO");
			}
			if (End.crudEndereco(acao, cnpj, endereco)) {
				System.out.println("ENDERECO INSERIDO COM SUCESSO");
			} else {
				System.out.println("ERRO AO INSERIR ENDERECO");
			}
			if (Cont.crudContato(acao, cnpj, contato)) {
				System.out.println("CONTATO INSERIDO COM SUCESSO");
				System.out.println("_____________________________________");
			} else {
				System.out.println("ERRO AO INSERIR CONTATO");
				System.out.println("_____________________________________");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO TRY/CATCH - ERRO AO INSERIR ESTABELECIMENTO");
			System.out.println("_____________________________________");
		}
		response.sendRedirect("ControlEstabelecimento?action=Tabela");
	}
}