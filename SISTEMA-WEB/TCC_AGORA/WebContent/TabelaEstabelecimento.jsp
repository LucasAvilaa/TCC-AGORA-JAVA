<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Tabela Filiais</title>
      <link rel="stylesheet" type="text/css" href="css/CadastroCliente.css">
   </head>
   <body>
      <jsp:include page="index.xhtml">
         <jsp:param name="cabecalho" value="cabecalho"/>
      </jsp:include>
      <f:view>
         <p>
         	<h2>LISTA DE FILIAIS</h2>
       	</p>
                 
         <br />
         <p>
            <a href="ControlEstabelecimento?action">
            <img src="img/adicionar.png" style="width: 31px; height: 28px; " title="ADICIONAR" />
            </a>
         </p>
         <table border="1">
            <thead>
               <tr>
                  <th style="width: 357px; ">RAZÃO SOCIAL</th>
                  <th style="width: 166px; ">CNPJ</th> 
                  <th colspan="2" style="width: 72px; ">AÇÃO</th>
               </tr>
            </thead>
            <tbody>
               <c:forEach items="${estabelecimento}" var="estabelecimento">
                  <tr>
                     <td>
                        <c:out value="${estabelecimento.razaoSocial}" />
                     </td>
                     <td>
                        <c:out value="${estabelecimento.cnpj}" />
                     </td>     
                        <td><a href='ControlEstabelecimento?action=Edit&idEstab=<c:out value="${estabelecimento.idEstab}"/>&cod=<c:out value="${estabelecimento.cnpj}"/>'><img src="img/refresh-icon.png" style="width: 21px; height: 21px; " title="ATUALIZAR"></a></td>
                 		<td><a href='ControlEstabelecimento?action=Delete&idEstab=<c:out value="${estabelecimento.idEstab}"/>&cod=<c:out value="${estabelecimento.cnpj}"/>'><img src="img/delete.png" style="width: 21px; height: 21px; " title="EXCLUIR"></a></td>
                      </tr>
               </c:forEach>  
            </tbody>
         </table>
      </f:view>
   </body>
</html>