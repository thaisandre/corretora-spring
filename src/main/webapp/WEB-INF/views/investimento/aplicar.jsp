<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	<p>Seus investimentos</p><br />
	<table>
		<tr>
			<th>tipo</th>
			<th>taxa</th>
			<th>prazo</th>
			<th>valor mínimo</th>
			<th>aplicar</th>
		</tr>
		
		<c:forEach items="${investimentos}" var="investimento" >
			<tr>
				<td>${investimento.tipo} </td>
				<td>${investimento.taxaDeJuros}</td>
				<td>${investimento.prazo}</td>
				<td>${investimento.valorMinimo }</td>
				<td>
					<form:select path="conta">
						<form:options items="${contas}"/>
						<form:errors path="conta" />
					</form:select>
					<form:input path="valor" id="valor" />
					<form:errors path="valor" />				
				</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="home" >voltar ao menu</a>
</body>
</html>