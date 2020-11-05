<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <title>ESTOQUE</title>
      <link rel="shortcut icon" href="img/Logo_Padaria.png"/>
      <link rel="stylesheet" type="text/css" href="css/estilo2.css" />
      <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
      <script src="//cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
   </head>
   <style>
   	form{
    	margin-bottom: 30px;
    }
   </style>
   <body>
   	<%
   		String usuario = (String) session.getAttribute("usuario");
   		if(usuario == null){
   			response.sendRedirect("Login.xhtml");
   		}
   	%>
      <jsp:include page="index.jsp"></jsp:include>
  <f:view>  
         <form action="ControlEstoque" method="post" name="cadastroEstoque">
             
            	<h1 class="text-center margintop" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center;">ESTOQUE</span></h1>
<div class="card border bg-dark text-white" style="background-color: #fff;  margin-top: 15px; position: relative;left: 6%; width: 27em; font-family: sans-serif">
            
             
            <br />
            <p><input value="ENVIAR" type="submit" id="btn" class="btn btn-success" style="width: 10em; height: 2.5em; margin-right:0.4em; margin-left: 3em" />
             <a href="ControlEstoque?action=Tabela" class="btn btn-danger text-white" style="width: 10em; height: 2.5em;border: 0px;">CANCELAR</a> </p>
            <fieldset id="informacoes">
               <legend  style="text-align:center; margin-top: 1em; margin-bottom: 1em;" class="bg-light text-dark">PRODUTO</legend>
               <p style="text-align:center">
                  <label  class="font-weight-bold" style="text-align: left">
                     NOME: <br /> <h:selectOneMenu style="height: 1.5em; width: 20em" id="idProd" >                     
		                        <f:selectItem itemValue="#{estoque.tbProduto.idProduto}" itemLabel="#{estoque.tbProduto.nomeProduto}"/> 
		                        <f:selectItem noSelectionOption="true" itemValue="___________________" itemDisabled="true"/> 
		                        <f:selectItems value="#{tbProduto.lista}" itemValue="#{tbProduto.lista}"/>
		                    </h:selectOneMenu>
                  </label>
               </p>
               <p style="text-align:center">
                  <label  class="font-weight-bold" style="text-align: left">
                     QUANTIDADE: <br /><input name="quantidade" type="number" maxlength="20" value="<c:out value="${estoque.quantidade}"/>" required="required" style="height: 1.5em; width: 20em"/>
                  </label>
               </p>                
                <p style="text-align:center">
                	<label  class="font-weight-bold" style="text-align: left">
                    	 DATA ENTRADA: <br /><input name="dataEntrada" type="date" id="data" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${estoque.dataEntrada}"/>" required="required" style="height: 1.5em; width: 20em" />
                  	</label>                  
               </p>  
               <p style="text-align:center">
               	 <label  class="font-weight-bold" style="text-align: left">
                     DATA VENCIMENTO: <br /><input name="dataVencimento" type="date" id="data" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${estoque.dataVencimento}"/>" required="required" style="height: 1.5em; width: 20em" />
                  
                  </label>
               </p>  
               
            </fieldset> 
            </div>
         </form>
   </f:view>
   </body>  
</html>	