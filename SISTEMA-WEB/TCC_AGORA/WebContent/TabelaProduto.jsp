<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>TABELA DE PRODUTOS</title>
      <link rel="shortcut icon" href="img/Logo_Padaria.png"/>
      <link rel="stylesheet" type="text/css" href="css/CadastroCliente.css">
   </head>
   <body>
      <jsp:include page="index.xhtml" flush="false">
         <jsp:param name="cabecalho" value="cabecalho"/>
      </jsp:include>
      <f:view> 
         	<h2>LISTA DE PRODUTOS</h2>
            
         <br />
         <p>
            <a href="ControlProduto?action">
            <img src="img/adicionar.png" style="width: 31px; height: 28px; " title="ADICIONAR" />
            </a>
         </p>
         <table border="1">
            <thead>
               <tr>
                  <th style="width: 147px; ">PRODUTO</th>
                  <th style="width: 141px; ">DESCRIÇÃO</th>
                  <th style="width: 105px; ">CATEGORIA</th>
                  <th style="width: 181px; ">VALOR COMPRA</th>
                  <th style="width: 156px; ">VALOR VENDA</th> 
                  <th colspan="2">AÇÃO</th>
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
                     <td><a href='ControlProduto?action=Edit&idProd=<c:out value="${produto.idProduto}"/>'><img src="img/refresh-icon.png" style="width: 21px; height: 21px; " title="ATUALIZAR"></a></td>
                     <td><a href='ControlProduto?action=Delete&idProd=<c:out value="${produto.idProduto}"/>'><img src="img/delete.png" style="width: 21px; height: 21px; " title="EXCLUIR"></a></td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
      </f:view>
   </body>
</html>