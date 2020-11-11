package controller;

import java.io.IOException;

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
	private static String criar_excluir_item = "/CadastroCompra.jsp";
	private static String compra_atual = "/ControlItensCompra?action=InserirItens&idCompra=";
	private DaoCompra Dao;
	private String acao = "II";
	private Integer idCompra = null; 
	private Integer idProduto = null; 
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
		String idProd = request.getParameter("idItem");   
		
		if (idCom != null) {
			System.out.println(idCom);
			idCompra = Integer.parseInt(idCom);
			compra.setIdCompra(idCompra);
		}
 
		if (idProd != null) {
			idProduto = Integer.parseInt(idProd);
			produto.setIdProduto(idProduto);
		} 
		
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		}
}