<%@page import="java.util.List"%>
<%@page import="negocio.Curso"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de cursos da Instituição</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</head>
<body>
	<h1>Lista de cursos cadastrados</h1>
	
	<%
		String novo = (String) request.getAttribute("novocurso");
		if (novo != null && novo != "") {
			out.println("<b>O curso " + novo + " foi cadastrado com sucesso!!</b>");
		}
	
		String excluido = (String) request.getAttribute("cursoexcluido");
		if (excluido != null && excluido != "") {
			out.println("O curso <b>" + excluido + "</b> foi excluído!!");
		}
	%>
	<table class="table table-striped">
	<tr>
		<td>Código</td>
		<td>Nome</td>
		<td>Tipo</td>
		<td>Ação</td>
	</tr>
	<%
		List<Curso> listaCurso = (List<Curso>)request.getAttribute("cursos");
		for(Curso c : listaCurso){
			out.println("<tr>");
			out.println("<td>" + c.getNumero() + "</td>");
			out.println("<td>" + c.getNome() + "</td>");
			out.println("<td>" + c.getTipoDescricao() + "</td>");
			out.println("<td>");

			out.println("<a href='controlecurso?acao=editar&numero="
					+ c.getNumero()
					+"'> <img width='15px' height='15px' src='icones/edit.png'/></a>");

			out.println("&nbsp;&nbsp;<a href='controlecurso?acao=excluir&numero="
						+ c.getNumero()
						+"'><img width='15px' height='15px' src='icones/delete.png'/> </a>");
			out.println("</td>");
			out.println("</tr>");
		}
	%>
	</table>

	<br>
	<a href="menu.html">Voltar para menu</a>
</body>
</html>