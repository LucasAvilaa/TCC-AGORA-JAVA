package controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoCompra;
import model.TbCompra;
import model.TbCompraProduto;
import model.TbProduto;

/**
 * Servlet implementation class ControlCliente2
 */
@WebServlet(name = "compraItens", urlPatterns = { "/ControlItensCompra" })
public class ControlItensCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String criar_excluir_item = "/CadastroItensCompra.jsp";
	private DaoCompra Dao;
	private String acao = "II";
	private Integer idCompra = null; 
	private TbCompraProduto compralista = new TbCompraProduto();
	private TbCompra compra = new TbCompra();
	private TbProduto produto = new TbProduto();

	public ControlItensCompra() {
		super();
		Dao = new DaoCompra();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		String idCom = request.getParameter("idCompra"); 
		if (idCom != null) {
			idCompra = Integer.parseInt(idCom);
			compra.setIdCompra(idCompra);
		}
 
		if (action.equalsIgnoreCase("InserirItens")) { 
			acao = "II";
			System.out.println("CHECOU NO CONTROL INSERIR ITENS COM SUCESSO");
			request.setAttribute("compra", Dao.CompraGerada());
			forward = criar_excluir_item;
		}
		
		else if (action.equalsIgnoreCase("EditItens")) {
			request.setAttribute("item", Dao.itensPorCompra(compra));
			acao = "AI";
			forward = criar_excluir_item + compra.getIdCompra();
		} else if (action.equalsIgnoreCase("DeleteItens")) {
			try {
				acao = "EI";
				if (Dao.crudCompraItens(acao, compralista, compra, produto)) {
					System.out.println(
							"COMPRA:" + compra.getIdCompra() + " ITEM EXCLUIDO COM SUCESSO: " + produto.getIdProduto());
					forward = criar_excluir_item + compra.getIdCompra();
				} else {
					System.out.println(
							"COMPRA:" + compra.getIdCompra() + " FALHA AO EXCLUIR ITEM: " + produto.getIdProduto());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		compralista.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		produto.setIdProduto(Integer.parseInt(request.getParameter("idProd")));
		produto.setValorUniCompra(BigDecimal.valueOf(Double.valueOf(request.getParameter("vUnitCompra"))));

		try {
			System.out.println("AÇÃO: " + acao);
			if (acao.equals("II")) {
				if (Dao.crudCompraItens(acao, compralista, compra, produto)) {
					System.out.println("ITEM: " + compralista.getId() + " INCLUIDO COM SUCESSO NA COMPRA: "
							+ compra.getIdCompra());

				} else {
					System.out.println("ERRO AO INSERIR ITEM NA COMPRA");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO AO INSERIR ITEM NA COMPRA  --TRY/CATCH");
		}
		response.sendRedirect("ControlCompra?action=Tabela");
	}
}