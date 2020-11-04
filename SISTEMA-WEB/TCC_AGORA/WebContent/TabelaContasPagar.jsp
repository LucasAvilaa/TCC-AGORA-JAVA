<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <title>CONTAS A PAGAR</title>
      <link rel="shortcut icon" href="img/Logo_Padaria.png"/>
      <link rel="stylesheet" type="text/css" href="css/estilo2.css" />
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
         	<h1 class="text-center margintop" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center">CONTAS A PAGAR</span></h1>
        
            
         <br />
         <p>
            <a href="ControlContasPagar?action">
          		 <button class="btn btn-success" style="height: 2.2em; width: 8em;"> ADICIONAR
            <img src="img/plus.svg" style="width: 31px; height: 28px; " title="ADICIONAR" />
            </button>
            </a>
         </p>
         <table border="1" class="table table-hover table-dark">
            <thead>
               <tr>
                  <th style="width: 147px; ">REFERÊNCIA</th>
                  <th style="width: 141px; ">DESCRIÇÃO</th> 
                  <th style="width: 141px; ">CATEGORIA</th> 
                  <th style="width: 193px; ">DATA EMISSÃO</th> 
                  <th style="width: 105px; ">VENCIMENTO</th>
                  <th style="width: 181px; ">VALOR TOTAL</th>
                  <th colspan="2" style="text-align: center" >AÇÃO</th>
               </tr>
            </thead>
            <tbody>
               <c:forEach items="${conta}" var="conta">
                  <tr>
                     <td>
                        <c:out value="CONTA/${conta.idPagar}" />
                     </td>
                     <td>
                        <c:out value="${conta.descricao}" />
                     </td>
                     <td>
                        <c:out value="${conta.categoria}" />
                     </td>
                     <td>
                        <fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss" value="${conta.tbCompra.dataCriada}"/> 
                     </td>
                     <td>
                     	<fmt:formatDate pattern="dd/MM/yyyy" value="${conta.dataVencimento}"/> 
                     </td>
                     <td>
                        <c:out value="R$ ${conta.valorPagar}" />
                     </td> 
                     <td><a href='ControlContasPagar?action=Edit&idPagar=<c:out value="${conta.idPagar}"/>'><button class="btn btn-success" style="height: 2.2em; width: 3em;"><img src="img/edit.svg" style="width: 21px; height: 21px; " title="EDITAR" /></button></a></td>
                     <td><a href='ControlContasPagar?action=Delete&idPagar=<c:out value="${conta.idPagar}"/>'><button class="btn" style="height: 2.2em; width: 3em; background-color: #ee0000"><img src="img/trash-2.svg" style="width: 21px; height: 21px; " title="EXCLUIR" /></button></a></td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
      </f:view>
   </body>
</html>