<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de curso concluído!</title>
</head>
<body>
<%
	out.println("Curso <b>" + request.getParameter("nome") + "</b> cadastrado!!!");
%>

</body>
</html>