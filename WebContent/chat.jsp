<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UTChat</title>
</head>
<body>

	<%@include file="./header.jsp"%>

	<div class="container-fluid">

		<div class="row m-2">

			<div class="col-8">

				<div class="container">

					<div class="row py-2">
						<textarea id="history" cols="100" rows="20" readonly> </textarea>
					</div>

					<div class="row">

						<input id="txtMessage" type="text" />
						<input id="username" type="hidden" value=<%= request.getSession().getAttribute("username") %>>
						<input id="endDate" type="hidden" value=<%= request.getParameter("endDate") %>>
						<input id="chatId" type="hidden" value=<%= request.getParameter("chatId") %>>
						<button id="btnSend" class="btn mx-2"> <img src="./images/paper-plane.png" width="30" height="30"> </button>

					</div>

				</div>

			</div>

			<div class="col-4"></div>

		</div>

	</div>

</body>

<script src="./js/jquery-3.6.0.min.js"></script>
<script src="./js/chat.js"></script>

</html>