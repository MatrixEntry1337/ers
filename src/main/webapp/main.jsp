<%@ include file="head.jsp"%>
<%@ include file="top.jsp"%>
<div class="container content">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
					<div class="panel-body">
						<button class="btn btn-link btn-none btn-lg pull-right">
								<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
						</button>
						<h3>Reimbursements</h3>
						<c:forEach var="reim" items="${reims}">
						<div class="panel panel-default">
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
									<a href="#" data-toggle="collapse" data-target="#<c:out value="${reim.id}"/>"
										aria-expanded="false" aria-controls="">
										<span class="glyphicon glyphicon-option-horizontal" aria-hidden="true"></span>
									</a>
								</div>
								<div>
								<span class="panel-title"><c:out value="${ reim.type.type }" /></span>
								<em><fmt:formatNumber type="currency" value="${ reim.amount }" /></em>
								</div>
							</div>
							<div class="panel-body collapse" id="<c:out value="${ reim.id }"/>">
								<ul class="list-group reim-list">
									<li class="list-group-item reim-list-item">
										<b>Description:</b>
										<c:out value="${ reim.description }"/>
									</li>
									<li class="list-group-item reim-list-item">
										<b>Author:</b>
										<i><c:out value="${ reim.author.firstName } ${ reim.author.lastName }"/></i>
									</li>
									<li class="list-group-item reim-list-item">
										<b>Submitted:</b>
										<c:out value="${ reim.submitted }"/>
									</li>
									<li class="list-group-item reim-list-item">
										<b>Resolver:</b>
										<i><c:out value="${ reim.resolver.firstName } ${ reim.resolver.lastName }"/></i>
									</li>
									<li class="list-group-item reim-list-item">
										<b>Resolved:</b>
										<i><c:out value="${ reim.resolved }"/></i>
									</li>
								</ul>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th><input type="checkbox" /></th>
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
							<td><input type="checkbox" />
							<td><c:out value="${reim.amount}" /></td>
							<td><c:out value="${reim.type.type}" /></td>
							<td><c:out value="${reim.description}" /></td>
							<td><c:out value="${reim.status.status}" /></td>
							<td><c:out value="${reim.submitted}" /></td>
							<td><c:out value="${reim.resolved}" /></td>
							<td><c:out value="${reim.author.username}" /></td>
							<td><c:out value="${reim.resolver.username}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<%@ include file="bottom.jsp"%>