package controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoProduto;
import model.TbFornecedore;
import model.TbProduto;

@WebServlet(name = "produto", urlPatterns={"/ControlProduto"})
public class ControlProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String tabela = "/TabelaProduto.jsp";
    private static String criar_editar = "/CadastroProduto.jsp"; 
	private DaoProduto Dao;
	private String acao = "I"; 
	private Integer idProd = null;
	private TbProduto produto = new TbProduto();
 	private TbFornecedore fornecedor = new TbFornecedore();
	
    public ControlProduto() {
        super();
        Dao = new DaoProduto(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String forward = "";
		 String action = request.getParameter("action");  
		 String idPro = request.getParameter("idProd");  
		
 		if(idPro != null) {  
 			 idProd = Integer.parseInt(idPro);
			 produto.setIdProduto(idProd);
		 }		 
		 if(action.equalsIgnoreCase("Tabela")) {			 	
				 try {				
					request.setAttribute("produto", Dao.listaProdutos()); 
					forward = tabela;
				} catch (Exception e) {
					e.printStackTrace();
				}		 
		 }
		 else if(action.equalsIgnoreCase("Delete")) { 
			 try {
					acao = "E"; 		 
					if (Dao.crudProduto(acao, produto )) {
						System.out.println("PRODUTO DELETADO COM SUCESSO");
						System.out.println("______________________________________");
					} 
					request.setAttribute("produto",  Dao.listaProdutos()); 
					forward = tabela;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		else if(action.equalsIgnoreCase("Edit")){   
			request.setAttribute("produto", Dao.produtoPorId(produto)); 
			System.out.println("_____________________________________"); 
			System.out.println("ID PRODUTO ALTERANDO " + produto.getIdProduto());  
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
			   
		    fornecedor.setIdForn(request.getParameter("fornecedor")); 
		 	produto.setTbFornecedore(fornecedor); 
		 	 
			produto.setCategoria(request.getParameter("categoria"));
			produto.setDescricaoProduto(request.getParameter("descricao"));
			produto.setNomeProduto(request.getParameter("nomeProduto"));
			produto.setValorUniCompra(BigDecimal.valueOf(Double.valueOf(request.getParameter("vUnitCompra"))));
			produto.setValorUniVenda(BigDecimal.valueOf(Double.valueOf(request.getParameter("vUnitVenda"))));
 
			 try {
				 System.out.println("AÇÃO: " + acao ); 	 
					 if(Dao.crudProduto(acao, produto )) {
						 System.out.println("PRODUTO INSERIDO COM SUCESSO"); 
					 }
					 else {
						 System.out.println("ERRO AO INSERIR PRODUTO"); 
					 }
			} catch (Exception e) {
				e.printStackTrace(); 
				System.out.println("ERRO TRY/CATCH - ERRO AO INSERIR PRODUTO"); 
				System.out.println("_____________________________________");
			}
			  response.sendRedirect("ControlProduto?action=Tabela");		
	}	
}