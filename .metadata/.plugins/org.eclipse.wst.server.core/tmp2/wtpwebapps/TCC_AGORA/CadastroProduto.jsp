<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>CADASTRO DE PRODUTOS</title>
      <link rel="shortcut icon" href="img/Logo_Padaria.png"/>
      <link rel="stylesheet" type="text/css" href="css/CadastroCliente.css">
      <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
      <script src="//cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
   </head>
   <body>
      <jsp:include page="index.xhtml" flush="false">
         <jsp:param name="cabecalho" value="cabecalho"/>
      </jsp:include>
  <f:view>  
         <form action="ControlProduto" method="POST" name="cadastroProduto">
            
            	<h2>CADASTRO DE PRODUTO</h2>
           
            <br />
            <p><input value="ENVIAR" type="submit" id="btn"> <a href="ControlProduto?action=Tabela">CANCELAR</a> </p>
            <fieldset id="informacoes">
               <legend>INFORMAÇÕES BÁSICAS </legend>
               <p>
                  <label>
                     NOME: <input name="nomeProduto"  maxlength="50" value="<c:out value="${produto.nomeProduto}"/>" required="required" style="width: 291px; "/>
                  </label>
               </p>
               <p>
                  <label>
                     DESCRIÇÃO: <input name="descricao" maxlength="50" value="<c:out value="${produto.descricaoProduto}"/>" required="required"  style="width: 248px; "/>
                  </label>
               </p>
               <p>
                  <label>
                     CATEGORIA: 
                  		 <h:selectOneMenu style="width: 195px; height: 24px" id="categoria">
	                        <f:selectItem itemValue="#{produto.categoria}" itemDisabled="true"/>
	                        <f:selectItem noSelectionOption="true" itemValue="___________" itemDisabled="true"/>
	                        <f:selectItem itemValue="MERCEARIA" itemLabel="MERCEARIA"/>
	                        <f:selectItem itemValue="PRODUCAO" itemLabel="PRODUÇÃO"/>
	                        <f:selectItem itemValue="REFRIGERANTE" itemLabel="REFRIGERANTE   "/>
	                         <f:selectItem itemValue="LANCHES" itemLabel="LANCHES"/>
	                         <f:selectItem itemValue="DOCES" itemLabel="DOCES"/>
	                        <f:selectItem itemValue="COPA" itemLabel="COPA"/>
                     	</h:selectOneMenu>
                  </label>
                </p>
                <p>
                  <label>
                     VALOR COMPRA: <input name="vUnitCompra" value="<c:out value="${produto.valorUniCompra}"/>" placeholder="R$000,00"  required="required" style="width: 90px; "/>
                  </label>
                  <label>
                     VALOR VENDA: <input name="vUnitVenda" type="text" value="<c:out value="${produto.valorUniVenda}"/>" required="required" placeholder="R$000,00"  style="width: 90px; ">
                  </label>
               </p>  
                 <p>  
                  <label>
                     FORNECEDOR: 
                     <h:selectOneMenu style="width: 180px; height: 24px" id="fornecedor" >                     
                        <f:selectItem itemValue="#{produto.tbFornecedore.razaoSocial}"/> 
                        <f:selectItem noSelectionOption="true" itemValue="___________________" itemDisabled="true"/> 
                        <f:selectItems value="#{tbFornecedore.fornecedores}" itemValue="#{tbFornecedore.fornecedores}"/>
                     </h:selectOneMenu>
                  </label>
               </p> 
               
            </fieldset> 
         </form>
   </f:view>
   </body> 
   </body> 
</html>	