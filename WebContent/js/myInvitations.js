$(document).ready(function() {
	
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