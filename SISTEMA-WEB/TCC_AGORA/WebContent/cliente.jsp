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
</head>
<body>

<f:view>
	<form action="ControlCliente" method="POST" name="cadastroCliente" >  
		<p>NOME: <input name="nome" style="width: 209px; " maxlength="50" value="<c:out value="${cliente.nome}"/>" required="required"></p>
		<p>SOBRENOME:<input name="sobrenome" maxlength="50" value="<c:out value="${cliente.sobrenome}"/>" required="required"></p>
		<p>CPF: <input name="cpf" style="width: 130px;" maxlength="14"value="<c:out value="${cliente.cpf}"/>" placeholder="xxx.xxx.xxx-xx"   required="required"></p>
		<p>RG: <input name="rg" style="width: 130px;" maxlength="12" value="<c:out value="${cliente.rg}"/>" placeholder="xx.xxx.xxx-x"  required="required"></p>
		<p>DATA NASCIMENTO: <input name="data" type="date" style="width: 181px;" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${cliente.dtNasc}"/>"  maxlength="10" placeholder="dd/MM/yyyy"   required="required"></p>
		<p>SEXO: <h:selectOneMenu style="width: 130px; " id="sexo">
					 
					<f:selectItem itemLabel=" " itemValue=" "/>
					<f:selectItem itemLabel="Masculino" itemValue="M"/>
					<f:selectItem itemLabel="Feminino" itemValue="F"/>
				
				</h:selectOneMenu>
		</p>
		<p><input value="ENVIAR" type="submit"><input value="CANCELAR" type="button" onclick="/T"></p>
		</form>
 	 
</f:view>
</body>
</html>
