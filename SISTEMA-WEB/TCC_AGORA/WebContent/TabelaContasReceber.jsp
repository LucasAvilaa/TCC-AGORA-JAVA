<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>CONTAS A RECEBER</title>
      <link rel="shortcut icon" href="img/Logo_Padaria.png"/>
      <link rel="stylesheet" type="text/css" href="css/CadastroCliente.css" />
   </head>
   <body>
   	<%
   		String usuario = (String) session.getAttribute("usuario");
   		if(usuario == null){
   			response.sendRedirect("Login.xhtml");
   		}
   	%>
      <jsp:include page="index.jsp" flush="false">
         <jsp:param name="cabecalho" value="cabecalho"/>
      </jsp:include>
      <f:view> 
         	<h1 class="text-center margintop" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center">Contas a receber</span></h1>
   
            
         <br />
         <p>
            <a href="ControlContasReceber?action">
           <button class="btn btn-success" style="height: 2.2em; width: 8em;"> Adicionar
            <img src="img/plus.svg" style="width: 31px; height: 28px; " title="ADICIONAR" />
            </button>
            </a>
         </p>
         <table border="1" class="table table-hover table-dark"
         >
            <thead>
               <tr>
                  <th style="width: 147px; ">REFERÊNCIA</th>
                  <th style="width: 141px; ">METODO PAGAMENTO</th> 
                  <th style="width: 141px; ">DINHEIRO</th> 
                  <th style="width: 193px; ">DEBITO</th> 
                  <th style="width: 105px; ">CREDITO</th>  
                  <th style="width: 181px; ">DATA VENDA</th>
                  <th style="width: 181px; ">DATA PREVISTA RECEBER</th>
                  <th colspan="2" style="width: 72px; ">AÇÃO</th>
               </tr>
            </thead>
            <tbody>
               <c:forEach items="${receber}" var="receber">
                  <tr>
                     <td>
                        <c:out value="VENDA/ ${receber.idReceber}" />
                     </td>
                     <td>
                        <c:out value="${receber.metodoPagamento}" />
                     </td>
                     <td>
                        <c:out value="${receber.dinheiro}" />
                     </td>
                     <td>
                        <c:out value="${receber.debito}" />
                     </td>
                     <td>
                        <c:out value="${receber.credito}" />
                     </td> 
                     <td> 
                     	<fmt:formatDate pattern="dd/MM/yyyy" value="${receber.dataCompra}"/> 
                     </td>
                     <td>
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${receber.dataPrevistaReceber}"/>
                     </td> 
                     <td><a href='ControlContasReceber?action=Edit&idReceber=<c:out value="${receber.idReceber}"/>'><button class="btn btn-success" style="height: 2.2em; width: 8em;"> Editar <img src="img/edit.svg" style="width: 21px; height: 21px; " title="EXCLUIR"></button></a></td>
                     <td><a href='ControlContasReceber?action=Delete&idReceber=<c:out value="${receber.idReceber}"/>'><button class="btn btn-success" style="height: 2.2em; width: 8em;"> Excluir <img src="img/trash-2.svg" style="width: 21px; height: 21px; " title="EXCLUIR"></button></a></td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
      </f:view>
   </body>
</html>