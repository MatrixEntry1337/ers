<%@ include file="head.jsp" %>
<%@ include file="top.jsp" %>
		<div class="container content">
			<div class="row">
				<div class="col-md-4 col-sm-8 col-xs-10 col-xs-offset-1 col-sm-offset-2 col-md-offset-4">
					
					<c:if test="${ user != null }">
					<div class="alert alert-success" role="alert">
						<p>You are already logged in!</p>
						<br>
						<p>Continue to Reimbursements <a href="main.do">here</a></p>
					</div>
					</c:if>
					
					<c:if test="${ user == null }">
					<form action="login.do" method="post" class="form-signin">
						<h2 class="form-signin-heading">Login</h2>
						<label for="inputUsername" class="sr-only">Username</label> </span>
						<input type="text" class="form-control form-input"
							name="username" placeholder="Username" required autofocus>
						<label for="inputPassword" class="sr-only">Password</label>
						<input type="password" name="password" 
							class="form-control form-input" placeholder="Password" required="">
						<div class="form-button">
							<button class="btn btn-md btn-primary btn-lf pull-right"
								type="submit">Sign in</button>
						</div>
						<p class="text-danger"><c:out value="${ loginMessage }"/></p>
					</form>
					</c:if>
				</div>
			</div>
		</div>
	<%@ include file="bottom.jsp" %>