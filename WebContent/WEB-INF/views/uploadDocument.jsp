<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Documents Upload</title>
<style type="text/css">
	.error
	{
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

	<h1 align="center">Documents Upload for ${fname} ${lname}</h1>
	<br />
	<br />
	<c:if test="${not empty message}">
		<h1 align="center" class="errorMessage">${message}</h1>
		<br />
		<br />
	</c:if>
	<c:url value="/uploadDocumentPost" var="uploadDocumentPost" />
	<form:form action="${uploadDocumentPost}" method="POST" commandName="fileBucket" enctype="multipart/form-data">
	
		<table border="0" align="center">
		
			<tr>
				<td>Upload File : </td>
				<td><form:input type="file" path="multipartFile" /></td>
				<td><form:errors path="multipartFile" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Description : </td>
				<td><form:input path="description" /></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3" align="center"><input type="submit" value="Upload" /></td>
			</tr>
		
		</table>
	
	</form:form>
	
	<br />
	<br />
	<h2>Click <a href='<c:url value="/index" />'>Here </a>to go to Home Page.</h2>

</body>
</html>