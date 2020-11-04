<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <title>CONTAS A RECEBER</title>
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
         <form action="ControlContasReceber" method="post" name="cadastroContasReceber">
            
            	<h1 class="text-center margintop" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center;">Contas a receber</span></h1>
<div class="card border bg-dark text-white" style="background-color: #fff;  margin-top: 15px; position: relative;left: 5%; width: 27em; font-family: sans-serif">
            
           
            <br />
            <p><input value="ENVIAR" type="submit" id="btn" class="btn btn-success" style="width: 10em; height: 2.5em; margin-right:0.4em; margin-left: 3em" />
            	<a href="ControlContasReceber?action=Tabela" class="btn btn-danger text-white" style="width: 10em; height: 2.5em;border: 0px;">CANCELAR</a> </p>
            <fieldset id="informacoes">
               <legend style="text-align:center; margin-top: 1em; margin-bottom: 1em;" class="bg-light text-dark"
               >INFORMAÇÕES BÁSICAS </legend> 
               <p>
               	<label  class="font-weight-bold" style="text-align: left">
                     REFERÊNCIA VENDA: <input name="idVenda"  value="<c:out value="VENDA/ ${receber.idReceber}"/>"   style="width: 148px; "/>
                  </label>
                
               </p> 
               <p>
               	  <label  class="font-weight-bold" style="text-align: left">
                     METODO DE PAGAMENTO: 
                      <h:selectOneMenu style="width: 150px; height: 24px" id="condicaoPagamento" onchange="trocou()">
	                        <f:selectItem itemValue="#{receber.metodoPagamento}" itemLabel="#{receber.metodoPagamento}"/>
	                        <f:selectItem noSelectionOption="true" itemValue="_________" itemDisabled="true"/>
	                        <f:selectItem itemValue="DI" itemLabel="DINHEIRO"/>
	                        <f:selectItem itemValue="DE" itemLabel="DEBITO"/>
	                        <f:selectItem itemValue="CR" itemLabel="CREDITO"/>
                     </h:selectOneMenu> 
                  </label>
               </p>
               <p>
                  <label  class="font-weight-bold" style="text-align: left">
                     DINHEIRO: <input name="dinheiro" id="dinheiro" onfocus="campo_atual(id)" onblur="pagamento()" maxlength="7" onchange="valorCom(id, this.value)" value="<c:out value="R$ ${receber.dinheiro}"/>"   style="width: 148px; "/>
                  </label>
               </p>   
               <p>
                  <label  class="font-weight-bold" style="text-align: left">
                     CARTÃO DEBITO: <input name="debito" id="debito" onfocus="campo_atual(id)" onblur="pagamento()" maxlength="7" onchange="valorCom(id, this.value)" value="<c:out value=" R$ ${receber.debito}"/>"   style="width: 148px; "/>
                  </label>
               </p>
               <p>
                  <label  class="font-weight-bold" style="text-align: left">
                     CARTÃO CRÉDITO: <input name="credito" id="credito" onfocus="campo_atual(id)" onblur="pagamento()" maxlength="7" onchange="valorCom(id, this.value)" value="<c:out value="R$ ${receber.credito}"/>"   style="width: 148px; "/>
                  </label>
               </p>    
               <p>
                  <label  class="font-weight-bold" style="text-align: left">
                     DATA DA VENDA: <input name="dataVenda" type="date" id="data" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${receber.dataCompra}"/>" style="width: 158px; " />
                  
                  </label> 
               </p>  
               <p>
                  <label  class="font-weight-bold" style="text-align: left">
                     DATA PREVISTA RECEBER: <input name="dataPrevista" type="date" id="data" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${receber.dataPrevistaReceber}"/>" style="width: 158px; " />
                  </label> 
               </p>  
            </fieldset> 
            </div>
         </form>
   </f:view>
   <script type="text/javascript"> 
   		function trocou(){ 
   			var pagamento = document.getElementById("condicaoPagamento").value;
   	   		var dinheiro = document.getElementById("dinheiro");
   	   		var debito = document.getElementById("debito");
   	   		var credito = document.getElementById("credito");
   			dinheiro.value = "R$ 0.00";   			
   			debito.value = "R$ 0.00" 	 
   			credito.value = "R$ 0.00"
   			 
   			if(pagamento == "CR"){ 
   				credito.focus(); 
   			}
   			else if(pagamento == "DI"){ 
   				dinheiro.focus(); 
   			}
   			else if(pagamento == "DE"){ 
   				debito.focus(); 
   			}
   		}
   		function campo_atual(id){
   			document.getElementById(id).value = ""; 
   		} 
   		function pagamento(){
   			var pagamento = document.getElementById("condicaoPagamento").value;
   	   		var dinheiro = document.getElementById("dinheiro");
   	   		var debito = document.getElementById("debito");
   	   		var credito = document.getElementById("credito");
   			
   			if(pagamento == "CR"){ 
   				if(credito.value == ""){
   					window.alert("PREENCHA O CAMPO CREDITO");
   					credito.focus();
   				} 
   			}
   			else if(pagamento == "DI"){ 
   				if(dinheiro.value == ""){
   					window.alert("PREENCHA O CAMPO DINHEIRO");
   					dinheiro.focus(); 
   				}  
   			}
   			else if(pagamento == "DE"){ 
   				if(debito.value == ""){
   					window.alert("PREENCHA O CAMPO DEBITO");
   					debito.focus(); 
   				}  
   			}
   		}
   		
   		function valorCom(id, valor){
   			document.getElementById(id).value = "R$ " + valor; 
   		}
   </script>
   </body>  
</html>	