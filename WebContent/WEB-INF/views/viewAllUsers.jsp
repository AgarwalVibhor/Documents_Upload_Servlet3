<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Users Page</title>
</head>
<body>

	<h1 align="center">List of All Users</h1>
	<br />
	<br />
	<table border="1" align="center">
	
		<tr>
			<th>Serial No.</th>
			<th>User ID</th>
			<th>SSO ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>&nbsp;</th>
		</tr>
		<tr>
			<td colspan="7">&nbsp;</td>
		</tr>
		<c:forEach items="${users}" var="user" varStatus="counter">
			<tr>
				<td>${counter.index + 1}</td>
				<td>${user.id}</td>
				<td>${user.ssoId}</td>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.email}</td>
				<td><a href='<c:url value="/finalDocuments/${user.id}" />'><input type="submit" value="View Documents"></a></td>
			</tr>
		</c:forEach>
	
	</table>
	
	<br />
	<br />
	<h2>Click <a href='<c:url value="/index" />'>Here </a>to go to Home Page.</h2>

</body>
</html>