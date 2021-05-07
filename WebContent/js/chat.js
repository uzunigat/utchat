$(document).ready(function() {

	let endDate = $("#endDate").val();
	let chatId = $("#chatId").val();
	
	let height = $(window).height();

    $('#container').height(height);

	console.log(chatId);

	let username = $('#username');
	let ws = new WebSocket("ws://"+location.hostname+":8080/UTChat/echo/" + chatId + "/" + username.val());


	ws.addEventListener("open", function(evt) {
		console.log("Connection established");

	});

	ws.addEventListener("message", function(evt) {

		let message = evt.data;
		let message_tag;
		
		let sender = message.split(":")[0].replace(" ", "");
		let content = message.split(":")[1].replace(" ", "");
		
		let currentDate = new Date();
		
		if(sender != "Server"){

			if ($("#username").val().replace(" ", "") == sender) 
	
				message_tag = `<div class="media media-chat media-chat-reverse"><div class="media-body"> <p> ${content} </p> <p class="meta"> <time datetime="2018">${currentDate.getHours()}:${currentDate.getMinutes()}</time> </p> </div> </div>`
	
			else 
				
				message_tag = `<div class="media media-chat" ><img class="avatar" src="https://img.icons8.com/color/36/000000/administrator-male.png"> <div class="media-body"> <p>${content}</p> <p class="meta"> <time datetime="2018">${currentDate.getHours()}:${currentDate.getMinutes()}</time> </p> </div> </div>`;
			
		}
		
		else {
		
			message_tag = `<div class="media media-meta-day">${content}</div>`;
		
		}
		
		$("#chat-content").append(message_tag);

	});

	ws.addEventListener("close", function(evt) {
		console.log("Connection closed");
	});


	let btnSend = document.getElementById("btnSend");
	btnSend.addEventListener("click", function(clickEvent) {
		ws.send(txtMessage.value);
		$("#txtMessage").val("");
		txtMessage.focus();
	});

	let btnClose = document.getElementById("btnClose");

	btnClose.addEventListener("click", function(clickEvent) {
		ws.close();
	});


});