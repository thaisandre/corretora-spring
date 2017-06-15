<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Não autorizado</title>
</head>
<body>
	<p>Você não tem autorização para ver essa página!</p>
	<br />
	<form:form servletRelativeAction="/home">
		<input type="submit" value="voltar ao menu" />
	</form:form>
</body>
</html>