<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://java.sun.com/jsf/core" prefix="f"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">	
		<title>Cadastro Clientes</title>
		<link rel="stylesheet" type="text/css" href="css/CadastroCliente.css">
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="//cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
	</head>
	
	<body>	
	<f:view>
		
		<form action="ControlCliente" method="POST" name="cadastroCliente">  
		
		<h2>CADASTRO DE CLIENTE</h2>
		<p style="align:rigth"><input value="ENVIAR" type="submit"> <a href="ControlCliente?action=tabela">CANCELAR</a> </p>
		<fieldset id="informacoes">
			<legend>INFORMAÇÕES BÁSICAS </legend>
			<p>NOME: <input name="nome"  maxlength="50" value="<c:out value="${cliente.nome}"/>" required="required" style="width: 364px; "/></p> 
			<p>SOBRENOME: <input name="sobrenome" maxlength="50" value="<c:out value="${cliente.sobrenome}"/>" required="required" style="width: 314px; "/></p>
			<p>CPF: <input name="cpf" id="cpf" value="<c:out value="${cliente.cpf}"/>" placeholder="xxx.xxx.xxx-xx" required="required" style="width: 163px; "/>
			   RG: <input name="rg" id="rg" value="<c:out value="${cliente.rg}"/>" placeholder="xx.xxx.xxx-x"  required="required" style="width: 179px; "/></p>
			<p>NASCIMENTO: <input name="data" type="date" id="data" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${cliente.dtNasc}"/>"   placeholder="dd/mm/yyyy" required="required" style="width: 148px; ">  
			   SEXO: <h:selectOneMenu style="width: 106px; height: 24px" id="sexo">				
						<f:selectItem itemLabel=" " itemValue=" "/>
						<f:selectItem itemLabel="Masculino" itemValue="M"/>
						<f:selectItem itemLabel="Feminino" itemValue="F"/>				
					 </h:selectOneMenu>
			</p>
		</fieldset>
		
		<fieldset id="endereco">
			<legend>ENDEREÇO</legend>
			<p>CEP: <input type="text" name="cep" style="width: 100px; " id="cep"> 
			   CIDADE: <input type="text" name="cidade" disabled="disabled" style="width: 205px; "></p>				    
			<p>ESTADO: <input type="text" name="estado" style="width: 75px; " disabled="disabled">
			   BAIRRO: <input type="text" name="bairro" disabled="disabled" style="width: 197px; "></p>				 								
			<p>RUA: <input type="text" name="rua" disabled="disabled" style="width: 221px; ">
			   NÚMERO: <input type="number" name="numero" style="width: 69px; "> </p>
		</fieldset>	 
			 		
		<fieldset id="contato">
			<legend>CONTATO</legend>
			<p>EMAIL: <input type="text" name="email" style="width: 354px; " placeholder="seuemail@email.com"></p>
			<p>CELULAR: <input type="text" id="celular" name="celular" style="width: 174px; " placeholder="(XX) XXXXX-XXXX "></p>
		</fieldset>			 		 			 		 		 			
		</form>	
	</f:view>
	<script>
		$("#cep").mask("99999-999");
	//	$("#data").mask("99/99/9999");
		$("#celular").mask("(99)99999-9999");
		$("#rg").mask("99.999.999-9");
		$("#cpf").mask("999.999.999-99");
	</script>
	</body>
</html>
