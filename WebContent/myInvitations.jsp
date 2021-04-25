<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Chat"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Invitations</title>
</head>
<body>
	<%@include file="./header.jsp"%>

	<div class="container-fluid">

		<div class="h3 m-3 text-secondary float-left">My Invitations</div>

		<table id='myTable' class="table m-3">
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col">Title</th>
					<th scope="col">Description</th>
					<th scope="col">Debut</th>
					<th scope="col">Fin</th>
					<th scope="col">Enter</th>
				</tr>
			</thead>
			<tbody>
			<tbody>

				<%
				Date today = new Date();

				Vector<Chat> myChats = Chat.searchAllMyChats((String) request.getSession().getAttribute("email"), 0);

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd - HH:mm:ss");

				for (int i = 0; i < myChats.size(); i++) {

					out.print("<tr>");
					out.print("<td> " + myChats.elementAt(i).getId() + " </td>");
					out.print("<td> " + myChats.elementAt(i).getTitle() + " </td>");
					out.print("<td> " + myChats.elementAt(i).getDescription() + " </td>");
					out.print("<td> " + simpleDateFormat.format(myChats.elementAt(i).getStartDate()) + " </td>");
					out.print("<td> " + simpleDateFormat.format(myChats.elementAt(i).getEndDate()) + " </td>");

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


</body>

<script src="./js/jquery-3.6.0.min.js"></script>
<script src="./js/myInvitations.js"></script>
</html>