<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Seus investimentos</title>
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
		</tr>
		
		<c:forEach items="${investimentos}" var="investimento" >
			<tr>
				<td>${investimento.tipo} </td>
				<td>${investimento.taxaDeJuros}</td>
				<td>${investimento.prazo}</td>
				<td>${investimento.valorMinimo }</td>
				<td>
					<form:form servletRelativeAction="/aplicar" method="post" commandName="contas">
					<form:select path="contas" >
						<form:options items="${contas}"/>
						<form:errors path="contas" />
					</form:select>
					<input type="submit" name="aplicar" />
					</form:form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="home" >voltar ao menu</a>
</body>
</html>