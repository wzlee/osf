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
			console.log("success");
		})
		.fail(function() {
			console.log("error");
		})
		.always(function() {
			console.log("complete");
		});
		
	});

	$('#like').click(function() {
		if($(this).hasClass('empty'))
			$(this).removeClass().addClass('red heart icon');
		else 
			$(this).removeClass().addClass('empty red heart icon');
	});
})