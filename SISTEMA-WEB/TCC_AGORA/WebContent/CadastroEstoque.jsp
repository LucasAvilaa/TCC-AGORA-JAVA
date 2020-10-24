<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>ESTOQUE</title>
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
         <form action="ControlEstoque" method="POST" name="cadastroEstoque">
             
            	<h1 class="text-center margintop" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center;">Cadastro de Estoque</span></h1>
<div class="card border bg-dark text-white" style="background-color: #fff;  margin-top: 15px; position: relative;left: 33%; width: 27em; font-family: sans-serif">
            
             
            <br />
            <p><input value="ENVIAR" type="submit" id="btn" class="btn btn-success" style="width: 10em; height: 2.5em; margin-right:0.4em; margin-left: 3em">
             <a href="ControlEstoque?action=Tabela" class="btn btn-danger" style="width: 10em; height: 2.5em;">CANCELAR</a> </p>
            <fieldset id="informacoes">
               <legend  style="text-align:center; margin-top: 1em; margin-bottom: 1em;" class="bg-light text-dark">PRODUTO</legend>
               <p>
                  <label>
                     NOME:  <h:selectOneMenu style="width: 180px; height: 24px" id="idProd" >                     
		                        <f:selectItem itemValue="#{estoque.tbProduto.idProduto}" itemLabel="#{estoque.tbProduto.nomeProduto}"/> 
		                        <f:selectItem noSelectionOption="true" itemValue="___________________" itemDisabled="true"/> 
		                        <f:selectItems value="#{tbProduto.lista}" itemValue="#{tbProduto.lista}"/>
		                    </h:selectOneMenu>
                  </label>
               </p>
               <p>
                  <label>
                     QUANTIDADE: <input name="quantidade" type="number" maxlength="20" value="<c:out value="${estoque.quantidade}"/>" required="required" style="width: 138px; "/>
                  </label>
               </p>                
                <p>
                	<label>
                    	 DATA ENTRADA: <input name="dataEntrada" type="text"  value="<fmt:formatDate pattern="dd/MM/yyyy" value="${estoque.dataEntrada}"/>" disabled="disabled" required="required" style="width: 98px; ">
                  	</label>                  
               </p>  
               <p>
               	 <label>
                     DATA VENCIMENTO: <input name="dataVencimento" type="date" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${estoque.dataVencimento}"/>" required="required" style="width: 155px; ">
                  </label>
               </p>  
               
            </fieldset> 
            </div>
         </form>
   </f:view>
   </body> 
   </body> 
</html>	