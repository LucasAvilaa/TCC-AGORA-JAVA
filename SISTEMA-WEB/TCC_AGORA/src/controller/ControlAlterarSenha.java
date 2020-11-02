package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoLogin;
import model.TbFuncionario;
import model.TbLogin;

/**
 * Servlet implementation class ControlAlterarSenha
 */
@WebServlet("/ControlAlterarSenha")
public class ControlAlterarSenha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlAlterarSenha() {
        super(); 
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("AlterarSenha.xhtml"); 
	
	} 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DaoLogin log = new DaoLogin();  
		
		TbFuncionario funcionario = new TbFuncionario();
		TbLogin login = new TbLogin();
		login.setUsuario(request.getParameter("username"));
		login.setSenha(request.getParameter("password"));
		funcionario.setCpf(request.getParameter("cpf"));
		
		if(log.alterarSenha(login, funcionario)) {
			System.out.println("SENHA ALTERADA COM SUCESSO");
			response.sendRedirect("Login.xhtml"); 
		}else {
			System.out.println("ERRO AO ALTERAR SENHA");
			response.sendRedirect("AlterarSenha.xhtml"); 
		}

	
	}

}
