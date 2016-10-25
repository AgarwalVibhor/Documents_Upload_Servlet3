<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Success Page</title>
</head>
<body>

	<h1>${user.firstName} ${user.lastName} ! Your profile has been updated successfully.</h1>
	<br />
	
	<h2>Click <a href='<c:url value="/finalDocuments/${user.id}" />'>Here</a> to update his documents uploaded.</h2>
	
	<br />
	<br />
	<h2>Click <a href='<c:url value="/index" />'>Here </a>to go to Home Page.</h2>

</body>
</html>