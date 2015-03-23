# URL Design #
-------------

## Account ##



##  登录
  
登录页面  
	
	/account/login

用户登录  

	POST /account/login
	{
		email: email,
		password: password
	}
		
## 注册

注册页面  

	/account/register 

用户注册 
  
	POST /account/register
	{
		email: email,
		password: password,
		cfmPwd: cfmPwd
	} 

  		
##  注册激活
	
	key是系统为新注册用户生成的激活码，用户由此链接激活

	/account/activation/{key}



## 退出 
	
用户退出登录	

	/account/logout  
		

## 忘记密码
## 重置密码







## User

## 用户详细描述页面  

	/user/{id}/info



## Post

## 获取日志 

获取日志的详细信息及内容  
	
	/post/{id}

## 日志编辑页面(新增日志)
	
	/post/create

## 新增一篇日志

	POST /post/create
	{
		content: content,
		title: title,
		tags: tags.join(' '),
		post_status: post_status,
		comment_status: comment_status
	}		

参数说明

| 字段           | 说明          | 备注                                 |
| -------------- |:-------------:| :-----                               |
| tags			 | 日志标签 	 | 多个标签用空格隔开                   |
| post_status    | 日志状态	     | 0：公开；1：私密；2：保存；3：编辑   |
| comment_status | 评论设置      | 0：允许评论；1：不允许评论           |


## 编辑日志
	
	/post/{id}/edit

## 更新日志(保存修改)

	POST /post/{id}/update
	{
		content: content,
		title: title,
		tags: tags.join(' '),
		post_status: post_status,
		comment_status: comment_status
	}

## 喜欢一篇日志

	/post/{id}/like

## 撤销日志喜欢

	/post/{id}/undolike

   
## 删除一篇日志
	
	/post/{id}/delete

## 日志相关错误说明


| 错误码         | 错误信息      | 说明                                 |
| -------------- |:-------------:| :-----                               |
| | | |

	



## Album

## 相册列表

	/album/user/{id}


## 相册图片列表

	/album/{id}/photos


## 图片上传页面(指定相册)

	/album/{album_id}/upload

## 图片上传页面(未指定相册)

	/album/upload

## 上传图片(指定相册)

	POST /album/{album_id}/upload/photo
	{
		album_id: album_id,
		uploader_input: img
	}

## 上传图片(未指定相册)
	
	POST /album/upload/photo
	{
		uploader_input: img
	}

参数说明

| 字段         | 说明      |
| -------------- |:-------------:|
| album_id | 相册ID |
| uploader_input | 用户选择上传的图片 |


## 创建相册

	POST　/ablum/create





## Comment

## 创建评论

	POST /comment/create
	{
		comment_object_type: 0,
		comment_object_id: id,
		comment_content: content,
		comment_parent: parent
	}

参数说明

| 字段         | 说明      |
| -------------- |:-------------:|
| comment_object_type | 评论的对象类型 0：日志；1：图片；2：相册 |
| comment_object_id | 评论的对象ID |
| comment_content | 评论内容 |
| comment_parent | 回复的评论ID | 


## 删除评论

	/comment/{id}/delete

## 评论列表
日志/图片/相册的评论

	/commnet/{object_type}/{object_id}

