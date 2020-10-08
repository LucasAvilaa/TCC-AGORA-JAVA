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

import dao.DaoCliente;
import dao.DaoContato;
import dao.DaoEndereco;
import model.TbCliente;
import model.TbContato;
import model.TbEndereco;
import model.TbLogin;

/**
 * Servlet implementation class ControlCliente2
 */
@WebServlet(name = "Clientes", urlPatterns={"/ControlCliente"})
public class ControlCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String tabela = "/TabelaCliente.jsp";
    private static String criar_editar = "/Cliente.jsp"; 
	private DaoCliente Dao;
	private DaoEndereco End;
	private DaoContato Cont;
	private String cpf;
	private String acao = null; 
	private String idcli;
	private TbLogin login = new TbLogin();
	private TbCliente cliente = new TbCliente(); 
	private TbEndereco endereco = new TbEndereco();
	private TbContato contato = new TbContato();
	
    public ControlCliente() {
        super();
        Dao = new DaoCliente(); 
        End = new DaoEndereco();
        Cont = new DaoContato(); 
        login = new TbLogin();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String forward = "";
		 String action = request.getParameter("action");  
		 String idCli = request.getParameter("idCli"); 
		 cpf = request.getParameter("cod");
		 if(idCli != null && cpf !=null) {  
			 idcli = String.valueOf(idCli);
			 cliente.setIdCli(idcli);
			 cliente.setCpf(cpf);
		 } 	
		 if(action.equalsIgnoreCase("tabela")) {			 	
				 try {				
					request.setAttribute("cliente", Dao.listaCliente());
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
						Dao.crudCliente(acao, cliente);
						End.crudEndereco(acao, cpf, endereco);
						Cont.crudContato(acao, cpf, contato);
						System.out.println("_____________________________________");
						System.out.println("ID CLIENTE DELETADO " + cliente.getIdCli());
						System.out.println("CPF CLIENTE DELETADO " + cliente.getCpf());
						request.setAttribute("cliente", Dao.listaCliente());
						request.setAttribute("endereco", End.listaEndereco());
						request.setAttribute("contato", Cont.listaContato());
						forward = tabela;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
		 }
		else if(action.equalsIgnoreCase("edit")){   
			request.setAttribute("cliente", Dao.ClientePorId(cliente));
			request.setAttribute("endereco", End.enderecoPorId(cpf));
			request.setAttribute("contato", Cont.contatoPorId(cpf));
			System.out.println("_____________________________________");
			System.out.println("ID CLIENTE ALTERANDO " + cliente.getIdCli());
			System.out.println("CPF CLIENTE ALTERANDO " + cliente.getCpf());
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
		 
			 cliente.setNome(request.getParameter("nome"));
			 cliente.setSobrenome(request.getParameter("sobrenome"));
			 cliente.setRg(request.getParameter("rg"));
			 cliente.setCpf(request.getParameter("cpf"));  
			 cliente.setSexo(request.getParameter("sexo"));  
			 
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
			 System.out.println("Ativo: " + Boolean.parseBoolean(request.getParameter("ativo")));
			 cliente.setAtivo(Boolean.parseBoolean(request.getParameter("ativo"))); 
			 
			 Date data = null;
			 if(request.getParameter("data") != null) {
				 try { 				
					data = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("data"));
					cliente.setDtNasc(data);
				} catch (ParseException e) { 
					e.printStackTrace();
					System.out.println("ERRO NA CONVERS�O DA DATA");
				}				 
			 }
			 try {
				 System.out.println("A��O: " + acao ); 			  
					 if(Dao.crudCliente(acao, cliente)) {
						 System.out.println("CLIENTE INSERIDO COM SUCESSO"); 
					 }
					 else {
						 System.out.println("ERRO AO INSERIR CLIENTE"); 
					 }
					 if(End.crudEndereco(acao, cpf, endereco)) {
						 System.out.println("ENDERECO INSERIDO COM SUCESSO");
					 }
					 else {
						 System.out.println("ERRO AO INSERIR ENDERECO"); 
					 }
					 if(Cont.crudContato(acao, cpf, contato) ) {
						 System.out.println("CONTATO INSERIDO COM SUCESSO");
						 System.out.println("_____________________________________");
					 }
					 else {
						 System.out.println("ERRO AO INSERIR CONTATO");
						 System.out.println("_____________________________________"); 
					 }
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ERRO AO INSERIR CLIENTE");
				System.out.println("_____________________________________");
			}
			  response.sendRedirect("ControlCliente?action=tabela");		
	}	
}