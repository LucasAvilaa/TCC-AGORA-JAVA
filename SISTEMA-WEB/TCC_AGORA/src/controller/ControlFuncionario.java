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
import dao.DaoContato;
import dao.DaoEndereco;
import dao.DaoFuncionario;
import model.TbContato;
import model.TbEndereco;
import model.TbFuncionario;
import model.TbLogin;

/**
 * Servlet implementation class Controlfuncionario2
 */
@WebServlet(name = "funcionarios", urlPatterns={"/ControlFuncionario"})
public class ControlFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String tabela = "/Tabelafuncionario.jsp";
    private static String criar_editar = "/funcionario.jsp"; 
	private DaoFuncionario Dao;
	private DaoEndereco End;
	private DaoContato Cont;
	private String cpf = null;
	private String acao = null; 
	private String idFunc = null;
	private TbLogin login = new TbLogin();
	private TbFuncionario funcionario = new TbFuncionario(); 
	private TbEndereco endereco = new TbEndereco();
	private TbContato contato = new TbContato();
	
    public ControlFuncionario() {
        super();
        Dao = new DaoFuncionario(); 
        End = new DaoEndereco();
        Cont = new DaoContato(); 
        login = new TbLogin();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String forward = "";
		 String action = request.getParameter("action");  
		 String idFunc = request.getParameter("idFunc"); 
		 if(idFunc != null) {  
			 idFunc= String.valueOf(idFunc);
			 funcionario.setIdFunc(idFunc);
		 }		 
		 if(action.equalsIgnoreCase("tabela")) {			 	
				 try {				
					request.setAttribute("funcionario", Dao.listaFuncionario());
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
						Dao.crudFuncionario(acao, funcionario);
						End.crudEndereco(acao, cpf, endereco);
						Cont.crudContato(acao, cpf, contato);
						
						request.setAttribute("funcionario", Dao.listaFuncionario());
						request.setAttribute("endereco", End.listaEndereco());
						request.setAttribute("contato", Cont.listaContato());
						forward = tabela;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
		 }
		else if(action.equalsIgnoreCase("edit")){   
			request.setAttribute("funcionario", Dao.funcionarioPorId(funcionario));
			request.setAttribute("endereco", End.enderecoPorId(cpf));
			request.setAttribute("contato", Cont.contatoPorId(cpf));
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
			 cpf = request.getParameter("cpf");
			 
		  	 funcionario.setIdFunc(request.getParameter("idFunc"));
			 funcionario.setNome(request.getParameter("nome"));
			 funcionario.setSobrenome(request.getParameter("sobrenome"));
			 funcionario.setRg(request.getParameter("rg"));
			 funcionario.setCpf(request.getParameter("cpf"));  
			 funcionario.setSexo(request.getParameter("sexo")); 
			 funcionario.setCargo(request.getParameter("cargo"));
			 
			 endereco.setCep(request.getParameter("cep"));
			 endereco.setRua(request.getParameter("rua"));
			 
			 login.setUsuario(request.getParameter("login"));
			 login.setSenha(request.getParameter("senha"));
			 
			 if(request.getParameter("numero") != null) {
				 endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
			 }
			
			 endereco.setBairro(request.getParameter("bairro"));
			 endereco.setEstado(request.getParameter("estado"));
			 endereco.setCidade(request.getParameter("cidade"));
	 
			 contato.setEmail(request.getParameter("email"));
			 contato.setNumero(request.getParameter("celular"));
		 
			 funcionario.setAtivo(true); 
			 
			 Date data = null;
			 if(request.getParameter("data") != null) {
				 try { 				
					data = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("data"));
					funcionario.setDtNasc(data);
				} catch (ParseException e) { 
					e.printStackTrace();
					System.out.println("ERRO NA CONVERSÃO DA DATA");
				}				 
			 }
			 try {
				 System.out.println("AÇÃO: " + acao );
				 if(acao.equals("I")) {				  
					 if(Dao.crudFuncionario(acao, funcionario)) {
						 End.crudEndereco(acao, cpf, endereco) ;
						 Cont.crudContato(acao, cpf, contato);
						 System.out.println("CRIADO COM SUCESSO");
						 	}							
						}
				 else{  
					 funcionario.setIdFunc(idFunc);
					 Dao.crudFuncionario(acao,funcionario);	
					 End.crudEndereco(acao, cpf, endereco);
					 Cont.crudContato(acao, cpf, contato);
					System.out.println("ALTERADO COM SUCESSO: " + funcionario.getIdFunc());	
				 }				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ERRO AO INSERIR FUNCIONARIO");
			}
			  response.sendRedirect("ControlFuncionario?action=tabela");		
	}	
}