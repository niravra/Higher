<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employer Home Page</title>

<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">

</head>

<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="joblist" value="${jobs}" />
<a class = "btn btn-primary" href="${contextPath}/jobs/register.htm" >Add a new Job for ${user.company.companyName}</a>
<%-- <a href="${contextPath}/jobs/delete.htm" >Delete an existing Job for ${user.company.companyName}</a> <br /> --%>
<a id ="a1" class = "btn btn-danger" href="${contextPath}" > Logout</a>
 <h1>The Below Jobs are already present for ${user.company.companyName}</h1>


<table class="table-condensed" border="1">

<tr>
<td id="td1"> Requesition ID </td>
<td id="td1"> Job Name </td>
<td id="td1"> Job Description </td>
<td id="td1"> Posted By Name </td>
<td id="td1"> Posted By UserName </td>
<td id="td1"> Posted Date </td>
<td id="td1"> Update Job </td>
<!-- <td id="td1"> Delete Job</td> -->

</tr>
<c:forEach items="${joblist}" var="job">

<form:form action="${contextPath}/jobs/update.htm" commandName="joblist"
		method="get">
<tr>
<td><input type="text" name="jobId" value="${job.jobID}" readonly></td>
<td> ${job.jobName}</td>
<td> ${job.jobDesc}</td>
<td> ${job.postedName}</td>
<td> ${job.postedID}</td>
<td> ${job.postedDate}</td>
<td><input class = "btn btn-success" type="submit" name="action" value="Update"></td>
<!-- <td><input type="submit" name="action" value="Delete"></td> -->
</tr>
</form:form>


</c:forEach>
</table> 

</body>
</html>