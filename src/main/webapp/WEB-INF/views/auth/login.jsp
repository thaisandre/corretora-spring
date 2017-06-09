<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<p>Login</p>
	<form:form servletRelativeAction="/login">
		<table>
			<tr>
				<td>login:</td>
				<td><input type="text" name="username" value=''/></td>
			</tr>
			<tr>
				<td>senha:</td>
				<td><input type="password" name="password" value=''/></td>
			</tr>
			<tr>
			<td	colspan='2'>
				<input	name="submit"	type="submit"
				value="Logar"/>
			</td>
			</tr>
		</table>
	</form:form>
</body>
</html>