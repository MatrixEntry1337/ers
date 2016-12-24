<%@ include file="../../head.jsp" %>
<%@ include file="../../top.jsp" %>
<%@ include file="../../createModal.jsp" %>
<div class="container content">
	<div class="row">
		<div class="col-md-8 col-sm-8 col-xs-12 col-md-offset-2 col-sm-offset-2">
			<p class="bg-danger text-danger client-message">
				<c:out value="${ errorMessage }" />
			</p>
			<p class="bg-success test-success client-message">
				<c:out value="${ successMessage }" />
			</p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<div>
				<ul class="nav nav-pills nav-stacked">
					<li role="presentation" class="<c:if test="${currentSort==All}">active</c:if>">
						<a href="/ers/secure/main.do">All</a>					
					</li> 
					<c:forEach var="each" items="${ status }">
					<li role="presentation" class="<c:if test="${currentSort==each.status}">active</c:if>">
						<a href="/ers/secure/<c:out value="${ each.status }"/>.do"><c:out value="${ each.status }" /></a>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="col-md-8">
			<div class="panel panel-default">
					<div class="panel-body">
						<c:if test="${ user.role.role == 'Employee' }">
						<button class="btn btn-link btn-none btn-lg pull-right" 
						data-toggle="modal" data-target="#createReim">
								<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
						</button>
						</c:if>
						<h3>Reimbursements</h3>
						<c:forEach var="reim" items="${ reims }">
							<c:if test="${ reim.status.status == 'Pending' }">
								<div class="panel panel-info">
							</c:if>
							<c:if test="${ reim.status.status == 'Denied' }">
								<div class="panel panel-danger">
							</c:if>
							<c:if test="${ reim.status.status == 'Accepted' }">
								<div class="panel panel-success">
							</c:if>
							<div class="panel-heading">
								<div class="pull-right">
									<c:if test="${ reim.status.status == 'Pending' }">
										<span class="glyphicon glyphicon-question-sign pending" aria-hidden="true"></span>
									</c:if>
									<c:if test="${ reim.status.status == 'Denied' }">
										<span class="glyphicon glyphicon-remove-sign denied" aria-hidden="true"></span>
									</c:if>
									<c:if test="${ reim.status.status == 'Accepted' }">
										<span class="glyphicon glyphicon-ok-sign accepted" aria-hidden="true"></span>
									</c:if>	
									<span data-toggle="collapse" data-target="#<c:out value="${reim.id}"/>"
										aria-expanded="false" aria-controls="">
										<span class="caret"></span>
									</span>
								</div>
								<div>
								<!-- <input type="checkbox" name="selectReim"> -->
								<span class="panel-title"><c:out value="${ reim.type.type } " />-</span>
								<span class="badge"><em><fmt:formatNumber type="currency" value=" ${ reim.amount }" /></em></span>
								<span class="shorten"><c:out value="${ reim.description }"/></span>
								</div>
							</div>
							<div class="panel-body collapse" id="<c:out value="${ reim.id }"/>">
								<ul class="list-group reim-list">
									<li class="list-group-item reim-list-item">
										<b>Description:</b>
										<c:out value="${ reim.description }"/>
									</li>
									<li class="list-group-item reim-list-item">
										<b>Submitted:</b>
										<fmt:formatDate type="both" value="${ reim.submitted }"/>
									</li >
									<c:if test="${ reim.author != null && user.role.role == 'Manager' }">
									<li class="list-group-item reim-list-item">
										<b>Author:</b>
										<i><c:out value="${ reim.author.firstName } ${ reim.author.lastName }"/></i>
									</li>
									</c:if>
									<c:if test="${ reim.resolver != null }">
									<li class="list-group-item reim-list-item">
										<b>Resolver:</b>
										<i><c:out value="${ reim.resolver.firstName } ${ reim.resolver.lastName }"/></i>
									</li>
									<li class="list-group-item reim-list-item">
										<b>Resolved:</b>
										<fmt:formatDate type="both" value="${ reim.resolved }"/>
									</li>
									</c:if>
									<li class="list-group-item reim-list-item reim-list-button">
									<c:if test="${ user.role.role == 'Manager' }">
										<c:if test="${ reim.resolved == null }" >
										<div class="pull-right">
											<form class="manager-action" action="/ers/secure/accept.do" method="post">
												<button type="submit" class="btn btn-success" 
													name="acceptReim" value="<c:out value="${ reim.id }"/>">
													Approve</button>
											</form>
											<form class="manager-action" action="/ers/secure/deny.do" method="post">
												<button type = "submit" class="btn btn-danger"
													name="denyReim" value="<c:out value="${ reim.id }"/>">
													Deny</button>
											</form>
										</div>
										</c:if>
									</c:if>
									</li>
								</ul>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="/ers/ers.js"></script>
<%@ include file="../../bottom.jsp"%>