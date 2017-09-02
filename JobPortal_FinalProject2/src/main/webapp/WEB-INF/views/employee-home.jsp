<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Home</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="joblist" value="${jobs}" />

<a class = "btn btn-primary" href="${contextPath}/jobs/search.htm" >Search Job</a>
<a class = "btn btn-primary" href="${contextPath}/jobs/applied.htm" > View Applied Jobs</a>
<a id ="a1" class = "btn btn-danger" href="${contextPath}" > Logout</a>
<h1>Welcome ${user.firstName}</h1>

<table border="1">

<tr>
<td id="td1"> Requesition ID </td>
<td id="td1"> Company </td>
<td id="td1"> Job Name </td>
<td id="td1"> Job Description </td>
<td id="td1"> Job Posted By </td>
<td id="td1"> Job Posted Date </td>
<td id="td1"> Apply Job </td>


</tr>
<c:forEach items="${joblist}" var="job">

<form:form action="${contextPath}/jobs/add.htm" commandName="joblist"
		method="post">
<tr>
<td><input type="text" name="jobID" value="${job.jobID}" readonly></td>
<td>${job.company.companyName}</td>
<td> ${job.jobName}</td>
<td> ${job.jobDesc}</td>
<td> ${job.postedName}</td>
<td> ${job.postedDate}</td>
<td><input class = "btn btn-success" type="submit" name="action" value="apply"></td>
</tr>
</form:form>


</c:forEach>
</table> 



</body>

</html>