<%@page import="controllerOLD.ControlLogin"%>
<%
	String usuario = request.getParameter("username"); 
	String senha = request.getParameter("password");  
 
 	ControlLogin log = new ControlLogin(); 
 	
 	if(log.validaLogin(usuario, senha)){
 		session.setAttribute("usuario", usuario);
 		response.sendRedirect("index.jsp");	 
 	} 	 
 	else{
 		response.sendRedirect("login.xhtml"); 
	}
%>