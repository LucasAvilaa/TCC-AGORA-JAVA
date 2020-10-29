<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>CONTAS A RECEBER</title>
      <link rel="shortcut icon" href="img/Logo_Padaria.png"/>
      <link rel="stylesheet" type="text/css" href="css/CadastroCliente.css">
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
      <jsp:include page="index.jsp" flush="false">
         <jsp:param name="cabecalho" value="cabecalho"/>
      </jsp:include>
  <f:view>  
         <form action="ControlContasReceber" method="POST" name="cadastroContasReceber">
            
            	<h1 class="text-center margintop" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center;">Contas a receber</span></h1>
<div class="card border bg-dark text-white" style="background-color: #fff;  margin-top: 15px; position: relative;left: 33%; width: 27em; font-family: sans-serif">
            
           
            <br />
            <p><input value="ENVIAR" type="submit" id="btn" class="btn btn-success" style="width: 10em; height: 2.5em; margin-right:0.4em; margin-left: 3em"> <a href="ControlContasReceber?action=Tabela" class="btn btn-danger" style="width: 10em; height: 2.5em;">CANCELAR</a> </p>
            <fieldset id="informacoes">
               <legend style="text-align:center; margin-top: 1em; margin-bottom: 1em;" class="bg-light text-dark"
               >INFORMAÇÕES BÁSICAS </legend> 
               <p>
               	<label>
                     REFERÊNCIA VENDA: <input name="idVenda"  value="<c:out value="VENDA/ ${receber.idReceber}"/>"   style="width: 148px; "/>
                  </label>
                
               </p> 
               <p>
               	  <label>
                     METODO DE PAGAMENTO: 
                      <h:selectOneMenu style="width: 150px; height: 24px" id="condicaoPagamento">
	                        <f:selectItem itemValue="#{receber.metodoPagamento}"/>
	                        <f:selectItem noSelectionOption="true" itemValue="_________"/>
	                        <f:selectItem itemValue="D" itemLabel="DINHEIRO"/>
	                        <f:selectItem itemValue="C" itemLabel="CARTÃO"/>
	                        <f:selectItem itemValue="CD" itemLabel="CARTÃO/DINHEIRO"/>
                     </h:selectOneMenu> 
                  </label>
               </p>
               <p>
                  <label>
                     DINHEIRO: <input name="dinheiro" value="<c:out value="${receber.dinheiro}"/>"   style="width: 148px; "/>
                  </label>
               </p>   
               <p>
                  <label>
                     CARTÃO DEBITO: <input name="debito" value="<c:out value="${receber.debito}"/>"   style="width: 148px; "/>
                  </label>
               </p>
               <p>
                  <label>
                     CARTÃO CRÉDITO: <input name="credito" value="<c:out value="${receber.credito}"/>"   style="width: 148px; "/>
                  </label>
               </p> 
                <p>
                  <label>
                     TOTAL: <input name="total" type="number" readonly="readonly" value='<c:out value=""></c:out>' placeholder="R$000,00"  required="required" style="width: 90px; "/>
                  </label> 
               </p>   
               <p>
                  <label>
                     DATA DA VENDA: <input name="dataVenda"  value="<fmt:formatDate pattern="dd/MM/yyyy" value="${receber.dataCompra}" />" style="width: 100px; "/>
                  </label> 
               </p>  
               <p>
                  <label>
                     DATA PREVISTA RECEBER: <input name="dataPrevista" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${receber.dataPrevistaReceber}" />" style="width: 100px; "/>
                  </label> 
               </p>  
            </fieldset> 
            </div>
         </form>
   </f:view>
   </body> 
   </body> 
</html>	