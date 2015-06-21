$(document).ready(function(){
	$('.heart.icon').click(function(){
		var user_id = $(this).attr('user_id');
		var object_type = $(this).attr('object_type');
		var object_id = $(this).attr('object_id');
		var url = basePath;

		//已经喜欢 点击取消喜欢
		if($(this).hasClass('red')){
			url += '/undo'
			$(this).removeClass('red');
		} 
		//还未喜欢 点击喜欢
		else{
			url += '/do'
			$(this).addClass('red');
		}
		$.ajax({
			url: url,
			type: 'POST',
			dataType: 'json',
			data:{
				user_id: user_id,
				object_type: object_type,
				object_id: object_id
			}
		})
		.success(function(data){
			alert(data.status);
		})	
			
	});

})