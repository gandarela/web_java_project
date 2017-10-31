<%@page import="sv.CadastroAluno"%>
<%@page
	import="com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de ${form.entidade}</title>
</head>
<%@ page import="sv.*"%>
<body>
	<h1>Editando ${form.entidade}</h1>
	<hr>
	<form
		action="SistaCad?control=sv.Cadastro${form.entidade}&action=salvar&ID=${form.id}"
		method="post">
		<p>${form.id }</p>
		<%
		    FormExibir form = (FormExibir) request.getAttribute( "form" );
			
			
		    for( String key : form.getCampos().keySet() ) {
		        out.print( "<label>" + key + "</label>" );
		        out.print( "<input type=\"text\" name=" + key + " value=\"" + form.getCampos().get( key ) + "\"/><br>" );
		    }

		%>
		<button type="submit">SALVAR</button>
		<br>
	</form>
</body>
</html>

