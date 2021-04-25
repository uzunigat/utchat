<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Chat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Chats</title>
</head>
<body>

	<%@include file="./header.jsp"%>
	<%@include file="./modals/myChats_modal.jsp"%>

	<div class="container-fluid">

		<div class="h3 m-3 text-secondary float-left">My Chats</div>

		<button type="button" class="btn btn-success float-right m-3"
			data-toggle="modal" data-target="#exampleModal">Create Chat</button>

		<table id="myTable" class="table m-3">
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col">Title</th>
					<th scope="col">Description</th>
					<th scope="col">Debut</th>
					<th scope="col">Fin</th>
					<th scope="col">Edit</th>
					<th scope="col">Enter</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>

				<%
				Date today = new Date();

				Vector<Chat> myChats = Chat.searchAllMyChats((String) request.getSession().getAttribute("email"), 1);

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd*HH:mm");

				for (int i = 0; i < myChats.size(); i++) {

					out.print("<tr>");
					out.print("<td> " + myChats.elementAt(i).getId() + " </td>");
					out.print("<td> " + myChats.elementAt(i).getTitle() + " </td>");
					out.print("<td> " + myChats.elementAt(i).getDescription() + " </td>");
					out.print("<td>" + simpleDateFormat.format(myChats.elementAt(i).getStartDate()) + "</td>");
					out.print("<td>" + simpleDateFormat.format(myChats.elementAt(i).getEndDate()) + "</td>");
					out.print(
					"<td> <button class='btn btn-primary' type='button' data-toggle='modal' data-target='#exampleModal2'> Edit </button> </td>");

					if (myChats.elementAt(i).getEndDate().after(today) && myChats.elementAt(i).getStartDate().before(today)) {
						out.print("<td> <button class='btn btn-success' type='button'> Enter </button> </td>");

					}

					else {
						out.print("<td> <button class='btn btn-success' type='button' disabled> Enter </button> </td>");
					}

					out.print("</tr>");

				}
				%>


			</tbody>
		</table>

	</div>

	<input type="hidden" name="pONumb" value="${sessionScope.username}" />

</body>

<script src="./js/jquery-3.6.0.min.js"></script>
<script src="./js/myChats.js"></script>

</html>