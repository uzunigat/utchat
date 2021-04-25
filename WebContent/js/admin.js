$(document).ready(function() {

	var lastEmail;

	// code to read selected table row cell data (values).
	$("#myTable").on('click', '.btn-success', function() {

		// get the current row
		let currentRow = $(this).closest("tr");

		let email = currentRow.find("td:eq(0)").text().replace(/[^a-zA-Z0-9.@]/g, ''); // get current row 1st TD value
		lastEmail = email;
		let nickname = currentRow.find("td:eq(1)").text(); // get current row 2nd TD
		let role = currentRow.find("td:eq(2)").text(); // get current row 3rd TD

		console.log("Email: " + email);
		console.log("Nickname: " + nickname);
		console.log("Role: " + role);

		$("#email").val(email);
		$("#nickname").val(nickname);

		(role.localeCompare("Admin") == 0) ? $("#role").val("Admin") : $("#role").val("Other");


	});

	$("#myTable").on('click', '.btn-danger', function() {

		// get the current row
		let currentRow = $(this).closest("tr");

		let email = currentRow.find("td:eq(0)").text().replace(/[^a-zA-Z0-9.@]/g, ''); // get current row 1st TD value
		let nickname = currentRow.find("td:eq(1)").text(); // get current row 2nd TD
		let role = currentRow.find("td:eq(2)").text(); // get current row 3rd TD

		console.log("Email: " + email);
		console.log("Nickname: " + nickname);
		console.log("Role: " + role);

		r = confirm("Are you sure to delete: " + email + "?");

		if (r) {

			let form = document.createElement("form");
			let element1 = document.createElement("input");

			form.method = "POST";
			form.action = "/UTChat/DeleteUser";

			element1.value = email;
			element1.name = "email";
			element1.setAttribute("type", "hidden");

			form.appendChild(element1);

			document.body.appendChild(form);

			form.submit();
		}

	});

	$("#form").submit(function(eventObj) {
		$("<input />").attr("type", "hidden")
			.attr("name", "lastEmail")
			.attr("value", lastEmail)
			.appendTo("#form");
		return true;
	});
});