<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<title>UTChat</title>
</head>
<body>

	<div class="container-fluid">

		<nav class="navbar navbar-dark bg-dark justify-content-between">
			<a href="index.jsp" class="navbar-brand text-white">UTChat</a>
		</nav>

		<div class="m-5 p-5 card">

			<%
			if (request.getAttribute("response") != null && request.getAttribute("response").equals("Already_Added")) {

				out.print("<div class = 'alert alert-danger text-center'> This User is already on the system, please try again </div>");

			}
			%>

			<form action="/UTChat/ValidationRegister" method="post">
				<div class="form-group mx-5">
					<label for="exampleInputEmail1">Email</label> <input type="email"
						class="form-control" name="email" id="email"
						aria-describedby="emailHelp" placeholder="Enter email">
				</div>
				<div class="form-group mx-5">
					<label for="exampleInputEmail1">Username</label> <input type="text"
						class="form-control" name="username" id="username"
						aria-describedby="emailHelp" placeholder="Enter email">
				</div>

				<div class="form-group mx-5">
					<label for="exampleInputPassword1">Password</label> <input
						type="password" class="form-control" name="password"
						id="password" placeholder="Password">
				</div>

				<div class="form-group mx-5">
					<label for="exampleInputPassword1">Confirm Password</label> <input
						type="password" class="form-control" name="confirmPassword"
						id="confirmPassword" placeholder="Confirm Password">
				</div>

				<button id="registerButton" type="submit" class="btn btn-primary mx-5 my-2 float-right ">
					Register</button>

			</form>

		</div>

	</div>


</body>

<script src="./js/jquery-3.6.0.min.js"></script>
<script src="./js/register.js"></script>

</html>