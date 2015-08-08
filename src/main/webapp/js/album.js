$(document).ready(function(){
	var photos = [];


	$('#uploader_input').live('change', function(event) {
	   $.ajaxFileUpload({
	                		url: basePath+'/album/upload/photo', 
	                		secureuri:false,
	                		fileElementId:'uploader_input',
	                		success: function (data, status){
	                				data = jQuery(data).find('pre:first').text();
	                				data = jQuery.parseJSON(data);
	                				var $imgCard = $('<div class="card" id="card'+data.id+'">'+
				    									'<a class="image" href="#">'+
				      										'<img src="'+img_base_url+data.key+album_thumbnail+'">'+
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
			data: JSON.stringify({album_desc: album_desc, photos: photos, tags: tags})
		})
		.done(function(data) {
			var status = data.status;
			var author = data.album.user_id;
			if(SUCCESS_ALBUM_CREATE == status || SUCCESS_ALBUM_UPDATE == status) {
				self.location = basePath + "/user/" + author;
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