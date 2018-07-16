<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	.applyCss{
		padding: 13px;
    	font-size: 20px;
    	border: 1px solid #bd2121;
    	background-color: #bd2121;
    	color: white;
   		text-decoration: none !important;
	}
</style>
</head>
<body>
	<div style="margin-top: 20px;">
		<a class="applyCss" href="${pageContext.request.contextPath}/logout">Logout</a>
	</div>
	<div>
		<h1>Login User Profile</h1>
		<h5>UserName : ${loginUserProfile.stdName}</h5>
		<h5>Email : ${loginUserProfile.email}</h5>
		<h5>Mobile : ${loginUserProfile.mobile}</h5>
	</div>
</body>
</html>