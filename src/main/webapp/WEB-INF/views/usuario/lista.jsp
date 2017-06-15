<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>listagem usuarios</title>
</head>
<body>
${sucesso}
	<table>
		<tr>
			<th>login</th>
			<th>senha</th>
		</tr>
		
		<c:forEach items="${usuarios}" var="usuario" >
			<tr>
				<td>${usuario.login} </td>
				<td>${usuario.senha}</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<form:form servletRelativeAction="/home">
		<input type="submit" value="voltar ao menu" />
	</form:form>
</body>
</html>