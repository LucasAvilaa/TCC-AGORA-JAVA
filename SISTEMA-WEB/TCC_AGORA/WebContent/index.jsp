<?xml version="1.0" encoding="ISO-8859-1" ?> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//Dth XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/Dth/xhtml1-transitional.dth">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
		<% String a = (String) session.getAttribute("usuario");
			if(a == null){
				response.sendRedirect("login.xhtml");
			}else{
				out.print("Bem vindo " + a + " <br />");
			}
		
		%>
	
	<h1>Bem vindo</h1>
	
	<a href="deslogar.jsp">	DESLOGAR </a> 
	
</body>
</html>