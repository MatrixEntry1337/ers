<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ERS App</title>
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

<link rel="stylesheet" href="ers.css">
</head>
<body>
	<div class="wrapper">
		<div class="container content">
			<div class="row">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Amount</th>
							<th>Type</th>
							<th>Description</th>
							<th>Status</th>
							<th>Submitted</th>
							<th>Resolved</th>
							<th>Author</th>
							<th>Resolver</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="reim" items="${reims}">
							<tr>
								<td><c:out value="${reim.amount}"/></td>
								<td><c:out value="${reim.type.type}"/></td>
								<td><c:out value="${reim.description}"/></td>
								<td><c:out value="${reim.status.status}"/></td>
								<td><c:out value="${reim.submitted}"/></td>
								<td><c:out value="${reim.resolved}"/></td>
								<td><c:out value="${reim.author.username}"/></td>
								<td><c:out value="${reim.resolver.username}"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
</body>
</html>