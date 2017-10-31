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
<%@ page import="sv.FormExibir"%>
<body>
	<h1>Ficha do ${form.entidade}</h1>
	<hr>
	<%
	    FormExibir form = (FormExibir) request.getAttribute( "form" );

	    for( String key : form.getCampos().keySet() ) {
	        out.print( key + ":" + form.getCampos().get( key ) + "<br>" );
	    }
	%>

	<br>

<a href="SistaCad?control=sv.Cadastro&action=exibir&entidade=${form.entidade}&ID=${form.id}&editar=true"> EDITAR ${form.entidade} </a>
</body>
</html>

