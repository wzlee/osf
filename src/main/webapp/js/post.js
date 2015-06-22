$(document).ready(function(){
	$('#send').click(function() {
		var title = $('#title').val();
		var content = $('#content').val();
		//var tags = $('#tags').val();
		var post_status = $('input[name="post_status"][checked]').val();
		var comment_status = $('input[name="comment_status"][checked]').val();
		$.ajax({
			url: basePath + '/post/create',
			type: 'POST',
			dataType: 'json',
			data: {
			       content: content,
			       title: title,
			       tags: tags.join(' '),
			       post_status: post_status,
			       comment_status: comment_status}
		})
		.success(function(data) {
			var status = data.status;
			var author = data.post.post_author;
			if(SUCCESS_POST_CREATE == status) {
				self.location = basePath + "/user/"+author;
			}
		})
		.fail(function() {
			console.log("error");
		})
		.always(function() {
			console.log("complete");
		});
		
	});

	$('#like').click(function() {
		var object_type = $('meta[name=type]').attr('content');
		var object_id = $('meta[name=id]').attr('content');
		var url = basePath;

		if(object_type == 'post') {
			object_type = 0;
		} else if(object_type == 'photo') {
			object_type = 1;
		} else if(object_type == 'album') {
			object_type = 2;
		} else {
			return;
		}		

		if($(this).hasClass('empty')){
			url += '/do';
			$(this).removeClass().addClass('red heart icon');
		}
		else {
			url += '/undo';
			$(this).removeClass().addClass('empty red heart icon');
		}
		$.ajax({
			url: url,
			type: 'POST',
			dataType: 'json',
			data:{
				object_type: object_type,
				object_id: object_id
			}
		})
		.success(function(data){
			
		})	
	});
})