<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>ESTOQUE</title>
      <link rel="shortcut icon" href="img/Logo_Padaria.png"/>
      <link rel="stylesheet" type="text/css" href="css/CadastroCliente.css">
   </head>
   <body>
      <jsp:include page="index.xhtml" flush="false" >
         <jsp:param name="cabecalho" value="cabecalho"/>
      </jsp:include>
      <f:view>
         
         	<h2>PRODUTOS EM ESTOQUE</h2>
          
         <br />
         <p>
            <a href="ControlEstoque?action">
            <img src="img/adicionar.png" style="width: 31px; height: 28px; " title="ADICIONAR" />
            </a>
         </p>
         <table border="1">
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
                     <td><a href='ControlEstoque?action=Edit&idEstoque=<c:out value="${estoque.idEstoque}"/>'><img src="img/refresh-icon.png" style="width: 21px; height: 21px; " title="ATUALIZAR"></a></td>
                     <td><a href='ControlEstoque?action=Delete&idEstoque=<c:out value="${estoque.idEstoque}"/>'><img src="img/delete.png" style="width: 21px; height: 21px; " title="EXCLUIR"></a></td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
      </f:view>
   </body>
</html>