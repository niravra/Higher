<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Company Registration</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
    
</head>
<body>
<h2>Register a New Company</h2>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a class = "btn btn-primary" href="${contextPath}/user/admin.htm" >Home Page</a>

	<form:form action="${contextPath}/company/register.htm" commandName="company"
		method="post">

		<table>
			<tr>
				<td>Company Name:</td>
				<td><form:input path="companyName" size="30" required="required" />
					<font color="red"><form:errors path="companyName" /></font></td>
			</tr>

			<tr>
				<td>Company Address:</td>
				<td><form:input path="companyAddress" size="30" required="required" />
					<font color="red"><form:errors path="companyAddress" /></font></td>
			</tr>


			<tr>
				<td>Company Description:</td>
				<td><form:input path="companyDescription" size="30" required="required" />
					<font color="red"><form:errors path="companyDescription" /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input class = "btn btn-success" type="submit" value="Register Company" /></td>
			</tr>
		</table>

	</form:form>
</body>
</html>