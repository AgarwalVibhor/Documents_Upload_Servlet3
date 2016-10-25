<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Documents Start</title>
<style type="text/css">
	.errorMessage {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}
</style>
</head>
<body>

	<h1 align="center">User Documents Search</h1>
	<br />
	<br />
	<c:if test="${not empty message}">
		<h1 align="center" class="errorMessage">${message}</h1>
		<br />
		<br />
	</c:if>
	
	<form action='<c:url value="listDocumentsPost" />' method="POST">
	
		<table border="0" align="center">
		
			<tr>
				<td>USER ID : </td>
				<td><input type="text" name="userId" /></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td  colspan="2" align="center"><input type="submit" value="Check Id" /></td>
			</tr>
		
		</table>
	
	</form>
	
	<br />
	<br />
	<h2>Click <a href='<c:url value="/index" />'>Here </a>to go to Home Page.</h2>

</body>
</html>