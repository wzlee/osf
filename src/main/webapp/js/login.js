$(document).ready(function(){
	$('#loginbtn').live('click', function() {
		var email = $('#email').val();
		var password = $('#password').val();
		var error_area=$('#error');
		var err_msg="";

		$(error_area).find('ul:first').text('');
		$(error_area).addClass('hidden');


		$.ajax({
			url: basePath+'/account/login',
			type: 'POST',
			dataType: 'json',
			data: {email: email,
				   password: password},
		})
		.success(function(data) {
			if(data.status==SUCCESS_ACCOUNT_LOGIN){
				self.location = basePath + '/';
				
			} else{

				if(data.status == ERROR_EMAIL_NOT_REG){
					err_msg="邮箱未注册";
				} else if(data.status == ERROR_EMAIL_EMPTY){
					err_msg="请输入邮箱";
				} else if(data.status == ERROR_EMAIL_FORMAT){
					err_msg="请输入正确的邮箱地址";
				} else if(data.status == ERROR_PWD_EMPTY){
					err_msg="请输入密码";
				} else if(data.status == ERROR_PWD_DIFF){
					err_msg = "密码错误"
				} else{
					err_msg="邮箱或密码错误";
				}	
				var msg=$('<li>'+err_msg+'</li>')
				$(error_area).find('ul:first').prepend($(msg));
				$(error_area).removeClass('hidden');
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