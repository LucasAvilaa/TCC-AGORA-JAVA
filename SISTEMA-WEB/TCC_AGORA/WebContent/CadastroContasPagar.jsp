
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>CONTAS A PAGAR</title>
      <link rel="shortcut icon" href="img/Logo_Padaria.png"/>
      <link rel="stylesheet" type="text/css" href="css/CadastroCliente.css">
      <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
      <script src="//cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
      <style>
      	
      </style>
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
         <form action="ControlContasPagar" method="POST" name="cadastroContasPagar"> 
         
            <h1 class="text-center margintop" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center;">Inserir conta a pagar</span></h1>
<div class="card border bg-dark text-white" style="background-color: #fff;  margin-top: 15px; position: relative;left: 33%; width: 27em; font-family: sans-serif">
            
           
            <br />
            <p><input value="ENVIAR" type="submit" id="btn" class="btn btn-success" style="width: 10em; height: 2.5em; margin-right:0.4em; margin-left: 3em">
             <a href="ControlContasPagar?action=Tabela"  class="btn btn-danger" style="width: 10em; height: 2.5em;">CANCELAR</a> </p>
            <fieldset id="informacoes">
               <legend  style="text-align:center; margin-top: 1em; margin-bottom: 1em;" class="bg-light text-dark">INFORMAÇÕES BÁSICAS </legend>
               <p style="text-align: center">
               	<label>
                     DOCUMENTO DE ORIGEM: <input readonly="readonly" maxlength="50" value="<c:out value="COMPRA/${conta.tbCompra.idCompra}"/>"   style="width: 248px; "/>
                  </label>
                  <label>
                     DESCRIÇÃO:  <br/><input name="descricao" maxlength="50" value="<c:out value="${conta.descricao}"/>" required="required"  style="width: 248px; "/>
                  </label>
               </p> 
               <p style="text-align: center">
                  <label>
                     CATEGORIA:  <br/><h:selectOneMenu style="width: 248px; height: 24px" id="categoria">
                        <f:selectItem itemValue="#{conta.categoria}"/>
                        <f:selectItem noSelectionOption="true" itemValue="_________" itemDisabled="true"/>
                        <f:selectItem itemValue="USO E CONSUMO" itemLabel="USO E CONSUMO"/>
                        <f:selectItem itemValue="MATÉRIA PRIMA" itemLabel="MATÉRIA PRIMA"/>
                        <f:selectItem itemValue="PRODUTO PARA REVENDA" itemLabel="PRODUTO PARA REVENDA"/>
                     </h:selectOneMenu> 
                  </label>
               </p>   
               <p style="text-align: center">
                  <label>
                     DATA VENCIMENTO:  <br/><input name="dataVencimento" type="date" id="data" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${conta.dataVencimento}"/>" required="required" style="width: 248px; ">
                  
                  </label>
               </p> 
                <p style="text-align: center">
                  <label>
                     VALOR COMPRA: <br/> <input name="valor" type="number" value='<c:out value="${conta.valorPagar}"></c:out>' placeholder="R$000,00"  required="required" style="width: 248px; "/>
                  </label> 
               </p>    
            </fieldset> 
            </div>
         </form>
   </f:view>
   </body> 
   </body> 
</html>	