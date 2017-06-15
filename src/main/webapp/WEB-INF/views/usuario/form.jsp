<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro</title>
</head>
<body>
	<form:form servletRelativeAction="/usuario" method="post" commandName="usuarioForm">
		<label for="login">login</label>
		<form:input path="login" id="login"/><br />
		<form:errors id="login" />
		
		<label for="senha">senha</label>
		<form:input path="senha" id="senha"/><br /><br />
		<form:errors id="login" />
		
		<label for="roles">tipo de autorização:</label><br />
			<form:checkbox path="roles" value="ROLE_ADMIN"/>administrador<br />
			<form:checkbox path="roles" value="ROLE_CLIENTE"/>cliente<br /><br />
		
		<input type="submit" value="cadastrar"/>
	</form:form>
	<br />
	<form:form servletRelativeAction="/home">
		<input type="submit" value="voltar ao menu" />
	</form:form>
</body>
</html>