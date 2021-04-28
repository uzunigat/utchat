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

		<div class="card card-bordered">
			<div class="card-header">
				<h4 class="card-title">
					<strong>Chat</strong>
				</h4>
			</div>

			<div class="ps-container ps-theme-default ps-active-y"
				id="chat-content"
				style="overflow-y: scroll !important; height: 400px !important;">

				<div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;">
					<div class="ps-scrollbar-x" tabindex="0"
						style="left: 0px; width: 0px;"></div>
				</div>
				<div class="ps-scrollbar-y-rail"
					style="top: 0px; height: 0px; right: 2px;">
					<div class="ps-scrollbar-y" tabindex="0"
						style="top: 0px; height: 2px;"></div>
				</div>


			</div>

			<div class="publisher bt-1 border-light">

				<input id="txtMessage" type="text" class='publisher-input' /> <input
					id="username" type="hidden"
					value=<%=request.getSession().getAttribute("username")%>> <input
					id="endDate" type="hidden"
					value=<%=request.getParameter("endDate")%>> <input
					id="chatId" type="hidden" value=<%=request.getParameter("chatId")%>>
				<button id="btnSend" class="btn mx-2">
					<img src="./images/paper-plane.png" width="30" height="30">
				</button>

			</div>

		</div>

	</div>




</body>

<script src="./js/jquery-3.6.0.min.js"></script>
<script src="./js/chat.js"></script>

</html>