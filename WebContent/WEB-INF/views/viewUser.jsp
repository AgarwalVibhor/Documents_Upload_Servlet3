<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Profile</title>
</head>
<body>

	<h1 align="center">User Profile</h1>
	<br />
	<br />
	<table border="1" align="center">
	
		<tr>
			<th>User ID : </th>
			<td>${user.id}</td>
		</tr>
		<tr>
			<th>SSO ID : </th>
			<td>${user.ssoId}</td>
		</tr>
		<tr>
			<th>First Name : </th>
			<td>${user.firstName}</td>
		</tr>
		<tr>
			<th>Last Name : </th>
			<td>${user.lastName}</td>
		</tr>
		<tr>
			<th>Email : </th>
			<td>${user.email}</td>
		</tr>
		<tr>
			<th>User Documents : </th>
			<td><a href='<c:url value="/finalDocuments/${user.id}" />'>Click Here to view User Documents</a></td>
		</tr>
	
	</table>
	
	<br />
	<br />
	<h2>Click <a href='<c:url value="/index" />'>Here </a>to go to Home Page.</h2>

</body>
</html>