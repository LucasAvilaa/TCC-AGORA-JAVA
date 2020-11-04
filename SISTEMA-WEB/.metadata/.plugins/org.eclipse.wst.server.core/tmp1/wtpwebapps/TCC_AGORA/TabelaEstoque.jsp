<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <title>ESTOQUE</title>
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
         
         	<h1 class="text-center margintop" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center">Produtos Estoque</span></h1>
            
          
         <br />
         <p>
            <a href="ControlEstoque?action">
             <button class="btn btn-success" style="height: 2.2em; width: 8em;"> Adicionar
            <img src="img/plus.svg" style="width: 31px; height: 28px; " title="ADICIONAR" />
            </button>
            </a>
         </p>
         <table border="1" class="table table-hover table-dark">
            <thead>
               <tr>
                  <th style="width: 467px; ">PRODUTO</th>
                  <th style="width: 141px; ">CATEGORIA</th>                  
                  <th style="width: 156px; ">QUANTIDADE</th> 
                  <th style="width: 160px; ">DATA ENTRADA</th>
                  <th style="width: 216px; ">DATA VENCIMENTO</th>
                  <th colspan="2" style="width: 72px; ">AÇÃO</th>
               </tr>
            </thead>
            <tbody>
               <c:forEach items="${estoque}" var="estoque">
                  <tr>
                     <td>
                        <c:out value="${estoque.tbProduto.nomeProduto}" />
                     </td>
                     <td>
                        <c:out value="${estoque.tbProduto.categoria}" />
                     </td>
                     <td>
                        <c:out value="${estoque.quantidade}" />
                     </td>
                     <td> 
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${estoque.dataEntrada}"/>
                     </td>
                     <td> 
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${estoque.dataVencimento}"/>
                     </td> 
                     <td><a href='ControlEstoque?action=Edit&idEstoque=<c:out value="${estoque.idEstoque}"/>'><button class="btn btn-success" style="height: 2.2em; width: 8em;"> Editar <img src="img/edit.svg" style="width: 21px; height: 21px; " title="EXCLUIR" /></button></a></td>
                     <td><a href='ControlEstoque?action=Delete&idEstoque=<c:out value="${estoque.idEstoque}"/>'><button class="btn btn-success" style="height: 2.2em; width: 8em;"> Excluir <img src="img/trash-2.svg" style="width: 21px; height: 21px; " title="EXCLUIR" /></button></a></td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
      </f:view>
   </body>
</html>