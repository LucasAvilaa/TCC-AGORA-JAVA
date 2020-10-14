<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>COMPRAS</title>
      <link rel="stylesheet" type="text/css" href="css/CadastroCliente.css">
   </head>
   <body>
      <jsp:include page="index.xhtml" flush="false" >
         <jsp:param name="cabecalho" value="cabecalho"/>
      </jsp:include>
      <f:view>
         <p>
         	<h2>COMPRAS</h2>
       	</p>      
         
         <br />
         <p>
            <a href="ControlCompra?action=CriarCompra">
            <img src="img/adicionar.png" style="width: 31px; height: 28px; " title="ADICIONAR" />
            </a>
         </p>
         <table border="1">
            <thead>
               <tr>
                  <th style="width: 141px; ">REFERÊNCIA</th>
                  <th style="width: 167px; ">DATA COMPRA</th>                  
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
                        <c:out value=" " />
                     </td>
                     <td> 
                        <c:out value="${compra.status}" />
                     </td> 
                     <td><a href='ControlCompra?action=Edit&idCompra=<c:out value="${compra.idCompra}"/>'><img src="img/refresh-icon.png" style="width: 21px; height: 21px; " title="ATUALIZAR"></a></td>
                     <td><a href='ControlCompra?action=Delete&idCompra=<c:out value="${compra.idCompra}"/>'><img src="img/delete.png" style="width: 21px; height: 21px; " title="EXCLUIR"></a></td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
      </f:view>
   </body>
</html>