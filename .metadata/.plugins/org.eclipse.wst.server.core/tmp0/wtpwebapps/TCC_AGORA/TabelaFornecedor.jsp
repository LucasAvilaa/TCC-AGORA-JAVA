<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>TABELA DE FORNECEDORES</title>
      <link rel="shortcut icon" href="img/Logo_Padaria.png"/>
      <link rel="stylesheet" type="text/css" href="css/CadastroCliente.css">
   </head>
   <body>
      <jsp:include page="index.xhtml">
         <jsp:param name="cabecalho" value="cabecalho"/>
      </jsp:include>
      <f:view>
      
         	<h2>LISTA DE FORNECEDORES</h2>
       
                 
         <br />
         <p>
            <a href="ControlFornecedores?action">
            <img src="img/adicionar.png" style="width: 31px; height: 28px; " title="ADICIONAR" />
            </a>
         </p>
         <table border="1">
            <thead>
               <tr>
                  <th style="width: 244px; ">RAZÃO SOCIAL</th>
                  <th style="width: 166px; ">CNPJ</th>
                  <th style="width: 244px; ">CIDADE</th>
                  <th style="width: 166px; ">ESTADO</th>
                  <th colspan="2" style="width: 72px; ">AÇÃO</th>
               </tr>
            </thead>
            <tbody>
               <c:forEach items="${fornecedor}" var="fornecedor">
                  <tr>
                     <td>
                        <c:out value="${fornecedor.razaoSocial}" />
                     </td>
                     <td>
                        <c:out value="${fornecedor.cnpj}" />
                     </td>
                     <td>
                        <c:out value="${fornecedor.tbPrincipalPessoas.tbEnderecos.cidade}" />
                     </td>
                     <td>
                        <c:out value="${fornecedor.tbPrincipalPessoas.tbEnderecos.estado}" />
                     </td>
                     <td><a href='ControlFornecedores?action=Edit&idForn=<c:out value="${fornecedor.idForn}"/>&cod=<c:out value="${fornecedor.cnpj}"/>'><img src="img/refresh-icon.png" style="width: 21px; height: 21px; " title="ATUALIZAR"></a></td>
                     <td><a href='ControlFornecedores?action=Delete&idForn=<c:out value="${fornecedor.idForn}"/>&cod=<c:out value="${fornecedor.cnpj}"/>'><img src="img/delete.png" style="width: 21px; height: 21px; " title="EXCLUIR"></a></td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
      </f:view>
   </body>
</html>