<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro</title>
</head>
<body>
	<c:url value="/usuario" var="url" />
	<form action="${url}" method="post">
		<div>
			<label for="login">login</label>
			<input type="text" name="login" id="login"/>
		</div>
		<div>
			<labeL for="senha">senha</labeL>
			<input type="password" name="senha" id="senha"/>
		</div>
		<div>	
			<input type="submit" name="entrar" />
		</div>
	</form>
</body>
</html>