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
    private static String tabela = "/TabelaFornecedor.jsp";
    private static String criar_editar = "/Fornecedor.jsp"; 
	private DaoFornecedor Dao;
	private DaoEndereco End;
	private DaoContato Cont;
	private String cnpj = null;
	private String acao = "I"; 
	private String idForn;
	private TbFornecedore fornecedor = new TbFornecedore(); 
	private TbEndereco endereco = new TbEndereco();
	private TbContato contato = new TbContato();
	
    public ControlFornecedor() {
        super();
        Dao = new DaoFornecedor(); 
        End = new DaoEndereco();
        Cont = new DaoContato();
        fornecedor.setAtivo(true);        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String forward = "";
		 String action = request.getParameter("action");  
		 String idFor = request.getParameter("idForn"); 
		 		cnpj = request.getParameter("cod");
		 		if(acao.equals("I")) {
		 			request.setAttribute("acao", "I");
		 		}else {
		 			request.setAttribute("acao", "A");		 			
		 		}
		
 		if(idFor != null) {  
			 idForn = String.valueOf(idFor);
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
					if(End.crudEndereco(acao, cnpj, endereco)) {
						System.out.println("ENDEREÇO DELETADO COM SUCESSO");
					}
					if (Cont.crudContato(acao, cnpj, contato)) {
						System.out.println("CONTADO DELETADO COM SUCESSO");
					}
					if (Dao.crudFornecedor(acao, fornecedor)) {
						System.out.println("FORNECEDOR DELETADO COM SUCESSO");
						System.out.println("______________________________________");
					} 
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
			request.setAttribute("ativo", Dao.fornecedorPorId(fornecedor));
			System.out.println("_____________________________________");
			System.out.println("ID FORNECEDOR ALTERANDO " + fornecedor.getIdForn()); 
			System.out.println("CNPJ FORNECEDOR ALTERANDO " + cnpj); 
			acao = "A";
			forward = criar_editar;
		}
		else {
			forward = criar_editar; 
			request.setAttribute("ativo", Dao.fornecedorPorId(fornecedor));
			acao = "I";
		}		 
		 RequestDispatcher view = request.getRequestDispatcher(forward);
		 view.forward(request, response);
	}
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(acao.equals("I")) {
 			request.setAttribute("acao", "I");
 		}else {
 			request.setAttribute("acao", "A");		 			
 		}
		
			if (acao.equals("I")) {
				cnpj = request.getParameter("cnpj");
				fornecedor.setAtivo(true);
			}
			 
			 fornecedor.setRazaoSocial(request.getParameter("razao-social"));
			 fornecedor.setCnpj(request.getParameter("cnpj"));
			 fornecedor.setAtivo(Boolean.valueOf(request.getParameter("ativo")));
			 
			 if(request.getParameter("numero") != "") {
				 endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
			 }	 
			 
			 if (request.getParameter("cep") != "") {
				 endereco.setCep(request.getParameter("cep"));
			}
			 endereco.setRua(request.getParameter("rua"));
			 endereco.setBairro(request.getParameter("bairro"));
			 endereco.setEstado(request.getParameter("estado"));
			 endereco.setCidade(request.getParameter("cidade"));
	 
			 contato.setEmail(request.getParameter("email"));
			 contato.setNumero(request.getParameter("celular"));
		 
			 fornecedor.setAtivo(Boolean.parseBoolean(request.getParameter("ativo"))); 
			 
			 try {
				 System.out.println("AÇÃO: " + acao ); 	 
					 if(Dao.crudFornecedor(acao, fornecedor)) {
						 System.out.println("FORNECEDOR INSERIDO COM SUCESSO"); 
					 }
					 else {
						 System.out.println("ERRO AO INSERIR FORNECEDOR"); 
					 }
				 	 if(End.crudEndereco(acao, cnpj, endereco)) {
						 System.out.println("ENDERECO INSERIDO COM SUCESSO");
					 }
					 else {
						 System.out.println("ERRO AO INSERIR ENDERECO");  
					 }
				 	 if(Cont.crudContato(acao, cnpj, contato) ) {
						 System.out.println("CONTATO INSERIDO COM SUCESSO");
						 System.out.println("_____________________________________");
					 }
					 else {
						 System.out.println("ERRO AO INSERIR CONTATO");
						 System.out.println("_____________________________________"); 
					 }
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ERRO TRY/CATCH - ERRO AO INSERIR FORNECEDOR");
				System.out.println("_____________________________________");
			}
			  response.sendRedirect("ControlFornecedores?action=tabela");		
	}	
}