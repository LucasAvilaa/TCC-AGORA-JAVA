<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <title>CADASTRO DE PRODUTOS</title>
      <link rel="shortcut icon" href="img/Logo_Padaria.png"/>
      <link rel="stylesheet" type="text/css" href="css/estilo2.css" />
      <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
      <script src="//cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
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
         <form action="ControlProduto" method="post" name="cadastroProduto">
            
            	<h1 class="text-center margintop" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center;">CADASTRO DE PRODUTOS</span></h1>
            <div class="card border bg-dark text-white" style="background-color: #fff;  margin-top: 15px; position: relative;left: 6%; width: 27em; font-family: sans-serif">
           
           
            <br />
            <p><input value="ENVIAR" type="submit" id="btn" class="btn btn-success" style="width: 10em; height: 2.5em; margin-right:0.4em; margin-left: 3em" />
               <a href="ControlProduto?action=Tabela" class="btn btn-danger" style="width: 10em; height: 2.5em;"> CANCELAR</a> </p>
            <fieldset id="informacoes">
               <legend style="text-align:center; margin-top: 1em; margin-bottom: 1em;" class="bg-light text-dark">INFORMAÇÕES BÁSICAS </legend>
               <p style="text-align: center">
                  <label class="font-weight-bold" style="text-align: left">
                  NOME: <input name="nomeProduto" style ="height: 1.5em; width: 20em" name="descricao" class="input-group mb-3"  maxlength="50" value="<c:out value="${produto.nomeProduto}"/>" required="required" />
                  </label>
               </p>
               <p style="text-align: center">
                  <label  class="font-weight-bold" style="text-align: left">
                     DESCRIÇÃO: <input name="descricao" maxlength="50" value="<c:out value="${produto.descricaoProduto}"/>"  style="height: 1.5em; width: 20em" class="inputItens input-group mb-3"/>
                  </label>
               </p>
               <p style="text-align: center">
                  <label  class="font-weight-bold" style="text-align: left">
                     CATEGORIA: 
                     	<br />
                  		 <h:selectOneMenu style="height: 1.5em; width: 20em" id="categoria" >
	                        <f:selectItem itemLabel="#{produto.categoria}"  itemValue="#{produto.categoria}"  />
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
                <p style="text-align:center">
                  <label class="font-weight-bold" style="text-align: left; padding-left: 3.3em;">
                     VALOR COMPRA: <input name="vUnitCompra" type="number" value="<c:out value="${produto.valorUniCompra}"/>" placeholder="R$000,00"  required="required"style="height: 1.5em; width: 20em "/>
                  </label>
                  </p>
                  <p style="text-align: center">
                  <label class="font-weight-bold" style="text-align: left; padding-left: 3.3em;">
                     VALOR VENDA: <input name="vUnitVenda" type="number" value="<c:out value="${produto.valorUniVenda}"/>" required="required" placeholder="R$000,00" style="height: 1.5em; width: 20em" />
                  </label> 
               </p>  
                 <p style="text-align: center">  
                  <label class="font-weight-bold" style="text-align: left; padding-left: 3.3em;">
                     FORNECEDOR: 
                     <h:selectOneMenu style="height: 1.5em; width: 20em" id="fornecedor"  >                     
                        <f:selectItem itemValue="#{produto.tbFornecedore.razaoSocial}" /> 
                        <f:selectItem noSelectionOption="true" itemValue="___________________" itemDisabled="true"/> 
                        <f:selectItems value="#{tbFornecedore.fornecedores}" itemValue="#{tbFornecedore.fornecedores}"/>
                     </h:selectOneMenu> 
                  </label>
               </p> 
               
            </fieldset> 
            </div>
         </form>
   </f:view>
   </body>  
</html>	