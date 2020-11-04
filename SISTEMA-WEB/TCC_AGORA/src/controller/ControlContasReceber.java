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

import dao.DaoContasReceber;
import model.TbComanda;
import model.TbContasReceber;

@WebServlet(name = "contasReceber", urlPatterns = { "/ControlContasReceber" })
public class ControlContasReceber extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String tabela = "/TabelaContasReceber.jsp";
	private static String criar_editar = "/CadastroContasReceber.jsp";
	private DaoContasReceber Dao;
	private String acao = "I";
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

		if (request.getParameter("idReceber") != null) {
			idReceber = Integer.parseInt(request.getParameter("idReceber"));
			receber.setIdReceber(idReceber);
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
					request.setAttribute("receber", Dao.ListaReceber());
					forward = tabela;
				} catch (Exception e) {
					e.printStackTrace();
				} 
		} 
		else if (action.equalsIgnoreCase("Delete")) {
			try {
				acao = "E";
				Dao.crudContasReceber(acao, receber, comanda);
				request.setAttribute("receber", Dao.ListaReceber());
				forward = tabela;

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("Edit")) {
			request.setAttribute("receber", Dao.ContaReceberPorId(receber));
			acao = "A";
			forward = criar_editar;
		} else {
			forward = criar_editar;
			acao = "I";
		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		receber.setDebito(BigDecimal.valueOf(Double.valueOf(0)));
		receber.setDinheiro(BigDecimal.valueOf(Double.valueOf(0))); 
		receber.setCredito(BigDecimal.valueOf(Double.valueOf(0)));
		
		receber.setMetodoPagamento(request.getParameter("condicaoPagamento")); 
		
		if(request.getParameter("condicaoPagamento").equals("DI") || request.getParameter("condicaoPagamento").equals("DINHEIRO")) {
			if (!request.getParameter("dinheiro").equals("R$ 0.00")) {
				receber.setDinheiro(BigDecimal.valueOf(Double.parseDouble(request.getParameter("dinheiro").replace("R$ ",""))));
			}
		}else if (request.getParameter("condicaoPagamento").equals("DE") || request.getParameter("condicaoPagamento").equals("DEBITO")){
				if (!request.getParameter("debito").equals("R$ 0.00")) {
					receber.setDebito(BigDecimal.valueOf(Double.parseDouble(request.getParameter("debito").replace("R$ ",""))));
			}
		}
		else if(request.getParameter("condicaoPagamento").equals("CR") || request.getParameter("condicaoPagamento").equals("CREDITO")){
			if (!request.getParameter("credito").equals("R$ 0.00")) {
				receber.setCredito(BigDecimal.valueOf(Double.parseDouble(request.getParameter("credito").replace("R$ ",""))));
			}
		} 
		
		Date dataPrevista = null;
		try {
			DateFormat dataCru = new SimpleDateFormat("yyyy-mm-dd");
			Date date = dataCru.parse(request.getParameter("dataPrevista"));

			DateFormat dataConv = new SimpleDateFormat("dd-mm-yyyy");
			String date2 = dataConv.format(date);

			dataPrevista = new SimpleDateFormat("dd-MM-yyyy").parse(date2); 
			  
			receber.setDataPrevistaReceber(dataPrevista);

		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("ERRO NA CONVERSÃO DA DATA");
		}
		Date dataCompra = null;
		try {
			DateFormat dataCru = new SimpleDateFormat("yyyy-mm-dd");
			Date date = dataCru.parse(request.getParameter("dataVenda"));

			DateFormat dataConv = new SimpleDateFormat("dd-mm-yyyy");
			String date2 = dataConv.format(date);

			dataCompra = new SimpleDateFormat("dd-MM-yyyy").parse(date2);  
			receber.setDataCompra(dataCompra);

		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("ERRO NA CONVERSÃO DA DATA");
		}

		try {
			System.out.println("AÇÃO: " + acao);
			if (Dao.crudContasReceber(acao, receber, comanda)) {
				System.out.println("CONTA INSERIDO COM SUCESSO");
			} else {
				System.out.println("ERRO AO INSERIR CONTA");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO TRY/CATCH - ERRO AO INSERIR CONTA");
			System.out.println("_____________________________________");
		}
		response.sendRedirect("ControlContasReceber?action=Tabela");
	}
}