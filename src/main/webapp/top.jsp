<body>
	<div class="wrapper">
		<nav class="navbar navbar-default ers-nav">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">Project ERS</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<!--<ul class="nav navbar-nav">--> 
					<!--<li class="active"><a href="#">Home</a></li>-->
					<!--<li><a href="#">Link</a></li>-->
					<!--<li><a href="#">Link</a></li>-->
					<!--</ul>-->
					<c:if test="${ user == null }">
					<a href="login.jsp">
						<button type="button" 
						class="btn btn-primary btn-sm navbar-btn pull-right">
						Sign In</button>
					</a>
					</c:if>
					<c:if test="${ user != null }">
						<p class="navbar-text navbar-right">Signed in as 
							<a href="#" class="navbar-link">
								<c:out value="${ user.username }"/>
							</a>
						</p>
					</c:if>
					<c:if test="${ user != null }">
					<a href="/ers/secure/logout.do">
						<button type="button"
						class="btn btn-primary btn-sm navbar-btn pull-right">
						Sign Out</button></a>
					</c:if>
				</div>
				<!--/.navbar-collapse -->
			</div>
		</nav>