<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>PEDIDO DE COMPRA</title>
<link rel="shortcut icon" href="img/Logo_Padaria.png" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/inserirItens.css"
	media="screen" />
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
</head>
<body> 
	<jsp:include page="index.jsp"></jsp:include>
	<%
   		String usuario = (String) session.getAttribute("usuario");
   		if(usuario == null){
   			response.sendRedirect("Login.xhtml");
   		}
   	%>
	<f:view>
		<form action="ControlItensCompra" method="post" name="cadastroCompra">

			<h1 class="text-center margintop" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center">PEDIDO DE COMPRA</span></h1>
            

			<br />
			<p> 
				<a  href="ControlCompra?action=ConfirmarCompra&idCompra=<c:out value="${compra.idCompra}"/>">
					<input type="button" value=" CONFIRMAR COMPRA" class="btn btn-success" style="width: 12em; height: 2.5em; margin-right:0.4em" />
				</a>
				<a href="ControlCompra?action=FinalizarCompra&idCompra=<c:out value="${compra.idCompra}"/>">
					<input type="button" value=" FINALIZAR COMPRA " class="btn btn-primary" style="width: 12em; height: 2.5em; margin-right:0.4em"/>
				</a>
				<a href="ControlCompra?action=Tabela"> 
					<input type="button" value=" VOLTAR " class="btn btn-danger" style="height: 2.5em;" /> 
				</a>
				
			</p>
			<br />
			<fieldset id="informacoes">
				<p >
					<label class="text-white font-weight-bold"> REFERÊCIA: <c:out value="COMPRA/${compra.idCompra}" />
					</label> <label style="padding-left: 90px" class="text-white font-weight-bold"> DATA CRIADA: <fmt:formatDate
							pattern="dd/MM/yyyy" value="${compra.dataCriada}" />
					</label>
				</p>
				<p>
					<label class="text-white font-weight-bold"> SITUAÇÃO: <c:out value="${compra.status}" />
					</label> <label style="padding-left: 168px " class="text-white font-weight-bold"> DATA FINALIZADA: <fmt:formatDate
							pattern="dd/MM/yyyy" value="${compra.dataFinalizada}" />
					</label>
				</p>

				<br />
				<table border="1" class="table table-hover table-dark">
					<thead>
						<tr>
							<th style="width: 193px;">PRODUTO</th>
							<th style="width: 195px;">FORNECEDOR</th>
							<th style="width: 125px;">QUANTIDADE</th>
							<th style="width: 154px;">VALOR UNITÁRIO</th>
							<th style="width: 94px;">SUBTOTAL</th>
							<th colspan="2">AÇÃO</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${itens}" var="itens">
							<tr>
								<td><c:out value="${itens.tbProduto.nomeProduto}"></c:out>
								</td>
								<td><c:out value="${itens.tbProduto.tbFornecedore.razaoSocial}"></c:out>
								</td>
								<td contenteditable="true" align="center" onchange="multiplica()">
									<c:out value="${itens.quantidade}" />
									
								</td>
								<td contenteditable="true" align="center">  
									<c:out value="${itens.tbProduto.valorUniCompra}"></c:out>
								</td>
								<td> 
									<c:out value="${itens.subtotal}"></c:out>
								</td>
								<td><a href='ControlCompra?action=EditItens&idCompra=<c:out value="${compra.idCompra}"/>&idItem=<c:out value="${itens.tbProduto.idProduto}"/>'>
								 		EDITAR <img src="img/edit.svg" style="width: 21px; height: 21px; " title="EXCLUIR" /> 
									</a>
								</td>
							 	<td><a href='ControlCompra?action=DeleteItens&idCompra=<c:out value="${compra.idCompra}"/>&idItem=<c:out value="${itens.tbProduto.idProduto}"/>'>
									  		EXCLUIR <img src="img/trash-2.svg" style="width: 21px; height: 21px; " title="EXCLUIR" /> 
									</a>
								</td>

							</tr>
						</c:forEach>
						<tr>
							<td colspan="7">
								<a href="#" onclick="abrir()">INSERIR ITENS</a>
							</td>
						</tr>
						<tr>
							<td colspan="7" style="color: white"></td>
						</tr>
						<tr>
							<td colspan="4">TOTAL</td>
							<td colspan="3"><c:out value="R$ ${total.total}"></c:out></td>
						</tr>
					</tbody>
				</table>
			</fieldset>
		</form>
		<div class="inserir-itens-container" id="inserir-itens-container">
			<div class="inserir-itens">
				<form action="ControlCompra" method="post" name="cadastroItensCompra">

					<fieldset id="informacoes">
					<legend class="text-center margintop h2" style="margin-top: 0.4em;"><span class="badge badge-secondary text-center">ITEM</span></legend>
 
						<p style="text-align:center; "  >
							<label  class="font-weight-bold" style="text-align: left" > NOME: <br /><h:selectOneMenu style="height: 1.5em; width: 20em"   id="idProd">
									<f:selectItem itemValue="#{item.tbProduto.idProduto}"	itemLabel="#{item.tbProduto.nomeProduto}" />
									<f:selectItem noSelectionOption="true" itemValue="___________________" itemDisabled="true" />
									<f:selectItems value="#{tbProduto.lista}" itemValue="#{tbProduto.lista}" />
								</h:selectOneMenu>
							</label>
						</p> 
						<p style="text-align:center">
							<label  class="font-weight-bold" style="text-align: left"> QUANTIDADE: <br /><input name="quantidade" type="number"
								value="<c:out value="${item.quantidade}"/>" required="required"
								style="height: 1.5em; width: 20em" />
							</label>
						</p> 
						 
					</fieldset>
					<p>
						<input value="ADICIONAR" type="submit" class="btn btn-success" />
						<a href="#" onclick="this.href='ControlCompra?action=EditCompra&idCompra='+ ${compra.idCompra}" class="btn btn-dark">CANCELAR</a>
					</p>
				</form>
			</div>
		</div>
	</f:view>
	<script>     
	var url_atual = window.location.href;
   	if(url_atual.indexOf("/ControlCompra?action=EditItens&idCompra=") != -1){
   		document.getElementById("inserir-itens-container").style.display = 'flex';  
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
