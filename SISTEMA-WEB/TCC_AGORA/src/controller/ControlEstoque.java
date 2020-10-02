package controller;

import java.io.IOException;
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

@WebServlet(name = "estoque", urlPatterns={"/ControlEstoque"})
public class ControlEstoque extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String tabela = "/TabelaEstoque.jsp";
    private static String criar_editar = "/estoque.jsp"; 
	private DaoEstoque Dao;
	private String acao = null; 
	private Integer idEstoque = null;
	private TbEstoque estoque = new TbEstoque();
	
    public ControlEstoque() {
    	super();
        Dao = new DaoEstoque();        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String forward = "";
		 String action = request.getParameter("action");  
		 String idEst = request.getParameter("idEstoque"); 
		 if(idEstoque != null) {  
			 idEstoque = Integer.parseInt(idEst);
			 estoque.setIdEstoque(idEstoque);
		 }		 
		 if(action.equalsIgnoreCase("tabela")) {			 	
				 try {				
					request.setAttribute("estoque", Dao.listaEstoque());
					forward = tabela;
				} catch (Exception e) {
					e.printStackTrace();
				}		 
		 }
		 else if(action.equalsIgnoreCase("delete")) { 
					try {
						acao = "E"; 
						Dao.crudEstoque(acao, estoque);						
						request.setAttribute("estoque", Dao.listaEstoque());
						forward = tabela;

					} catch (Exception e) {
						e.printStackTrace();
					}
		 }
		else if(action.equalsIgnoreCase("edit")){   
			request.setAttribute("estoque", Dao.estoquePorId(estoque));
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
			 TbProduto tbProduto = new TbProduto();
			 tbProduto.setIdProduto(Integer.parseInt(request.getParameter("idProd")));
		  	 estoque.setTbProduto(tbProduto);
		  	 
		  	 estoque.setIdEstoque(Integer.parseInt(request.getParameter("idEstoq")));
		  	 estoque.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
	 
			 Date dataEntrada = null;
			 if(request.getParameter("dataEntrada") != null) {
				 try { 				
					 dataEntrada = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataEntrada"));
					estoque.setDataEntrada(dataEntrada);
				} catch (ParseException e) { 
					e.printStackTrace();
					System.out.println("ERRO NA CONVERSÃO DA DATA DE ENTRADA");
				}				 
			 }			

			 Date dataVencimento = null;
			 if(request.getParameter("dataVencimento") != null) {
				 try { 				
					 dataVencimento = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataVencimento"));
					estoque.setDataVencimento(dataVencimento);
				} catch (ParseException e) { 
					e.printStackTrace();
					System.out.println("ERRO NA CONVERSÃO DA DATA DE VENCIMENTO");
				}				 
			 }
			 
			 try {
				 System.out.println("AÇÃO: " + acao );
				 if(acao.equals("I")) {				  
					 if(Dao.crudEstoque(acao, estoque)) {
						 System.out.println("CRIADO COM SUCESSO");
						 	}							
						}
				 else{  
					 estoque.setIdEstoque(idEstoque);
					 Dao.crudEstoque(acao, estoque);	
					System.out.println("ALTERADO COM SUCESSO: " + estoque.getIdEstoque());	
				 }				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ERRO AO INSERIR MOVIMENTAÇÃO");
			}
			  response.sendRedirect("ControlEstoque?action=tabela");		
	}	
}