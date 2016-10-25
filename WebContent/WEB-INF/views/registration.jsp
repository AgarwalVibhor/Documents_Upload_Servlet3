<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Page</title>
<style type="text/css">
	.error{
		color: red;
		font-weight: bold;
	}
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

	<h1 align="center">Registration Form</h1>
	<br />
	<br />
	<c:if test="${not empty message}">
		<h1 align="center" class="errorMessage">${message}</h1>
		<br />
		<br />
	</c:if>
	<c:url value="/registerPost" var="register" />
	<form:form action="${register}" method="POST" commandName="userForm">
	
		<table border="0" align="center">
		
			<tr>
				<td>SSO ID : </td>
				<td><form:input path="ssoId" /></td>
				<td><form:errors path="ssoId" cssClass="error" /></td>
			</tr>
			<tr>	
				<td>FIRST NAME : </td>
				<td><form:input path="firstName" /></td>
				<td><form:errors path="firstName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>LAST NAME : </td>
				<td><form:input path="lastName" /></td>
				<td><form:errors path="lastName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>EMAIL : </td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3" align="center"><input type="submit" value="Register" /></td>
			</tr>
		
		</table>
	
	</form:form>
	
	<br />
	<br />
	<h2>Click <a href='<c:url value="/index" />'>Here </a>to go to Home Page.</h2>

</body>
</html>