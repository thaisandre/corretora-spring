<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	${sucesso}
	<br />
	<p>Suas aplicacoes</p><br />
	<table>
		<tr>
			<th>titular</th>
			<th>numero</th>
			<th>tipo</th>
			<th>taxa</th>
			<th>prazo</th>
			<th>valor mínimo</th>
		</tr>
		
		<c:forEach items="${aplicacoes}" var="aplicacao" >
			<tr>
				<td>${aplicacao.conta.titular}</td>
				<td>${aplicacao.conta.numero}</td>
				<td>${aplicacao.investimento.tipo} </td>
				<td>${aplicacao.investimento.taxaDeJuros}</td>
				<td>${aplicacao.investimento.prazo}</td>
				<td>${aplicacao.investimento.valorMinimo }</td>
				<td><a href="/resgatar">resgatar</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="home" >voltar ao menu</a>

</body>
</html>