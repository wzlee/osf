$(document).ready(function(){
	$('#registerBtn').live('click', function() {
		var email = $('#email').val();
		var password = $('#password').val();
		var cfmPwd = $('#cfmPwd').val();

		$.ajax({
			url: basePath+'/account/register',
			type: 'POST',
			dataType: 'json',
			data: {email: email,
				   password: password,
				   cfmPwd: cfmPwd},
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