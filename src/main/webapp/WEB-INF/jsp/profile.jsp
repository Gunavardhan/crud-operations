<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	table{
		border-collapse:collapse;
		border:1px solid black;
	}
	tr,th,td{
		text-align:center;
		border:1px solid black;
	}
	.applyCss{
		padding: 13px;
    	font-size: 20px;
    	border: 1px solid #bd2121;
    	background-color: #bd2121;
    	color: white;
   		text-decoration: none !important;
	}
</style>
<script type="text/javascript">
	function deleteStudent(email){
		var result = confirm("Are you sure,you wants to delete?");
		if(result){
			document.forms[0].action = "${pageContext.request.contextPath}/deleteStudent?email="+email;
			document.forms[0].method = "POST";
			document.forms[0].submit();
		}
	}
	function editStudent(email){
		alert("Email : " + email);
		document.forms[0].action = "${pageContext.request.contextPath}/editStudent?email="+email;
		document.forms[0].method = "POST";
		document.forms[0].submit();
	}
	function checkDelMultiple(){
		alert("function calling");
		debugger;
		//if(document.getElementById('selectCheckbox').checked){
			//debugger;
			document.forms[0].action = "deletemultiple";
			document.forms[0].method="post";
			document.forms[0].submit();
		//}
		
	}
</script>
</head>
<body>
	<div style="margin-top: 20px;">
		<a class="applyCss" href="${pageContext.request.contextPath}/logout">Logout</a>
	</div>
	<h3>Welcome :${username}</h3>
	<div>
		<h1>Login User Profile</h1>
		<h5>UserName : ${loginUserProfile.stdName}</h5>
		<h5>Email : ${loginUserProfile.email}</h5>
		<h5>Mobile : ${loginUserProfile.mobile}</h5>
	</div>
	<div>
	<form action="">
	<table>
		<tr>
			<th>Student Name</th>
			<th>Email</th>
			<th>Password</th>
			<th>Phone no.</th>
			<th>User Role.</th>
			<th>Actions</th>
		</tr>
		
			<c:forEach var="student" items="${allStudents}">
			<c:if test="${student.getRole().equals('User')}">
			<tr>
				<td><c:out value="${student.stdName}"/></td>
				<td><c:out value="${student.email}"/></td>
				<td><c:out value="${student.pwd}"/></td>
				<td><c:out value="${student.mobile}"/></td>
				<td><c:out value="${student.role}"/></td>
				<td>
				<input type="checkbox" id="selectCheckbox" name="checkDeleteAll" value="${student.email}" required>&nbsp;
				<input type="button" value="Edit" onclick="editStudent('${student.email}')"/>&nbsp;&nbsp;
				<a href="${pageContext.request.contextPath}/deletestudent/${student.email}">Delete</a>&nbsp;
				<a href="${pageContext.request.contextPath}/viewstudent?email=${student.email}">View Student</a>&nbsp;
				<input type="button" value="Delete" onclick="deleteStudent('${student.email}')"/></td>
			</tr>
			</c:if>
			</c:forEach>
		
	</table>
	<input type="button" value="Delete All" onclick="checkDelMultiple()"/>
	</form>
	</div>
	<div>
	<h5 style="color:red">${msg}</h5>
	<form action="${pageContext.request.contextPath}/getuserinfo" method="post">
	`	<input type="email" name=email />
		<input type="submit" value="Get User Info"/>
	</form>
	</div>
</body>
</html>