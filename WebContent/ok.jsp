<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%@ page  import="sv.FormAluno" %>
<%
FormAluno form = (FormAluno) request.getAttribute( "form" );
if( form == null )
  form = new FormAluno(); %>
<h1>OK, aluno <%=form.getNome() %> incluido.</h1>
<hr>
<br>
<a href="http://localhost:8080/wf10/SistaCad">pag inicial</a>

</body>
</html>

<!-- 
Right-click on your project in Eclipse, select Properties
Click on Java Build path on the left
Click source tab on the right
Click "Add Folder" button and add your source folder (/src or wherever you moved it to) - só se já não estiver lá.
Ensure Allow output folders for source folders is checked below
Under newly added source path select output folder and point it 
to /WEB-INF/classes or other location of your choice (eu usei o /WEB-INF/lib).
 -->