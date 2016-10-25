<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
</head>
<body>
	
	<h1 align="center">Home Page</h1>
	<br />
	<h1>Click on any one of the links below : </h1>
	<br />
	<br />
	
	<h2 style="margin-left: 50px;"><a href='<c:url value="/registerGet" />'>Register a New User</a></h2>
	<br />
	
	<h2 style="margin-left: 50px;"><a href='<c:url value="/uploadStartGet" />'>Upload Document for User</a></h2>
	<br />
	
	<h2 style="margin-left: 50px;"><a href='<c:url value="/listDocumentsGet" />'>View All User Documents For a User</a></h2>
	<br />
	
	<h2 style="margin-left: 50px;"><a href='<c:url value="/deleteGet" />'>Delete User Profile</a></h2>
	<br />
	
	<h2 style="margin-left: 50px;"><a href='<c:url value="/updateStartGet" />'>Update User Profile</a></h2>
	<br />
	
	<h2 style="margin-left: 50px;"><a href='<c:url value="/viewGet" />'>View User Profile</a></h2>
	<br />
	
	<h2 style="margin-left: 50px;"><a href='<c:url value="/allUsers" />'>View All Existing Users</a></h2>
	<br />

</body>
</html>