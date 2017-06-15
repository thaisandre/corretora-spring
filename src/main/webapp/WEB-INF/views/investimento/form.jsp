<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib	uri="http://www.springframework.org/security/tags" prefix="security"	%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- <spring:hasBindErrors name="investimento">
		<ul>
			<c:forEach var="error" items="${errors.allErrors}" >
			<li><spring:message  code="${error.code}" text="${error.defaultMessage}" /></li>				
			</c:forEach>
		</ul>
		</spring:hasBindErrors> -->


	<form:form servletRelativeAction="/investimento" method="post" commandName="investimento">
		<security:csrfInput />
		<div>
			<form:select path="tipo" >
				<form:options items="${tipos}"/>
				<form:errors path="tipo" />
			</form:select>
		</div>
		<div>
			<label for="taxaDeJuros">taxa(a.a)</label>
			<form:input path="taxaDeJuros" id="taxaDeJuros" />
			<form:errors path="taxaDeJuros" />
		</div>
		<div>
			<labeL for="prazo">prazo</labeL>
			<form:input path="prazo" id="prazo" />
			<form:errors path="prazo" />
		</div>
		<div>
			<labeL for="valorMinimo">valor minimo</labeL>
			<form:input path="valorMinimo" id="valorMinimo" />
			<form:errors path="valorMinimo" />
		</div>
		<div>
			<input type="submit" value="cadastrar" />
		</div>
	</form:form>
	<br />
	<form:form servletRelativeAction="/home">
		<input type="submit" value="voltar ao menu" />
	</form:form>
</body>
</html>