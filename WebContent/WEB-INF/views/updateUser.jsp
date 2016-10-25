<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile Update Page</title>
<style type="text/css">
	.error
	{
		color: red;
		font-weight: bold;
	}
</style>
</head>
<body>

	<h1 align="center">User Profile Update</h1>
	<br />
	<br />
	<c:url value="/updatePost" var="updateUser" />
	<form:form action="${updateUser}" method="POST" commandName="user">
	
		<table>
		
			<tr>
				<td>User ID : </td>
				<td><form:input path="id" value="${user.id}" readonly="true" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>SSO ID : </td>
				<td><form:input path="ssoId" value="${user.ssoId}" /></td>
				<td><form:errors path="ssoId" cssClass="error" /></td>
			</tr>
			<tr>
				<td>First Name : </td>
				<td><form:input path="firstName" value="${user.firstName}" /></td>
				<td><form:errors path="firstName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Last Name : </td>
				<td><form:input path="lastName" value="${user.lastName}" /></td>
				<td><form:errors path="lastName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Email : </td>
				<td><form:input path="email" value="${user.email}" /></td>
				<td><form:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3" align="center"><input type="submit" value="Update" /></td>
			</tr>
		
		</table>
	
	</form:form>
	
	<br />
	<br />
	<h2>Click <a href='<c:url value="/index" />'>Here </a>to go to Home Page.</h2>
	

</body>
</html>