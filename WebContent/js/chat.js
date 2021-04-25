$(document).ready(function() {
	
	let endDate = $("#endDate").val();
	let chatId = $("#chatId").val();
	
	console.log(chatId);
	
	let username = $('#username');
	let ws = new WebSocket("ws://127.0.0.1:8080/UTChat/echo/" + chatId + "/" + username.val()); 
	
	
	ws.addEventListener( "open", function( evt ) {
        console.log( "Connection established" );
        
    });

    ws.addEventListener( "message", function( evt ) {
        let message = evt.data;
        console.log( "Receive new message: " + message );
        $("#history").val($("#history").val() + message + '\n');
    });
    
    ws.addEventListener( "close", function( evt ) {
        console.log( "Connection closed" );
    });
    
    
    let btnSend = document.getElementById( "btnSend" );
    btnSend.addEventListener( "click", function( clickEvent ) {
        ws.send( txtMessage.value );
        $("#txtMessage").val("");
        txtMessage.focus();
    });
 
    let btnClose = document.getElementById( "btnClose" );
    
    btnClose.addEventListener( "click", function( clickEvent ) {
        ws.close();
    });
	

});