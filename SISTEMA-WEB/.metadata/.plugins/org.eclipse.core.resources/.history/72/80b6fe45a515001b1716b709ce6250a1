<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	<jsp:include page="index.xhtml" flush="false">
		<jsp:param name="cabecalho" value="cabecalho" />
	</jsp:include>

	<f:view>
		<form action="ControlItensCompra" method="POST" name="cadastroCompra">

			<h2>PEDIDO COMPRA</h2>

			<br />
			<p> 
				<a href="ControlCompra?action=FinalizarCompra&idCompra=<c:out value="${compra.idCompra}"/>">
					<input type="button" value=" FINALIZAR COMPRA " />
				</a>
				<a href="ControlCompra?action=Tabela"> 
					<input type="button" value=" VOLTAR " style="background: rgba(178, 34, 34, 1); color: white"> 
				</a>
				
			</p>
			<br />
			<fieldset id="informacoes">
				<p>
					<label> REFERÊCIA: <c:out value="COMPRA/${compra.idCompra}" />
					</label> <label style="padding-left: 90px"> DATA COMPRA: <fmt:formatDate
							pattern="dd/MM/yyyy" value="${compra.dataCriada}" />
					</label>
				</p>
				<p>
					<label> SITUAÇÃO: <c:out value="${compra.status}" />
					</label> <label style="padding-left: 168px"> DATA FINALIZADA: <fmt:formatDate
							pattern="dd/MM/yyyy" value="${compra.dataFinalizada}" />
					</label>
				</p>

				<br />
				<table border="1">
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
								<td><a
									href='ControlCompra?action=EditItens&idCompra=<c:out value="${compra.idCompra}"/>&idItem=<c:out value="${itens.tbProduto.idProduto}"/>'
									onclick="abrir()"> <img src="img/refresh-icon.png"
										style="width: 21px; height: 21px;" title="ATUALIZAR" /></a></td>
								<td><a
									href='ControlCompra?action=DeleteItens&idCompra=<c:out value="${compra.idCompra}"/>&idItem=<c:out value="${itens.tbProduto.idProduto}"/>'><img
										src="img/delete.png" style="width: 21px; height: 21px;"
										title="EXCLUIR"></a></td>

							</tr>
						</c:forEach>
						<tr>
							<td colspan="7">
								<a href="#" onclick="abrir()">INSERIR ITENS</a>
							</td>
						</tr>
						<tr>
							<td colspan="7" style="color: white">.</td>
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
				<form action="ControlCompra" method="POST" name="cadastroItensCompra">

					<fieldset id="informacoes">
						<legend>PRODUTO</legend>
						<p>
							<label> NOME: <h:selectOneMenu style="width: 260px;" id="idProd">
									<f:selectItem itemValue="#{item.tbProduto.nomeProduto}"	itemLabel="#{item.tbProduto.nomeProduto}" />
									<f:selectItem noSelectionOption="true" itemValue="___________________" itemDisabled="true" />
									<f:selectItems value="#{tbProduto.lista}" itemValue="#{tbProduto.lista}" />
								</h:selectOneMenu>
							</label>
						</p> 
						<p>
							<label> QUANTIDADE: <input name="quantidade"
								value="<c:out value="${item.quantidade}"/>" required="required"
								style="width: 200px;" />
							</label>
						</p> 
						 
					</fieldset>
					<p>
						<input value="ADICIONAR" type="submit">
						<a href="#"onclick="fechar();">CANCELAR</a>
					</p>
				</form>
			</div>
		</div>
	</f:view>
</body>
<script>   
		window.onload = multiplica;

   		function abrir(){    
   	   			document.getElementById("inserir-itens-container").style.display = 'flex'; 
   		} 
   		
   		function fechar(){
   			document.getElementById("inserir-itens-container").style.display = 'none'; 
   		}  
	 
   </script>
</html>
