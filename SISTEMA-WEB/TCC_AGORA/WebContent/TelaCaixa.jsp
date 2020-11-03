<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
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
	weight:30em;
	height: 27.8em;
	float: left	;
	border: 1px solid black;
}
#tabela_centro{
	height: 25em;
	position: relative;
	left: 20%;
	float: left;
}
#elemento_centro{
	border:1px solid black;
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
		<jsp:include page="index.jsp" flush="false">
			<jsp:param name="cabecalho" value="cabecalho" />
		</jsp:include>
		<h1 class="text-center margintop" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center">Caixa</span></h1>
            
		<div class="div-alinhada">
			<div id="tabela_centro" >
				<p>
					<label class="font-weight-bold text-white" >
						 NÚMERO COMANDA: <input type="number" name="idComanda" id="idComanda" value="<c:out value="${comanda}" />"/> 
											<a href="#" onclick="this.href='ControlVenda?action=pesquisaComanda&idComanda='+document.getElementById('idComanda').value">PESQUISAR</a> 
					</label>					
				</p>
				<p>
					<label  class="font-weight-bold text-white" >
						STATUS COMANDA:  <c:out value="${status.statusComanda}"/> 
					</label>
				</p>
				<table border="1" class="table table-hover table-dark" >
					<thead>
						<tr style="height: 25px; font-size: 12px">
							<th style="width: 83px;">COD. PROD</th>
							<th style="width: 258px;">PRODUTO</th>
							<th style="width: 92px;">QUANT.</th>
							<th style="width: 82px;">VALOR UNIT.</th>
							<th style="width: 100px;">SUBTOTAL</th>
							<th colspan="2">AÇÃO</th>
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
							<td><a
								href='ControlVenda?action=Edit&idComanda=<c:out value="${comanda}"/>&codItem=<c:out value="${venda.tbProduto.idProduto}"/>'><img
									src="img/edit.svg" style="width: 21px; height: 21px;"
									title="ATUALIZAR" /></a></td>
							<td><a
								href='ControlVenda?action=Delete&idComanda=<c:out value="${comanda}"/>&codItem=<c:out value="${venda.tbProduto.idProduto}"/>'><img
									src="img/trash-2.svg" style="width: 21px; height: 21px;"
									title="EXCLUIR ITEM" /></a></td>

						</tr>
					
						</c:forEach>
					</tbody>
				</table>
			</div>
			</div>
			<div>
			<div id="elemento_centro">
				<form action="ControlVenda" method="post">
					<p style="text-align:center">
						<label  class="font-weight-bold text-white" style="text-align: left"> PRODUTO <br /><h:selectOneMenu style="height: 1.8em; width: 15em" id="produto" >                     
					                        <f:selectItem itemValue="#{item.tbProduto.idProduto}" itemLabel="#{item.tbProduto.nomeProduto}"/> 
					                        <f:selectItem noSelectionOption="true" itemValue="___________________" itemDisabled="true"/> 
					                        <f:selectItems value="#{tbProduto.lista}" itemValue="#{tbProduto.lista}"/>
					                    </h:selectOneMenu> 
						
						</label>
					</p>
					<p style="text-align:center">
						<label class="font-weight-bold text-white" style="text-align: left"> QUANTIDADE <br /><input type="text" name="quantidade" value="<c:out value='${item.quantidade}' />" />
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
						<label  class="font-weight-bold text-white" style="text-align: left"> TOTAL <br /><input type="text" readonly="readonly" style="background-color: #bab8b5; padding-left: 5px" value="<c:out value="${total.total}" />"> 
						</label>
					</p>
					<p style="text-align:center">
						<label>
							<a href="#" onclick="this.href='ControlVenda?action=FinalizarVenda'" class="btn btn-success" style="height: 2.2em; width: 12em;"> 
								FINALIZAR COMPRA <img src="img/edit.svg" style="width: 21px; height: 21px;" title="FINALIZAR COMPRA" />
						 	</a>
						</label>
						
					
						<label class="font-weight-bold text-white" >
							<input type="submit" value=" INSERIR ITEM " class="btn btn-primary" style="height: 2.2em; width: 12em;">							 
						</label>	
					</p>


				</form>
			</div>
		</div>
		<div style="background-color: gray">
			<p>OPERADOR: <c:out value="${usuario}"/>   DATA: <c:out value="${data}"/> HORA: <c:out value="${hora}"/></p>
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
								DINHEIRO RECEBIDO: <input name="dinheiroRecebido" onchange="teste();" style="width: 200px;" />
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
					<p>
						<input value=" RECEBER E FINALIZAR " type="submit" /> 
						<a href="#"	onclick="this.href='ControlVenda?action=pesquisaComanda&idComanda='+${comanda} ">CANCELAR</a>
					</p>
				</form>
			</div>
		</div>
	</f:view>
	<script >    
		var url_atual = window.location.href;
		if(url_atual.indexOf("/ControlVenda?action=FinalizarVenda") != -1){
			document.getElementById("inserir-itens-container").style.display = 'flex'; 
		}  
		
		function teste(){     
			   	var dinheiro = document.querySelector('[name=dinheiroRecebido]');
				var troco = document.querySelector('[name=troco]');
				var total = document.querySelector('[name=valorTotal]');
				troco.value = dinheiro.value - total.value; 
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