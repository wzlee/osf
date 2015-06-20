$(document).ready(function(){

	var commentType = 0;	//0 single comment for post; 1 comment to reply others
	var commentParent = 0;

	$("a.reply").live('click', function(){
		if($("#comments").find("#replyarea").length != 0)
			$("#replyarea").remove();
		
		commentType = 1;
		var ref = $(this).attr('ref');		
		var $commentRef = $('#comment'+ref);
		var replyTo = $commentRef.find('.author:first()').text();
		var avatarRef = $commentRef.find('a.avatar img:first').attr('src');
		var commentContentRef = $commentRef.find('.commentContent p:first()').text();
		commentParent = $commentRef.attr('author');

		var $replyarea = $('<div id="replyarea">'+
								'<div class="comment">'+
								  '<a href="#" class="avatar">'+
								    '<img src='+ avatarRef +'>'+
								  '</a>'+
								  '<div class="text commentContent">'+
								    '<p>'+commentContentRef+'</p>'+
								  '</div>'+
								'</div>'+
								'<div>'+
								  '<form class="ui reply form" id="replyform">'+
								    '<div class="field">'+
								      '<textarea id="replycontent"></textarea>'+
								    '</div>'+
								    '<div class="ui primary button" id="replybtn" replyTo="'+replyTo+'">'+
								      '评论'+
								    '</div>'+
								  '</form>'+								
								'</div>'+
							  '</div>');
		$('#comments').prepend($replyarea);
		$('#replycontent').focus();

	});



	$('#replybtn').live('click', function() {
		var header;		

		if(commentType == 0) {
			header = '<a class="author">me</a>';
		}
		else if(commentType == 1) {
			var replyTo = $(this).attr('replyTo');
			header = '<a class="author">me</a> 回复 ' + '<a class="author">'+replyTo+'</a>';
		}			
		var commentContent = $('#replycontent').val();
		if(commentContent == null || commentContent.length == 0) {
			return;
		}
		var commentObjectType = $('meta[name=type]').attr('content');
		if(commentObjectType == 'post') {
			commentObjectType = 0;
		} else if(commentObjectType == 'photo') {
			commentObjectType = 1;
		} else if(commentObjectType == 'album') {
			commentObjectType = 2;
		} else {
			return;
		}

		var commentObjectID = $('meta[name=id]').attr('content');

		$.ajax({
			url: basePath + '/comment/create',
			type: 'POST',
			dataType: 'json',
			data: {comment_object_type: commentObjectType,
				   comment_object_id: commentObjectID,
				   comment_content: commentContent,
				   comment_parent: commentParent}
		})
		.success(function(data) {
			var status = data.status;
			var $comment = $('<div class="comment" id="4">'+
							    '<a class="avatar">'+
							      '<img src="'+data.avatar+'">'+
							    '</a>'+
							    '<div class="content">'+
							      header+
							      '<div class="metadata">'+
							        '<span class="date">5 days ago</span>'+
							      '</div>'+
							      '<div class="text commentContent">'+
							        '<p>'+commentContent+'</p>'+
							      '</div>'+
							    '</div>'+
							  '</div>');
			$('#commentList').prepend($comment);
			$('#replyarea').remove();
			
			var $replyarea = $('<div id="replyarea">'+
								'<div>'+
								  '<form class="ui reply form" id="replyform">'+
								    '<div class="field">'+
								      '<textarea id="replycontent"></textarea>'+
								    '</div>'+
								    '<div class="ui primary button" id="replybtn">'+
								      '评论'+
								    '</div>'+
								  '</form>'+								
								'</div>'+
							  '</div>');
			$('#comments').prepend($replyarea);
			$('#replycontent').focus();
			commentType = 0;
			commentParent =0;
		})
		.fail(function() {
			console.log("error");
		})
		.always(function() {
			console.log("complete");
		});
		
		

	});



});