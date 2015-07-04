$(document).ready(function(){
	$('.explore .tags .tagbox a').live('click', function(){
		var tag_id = $(this).attr('id');
		var that = $(this);
		$.ajax({
			url: basePath + '/tag/'+tag_id+'/interest',
			type: 'GET',
			dataType: 'json'
		})
		.success(function(data){
			if(data.status == SUCCESS_INTEREST){
				$(that).text('已关注')
				$(that).parent('.hidden').css('opacity', '0.7');
			}

		})

	});
})