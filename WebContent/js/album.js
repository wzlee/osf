$(document).ready(function(){
	var photos = [];

	$('#imgbox img').click(function() {
		var src = $(this).attr('src');
		$('#mainimg').attr('src', src);
	});

	$('#uploader_input').live('change', function(event) {
	   $.ajaxFileUpload({
	                		url: basePath+'/album/upload/photo', 
	                		secureuri:false,
	                		fileElementId:'uploader_input',
	                		success: function (data, status){
	                				var imgUrl = 'http://osfimgs.oss-cn-hangzhou.aliyuncs.com/';
	                				data = jQuery(data).find('pre:first').text();
	                				data = jQuery.parseJSON(data);
	                				var $imgCard = $('<div class="card" id="card'+data.photo.id+'">'+
				    									'<a class="image" href="#">'+
				      										'<img src="'+imgUrl+data.photo.key+'">'+
				    									'</a>'+
				    									'<div class="content">'+
				    										'<textarea rows="" cols="" placeholder="添加描述..."></textarea>'+
				    									'</div>'+
				    									'<div class="extra meta">'+
															'<a href="#"><i class="delete icon"></i>删除</a>'+
				    									'</div>'+
				  									'</div>');
	                				$('#uploadedphotos').append($imgCard);

	                    			if(typeof(data.error) != 'undefined')
	                   				{
				                        if(data.error != '')
				                        {
				                            alert(data.error);
				                        }else
				                        {
				                            alert(data.msg);
				                        }
	                    			}
	                		},
	                		error: function (data, status, e){
	                    			alert(e);
	                		}
	            		}
	        		) ;
	});

	$('#saveAlbumBtn').click(function(event) {
		$('#uploadedphotos .card').each(function(index, el) {
			var photo_id = $(this).attr('id').substring(4);
			var photo_desc = $(this).find('textarea:first()').val();
			photos.push({"id":photo_id, "desc":photo_desc});
		});
		var album_desc = $('#album_desc').val();

		$.ajax({
			url: basePath+'/album/create',
			type: 'POST',
			dataType: 'json',
			contentType:'application/json;charset=UTF-8',
			data: JSON.stringify({album_desc: album_desc, photos: photos})
		})
		.done(function() {
			console.log("success");
		})
		.fail(function() {
			console.log("error");
		})
		.always(function() {
			console.log("complete");
		});
		
	});

})