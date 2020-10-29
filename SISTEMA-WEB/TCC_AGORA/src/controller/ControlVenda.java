package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoVenda;
import model.TbComanda;
import model.TbContasReceber;
import model.TbListaProduto;
import model.TbProduto;

@WebServlet(name = "venda", urlPatterns = { "/ControlVenda" })
public class ControlVenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String tabela = "/TelaCaixa.jsp";
	private static String criar_editar = "/ControlVenda?action=Edit.jsp"; //ARRUMAR ESSE LINK 
	private DaoVenda Dao;
	private String acao = null;
	private Integer idComanda = null;  
	private Integer idProduto = null;   
	private TbProduto produto = new TbProduto();
	private TbListaProduto lista = new TbListaProduto();
	private TbComanda comanda = new TbComanda();
	private TbContasReceber receber = new TbContasReceber();

	public ControlVenda() {
		super();
		Dao = new DaoVenda();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		String idComp = request.getParameter("idComanda"); 
		String idItem = request.getParameter("codItem"); 
		
		if (idComp != null) {
			idComanda = Integer.parseInt(idComp); 
			comanda.setIdComanda(idComanda);
			request.setAttribute("comanda", comanda.getIdComanda()); 
			System.out.println("COMANDA SELECIONADA " + comanda.getIdComanda());
		}  
		
		if(idItem != null) {
			idProduto = Integer.parseInt(idItem);
			produto.setIdProduto(idProduto);
		} 
		
		if(action.equalsIgnoreCase("Caixa")) { 
			forward = tabela;
		}
		
		if (action.equalsIgnoreCase("pesquisaComanda")) { 
			request.setAttribute("venda", Dao.listaProdutoPorComanda(comanda));
			request.setAttribute("total", Dao.valorTotal(comanda));
			forward = tabela;			 
		} else if (action.equalsIgnoreCase("Delete")) { 
			 try {
				 acao = "E";
					if (Dao.crudVenda(acao, comanda, lista, receber, produto)) {
						System.out.println("ITEM EXCLUIDA COM SUCESSO. ID COMANDA: " + comanda.getIdComanda());
						request.setAttribute("venda", Dao.listaProdutoPorComanda(comanda));
						
					} else {
						System.out.println("ERRO AO EXCLUIR ITEM. ID COMANDA: " + comanda.getIdComanda());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				forward = tabela;
			 request.setAttribute("venda", Dao.listaProdutoPorComanda(comanda));
			 forward = tabela;
		} else if (action.equalsIgnoreCase("Edit")) {
			request.setAttribute("venda", Dao.produtoPorId(comanda, lista));
			acao = "I";
			forward = criar_editar;
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		lista.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		produto.setIdProduto(Integer.parseInt(request.getParameter("produto")));
		lista.setTbProduto(produto);
		comanda.setIdComanda(Integer.parseInt(request.getParameter("comanda")));
		lista.setTbComanda(comanda);

		
		try {
			System.out.println("AÇÃO: " + acao);
			if (acao.equals("I")) { 
				Dao.crudVenda(acao, comanda, lista, receber, produto);
				System.out.println("CRIADO COM SUCESSO");
			} 
			else { 
				System.out.println("ALTERADO COM SUCESSO: ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO AO INSERIR VENDA");
		}
		response.sendRedirect("ControlVenda?action=tabela");
	}
}