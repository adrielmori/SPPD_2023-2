<%@page import="negocio.Curso"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alteração do curso</title>
<%
	Curso curso = (Curso) request.getAttribute("curso");

%>
</head>
<body>

<h2>Incluir curso</h2>

<form action="controlecurso" method="post">

	<input type="hidden" name="acao" value="alterar"/>
	Número: <%=curso.getNumero() %>
	<input type="hidden" value="<%=curso.getNumero()%>" name="txtNumero" size="12"/>
	<br>
	Nome:
	<input type="text" value="<%=curso.getNome() %>" name="txtNome" size="50"/>
	<br>
	Tipo:
	<select name="selTipo">
		<option value="B" <%=curso.getTipo().equals("B") ? "selected" : "" %> >Bacharelado</option>
		<option value="L" <%=curso.getTipo().equals("L") ? "selected" : "" %> >Licenciatura</option>
		<option value="T" <%=(curso.getTipo().equals("T") ? "selected" : "") %> >Tecnologico</option>
	</select>
	
	<br><br>
	<input type="submit" value="Gravar"/>

</form>

</body>
</html>