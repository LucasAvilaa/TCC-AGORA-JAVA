package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoProduto;
import model.TbProduto;

@WebServlet(name = "produto", urlPatterns={"/ControlProduto"})
public class ControlProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String tabela = "/TabelaProduto.jsp";
    private static String criar_editar = "/Produto.jsp"; 
	private DaoProduto Dao;
	private String acao = null; 
	private Integer idProd = null;
	private TbProduto prod = new TbProduto();
	
    public ControlProduto() {
        super();
        Dao = new DaoProduto(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String forward = "";
		 String action = request.getParameter("action");  
		 String idPro = request.getParameter("idProd"); 
		 if(idProd != null) {  
			 idProd = Integer.parseInt(idPro);
			 prod.setIdProduto(idProd);
		 }		 
		 if(action.equalsIgnoreCase("tabela")) {			 	
				 try {				
					request.setAttribute("produto", Dao.listaProdutos());
					forward = tabela;
				} catch (Exception e) {
					e.printStackTrace();
				}		 
		 }
		 else if(action.equalsIgnoreCase("delete")) { 
					try {
						acao = "E"; 
						Dao.crudProduto(acao, prod);						
						request.setAttribute("produto", Dao.listaProdutos());
						forward = tabela;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
		 }
		else if(action.equalsIgnoreCase("edit")){   
			request.setAttribute("produto", Dao.produtoPorId(prod));
			acao = "A";
			forward = criar_editar;
		}
		else {
			forward = criar_editar;
			acao = "I";
		}		 
		 RequestDispatcher view = request.getRequestDispatcher(forward);
		 view.forward(request, response);
	}
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 
		  	 prod.setIdProduto(Integer.parseInt(request.getParameter("idProd")));
		 //	 prod.setAtivo(Boolean.valueOf(request.getParameter("ativo")));
		  	 prod.setCategoria(request.getParameter("categoria"));
		  	 prod.setDescricaoProduto(request.getParameter("descricao"));
		  	 prod.setNomeProduto(request.getParameter("nome"));
		  	 prod.setValorUniCompra(BigDecimal.valueOf(Double.valueOf(request.getParameter("vUnitCompra"))));
		  	 prod.setValorUniVenda(BigDecimal.valueOf(Double.valueOf(request.getParameter("vUnitVenda"))));
			 
			 Date data = null;
			 if(request.getParameter("dataCadastro") != null) {
				 try { 				
					data = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataCadastro"));
					prod.setDataCadastro(data);
				} catch (ParseException e) { 
					e.printStackTrace();
					System.out.println("ERRO NA CONVERSÃO DA DATA");
				}				 
			 }
			 try {
				 System.out.println("AÇÃO: " + acao );
				 if(acao.equals("I")) {				  
					 if(Dao.crudProduto(acao, prod)) {
						 System.out.println("CRIADO COM SUCESSO");
						 	}							
						}
				 else{  
					 prod.setIdProduto(idProd);
					 Dao.crudProduto(acao, prod);	
					System.out.println("ALTERADO COM SUCESSO: " + prod.getIdProduto());	
				 }				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ERRO AO INSERIR PRODUTO");
			}
			  response.sendRedirect("ControlProduto?action=tabela");		
	}	
}