<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Suas contas</title>
</head>
<body>
${sucesso}<br />
	<p>Suas contas </p><br />
	<table>
		<tr>
			<th>titular</th>
			<th>numero</th>
			<th>saldo</th>
		</tr>
		
		<c:forEach items="${contas}" var="conta" >
			<tr>
				<td>${conta.titular} </td>
				<td>${conta.numero}</td>
				<td>${conta.saldo}</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<form:form servletRelativeAction="/home">
		<input type="submit" value="voltar ao menu" />
	</form:form>
</body>
</html>