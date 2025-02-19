package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date; 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import dao.DaoEstoque;
import dao.DaoVenda;
import model.TbComanda;
import model.TbContasReceber;
import model.TbListaProduto;
import model.TbProduto;

@WebServlet(name = "venda", urlPatterns = { "/ControlVenda" })
public class ControlVenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String tabela = "/TelaCaixa.jsp";
	private static String criar_editar = "/ControlVenda?action=pesquisaComanda&idComanda="; //ARRUMAR ESSE LINK 
	private DaoVenda Dao;
	private DaoEstoque Estoque;
	private String acao = "I";
	private Integer idComanda = null;  
	private Integer idProduto = null;    
	private TbProduto produto = new TbProduto();
	private TbListaProduto lista = new TbListaProduto();
	private TbComanda comanda = new TbComanda();
	private TbContasReceber receber = new TbContasReceber();

	public ControlVenda() {
		super();
		Dao = new DaoVenda();
		Estoque = new DaoEstoque();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		String idComp = request.getParameter("idComanda"); 
		String idItem = request.getParameter("codItem"); 
		 
		Date data = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		String dataFormatada = dateFormat.format(data);
		request.setAttribute("data", dataFormatada);  
		
		Date hora = new Date();
		SimpleDateFormat horaFormat = new SimpleDateFormat("hh:mm:ss"); 
		String horaFormatada = horaFormat.format(hora);
		request.setAttribute("hora", horaFormatada); 
		
		if (idComp != null) {
			idComanda = Integer.parseInt(idComp); 
			comanda.setIdComanda(idComanda);
			request.setAttribute("comanda", comanda.getIdComanda());  
		}  
		 
		if(idItem != null) {
			idProduto = Integer.parseInt(idItem);
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
		
		if(action.equalsIgnoreCase("Caixa")) { 
			acao = "I";
			forward = tabela;
		}
		
		else if (action.equalsIgnoreCase("pesquisaComanda")) {  
			request.setAttribute("venda", Dao.listaProdutoPorComanda(comanda));
			request.setAttribute("status", Dao.status(comanda));
			request.setAttribute("total", Dao.valorTotal(comanda)); 
			request.setAttribute("dataAberta", Dao.dataAberta(comanda));
			forward = tabela;			 
		} 
		else if (action.equalsIgnoreCase("Delete")) { 
			 try {
				 acao = "E";
					if (Dao.crudVenda(acao, comanda, lista, receber, produto)) {
						System.out.println("ITEM EXCLUIDA COM SUCESSO. ID COMANDA: " + comanda.getIdComanda());
						request.setAttribute("venda", Dao.listaProdutoPorComanda(comanda));
						request.setAttribute("status", Dao.status(comanda));
						request.setAttribute("total", Dao.valorTotal(comanda));
 				} else {
						System.out.println("ERRO AO EXCLUIR ITEM. ID COMANDA: " + comanda.getIdComanda());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			 acao = "I";
			 forward = tabela;
		} 
		else if (action.equalsIgnoreCase("Edit")) {
			acao = "A";
			request.setAttribute("item", Dao.produtoPorId(comanda, produto));	 
			forward = criar_editar + comanda.getIdComanda();
		}
		
		else if(action.equalsIgnoreCase("FinalizarVenda")) {
			acao = "P";  
			forward = criar_editar + comanda.getIdComanda();		
		}
	  
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		 			if(request.getParameter("quantidade") != null) {
						lista.setQuantidade(Integer.parseInt(request.getParameter("quantidade").trim()));
					} 
					receber.setDebito(BigDecimal.valueOf(Double.valueOf(0)));
					receber.setDinheiro(BigDecimal.valueOf(Double.valueOf(0))); 
					receber.setCredito(BigDecimal.valueOf(Double.valueOf(0)));
					
					if(request.getParameter("formaPagamento") != null) {
						receber.setMetodoPagamento(request.getParameter("formaPagamento"));
						System.out.println("METODO DE PAGAMENTO " + receber.getMetodoPagamento());
						

						if(request.getParameter("formaPagamento").equals("DE")) {
							  receber.setDebito(BigDecimal.valueOf(Double.valueOf(request.getParameter("valorTotal"))));
						}
						else if(request.getParameter("formaPagamento").equals("DI")) {
							receber.setDinheiro(BigDecimal.valueOf(Double.valueOf(request.getParameter("valorTotal")))); 
						}
						else if(request.getParameter("formaPagamento").equals("CR")) {
							  receber.setCredito(BigDecimal.valueOf(Double.valueOf(request.getParameter("valorTotal"))));
						}
						System.out.println("DINHEIRO: " + receber.getDinheiro() + "\n DEBITO: " + receber.getDebito() + "\n CREDITO: " + receber.getCredito());
					} 
					
					comanda.setIdComanda(idComanda);
					lista.setTbComanda(comanda);
			 
					try {
						if(request.getParameter("produto") != null) {
								produto.setIdProduto(Integer.parseInt(request.getParameter("produto")));
								lista.setTbProduto(produto);  
								
								if(Estoque.quantidadePorProduto(produto).getQuantidade() - Integer.parseInt(request.getParameter("quantidade").trim()) >=0 ) {
									System.out.println("A��O: " + acao); 
										
										if(Dao.crudVenda(acao, comanda, lista, receber, produto)) {
												if(acao == "I") {
													System.out.println("INSERIDO COM SUCESSO"); 
												}
												else {
													System.out.println("ALTERADO COM SUCESSO");
												}												 
												acao = "I";
										}
								}
						}
						else if(acao == "P"){
							if(Dao.crudVenda(acao, comanda, lista, receber, produto)) {
								System.out.println("VENDA FINALIZADA COM SUCESSO");  
							}else {
								System.out.println("ERRO AO FINALIZAR VENDA");
							}
							acao = "I";
						} 
							
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("ERRO AO INSERIR VENDA");
					} 
			response.sendRedirect("ControlVenda?action=pesquisaComanda&idComanda=" + comanda.getIdComanda()); 
	}	 
}