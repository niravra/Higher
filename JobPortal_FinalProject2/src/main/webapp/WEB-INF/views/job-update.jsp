<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jobs Add</title>
<link rel="stylesheet" href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">

</head>
<body>
<c:set var="companyname" value="${companyName}" />
<c:set var="job" value="${jobList}" />

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<a class = "btn btn-primary" href="${contextPath}/user/employer.htm" >Home Page</a>
<h2>Update the job for ${companyname}</h2>

	<form:form action="${contextPath}/jobs/update.htm"
		method="post">

		<table>
			<tr>
				<td>Job ID:</td>
				<td><input type ="text" name="jobId" value="${job.jobID}" readonly></td>
			</tr>
			
			<tr>
				<td>Job Name:</td>
				<td><input type ="text" name = "jobName" value="${job.jobName}"></td>
			</tr>
			
			<tr>
				<td>Job Description:</td>
				<td><input type ="text" name = "jobDesc" value="${job.jobDesc}"></td>
			</tr>

			

			<tr>
				<td colspan="2"><input class = "btn btn-success" type="submit" value="Update Job" /></td>
			</tr>
		</table>

	</form:form>
</body>
</html>