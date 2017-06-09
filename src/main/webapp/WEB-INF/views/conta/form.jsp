<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Conta</title>
</head>
<body>
	<p>olá ${usuarioLogado}</p>
	<form:form servletRelativeAction="/conta" method="post" commandName="contaForm">
		<form:hidden for="numero" path="numero" id="numero" required="false"/>
		<div>
			<label for="titular">titular</label>
			<form:input path="titular" id="titular"/>
			<form:errors path="titular" />
		</div>
		<div>
			<labeL for="saldo">saldo</labeL>
			<form:input path="saldo" id="saldo"/>
			<form:errors path="saldo" />
		</div>
		<div>	
			<input type="submit" value="cadastrar" />
		</div>
	</form:form>
	<br />
	<a href="home" >voltar ao menu</a>
</body>
</html>