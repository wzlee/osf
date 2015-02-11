$(document).ready(function(){
	$('#loginbtn').live('click', function() {
		var email = $('#email').val();
		var password = $('#password').val();

		$.ajax({
			url: basePath+'/account/login',
			type: 'POST',
			dataType: 'json',
			data: {email: email,
				   password: password},
		})
		.success(function(data) {
			console.log("success");
			alert(data);
		})
		.fail(function() {
			console.log("error");
		})
		.always(function() {
			console.log("complete");
		});
		
	});




})