<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>${usuarioLogado}</h2><br/>
	
	<p>Suas aplicacoes</p><br />
	<table>
		<tr>
			<th>numero da conta</th>
			<th>tipo</th>
			<th>valor investido</th>
			<th>taxa(a.a)</th>
			<th>prazo(em meses)</th>
			<th>data inicial</th>
			<th><span style="color:red">${message}</span></th>
		</tr>
		
		<c:forEach items="${aplicacoes}" var="aplicacao" >
			<tr>
				<td>${aplicacao.conta.numero}</td>
				<td>${aplicacao.investimento.tipo} </td>
				<td>${aplicacao.valor}</td>
				<td>${aplicacao.investimento.taxaDeJuros}</td>
				<td>${aplicacao.investimento.prazo}</td>
				<td>${aplicacao.dataInicial}</td>
				<td>
					<c:url value="resgatar/${aplicacao.id}" var="linkDetalhar" />
					<a href="${linkDetalhar}">resgatar</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<form:form servletRelativeAction="/home">
		<input type="submit" value="voltar ao menu" />
	</form:form>
</body>
</html>