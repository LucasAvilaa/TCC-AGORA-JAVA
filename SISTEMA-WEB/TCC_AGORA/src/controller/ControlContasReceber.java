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

import dao.DaoContasReceber;
import model.TbComanda;
import model.TbContasReceber;

@WebServlet(name = "contasReceber", urlPatterns={"/ControlContasReceber"})
public class ControlContasReceber extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String tabela = "/TabelaContasReceber.jsp";
    private static String criar_editar = "/contasReceber.jsp"; 
	private DaoContasReceber Dao; 
	private String acao = null; 
	private Integer idComanda = null; 
	private Integer idReceber = null;
	private TbContasReceber receber = new TbContasReceber(); 
	private TbComanda comanda = new TbComanda();
	
    public ControlContasReceber() {
        super();
        Dao = new DaoContasReceber();    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String forward = "";
		 String action = request.getParameter("action");   
		 
		 if(request.getParameter("idComanda") != null) {  
			 idComanda = Integer.parseInt(request.getParameter("idComanda"));
			 comanda.setIdComanda(idComanda);
		 }		
		 if(request.getParameter("idReceber") != null) {  
			idReceber = Integer.parseInt(request.getParameter("idReceber")); 
			receber.setIdReceber(idReceber);
		 }	
		 
		 if(action.equalsIgnoreCase("Tabela")) {			 	
				 try {				
					request.setAttribute("conta", Dao.ListaReceber());
					forward = tabela;
				} catch (Exception e) {
					e.printStackTrace();
				}		 
		 }
		 else if(action.equalsIgnoreCase("Delete")) { 
					try {
						acao = "E"; 
						Dao.crudContasReceber(acao, receber, comanda);						
						request.setAttribute("conta", Dao.ListaReceber());
						forward = tabela;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
		 }
		else if(action.equalsIgnoreCase("Edit")){   
			request.setAttribute("conta", Dao.ContaReceberPorId(idReceber));
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
 
		receber.setCredito(BigDecimal.valueOf(Double.parseDouble(request.getParameter("credito"))));		
		receber.setDebito(BigDecimal.valueOf(Double.parseDouble(request.getParameter("debito"))));
		receber.setDinheiro(BigDecimal.valueOf(Double.parseDouble(request.getParameter("dinheiro"))));
		receber.setMetodoPagamento(request.getParameter("metodoPag"));
		receber.setIdReceber(Integer.parseInt(request.getParameter("idReceber")) );
		
	 	 Date dataPrevistaReceber = null;
			 if(request.getParameter("dataPrevistaReceber") != null) {
				 try { 				
					 dataPrevistaReceber = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataPrevistaReceber"));
					 receber.setDataPrevistaReceber(dataPrevistaReceber);
				} catch (ParseException e) { 
					e.printStackTrace();
					System.out.println("ERRO NA CONVERSÃO DA DATA PREVISTA");
				}				 
			 }
			 Date dataCompra = null;
			 if(request.getParameter("dataCompra") != null) {
				 try { 				
					 dataCompra = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataCompra"));
					 receber.setDataCompra(dataCompra);
				} catch (ParseException e) { 
					e.printStackTrace();
					System.out.println("ERRO NA CONVERSÃO DA DATA DA COMPRA");
				}				 
			 }
			 try {
				 System.out.println("AÇÃO: " + acao );
				 if(acao.equals("I")) {				  
					 if(Dao.crudContasReceber(acao, receber, comanda) ) {
						 System.out.println("CRIADO COM SUCESSO");
						 	}							
						}
				 else{  
					 comanda.setIdComanda(idComanda);
					 Dao.crudContasReceber(acao, receber, comanda) ;	
					System.out.println("ALTERADO COM SUCESSO: " + receber.getIdReceber());	
				 }				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ERRO AO INSERIR CONTAS A RECEBER");
			}
			  response.sendRedirect("ControlContasReceber?action=Tabela");		
	}	
}