<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>CONTAS A PAGAR</title>
      <link rel="shortcut icon" href="img/Logo_Padaria.png"/>
      <link rel="stylesheet" type="text/css" href="css/CadastroCliente.css">
   </head>
   <body>
      <jsp:include page="index.xhtml" flush="false">
         <jsp:param name="cabecalho" value="cabecalho"/>
      </jsp:include>
      <f:view> 
         	<h1 class="text-center margintop" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center">Contas a pagar</span></h1>
        
            
         <br />
         <p>
            <a href="ControlContasPagar?action">
          		 <button class="btn btn-success" style="height: 2.2em"> Adicionar
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
                  <th colspan="2" style="width: 72px; ">AÇÃO</th>
               </tr>
            </thead>
            <tbody>
               <c:forEach items="${conta}" var="conta">
                  <tr>
                     <td>
                        <c:out value="CONTA/${conta.idPagar}" />
                     </td>
                     <td>
                        <c:out value="COMPRA/${conta.tbCompra.idCompra}" />
                     </td>
                     <td>
                        <c:out value="${conta.categoria}" />
                     </td>
                     <td>
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${conta.tbCompra.dataCriada}"/>
                     </td>
                     <td>
                     	<fmt:formatDate pattern="dd/MM/yyyy" value="${conta.dataVencimento}"/> 
                     </td>
                     <td>
                        <c:out value="R$ ${conta.valorPagar}" />
                     </td> 
                     <td><a href='ControlContasPagar?action=Edit&idPagar=<c:out value="${conta.idPagar}"/>'><button class="btn btn-success" style="height: 2.2em;"> Editar <img src="img/edit.svg" style="width: 21px; height: 21px; " title="EXCLUIR"></button></a></td>
                     <td><a href='ControlContasPagar?action=Delete&idPagar=<c:out value="${conta.idPagar}"/>'><button class="btn btn-success" style="height: 2.2em;"> Excluir <img src="img/trash-2.svg" style="width: 21px; height: 21px; " title="EXCLUIR"></button></a></td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
      </f:view>
   </body>
</html>