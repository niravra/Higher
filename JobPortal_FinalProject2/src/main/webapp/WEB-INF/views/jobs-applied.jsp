<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Home</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/AdminStyle.css"/>">
<link rel="stylesheet"
	href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="joblist" value="${user.joblists}" />

	<a class = "btn btn-primary" href="${contextPath}/user/employee.htm" >Home Page</a>
	<a class="btn btn-primary" href="${contextPath}/jobs/search.htm">Search
		Job</a>
		

	<h1>Applied Jobs for ${user.firstName}</h1>

	<c:choose>

		<c:when test="${empty joblist }">
			<h2>Please apply to jobs for viewing this section</h2>
		</c:when>

		<c:otherwise>
			<table border="1">

				<tr>
					<td id="td1">Requesition ID</td>
					<td id="td1">Company</td>
					<td id="td1">Job Name</td>
					<td id="td1">Job Description</td>
					<td id="td1">Job Posted on</td>

				</tr>
				<c:forEach items="${joblist}" var="job">

					<form:form action="${contextPath}/jobs/add.htm"
						commandName="joblist" method="post">
						<tr>
							<td><input type="text" name="jobID" value="${job.jobID}"
								readonly></td>
							<td>${job.company.companyName}</td>
							<td>${job.jobName}</td>
							<td>${job.jobDesc}</td>
							<td>${job.postedDate}</td>
						</tr>
					</form:form>


				</c:forEach>
			</table>

		</c:otherwise>
	</c:choose>
</body>

</html>