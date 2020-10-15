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
   </head>
   <body>
      <jsp:include page="index.xhtml" flush="false">
         <jsp:param name="cabecalho" value="cabecalho"/>
      </jsp:include>
  <f:view>  
         <form action="ControlContasPagar" method="POST" name="cadastroContasPagar">
            
            	<h2>INSERIR CONTAS A PAGAR</h2>
           
            <br />
            <p><input value="ENVIAR" type="submit" id="btn"> <a href="ControlContasPagar?action=Tabela">CANCELAR</a> </p>
            <fieldset id="informacoes">
               <legend>INFORMAÇÕES BÁSICAS </legend>
               <p>
                  <label>
                     DESCRIÇÃO: <input name="descricao" maxlength="50" value="<c:out value="COMPRA/ ${conta.tbCompra.idCompra}"/>" required="required"  style="width: 248px; "/>
                  </label>
               </p> 
               <p>
                  <label>
                     CATEGORIA: <h:selectOneMenu style="width: 210px; height: 24px" id="categoria">
                        <f:selectItem itemValue="#{tbContasPagar.categoria}"/>
                        <f:selectItem noSelectionOption="true" itemValue="_________" itemDisabled="true"/>
                        <f:selectItem itemValue="USO E CONSUMO" itemLabel="USO E CONSUMO"/>
                        <f:selectItem itemValue="MATÉRIA PRIMA" itemLabel="MATÉRIA PRIMA"/>
                        <f:selectItem itemValue="PRODUTO PARA REVENDA" itemLabel="PRODUTO PARA REVENDA"/>
                     </h:selectOneMenu>
						
                  </label>
               </p>  
                
               
               <p>
                  <label>
                     DATA VENCIMENTO: <input name="descricao" maxlength="50" value="<c:out value="${conta.dataVencimento}"/>" required="required"  style="width: 248px; "/>
                  </label>
               </p> 
                <p>
                  <label>
                     VALOR COMPRA: <input name="vUnitCompra" value="<c:out value=" "/>" placeholder="R$000,00"  required="required" style="width: 90px; "/>
                  </label> 
               </p>    
            </fieldset> 
         </form>
   </f:view>
   </body> 
   </body> 
</html>	