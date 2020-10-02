package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DaoContato;
import dao.DaoEndereco;
import dao.DaoEstabelecimento;
import model.TbContato;
import model.TbEndereco;
import model.TbEstabelecimento;

/**
 * Servlet implementation class Controlestabelecimento2
 */
@WebServlet(name = "estabelecimento", urlPatterns={"/ControlEstabelecimento"})
public class ControlEstabelecimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String tabela = "/TabelaEstabelecimento.jsp";
    private static String criar_editar = "/estabelecimento.jsp"; 
	private DaoEstabelecimento Dao;
	private DaoEndereco End;
	private DaoContato Cont;
	private String cnpj = null;
	private String acao = null; 
	private String idEstab = null;
	private TbEstabelecimento estabelecimento = new TbEstabelecimento(); 
	private TbEndereco endereco = new TbEndereco();
	private TbContato contato = new TbContato();
	
    public ControlEstabelecimento() {
        super();
        Dao = new DaoEstabelecimento(); 
        End = new DaoEndereco();
        Cont = new DaoContato(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String forward = "";
		 String action = request.getParameter("action");  
		 String idEsta = request.getParameter("idForn"); 
		 if(idEsta != null) {  
			 idEstab = String.valueOf(idEsta);
			 estabelecimento.setIdEstab(idEstab);
		 }		 
		 if(action.equalsIgnoreCase("tabela")) {			 	
				 try {				
					request.setAttribute("estabelecimento", Dao.listaEstabelecimento());
					request.setAttribute("endereco", End.listaEndereco());
					request.setAttribute("contato", Cont.listaContato());
					forward = tabela;
				} catch (Exception e) {
					e.printStackTrace();
				}		 
		 }
		 else if(action.equalsIgnoreCase("delete")) { 
					try {
						acao = "E"; 
						Dao.crudEstabelecimento(acao, estabelecimento);
						End.crudEndereco(acao, cnpj, endereco);
						Cont.crudContato(acao, cnpj, contato);
						
						request.setAttribute("estabelecimento", Dao.listaEstabelecimento());
						request.setAttribute("endereco", End.listaEndereco());
						request.setAttribute("contato", Cont.listaContato());
						forward = tabela;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
		 }
		else if(action.equalsIgnoreCase("edit")){   
			request.setAttribute("estabelecimento", Dao.estabelecimentoPorId(estabelecimento));
			request.setAttribute("endereco", End.enderecoPorId(cnpj));
			request.setAttribute("contato", Cont.contatoPorId(cnpj));
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
			 cnpj = request.getParameter("cnpj");
			 
		  	 estabelecimento.setIdEstab(request.getParameter("idEstab"));
			 estabelecimento.setRazaoSocial(request.getParameter("razao-social"));			 
			 
			 endereco.setCep(request.getParameter("cep"));
			 endereco.setRua(request.getParameter("rua"));
			 
			 if(request.getParameter("numero") != null) {
				 endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
			 }
			
			 endereco.setBairro(request.getParameter("bairro"));
			 endereco.setEstado(request.getParameter("estado"));
			 endereco.setCidade(request.getParameter("cidade"));
	 
			 contato.setEmail(request.getParameter("email"));
			 contato.setNumero(request.getParameter("celular"));
			 
			 try {
				 System.out.println("AÇÃO: " + acao );
				 if(acao.equals("I")) {				  
					 if(Dao.crudEstabelecimento(acao, estabelecimento)) {
						 End.crudEndereco(acao, cnpj, endereco) ;
						 Cont.crudContato(acao, cnpj, contato);
						 System.out.println("CRIADO COM SUCESSO");
						 	}							
						}
				 else{  
					 estabelecimento.setIdEstab(idEstab);
					 Dao.crudEstabelecimento(acao,estabelecimento);	
					 End.crudEndereco(acao, cnpj, endereco);
					 Cont.crudContato(acao, cnpj, contato);
					System.out.println("ALTERADO COM SUCESSO: " + estabelecimento.getIdEstab());	
				 }				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ERRO AO INSERIR ESTABELECIMENTO");
			}
			  response.sendRedirect("ControlEstabelecimento?action=tabela");		
	}	
}