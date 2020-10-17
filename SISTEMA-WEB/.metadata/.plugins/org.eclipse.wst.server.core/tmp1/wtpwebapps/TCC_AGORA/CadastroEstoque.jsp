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
             
            	<h2>INSERIR ITEM NO ESTOQUE</h2>
             
            <br />
            <p><input value="ENVIAR" type="submit" id="btn"> <a href="ControlEstoque?action=Tabela">CANCELAR</a> </p>
            <fieldset id="informacoes">
               <legend>PRODUTO</legend>
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
         </form>
   </f:view>
   </body> 
   </body> 
</html>	