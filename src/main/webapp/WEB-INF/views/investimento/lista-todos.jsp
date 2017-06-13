<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta  charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>Investimentos</p><br />
	<table>
		<tr>
			<th>tipo</th>
			<th>taxa</th>
			<th>prazo</th>
			<th>valor mínimo</th>
		</tr>
		
		<c:forEach items="${investimentos}" var="investimento" >
			<tr>
				<td>${investimento.tipo} </td>
				<td>${investimento.taxaDeJuros}</td>
				<td>${investimento.prazo}</td>
				<td>${investimento.valorMinimo }</td>
			</tr>
		</c:forEach>
		</table>	
</body>
</html>