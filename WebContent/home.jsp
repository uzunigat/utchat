<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UTChat - Home</title>
</head>
<body>

	<%@include file="./header.jsp"%>

	<div class="container m-5">

		<div class="row justify-content-center">

			<div class="col-3"></div>

			<div class="col-3 d-flex justify-content-center text-center">
				<div class="card bg-light" style="width: 22rem;">
					<div class="card-header">My Chats</div>
					<img src="./images/chat.png"
						class="card-img-top rounded mx-auto d-block my-2"
						style="width: 70%;">
					<div class="card-body">
						<a href="./myChats.jsp" class="btn btn-primary ">Go</a>
					</div>
				</div>
			</div>

			<div class="col-3"></div>

			<div class="col-3 d-flex justify-content-center text-center">
				<div class="card bg-light" style="width: 22rem;">
					<div class="card-header">My Invitations</div>
					<img src="./images/invitation.png"
						class="card-img-top rounded mx-auto d-block my-2"
						style="width: 70%;">
					<div class="card-body">
						<a href="./myInvitations.jsp" class="btn btn-primary">Go</a>
					</div>
				</div>
			</div>

			<%
			if (session.getAttribute("role").equals("Admin")) {
			%>

			<div class="col-3"></div>

			<div class='col-3 d-flex justify-content-center text-center mt-4'>
				<div class='card bg-light' style='width: 22rem;'>
					<div class='-header'>Admin</div>
					<img src='./images/admin.png'
						class='card-img-top rounded mx-auto d-block my-2'
						style='width: 70%;'>
					<div class="card-body">
						<a href='./admin.jsp' class='btn btn-primary'>Go</a>
					</div>
				</div>
			</div>

			<%
			}
			%>

		</div>
	</div>

</body>
</html>