<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menu</title>
</head>
<body>
<h2>olá, ${usuarioLogado}</h2>
	<br />
	<a href="conta/form">cadastrar nova conta</a><br />
	<a href="conta">suas contas</a><br />
	<a href="aplicacao">seus investimentos</a><br />
	<a href="aplicacao/form">fazer novo investimento</a><br />
	<br />
	${message}<br /><br />
	<br />
	<form:form servletRelativeAction="/logout">
		<input type="submit" value="logout" />
	</form:form>
	
</body>
</html>
