<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="shortcut icon" href="img/Logo_Padaria.png"/>
<link rel="stylesheet" type="text/css" href="css/inserirItens.css" />
<title>CAIXA</title>
<style>
input{
height: 1.8em; width: 15em;
}
body{

}
.div-alinhada{
	width:66%;
	min-height: 27.8em;
	float: left	;  
	margin-bottom: 30px;  
} 

#tabela_centro{
	min-height: 25em;
	width:80%;
	position: relative;
	left: 15%; 
	float: left; 
	height:33em;
	background-color: rgba(0,0,0, 0.7); 
	border-radius: 15px;

}
#elemento_centro{ 
	float: left;
	height:33em;
	width: 25em;
	background-color: rgba(0,0,0, 0.7);
	padding-top: 15px;  
	margin-bottom: 30px; 
	border-radius: 15px; 
}
#rodape {
	height: 26px;
	position:fixed;
    bottom:0;
    width:100%;
    left: 0px;
    padding-left: 10px; 
    font-weight: bold;
    background-color: gray;    
}
	th, td{
		padding: 6px;
	}
	table{
		width: 99%;
		margin: 0 auto;  
		border-radius: 0px 15px 15px 15px;
	}
</style>
</head>
<body>  
	<%
   		String usuario = (String) session.getAttribute("usuario");
   		if(usuario == null){
   			response.sendRedirect("Login.xhtml");
   		}
   	%>
	<f:view>
		<jsp:include page="index.jsp"></jsp:include>
		<h1 class="text-center margintop" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center">CAIXA</span></h1>
    
		<div class="div-alinhada">
			<div id="tabela_centro" > 
				<p style="padding: 15px 0 0 15px;">
					<label class="font-weight-bold text-white" >
						 NÚMERO COMANDA: <input type="number" name="idComanda" id="idComanda" value="<c:out value="${comanda}" />" style="text-align: right; width: 170px" /> 
											<a href="#" onclick="this.href='ControlVenda?action=pesquisaComanda&idComanda='+document.getElementById('idComanda').value"><img src="img/search-icon.png" style="height: 35px; background-color: gray; border-radius: 5px; padding: 3px" alt="PESQUISAR" title="PESQUISAR"/></a> 
					</label>					
				</p> 
				<p style="padding-left: 15px;">
					<label  class="font-weight-bold text-white" >
						STATUS COMANDA:  <c:out value="${status.statusComanda}"/> 
					</label>
					<label  class="font-weight-bold text-white" style="margin-left: 25px">
						DATA/HORA ABERTA:   <fmt:formatDate value="${dataAberta.dataCompra}" pattern="dd/MM/yyyy hh:mm:ss"/>
					</label>
				</p> 
				<table border="1" class="table-dark" >
					<thead>
						<tr style="height: 25px; font-size: 12px">
							<th style="width: 83px;">COD. PROD</th>
							<th style="width: 258px;">PRODUTO</th>
							<th style="width: 92px;">QUANT.</th>
							<th style="width: 82px;">VALOR UNIT.</th>
							<th style="width: 100px;">SUBTOTAL</th>
							<th colspan="2" style="text-align: center">AÇÃO</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${venda}" var="venda">
						<tr>
							<td><c:out value="${venda.tbProduto.idProduto}" /></td>
							<td><c:out value="${venda.tbProduto.nomeProduto}" /></td>
							<td><c:out value="${venda.quantidade}" /></td>
							<td><c:out value="${venda.tbProduto.valorUniVenda}" /></td>
							<td><c:out value="${venda.subtotal}" /></td>
							<td><a href='ControlVenda?action=Edit&idComanda=<c:out value="${comanda}"/>&codItem=<c:out value="${venda.tbProduto.idProduto}"/>'>
								<button class="btn btn-success" style="height: 2.2em; width: 3em;"><img src="img/edit.svg" style="width: 21px; height: 21px; " title="EDITAR" /></button>
								</a>
							</td>
							<td><a href='ControlVenda?action=Delete&idComanda=<c:out value="${comanda}"/>&codItem=<c:out value="${venda.tbProduto.idProduto}"/>'>
								<button class="btn" style="height: 2.2em; width: 3em; background-color: #ee0000"><img src="img/trash-2.svg" style="width: 21px; height: 21px; " title="EXCLUIR ITEM" /></button></a></td>

						</tr>
					
						</c:forEach>
					</tbody>
				</table>
			</div>
			</div> 
			<div id="elemento_centro">
				<form action="ControlVenda" method="post">
					<p style="text-align:center">
						<label  class="font-weight-bold text-white" style="text-align: left"> PRODUTO <br /><h:selectOneMenu style="height: 1.8em; width: 15em" id="produto" >                     
					                        <f:selectItem itemValue="#{item.tbProduto.idProduto}" itemLabel="#{item.tbProduto.nomeProduto}"/> 
					                        <f:selectItem noSelectionOption="true" itemValue="___________________" itemDisabled="true"/> 
					                        <f:selectItems value="#{tbEstoque.lista}" itemValue="#{tbEstoque.lista}"/>
					                    </h:selectOneMenu> 
						
						</label>
					</p>
					<p style="text-align:center">
						<label class="font-weight-bold text-white" style="text-align: left"> QUANTIDADE <br /><input type="text" name="quantidade" id="quantidade" value="<c:out value='${item.quantidade}' />" />
						</label>
					</p>
					<p style="text-align:center">
						<label class="font-weight-bold text-white" style="text-align: left"> PRECO UNITÁRIO <br /><input type="text" value="<c:out value='${item.tbProduto.valorUniVenda}' />"  style="background-color: #bab8b5; padding-left: 5px" readonly="readonly" />
						</label>
					</p>
					<p style="text-align:center">
						<label class="font-weight-bold text-white" style="text-align: left"> SUBTOTAL <br /><input type="text" style="background-color: #bab8b5; padding-left: 5px" value="<c:out value='${item.subtotal}' />" readonly="readonly" />
						</label>
					</p>
					<p style="text-align:center">
						<label  class="font-weight-bold text-white" style="text-align: left"> TOTAL <br /><input type="text" readonly="readonly" style="background-color: #bab8b5; padding-left: 5px" value="<c:out value="${total.total}" />" /> 
						</label>
					</p>
					<p style="text-align:center; margin-top: 2em;">
						<label class="font-weight-bold text-white" >
							<input type="submit" value="INSERIR ITEM" onclick="validaNumero()" class="btn btn-primary" style="height: 2.2em; width: 15em;" />							 
						</label>
						<label>
							<a href="#" onclick="this.href='ControlVenda?action=FinalizarVenda'" class="btn btn-success" style="height: 2.2em; width: 15em; margin-bottom: 20px "> 
								FINALIZAR COMPRA 
						 	</a>
						</label> 
					</p> 

				</form>
			</div> 
		<div class="inserir-itens-container" id="inserir-itens-container" >
			<div class="inserir-itens">
				<form action="ControlVenda" method="post" name="pagamento">

					<fieldset id="informacoes" >
						<legend>FINALIZAR COMPRA</legend>
						<p>
							<label>  
								FORMA DE PAGAMENTO:	<h:selectOneMenu style="width: 248px; height: 24px" id="formaPagamento">
								                   	     <f:selectItem itemValue="" itemLabel=""/>
								                        <f:selectItem noSelectionOption="true" itemValue="_________" itemDisabled="true"/>
								                        <f:selectItem itemValue="DI" itemLabel="DINHEIRO"/>
								                        <f:selectItem itemValue="DE" itemLabel="DEBITO"/>
								                        <f:selectItem itemValue="CR" itemLabel="CREDITO"/>
								                     </h:selectOneMenu> 
							</label> 
						</p>
						<p>		
							<label> 
								DINHEIRO RECEBIDO: <input name="dinheiroRecebido" onchange="calculo();" style="width: 200px;" />
							</label> 
						</p>
						<p>
							 <label>
							 	TROCO: <input name="troco" readonly="readonly" style="width: 200px;" />
							</label>
						</p> 
						<p>
							<label> 
								VALOR TOTAL: <input name="valorTotal" readonly="readonly" value="<c:out value="${total.total}"/>"	style="width: 200px;" />
							</label>	
						</p>
					</fieldset>
					<p style="text-align: center">
						<input value=" RECEBER E FINALIZAR " type="submit" class="btn btn-success" style="height: 2.3em;"/> 
						<a href="#"	class="btn btn-secondary" onclick="this.href='ControlVenda?action=pesquisaComanda&idComanda='+${comanda} ">CANCELAR</a>
					</p>
				</form>
			</div>
		</div> 
		<div id="rodape">
			 <p> OPERADOR: <c:out value="${usuario}"/> <span style="position: absolute; right: 15px"> DATA: <c:out value="${data}"/> HORA: <c:out value="${hora}"/> </span>  </p>
		</div>
	</f:view>
	<script >    
		function validaNumero(){
			if(document.getElementById("quantidade").value == ""){
				window.alert("A QUANTIDADE NÃO PODE ESTAR VAZIA");
				document.getElementById("quantidade").focus();
			}
		}
	
		var url_atual = window.location.href;
		if(url_atual.indexOf("/ControlVenda?action=FinalizarVenda") != -1){
			document.getElementById("inserir-itens-container").style.display = 'flex'; 
		}  
		
		function calculo(){     
			   	var dinheiro = document.querySelector('[name=dinheiroRecebido]');
				var troco = document.querySelector('[name=troco]');
				var total = document.querySelector('[name=valorTotal]');
				troco.value = dinheiro.value - total.value; 
				if(dinheiro.value < 0){
					dinheiro.value = "";
					window.alert("O DINHEIRO RECEBIDO NÃO PODE SER NEGATIVO");
				}else{
					if((dinheiro.value - total.value) >0){
						troco.style.color = "green";
						troco.style.background = "#90EE90";
					}else{
						troco.style.color = "#800000";
						troco.style.background = "#E9967A";
					}
				} 
		} 
 
   		function abrir(){    
   	   		 document.getElementById("inserir-itens-container").style.display = 'flex'; 
   		} 
   		
   		function fechar(){
   			document.getElementById("inserir-itens-container").style.display = 'none'; 
   		}    
</script>
</body>
</html>