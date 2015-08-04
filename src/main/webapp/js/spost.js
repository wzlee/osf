$(document).ready(function(){
	$('#spost_send').live('click',function() {
		var content = $('#spost_content').val();

		$.ajax({
			url: basePath + '/spost/create',
			type: 'POST',
			dataType: 'json',
			data: {
			       content: content
			   }
		})
		.success(function(data) {
			var status = data.status;
			var author = data.spost.post_author;
			if(SUCCESS_POST_CREATE == status) {
				var short_post=$('.empty.event:first').clone();
				$(short_post).removeClass('empty');
				$(short_post).find('img:first').attr('src', img_base_url + data.avatar);
				$(short_post).find('.summary > a').attr('href', basePath+'/user/'+data.spost.post_author);
				$(short_post).find('.summary > a').text(data.author_name);
				$(short_post).find('.extra').text(data.spost.post_content);
				$(short_post).find('.like > i').attr('object_id', data.spost.id);

				$(short_post).css('display', 'block');
				$('.feed:first').prepend($(short_post));
			}
		})
		.fail(function() {
			console.log("error");
		})
		.always(function() {
			console.log("complete");
		});
		
	});


	$(".actions .sport_link").click(function(){
		$(this).parent('.actions:first').css('display', 'none');
		$('.short_post').css('display', 'block');
	});
	
	$('#sport_cancel').click(function(){
		$('.short_post').css('display', 'none');
		$('#action_bar .actions:first').css('display', 'block');
	});
})