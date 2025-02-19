package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoLogin;
import model.TbLogin;

@WebServlet("/ControlLogin")
public class ControlLogin extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	public ControlLogin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String forward = "";
		if (action.equalsIgnoreCase("Deslogar")) {
			request.getSession().invalidate();
			forward = "Login.xhtml";
		} 

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DaoLogin log = new DaoLogin();  
		  
		TbLogin model = new TbLogin();
		model.setUsuario(request.getParameter("username"));
		model.setSenha(request.getParameter("password"));

		try { 
			if (log.validaLogin(model)) {
				HttpSession http = request.getSession();
				http.setAttribute("usuario", model.getUsuario());
				response.sendRedirect("index.jsp");
				System.out.println("LOGADO COM SUCESSO");
			} 
			 else {
				response.sendRedirect("Login.xhtml");
				System.out.println("USUARIO OU SENHA INCORRETO");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
