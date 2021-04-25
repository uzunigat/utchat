<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="config.MyConnection"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Settings</title>
</head>
<body>

	<%@include file="./header.jsp"%>
	<%@include file="./modals/admin_modal.jsp"%>

	<div class='container-fluid'>
		<table id="myTable" class="table table-striped">
			<thead>
				<tr>
					<th scope="col">Email</th>
					<th scope="col">Nickname</th>
					<th scope="col">Role</th>
					<th scope="col">Edit</th>
					<th scope="col">Delete</th>
				</tr>
			</thead>


			<%
			Connection conn = MyConnection.getInstance();

			Statement stmt = conn.createStatement();

			ResultSet res = stmt.executeQuery("SELECT * FROM User");

			while (res.next()) {
			%>

			<tbody>
				<tr>
					<td scope="row"><%=res.getString("email")%>
					</th>
					<td><%=res.getString("nickname")%></td>
					<td><%=res.getString("role")%></td>
					<td><button class="btn btn-success" type='button' data-toggle="modal" data-target="#exampleModal">
							Edit</button></td>
					<td><button class="btn btn-danger" type="button" >X</button></td>
				</tr>
			</tbody>


			<%
			}
			%>

		</table>

	</div>

</body>

<script src="./js/jquery-3.6.0.min.js"></script>
<script src="./js/admin.js"></script>
</html>