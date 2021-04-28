$(document).ready(function(){
	
	$("#registerButton").prop( "disabled", true );
	
	$("input").change(function(){
	
	console.log(checkPassword() && hasValues());
	
		if(checkPassword() && hasValues()){
			
			$("#registerButton").prop( "disabled", false );
		
		}
	
	})
	
	function checkPassword(){
	
		let password = $("#password").val();
		let confirmPassword = $("#confirmPassword").val();
		
		if( password.localeCompare(confirmPassword) == 0) {
		
			return true;
		
		}
		
		return false;
	
	}
	
	function hasValues() {
	
		if($("#confirmPassword").val() && $("#password").val() && $("#username").val() && $("#email").val()) {
		
			return true;
		
		}
		
		return false;
	
	}

});