<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/chat.css">
</head>
<body>

	<%
	if (session.getAttribute("username") == null) {
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
	%>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a href="home.jsp" class="navbar-brand text-white"> UTChat </a>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse float-right navbar-right"
			id="navbarNavDropdown">
			<ul class="navbar-nav ml-auto mr-5">
				<li class="nav-item"><a class="nav-link" href="./myChats.jsp">My Chats</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="./myInvitations.jsp">Invitations</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> <%=session.getAttribute("username")%>

				</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="#">Change Password</a> <a
							class="dropdown-item" href="#">Config</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="./Disconnect">Logout</a>
					</div></li>
			</ul>
		</div>
	</nav>

</body>

<script src="./js/jquery-3.6.0.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
</html>