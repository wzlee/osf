$(document).ready(function(){
    $('.setting.info .field').live('click', function(){
    	$(this).removeClass('error');
    });
    
    $('#user_name').live('click', function(){
    	$('#user_name_tip').text('');
    });
    
    $('#info_cancle').live('click', function(){
    	$(this).hide();
    	$('#user_name').parent('.field:first').addClass('disabled');
    	$('#user_desc').parent('.field:first').addClass('disabled');
    	$('#info_save').removeClass('info_save blue').addClass('modify').text('修改');
    });
    
    $('#info_save').live('click', function(){
    	if($(this).hasClass('modify')) {
    		$('.setting.info .disabled.field').removeClass('disabled');
    		$(this).text('保存');
    		$(this).removeClass('modify').addClass('info_save blue');
    		$('#info_cancle').css('display', 'inline');
    		return false;
    	}
        var current_url = window.location.href;
        var user_name = $('#user_name').val();
        var user_desc = $('#user_desc').val();
        if(user_name == null || user_name == '' || user_name.length == 0){
        	$('#user_name').parent('.field:first').addClass('error');
        	$('#user_name_tip').text('请输入用户名');
        	return false;
        }
        
        $.ajax({
          url: basePath + '/account/setting/info',
          type: 'POST',
          dataType: 'json',
          data:{
        	  user_name: user_name,
        	  user_desc:user_desc
          }
        })
        .success(function(data){
          if(data.status == ERROR_USERNAME_EXIST){
        	  $('#user_name').parent('.field:first').addClass('error');
        	  $('#user_name_tip').text('用户名已存在');
          } else if(data.status == ERROR_USERNAME_EMPTY ){
          	  $('#user_name').parent('.field:first').addClass('error');
        	  $('#user_name_tip').text('请输入用户名');
          } else {
        	  self.location = current_url;
          }
        })       	
    });
    
    $('#change_pwd').live('click', function(){
    	$(this).parent().hide();
    	$('.reset_pwd_area').show();
    	$('.email_check_area').hide();
    	
    });
    $('#cancle_save_pwd').live('click', function(){
    	$('.reset_pwd_area').hide();
    	$('.reset_pwd_area').prev().show();
    });
    
    $('#forget_pwd').live('click', function(){
    	$('.email_check_area').show();
    });
    
    $('#send_check_email').live('click', function(){
    	$(this).addClass("loading");
    	$.get(basePath + '/account/send_resetpwd_email', function(data){
    		if(data.status == SUCCESS_EMAIL_RESETPWD_SEND){
    			$('#send_check_email').removeClass("loading").addClass("disabled").text("已发送");
    		}
    	})
    	
    });
})