<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@taglib
	uri="http://java.sun.com/jstl/fmt" prefix="fmt1"%><%@taglib
	uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tabela Cliente</title>
</head>
<body>
	<f:view>
		<a href="ControlCliente?action"><img src="img/adicionar.png"
			style="width: 21px; height: 21px;"></a>
		<br>
		<table border="1">

			<thead>
				<tr>
					<th>Nome</th>
					<th>Sobrenome</th>
					<th>RG</th>
					<th>CPF</th>
					<th>Data Nascimento</th>
					<th>Sexo</th>
					<th colspan="2">Ação</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cliente}" var="cliente">
					<tr>
						<td><c:out value="${cliente.nome}" /></td>
						<td><c:out value="${cliente.sobrenome}" /></td>
						<td><c:out value="${cliente.rg}" /></td>
						<td><c:out value="${cliente.cpf}" /></td>
						<td><fmt:formatDate pattern="dd/MM/yyyy"
								value="${cliente.dtNasc}" /></td>
						<td><c:out value="${cliente.sexo}" /></td>
						<td><a
							href='ControlCliente?action=edit&idCli=<c:out value="${cliente.idCli}"/>'><img
								src="img/refresh-icon.png" style="width: 21px; height: 21px;"></a></td>
						<td><a
							href='ControlCliente?action=delete&idCli=<c:out value="${cliente.idCli}"/>'><img
								src="img/delete.png" style="width: 21px; height: 21px;"></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</f:view>

</body>
</html>