<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>olá, ${usuarioLogado}</h2>
${sucesso}
	
	<a href="usuario/form">cadastrar ususario</a><br />
	<a href="usuario">listar usuarios</a><br />
	<a href="conta/form">cadastrar nova conta</a><br />
	<a href="conta">listar contas</a><br />
	<a href="investimento/form">cadastrar investimentos</a><br />
	<a href="investimento">listar investimentos</a><br />
	<a href="aplicacao">listar aplicacoes</a><br />

</body>
</html>