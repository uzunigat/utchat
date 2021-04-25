$(document).ready(function() {

	let users = [];
	let currentDate = new Date();

	$("#startChat").click(function() {
		window.location.href = "./chat.jsp";
	});

	$("#members").change(function() {

		if (this.value.localeCompare("")) {
			let currentValue = $("#members").val();

			if (users.indexOf(currentValue) == -1) {

				users.push(currentValue);

				let tag = "<div class = 'form-group'> "
					+ "<input type='hidden' name='" + currentValue + "' id = '" + currentValue + "' value='" + currentValue + "'/>"
					+ " <button type='button' class='btn btn-danger'> X </button>"
					+ "  " + currentValue
					+ "<br/>"
					+ "</div>";

				$("#selectedOptions").append(tag)

			}
		}

	})

	$("#myTable").on('click', '.btn-primary', function() {

		let currentRow = $(this).closest("tr");

		let id = currentRow.find("td:eq(0)").text();
		let title = currentRow.find("td:eq(1)").text().replace(" ", "");
		let description = currentRow.find("td:eq(2)").text().replace(" ", "");
		let debut = currentRow.find("td:eq(3)").text().replace("*", "T");
		let fin = currentRow.find("td:eq(4)").text().replace("*", "T");

		console.log("id: " + id);
		console.log("Title: " + title);
		console.log("Description: " + description);
		console.log("Debut: " + debut);
		console.log("Fin: " + fin);

		$("#title2").val(title);
		$("#desc2").val(description);
		$("#startDate2").val("2020-04-22T20:12");
		$("#endDate2").val(fin);


	});

	$("#myTable").on('click', '.btn-success', function() {

		let currentRow = $(this).closest("tr");

		let id = currentRow.find("td:eq(0)").text();
		let fin = currentRow.find("td:eq(4)").text().replace("*", "T");

		let form = document.createElement("form");
		let element1 = document.createElement("input");
		let element2 = document.createElement("input");

		form.method = "POST";
		form.action = "chat.jsp";

		element1.value = id;
		element1.name = "chatId";
		element1.setAttribute("type", "hidden");

		element2.value = fin;
		element2.name = "endDate";
		element2.setAttribute("type", "hidden");

		form.appendChild(element1);
		form.appendChild(element2);

		document.body.appendChild(form);

		form.submit();


	});



});