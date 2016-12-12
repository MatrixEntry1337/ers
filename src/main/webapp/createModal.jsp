<!-- Modal -->
<div class="modal fade" id="createReim" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <form action="/secure/createReim.do" method="post">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Create Reimbursement</h4>
	      </div>
	      <div class="modal-body">
	       	<div class="form-group">
				<label for="amount">Amount:</label>
	 				<input type="text" class="form-control" name="amount"
	 					required placeholder="Enter amount..."/>
			</div>
	       	<div class="form-group">
	 				<label for="description">Description:</label>
	 				<textarea class="form-control" rows="5" name="description" 
	 					required placeholder="Enter your description here..."></textarea>
			</div>
			<div class="form-group">
			  <label for="type">Type:</label>
			  <select class="form-control" name="type">
			    <option></option>
			    <option>Lodging</option>
			    <option>Travel</option>
			    <option>Food</option>
			    <option>Other</option>
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