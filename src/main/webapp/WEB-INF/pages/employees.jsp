<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</head>
<body>
	<h1>All Employees</h1>
	<div class="container">
		<table class="table">
			<tr>
				<th>First</th>
				<th>Last</th>
				<th>Manager</th>
				<th>Department</th>
				<th>Department Budget</th>
			</tr>
			<c:forEach var="emp" items="${list}">
				<tr>
					<td>
						<c:choose>
							<c:when test="${emp.firstName == 'Mickey'}">
								<b>
									<c:out value="${emp.firstName}"/>
								</b>
							</c:when>
							<c:otherwise>
								<c:out value="${emp.firstName}"/>
							</c:otherwise>
						</c:choose>
					</td>
					<td><c:out value="${emp.lastName}"/></td>
					<td><c:out value="${emp.manager.firstName}"/></td>
					<td><c:out value="${emp.department.name}"/></td>
					<td><fmt:formatNumber type="currency" value="${emp.department.budget}"/></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>