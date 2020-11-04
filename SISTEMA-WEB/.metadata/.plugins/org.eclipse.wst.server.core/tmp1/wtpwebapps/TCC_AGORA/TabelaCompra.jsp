<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <title>COMPRAS</title>
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
       
         	<h1 class="text-center margintop" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center">COMPRAS</span></h1>
            
         
         <br />
         <p>
            <a href="ControlCompra?action=CriarCompra">
            <button class="btn btn-success" style="height: 2.2em; width: 8em;"> ADICIONAR
            <img src="img/plus.svg" style="width: 31px; height: 28px; " title="ADICIONAR" />
            </button>
            </a>
            
         </p>
         <table border="1" class="table table-hover table-dark">
            <thead>
               <tr>
                  <th style="width: 141px; ">REFERÊNCIA</th>
                  <th style="width: 167px; ">DATA CRIADA</th>                  
                  <th style="width: 197px; ">DATA FINALIZADA</th> 
                  <th style="width: 160px; ">TOTAL</th>
                  <th style="width: 154px; ">SITUAÇÃO</th>
                  <th colspan="2" style="width: 72px; ">AÇÃO</th>
               </tr>
            </thead>
            <tbody>
               <c:forEach items="${compra}" var="compra">
                  <tr>
                     <td>
                        <c:out value="COMP/${compra.idCompra}" />
                     </td>
                     <td>
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${compra.dataCriada}"/> 
                     </td>
                     <td>
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${compra.dataFinalizada}"/>
                     </td>
                     <td> 
                        <c:out value="R$ ${compra.total}" />
                     </td>
                     <td> 
                        <c:out value="${compra.status}" />
                     </td> 
                     <td><a href='ControlCompra?action=EditCompra&idCompra=<c:out value="${compra.idCompra}"/>'> <button class="btn btn-success" style="height: 2.2em; width: 8em;">EDITAR <img src="img/edit.svg" style="width: 25px; height: 25px;" title="ATUALIZAR" /> </button></a></td>
                     <td><a href='ControlCompra?action=DeleteCompra&idCompra=<c:out value="${compra.idCompra}"/>'><button class="btn btn-success" style="height: 2.2em; width: 8em;">EXCLUIR <img src="img/trash-2.svg" style="width: 21px; height: 21px; " title="EXCLUIR" /></button></a></td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
      </f:view>
   </body>
</html>