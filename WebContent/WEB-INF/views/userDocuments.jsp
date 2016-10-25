<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Documents</title>
<style type="text/css">
	.table
	{
		margin-left: 50px;
		margin-right: 50x;
	}
</style>
</head>
<body>

	<h1 align="center">Uploaded Documents for ${fname} ${lname}</h1>
	<br />
	<br />
	<table border="0" class="table">
	
		<tr>
			<th>Serial No.</th>
			<th>File ID</th>
			<th>File Name</th>
			<th>Type</th>
			<th>Description</th>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
		</tr>
		<tr>
			<td colspan="7">&nbsp;</td>
		</tr>
		<c:forEach items="${documents}" var="document" varStatus="counter">
			<tr>
				<td>${counter.index + 1}</td>
				<td>${document.id}</td>
				<td>${document.documentName}</td>
				<td>${document.type}</td>
				<td>${document.description}</td>
				<td><a href='<c:url value="/download/${userId}/${document.id}" />'><input type="submit" value="Download" /></a></td>
				<td><a href='<c:url value="/delete/${userId}/${document.id}" />'><input type="submit" value="Delete" /></a></td>
			</tr>
		</c:forEach>
	
	</table>
	
	<br />
	<br />
	<h2>Click <a href='<c:url value="/index" />'>Here </a>to go to Home Page.</h2>

</body>
</html>