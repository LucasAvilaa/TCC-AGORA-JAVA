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

import dao.DaoEstoque;
import model.TbEstoque;
import model.TbProduto;

@WebServlet(name = "estoque", urlPatterns = { "/ControlEstoque" })
public class ControlEstoque extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String tabela = "/TabelaEstoque.jsp";
	private static String criar_editar = "/CadastroEstoque.jsp";
	private DaoEstoque Dao;
	private String acao = null;
	private Integer idEstoque = null;
	private TbEstoque estoque = new TbEstoque();
	private TbProduto tbProduto = new TbProduto();

	public ControlEstoque() {
		super();
		Dao = new DaoEstoque();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		String idEst = request.getParameter("idEstoque");
		if (idEst != null) {
			idEstoque = Integer.parseInt(idEst);
			estoque.setIdEstoque(idEstoque);
		}

		if (action.equalsIgnoreCase("Tabela")) {  
			String sessao = (String) request.getSession().getAttribute("usuario"); 
			if(sessao != null) {     
				if(sessao.toString() != null) {   
				try {
					request.setAttribute("estoque", Dao.listaEstoque());
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
				if (Dao.crudEstoque(acao, estoque)) {
					System.out.println("ESTOQUE DELETADO COM SUCESSO");
					System.out.println("______________________________________");
				}
				request.setAttribute("estoque", Dao.listaEstoque());
				forward = tabela;

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("Edit")) {
			try {
				request.setAttribute("estoque", Dao.estoquePorId(estoque));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("_____________________________________");
			System.out.println("ID ESTOQUE ALTERANDO " + estoque.getIdEstoque());
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

		tbProduto.setIdProduto(Integer.parseInt(request.getParameter("idProd")));
		estoque.setTbProduto(tbProduto);

		estoque.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));

		Date data = null; // FORMATAÇÃO DATA DE VENCIMENTO
		try {
			DateFormat dataCru = new SimpleDateFormat("yyyy-mm-dd");
			Date date = dataCru.parse(request.getParameter("dataVencimento"));

			DateFormat dataConv = new SimpleDateFormat("dd-mm-yyyy");
			String date2 = dataConv.format(date);

			data = new SimpleDateFormat("dd-MM-yyyy").parse(date2);
			estoque.setDataVencimento(data);

		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("ERRO NA CONVERSÃO DA DATA DE VENCIMENTO");
		}

		try {
			System.out.println("AÇÃO: " + acao);
			if (Dao.crudEstoque(acao, estoque)) {
				System.out.println("ESTOQUE INSERIDO COM SUCESSO");
			} else {
				System.out.println("ERRO AO INSERIR ESTOQUE");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO TRY/CATCH - ERRO AO INSERIR ESTOQUE");
			System.out.println("_____________________________________");
		}
		response.sendRedirect("ControlEstoque?action=Tabela");
	}
}