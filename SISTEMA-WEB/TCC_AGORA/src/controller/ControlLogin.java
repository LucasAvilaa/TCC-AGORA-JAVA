package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoLogin;
import model.TbLogin;

@WebServlet("/ControlLogin")
public class ControlLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ControlLogin() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TbLogin model = new TbLogin();
		model.setUsuario(request.getParameter("username"));
		model.setSenha(request.getParameter("password"));
		
	 	DaoLogin log = new DaoLogin(); 
	 	
	 	try {
			if(log.validaLogin(model)){ 
				request.setAttribute("usuario", model.getUsuario());
				response.sendRedirect("ControlCliente?action=tabela");				
				System.out.println("LOGADO COM SUCESSO");				
			} 	 
			else{
				response.sendRedirect("login.xhtml"); 
				System.out.println("USUARIO OU SENHA INCORRETO");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
