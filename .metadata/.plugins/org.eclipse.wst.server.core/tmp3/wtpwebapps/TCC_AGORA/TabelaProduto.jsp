<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <title>TABELA DE PRODUTOS</title>
      <link rel="shortcut icon" href="img/Logo_Padaria.png"/>
      <link rel="stylesheet" type="text/css" href="css/estilo2.css" />
        <style>
	th, td{
		padding: 6px;
	}
	table{
		width: 99%;
		margin: 0 10px 30px 10px; 
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
         	<h1 class="text-center margintop" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center">LISTA DE PRODUTOS</span></h1>
             
         <br />
         <p>
            <a href="ControlProduto?action">
            <button class="btn btn-success" style="height: 2.2em; width: 8em;"> ADICIONAR
            <img src="img/plus.svg" style="width: 31px; height: 28px; " title="ADICIONAR" />
            </button>
            </a>
         </p>
         <table border="1" class="table-dark">
            <thead>
               <tr>
                  <th style="width: 300px">PRODUTO</th>
                  <th>DESCRIÇÃO</th>
                  <th>CATEGORIA</th>
                  <th>VALOR COMPRA</th>
                  <th>VALOR VENDA</th> 
                  <th colspan="2" style="text-align: center" >AÇÃO</th>
               </tr>
            </thead>
            <tbody>
               <c:forEach items="${produto}" var="produto">
                  <tr>
                     <td>
                        <c:out value="${produto.nomeProduto}" />
                     </td>
                     <td>
                        <c:out value="${produto.descricaoProduto}" />
                     </td>
                     <td>
                        <c:out value="${produto.categoria}" />
                     </td>
                     <td>
                        <c:out value="${produto.valorUniCompra}" />
                     </td>
                     <td>
                        <c:out value="${produto.valorUniVenda}" />
                     </td> 
                     <td><a href='ControlProduto?action=Edit&idProd=<c:out value="${produto.idProduto}"/>'><button class="btn btn-success" style="height: 2.2em; width: 3em;"><img src="img/edit.svg" style="width: 21px; height: 21px; " title="EDITAR" /></button></a></td>
                     <td><a href='ControlProduto?action=Delete&idProd=<c:out value="${produto.idProduto}"/>'><button class="btn" style="height: 2.2em; width: 3em; background-color: #ee0000"><img src="img/trash-2.svg" style="width: 21px; height: 21px; " title="EXCLUIR" /></button></a></td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
      </f:view>
   </body>
</html>