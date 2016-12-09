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
				<div
					class="col-md-4 col-sm-8 col-xs-10 col-xs-offset-1 col-sm-offset-2 col-md-offset-4">
					<form action="login.do" method="post" class="form-signin">
						<h2 class="form-signin-heading">ERS</h2>
						<label for="inputUsername" class="sr-only">Username</label> </span>
						<input type="text" id="inputEmail" class="form-control form-input"
							name="username" placeholder="Username" required="" autofocus="">
							<label for="inputPassword" class="sr-only">Password</label>
							<input type="password" id="inputPassw
							ord" 
								name="password" class="form-control form-input" placeholder="Password" required="">
						<div class="form-button">
							<button class="btn btn-md btn-primary btn-lf pull-right"
								type="submit">Sign in</button>
						</div>
						<p class="text-danger"><c:out value="${loginMessage}"/></p>
					</form>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>