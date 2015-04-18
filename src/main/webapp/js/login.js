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
			if(JSON.stringify(data).indexOf('1') == 0){
				self.location = basePath + '/';
				console.log("login success");
			}
			
		})
		.fail(function() {
			console.log("error");
		})
		.always(function() {
			console.log("complete");
		});
		
	});




})