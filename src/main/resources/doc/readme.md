## OSF

正如名字一样，OSF是一个开放、自由、分享的内容社区类网站原型。你会发现目前绝大多数的社交类网站、内容分享类、社区类、兴趣垂直类网站都有共同的特性，包括多用户，内容的发布、评论、喜欢，消息传递，Feed流，标签分类等等。打造OSF的初衷就是为了实现这些通用的模块，构建一个集合以上特点的内容社区类网站的原型。你可以用OSF构建一个单纯的社交网站，也可以加入标签成为一个兴趣社区，甚至两者皆可，这一切OSF都已为你提供。

![welcome](http://7xkkim.com1.z0.glb.clouddn.com/welcome.png)

## 主要功能 

* 邮箱注册激活验证 
* 多用户、用户间互相关注
* 标签系统
- Feed流  
  * 关注用户Feed
  * 关注标签Feed
* 说说、日志、相册
* 评论、回复
* 通知系统  
* 个人信息设置、账户安全
* 上传图片云存储

##  前端设计

Sketch文件已经放在了[osf_sketch](sketch) 


![explore](http://7xkkim.com1.z0.glb.clouddn.com/explore.png)

![feed](http://7xkkim.com1.z0.glb.clouddn.com/feed.png)

Sketch文件在[osf_sketch](sketch)

## 技术选型 

OSF选择Spring MVC作为后端基础框架，实现RESTFull url，为实现尽可能的前后端分离，除首屏数据渲染外均通过Ajax+json形式更新前端，url设计与数据交互规范见 [api](http)

MySQL作为OSF的关系型数据库，除Feed之外的所有数据均由其存储

Redis在OSF中的使用，主要缓存用户信息、统计计数，同时存储用户的Feed信息流，Feed信息读写设计可见[feed](feed)  

## 配置



## 后续版本计划 

* 下个版本将率先实现OAuth登陆
* 搜索功能  
* 发送链接 

