$(document).ready(function(){
	$('#imgbox img').click(function() {
		var src = $(this).attr('src');
		$('#mainimg').attr('src', src);
	});

	$('#uploader_input').change(function(event) {
	   $.ajaxFileUpload({
	                		url: basePath+'/album/upload/photo', 
	                		secureuri:false,
	                		fileElementId:'uploader_input',
	                		dataType: 'json',
	                		success: function (data, status){
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
	        		) 
        return false;	
	});
})