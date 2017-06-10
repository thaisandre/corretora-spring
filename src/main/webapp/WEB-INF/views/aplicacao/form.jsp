<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>aplicar</title>
</head>
<body>
	${sucesso}<br />
	<br />
	<p>Fazer nova aplicacao</p><br />
		<form:form servletRelativeAction="/aplicar" method="post"
			commandName="aplicacaoForm">
				
				<form:select path="investimentoId" id="investimentoId" items="${investimentos}" itemValue="id" itemLabel="tipo"/>
				<form:errors path="investimentoId" />
				
				<form:select path="contaId" id="contaId" items="${contas}" itemValue="id" itemLabel="numero" />
				<form:errors path="contaId" />
		
				<form:input path="valor" id="valor"/>
				<form:errors path="valor" />
				
				<br /><br />
				<input type="submit" value="aplicar" />
		</form:form>
	<br />
	<a href="home">voltar ao menu</a>
</body>
</html>