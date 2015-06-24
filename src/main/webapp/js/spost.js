$(document).ready(function(){
	$('#spost_send').click(function() {
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
})