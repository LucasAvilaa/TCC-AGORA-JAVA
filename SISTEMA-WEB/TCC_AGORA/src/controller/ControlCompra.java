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
@WebServlet(name = "compra", urlPatterns = { "/ControlCompra" })
public class ControlCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String tabela = "/TabelaCompra.jsp";
	private static String criar_editar = "/CadastroCompra.jsp";
	private static String editar_compra = "/ControlCompra?action=EditCompra&idCompra=";    
	private DaoCompra Dao;
	private String acao = "II"; 
	private Integer idCompra = null;
	private Integer idProduto = null;  
	private TbCompraProduto compralista = new TbCompraProduto();
	private TbCompra compra = new TbCompra();
	private TbProduto produto = new TbProduto();

	public ControlCompra() {
		super();
		Dao = new DaoCompra();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		String idCom = request.getParameter("idCompra");
		String idProd = request.getParameter("idItem");   
		
		if (idCom != null) { 
			idCompra = Integer.parseInt(idCom);
			compra.setIdCompra(idCompra);
		}

		if (idProd != null) {
			idProduto = Integer.parseInt(idProd);
			produto.setIdProduto(idProduto);
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
					request.setAttribute("compra", Dao.listaCompra());
					forward = tabela;
				} catch (Exception e) {
					e.printStackTrace();
				} 
			  
		}
		else if (action.equalsIgnoreCase("EditCompra")) {
			request.setAttribute("compra", Dao.CompraPorId(compra));
			request.setAttribute("itens", Dao.itensPorCompra(compra));  
			request.setAttribute("total", Dao.valorTotalIndividual(compra)); 
			forward = criar_editar;
		}
		
		else if (action.equalsIgnoreCase("ConfirmarCompra")) {
			Dao.confirmaCompra(compra);
			request.setAttribute("compra", Dao.CompraPorId(compra)); 
			forward = editar_compra + compra.getIdCompra();
		} 
		
		else if (action.equalsIgnoreCase("CriarCompra")) {

			try {
				acao = "IC";
				if (Dao.crudCompra(acao, compralista, compra, produto)) {  
					request.setAttribute("compra", Dao.CompraGerada().getIdCompra());  
					acao ="II";
				} else {
					System.out.println("FALHA AO CRIAR COMPRA");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("FALHA AO CRIAR COMPRA -- TRY/CATCH");
			}
			forward =  editar_compra + Dao.CompraGerada().getIdCompra();
		}

		else if (action.equalsIgnoreCase("FinalizarCompra")) {
			try {
				acao = "C";
				if (Dao.crudCompra(acao, compralista, compra, produto)) {
					request.setAttribute("compra", Dao.CompraGerada());
					System.out.println("COMPRA FINALIZADA COM SUCESSO");
				} else {
					System.out.println("FALHA AO FINALIZAR COMPRA");
				}
				acao = "I";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("FALHA AO FINALIZAR COMPRA -- TRY/CATCH");
			}
			forward = editar_compra + compra.getIdCompra();
		}
		
		else if (action.equalsIgnoreCase("DeleteCompra")) {
			try {
				acao = "EC";
				if (Dao.crudCompra(acao, compralista, compra, produto)) {
					System.out.println("COMPRA EXCLUIDA COM SUCESSO. ID COMPRA: " + compra.getIdCompra());
					request.setAttribute("compra", Dao.listaCompra());  
				} 
				else {
					System.out.println("ERRO AO EXCLUIR COMPRA. ID COMPRA: " + compra.getIdCompra());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			forward = tabela;
		}
		
		if (action.equalsIgnoreCase("InserirItens")) { 
			acao = "II";  
			request.setAttribute("compra", compra.getIdCompra());
			request.setAttribute("itens", Dao.itensPorCompra(compra));
			request.setAttribute("total", Dao.valorTotalIndividual(compra));
			forward = editar_compra + compra.getIdCompra();
		}
		
		else if (action.equalsIgnoreCase("EditItens")) {  
					acao = "AI"; 
					request.setAttribute("item", Dao.itensPorId(compra, produto));   
					forward = editar_compra + compra.getIdCompra();
					System.out.println("EDITAR ITENS. AÇÃO: " + acao);
		}
		
		else if (action.equalsIgnoreCase("DeleteItens")) {
			try {
				acao = "EI";
				if (Dao.crudCompraItens(acao, compralista, compra, produto)) {
					System.out.println(	"COMPRA:" + compra.getIdCompra() + " ITEM EXCLUIDO COM SUCESSO: " + produto.getIdProduto());						
					request.setAttribute("item", Dao.itensPorId(compra, produto));
					acao = "II";
					
				} else {
					System.out.println(
							"COMPRA:" + compra.getIdCompra() + " FALHA AO EXCLUIR ITEM: " + produto.getIdProduto());
				}
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
			forward = editar_compra + compra.getIdCompra();
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		compralista.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		produto.setIdProduto(Integer.parseInt(request.getParameter("idProd"))); 
		request.setAttribute("compra", Dao.CompraPorId(compra));

		try {
			System.out.println("AÇÃO: " + acao); 
				if (Dao.crudCompraItens(acao, compralista, compra, produto)) {
					System.out.println("ITEM: " + produto.getIdProduto() + " INCLUIDO/ALTERADO COM SUCESSO NA COMPRA: "
							+ compra.getIdCompra());

				} else {
					System.out.println("ERRO AO INSERIR ITEM NA COMPRA");
				} 

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO AO INSERIR ITEM NA COMPRA  --TRY/CATCH");
		}  
		response.sendRedirect("ControlCompra?action=EditCompra&idCompra=" + compra.getIdCompra());
	
	}
}