<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add User Form</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet"
	href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a class = "btn btn-primary" href="${contextPath}" > Job Portal</a>

	<h2>Register a New User</h2>

	<form:form action="${contextPath}/user/register.htm" commandName="user"
		method="post">

		<table class="table">
			<tr>
				<td>First Name:</td>
				<td><form:input path="firstName" size="30" required="required" />
					<font color="red"><form:errors path="firstName" /></font></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><form:input path="lastName" size="30" required="required" />
					<font color="red"><form:errors path="lastName" /></font></td>
			</tr>


			<tr>
				<td>User Name:</td>
				<td><form:input path="username" size="30" required="required" />
					<font color="red"><form:errors path="username" /></font></td>
			</tr>

			<tr>
				<td>Password:</td>
				<td><form:password path="password" size="30"
						required="required" /> <font color="red"><form:errors
							path="password" /></font></td>
			</tr>

			<tr>
				<td>Email Id:</td>
				<td><form:input path="email.emailAddress" size="30"
						type="email"  /> <font color="red"><form:errors
							path="email.emailAddress" /></font></td>
			</tr>
			<tr>
			<td>Role :</td>
			<td> <select name="myRole">
            <option value="employer">Employer</option>
            <option value="employee">Employee</option>
            </select>
            </td>
			</tr>
			
			<tr>
			<td>Company :</td>
			<td> <select name="myCompany">
            <c:forEach var="item" items="${companies}">
            <option value="${item.companyName}">${item.companyName}</option>
          </c:forEach>
            </select>
            </td>
			</tr>

			<tr>
				<td colspan="2"><input class = "btn btn-success" type="submit" value="Register User" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>