<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Home Page</title>

<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">

<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
    
</head>
<body>

 <c:set var="contextPath" value="${pageContext.request.contextPath}" />

<a class = "btn btn-primary" href="${contextPath}/company/register.htm" >Register New Company</a>
<a id ="a1" class = "btn btn-danger" href="${contextPath}" > Logout</a>
<h1>The Below Companies are already present in Portal</h1>
<table class="table-condensed"border="1">
<tr>
<td id="td1"> Company Name </td>
<td id="td1"> Company Address </td>
<td id="td1"> Company Description </td>
<td id="td1"> Company ID </td>
<td id="td1"> Delete Company </td>
</tr>
<c:forEach items="${companies}" var="comp">
<form:form action="${contextPath}/company/delete.htm" commandName="joblist"
		method="post">
<tr>
<td> ${comp.companyName}</td>
<td> ${comp.companyAddress}</td>
<td> ${comp.companyDescription}</td>
<td><input type="text" name="compID" value="${comp.companyId}" readonly></td>
<td><input class = "btn btn-danger" type="submit" name="action" value="delete"></td>
</tr>

</form:form>

</c:forEach>
</table>
</body>
</html>