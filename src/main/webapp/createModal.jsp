<!-- Modal -->
<div class="modal fade" id="createReim" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <form action="/ers/secure/createReim.do" method="post">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Create Reimbursement</h4>
	      </div>
	      <div class="modal-body">
	       	<div class="form-group">
				<label for="amount">Amount:</label>
	 				<input type="number" class="form-control" name="amount"
	 					min="0" step="any" required placeholder="Enter amount..."/>
			</div>
	       	<div class="form-group">
	 				<label for="description">Description:</label>
	 				<textarea class="form-control" rows="5" name="description" 
	 					required placeholder="Enter your description here..."></textarea>
			</div>
			<div class="form-group">
			  <label for="type">Type:</label>
			  <select class="form-control" name="type" required>
			    <option></option>
			  	<c:forEach var="type" items="${ types }">
			    <option><c:out value="${ type.type }" /></option>
			   	</c:forEach>
			  </select>
			</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">Submit</button>
	      </div>
	    </div>
    </form>
  </div>
</div>