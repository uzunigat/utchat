<!-- Modal -->
<%@page import="java.util.Iterator"%>
<%@page import="model.User"%>
<%@page import="java.util.Vector"%>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Create Chat</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form method="post" action="/UTChat/RegisterChat">
					<div class="form-group">
						<label for="InputTitle">Title</label> <input type="text"
							class="form-control" id="title" aria-describedby="emailHelp"
							placeholder="My chat" name="title">
					</div>
					<div class="form-group">
						<label for="InputDescrip tion">Description</label>
						<textarea class="form-control" id="desc"
							placeholder="This chat ..." name="description"> </textarea>
					</div>

					<div class="form-group">
						<label for="InputDate"> Start Date </label> <input
							type="datetime-local" class="form-control" id="startDate"
							name="startDate">
					</div>

					<div class="form-group">
						<label for="InputDate"> End Date </label> <input
							type="datetime-local" class="form-control" id="endDate"
							name="endDate">
					</div>

					<div class="form-group">
						<label for="InputDate"> Members </label> <select
							class="form-control" id="members" name="members">
							
							<option value="" > ----------------------- </option>

							<%
								Vector<String> members = User.SearchAllMembers();
	
								Iterator it = members.iterator();
	
								while (it.hasNext()) {
				
									String currentMember = (String) it.next();
									
									if(!currentMember.equals(request.getSession().getAttribute("username"))){
										out.print("<option value = " + currentMember + " > " + currentMember + "</option>");
									}
								}
							%>
						
						</select>
					</div>
					

					
					<br>
					
					<div id="selectedOptions" class="d-flex flex-column">
					
						
					</div>
					
					<input type="submit" class="btn btn-primary float-right" value="Submit">
				</form>
				
					
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Modify Chat</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form method="post" action="/UTChat/EditChat">
					<div class="form-group">
						<label for="InputTitle">Title</label> <input type="text"
							class="form-control" id="title2" aria-describedby="emailHelp"
							placeholder="My chat" name="title2" readonly>
					</div>
					<div class="form-group">
						<label for="InputDescrip tion">Description</label>
						<textarea class="form-control" id="desc2"
							placeholder="This chat ..." name="description2"> </textarea>
					</div>

					<div class="form-group">
						<label for="InputDate"> Start Date </label> <input
							type="datetime-local" class="form-control" id="startDate2"
							name="startDate2">
					</div>

					<div class="form-group">
						<label for="InputDate"> End Date </label> <input
							type="datetime-local" class="form-control" id="endDate2"
							name="endDate2">
					</div>
					
					<input type="submit" class="btn btn-primary float-right" value="Edit">
				</form>
				
					
			</div>
		</div>
	</div>
</div>