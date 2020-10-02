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
import dao.DaoFornecedor;
import model.TbContato;
import model.TbEndereco;
import model.TbFornecedore;

/**
 * Servlet implementation class Controlfornecedor2
 */
@WebServlet(name = "fornecedores", urlPatterns={"/ControlFornecedores"})
public class ControlFornecedor extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String tabela = "/Tabelafornecedor.jsp";
    private static String criar_editar = "/fornecedor.jsp"; 
	private DaoFornecedor Dao;
	private DaoEndereco End;
	private DaoContato Cont;
	private String cnpj = null;
	private String acao = null; 
	private String idForn = null;
	private TbFornecedore fornecedor = new TbFornecedore(); 
	private TbEndereco endereco = new TbEndereco();
	private TbContato contato = new TbContato();
	
    public ControlFornecedor() {
        super();
        Dao = new DaoFornecedor(); 
        End = new DaoEndereco();
        Cont = new DaoContato(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String forward = "";
		 String action = request.getParameter("action");  
		 String idForn = request.getParameter("idForn"); 
		 if(idForn != null) {  
			 idForn= String.valueOf(idForn);
			 fornecedor.setIdForn(idForn);
		 }		 
		 if(action.equalsIgnoreCase("tabela")) {			 	
				 try {				
					request.setAttribute("fornecedor", Dao.listaFornecedor());
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
						Dao.crudFornecedor(acao, fornecedor);
						End.crudEndereco(acao, cnpj, endereco);
						Cont.crudContato(acao, cnpj, contato);
						
						request.setAttribute("fornecedor", Dao.listaFornecedor());
						request.setAttribute("endereco", End.listaEndereco());
						request.setAttribute("contato", Cont.listaContato());
						forward = tabela;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
		 }
		else if(action.equalsIgnoreCase("edit")){   
			request.setAttribute("fornecedor", Dao.fornecedorPorId(fornecedor));
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
			 
		  	 fornecedor.setIdForn(request.getParameter("idForn"));
			 fornecedor.setRazaoSocial(request.getParameter("razao-social"));
			 fornecedor.setAtivo(Boolean.valueOf(request.getParameter("ativo")));
			 
			 
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
		 
			 fornecedor.setAtivo(true); 
			 
			 try {
				 System.out.println("AÇÃO: " + acao );
				 if(acao.equals("I")) {				  
					 if(Dao.crudFornecedor(acao, fornecedor)) {
						 End.crudEndereco(acao, cnpj, endereco) ;
						 Cont.crudContato(acao, cnpj, contato);
						 System.out.println("CRIADO COM SUCESSO");
						 	}							
						}
				 else{  
					 fornecedor.setIdForn(idForn);
					 Dao.crudFornecedor(acao,fornecedor);	
					 End.crudEndereco(acao, cnpj, endereco);
					 Cont.crudContato(acao, cnpj, contato);
					System.out.println("ALTERADO COM SUCESSO: " + fornecedor.getIdForn());	
				 }				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ERRO AO INSERIR FORNECEDOR");
			}
			  response.sendRedirect("ControlFornecedor?action=tabela");		
	}	
}