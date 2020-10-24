package controller;

import java.io.IOException;
import java.math.BigDecimal;
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

import dao.DaoContasPagar;
import model.TbCompra;
import model.TbContasPagar;

@WebServlet(name = "contasPagar", urlPatterns = { "/ControlContasPagar" })
public class ControlContasPagar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String tabela = "/TabelaContasPagar.jsp";
	private static String criar_editar = "/CadastroContasPagar.jsp";
	private DaoContasPagar Dao;
	private String acao = "I";
	private Integer idCompra = null;
	private Integer idPagar = null;
	private TbContasPagar pagar = new TbContasPagar();
	private TbCompra compra = new TbCompra(); 

	public ControlContasPagar() {
		super();
		Dao = new DaoContasPagar();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");

		if (request.getParameter("idCompra") != null) {
			idCompra = Integer.parseInt(request.getParameter("idCompra"));
			compra.setIdCompra(idCompra);
		}
		if (request.getParameter("idPagar") != null) {
			idPagar = Integer.parseInt(request.getParameter("idPagar"));
			pagar.setIdPagar(idPagar);
		}
		if (action.equalsIgnoreCase("Tabela")) {
			try {
				request.setAttribute("conta", Dao.listaContaPagar()); 
				forward = tabela;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("Delete")) {
			try {
				acao = "E";
				if (Dao.crudContaPagar(acao, pagar)) {
					System.out.println("CONTA DELETADO COM SUCESSO");
				}
				request.setAttribute("conta", Dao.listaContaPagar());
				forward = tabela;

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("Edit")) {
			request.setAttribute("conta", Dao.ContaPagarPorId(pagar));
			System.out.println("_____________________________________");
			System.out.println("ID CONTA ALTERANDO " + pagar.getIdPagar());
			acao = "A";
			forward = criar_editar;
		} else {
			forward = criar_editar;
			acao = "I";
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pagar.setDescricao(request.getParameter("descricao"));
		pagar.setCategoria(request.getParameter("categoria"));
		pagar.setValorPagar(BigDecimal.valueOf(Double.valueOf(request.getParameter("valor"))));
 
		Date data = null;
		try {
			DateFormat dataCru = new SimpleDateFormat("dd/MM/yyyy");
			data = dataCru.parse(request.getParameter("dataVencimento"));

			pagar.setDataVencimento(data);

		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("ERRO NA CONVERSÃO DA DATA");
		}

		try {
			System.out.println("AÇÃO: " + acao);
			if (Dao.crudContaPagar(acao, pagar)) {
				System.out.println("CONTA INSERIDO COM SUCESSO");
			} else {
				System.out.println("ERRO AO INSERIR CONTA");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO TRY/CATCH - ERRO AO INSERIR CONTA");
			System.out.println("_____________________________________");
		}
		response.sendRedirect("ControlContasPagar?action=Tabela");
	}
}