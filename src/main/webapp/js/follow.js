$(document).ready(function(){
	$('.follow').live('click', function(event) {
		var following_user_id = $(this).attr('following');
		var url = '';
		var that = $(this);

		if(!$(this).hasClass('active')) {
			url = basePath + '/follow/'+following_user_id;
		} else {
			url = basePath + '/follow/undo/'+following_user_id;
		}
		$.ajax({
			url: url,
			type: 'POST',
			dataType: 'json',
			data: {param1: 'value1'},
		})
		.done(function(data) {
			console.log("success");
			if('111000' == data.status) {
				$(that).text('已关注');
				$(that).addClass('active');
			} else if('111001' == data.status) {
				$(that).text('+关注');
				$(that).removeClass('active');
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