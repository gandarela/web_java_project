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
<%@ page import="sv.FormListar"%>
<body>
	<h1>Lista de ${form.entidade}</h1>
	<hr>
	<%
	    FormListar form = (FormListar) request.getAttribute( "form" );

	    for( int i = 0; i < form.getSize(); i++ )
	        out.print( form.getNome().get( i ) + "<br>" );
	%>

	<br>

</body>
</html>

