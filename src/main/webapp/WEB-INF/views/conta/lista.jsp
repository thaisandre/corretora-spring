<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<a href="home" >voltar ao menu</a>
</body>
</html>