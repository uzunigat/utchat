<!-- Modal -->
<%@page import="java.util.Iterator"%>
<%@page import="model.User"%>
<%@page import="java.util.Vector"%>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Edit User</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form id="form" method="post" action="/UTChat/EditUser">
					<div class="form-group">
						<label for="InputTitle">Email</label> <input type="text"
							class="form-control" id="email" aria-describedby="emailHelp"
							name="email" readonly>
					</div>
					<div class="form-group">
						<label for="InputDescription">Nickname</label> <input type="text"
							class="form-control" id="nickname" aria-describedby="emailHelp"
							name="nickname">
					</div>

					<div class="form-group">
						<label for="InputDate"> Role </label> <select class="form-control"
							id="role" name="role">
							<option value="Admin">Admin</option>
							<option value="Other">Other</option>

						</select>
					</div>

					<input type="submit" class="btn btn-success float-right"
						value="Edit" id="sendForm">
				</form>


			</div>
		</div>
	</div>
</div>