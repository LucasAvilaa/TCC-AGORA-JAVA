<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
   <head>
      <meta content="UTF-8" />
      <title>TABELA DE CLIENTES</title>
      <link rel="shortcut icon" href="img/Logo_Padaria.png"/>
     <link rel="stylesheet" type="text/css" href="css/estilo2.css" />
  	  <style>
	th, td{
		padding: 6px;
	}
	table{
		width: 99%;
		margin: 0 15px 0 10px; 
		border: none;
		border-radius: 0px 15px 15px 15px;
	}
</style>
   </head>
   <body>
   	<%
   		String usuario = (String) session.getAttribute("usuario");
   		if(usuario == null){
   			response.sendRedirect("Login.xhtml");
   		}
   	%>
      <jsp:include page="index.jsp"></jsp:include>
      <f:view>
        
         	<h1 class="text-center margintop" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center">LISTA DE CLIENTES</span></h1>
          <br />
         <p>
            <a href="ControlCliente?action">
             <button class="btn btn-success" style="height: 2.2em; width: 8em;"> ADICIONAR
            <img src="img/plus.svg" style="width: 31px; height: 28px; " title="ADICIONAR" />
            </button>
            </a>
         </p>
         <table border="1" class="table-dark">
            <thead>
               <tr>
                  <th >NOME</th>
                  <th >SOBRENOME</th>
                  <th >RG</th>
                  <th>CPF</th>
                  <th>DATA NASCIMENTO</th>
                  <th >SEXO</th>
                  <th colspan="2" style="text-align: center" >AÇÃO</th>
               </tr>
            </thead>
            <tbody>
               <c:forEach items="${cliente}" var="cliente">
                  <tr>
                     <td>
                        <c:out value="${cliente.nome}" />
                     </td>
                     <td>
                        <c:out value="${cliente.sobrenome}" />
                     </td>
                     <td>
                        <c:out value="${cliente.rg}" />
                     </td>
                     <td>
                        <c:out value="${cliente.cpf}" />
                     </td>
                     <td>
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${cliente.dtNasc}" />
                     </td>
                     <td>
                        <c:out value="${cliente.sexo}" />
                     </td>
                     <td><a href='ControlCliente?action=Edit&idCli=<c:out value="${cliente.idCli}"/>&cod=<c:out value="${cliente.cpf}"/>'><button class="btn btn-success" style="height: 2.2em; width: 3em;"><img src="img/edit.svg" style="width: 21px; height: 21px; " title="EDITAR" /></button></a></td>
                     <td><a href='ControlCliente?action=Delete&idCli=<c:out value="${cliente.idCli}"/>&cod=<c:out value="${cliente.cpf}"/>'><button class="btn" style="height: 2.2em; width: 3em; background-color: #ee0000"><img src="img/trash-2.svg" style="width: 21px; height: 21px; " title="EXCLUIR" /></button></a></td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
      </f:view>
   </body>
</html>