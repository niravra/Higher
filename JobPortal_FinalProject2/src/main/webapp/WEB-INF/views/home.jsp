<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Job Portal</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">

<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
 
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a class = "btn btn-info" href="${contextPath}">Job Portal</a>
	<a class = "btn btn-primary" href="${contextPath}/user/register.htm">Register a new User</a><br/>

	<h2>Existing User <small>Please Enter Your Credentials</small></h2>
	<form action="${contextPath}/user/login.htm" method="post">
	
		<table class="table">
		<tr>
		    <td>User Name:</td>
		    <td><input name="username" size="30" required="required" /></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><input type="password" name="password" size="30" required="required"/></td>
		</tr>
		
		<tr>
		    <td colspan="2"><input class = "btn btn-success" type="submit" value="Login" /></td>
		</tr>
				
		</table>

	</form>


</body>
</html>

