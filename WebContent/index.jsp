<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UTChat</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
</head>
<body>

	<div class="container-fluid">

		<nav class="navbar navbar-dark bg-dark justify-content-between">
			<a href="index.jsp" class="navbar-brand text-white">UTChat</a>
		</nav>

		<div class="m-5 p-5 card">

			<form action="/UTChat/Validation" method="post">
				<div class="form-group mx-5">
					<label for="exampleInputEmail1">Email</label> <input type="email"
						class="form-control" name="email" id="email"
						aria-describedby="emailHelp" placeholder="Enter email">
				</div>
				<div class="form-group mx-5">
					<label for="exampleInputPassword1">Password</label> <input
						type="password" class="form-control" name="password"
						id="exampleInputPassword1" placeholder="Password">
				</div>
				<div class="form-check mx-5">
					<input type="checkbox" class="form-check-input" id="exampleCheck1">
					<label class="form-check-label" for="exampleCheck1">Remember
						me</label>
				</div>

				<div class="text-center">
					don't have an account? <a href="./register.jsp"> Create new
						account </a>
				</div>
				<button type="submit" class="btn btn-primary mx-5 my-2 float-right ">Login</button>
				<br>
			</form>

		</div>

	</div>

</body>
</html>