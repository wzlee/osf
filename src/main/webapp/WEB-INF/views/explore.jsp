<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>探索OSF</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap2.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/navbar.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/semantic.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
  <script src="<%=request.getContextPath() %>/js/jquery.js"></script>
  <script src="<%=request.getContextPath() %>/js/jquery.row-grid.js"></script>
  <script src="<%=request.getContextPath() %>/js/basic.js"></script>
  <script src="<%=request.getContextPath() %>/js/code.js"></script>
  
  <script type="text/javascript">
	$(function(){		
		var options = {minMargin: 5, maxMargin: 10, itemSelector: ".box", firstItemClass: "first-item"};
		$(".gallery").rowGrid(options);	
		
		
		$(".topbar .header>div").click(function(){
			var index=$(this).index();
			var explore=$('.gallery:first');
			var tags=$('.tags:first');
			var users = $('.users:first');
			var active_tip=$('.topbar .active');
			if(index == 0){	
				$(explore).fadeIn(300);
				$(tags).fadeOut(200);
				$(users).fadeOut(200);
				$(active_tip).css('left', '19.5%');
			} else if(index == 1 ){							
				$(tags).fadeIn(300);
				$(explore).fadeOut(200);
				$(users).fadeOut(200);
				$(active_tip).css('left', '44%');
			} else{
				$(explore).fadeOut(300);
				$(tags).fadeOut(200);
				$(users).fadeIn(200);
				$(active_tip).css('left', '69%');
			}
		});
		
	});
  </script>
</head>
<body>
 	<%@ include file="topbar.jsp" %>
	<div class="explore">
		<div class="topbar">
			<div class="container">
				<div class="header">
					<div>探索</div>
					<div>标签</div>
					<div>用户</div>
				</div>
			</div>
			<div class="active"></div>
		</div>

		<div class="main">
			<div class="gallery" >
				<div class="box first-item"><img src="<%=request.getContextPath() %>/img/gallery/1.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/2.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/5.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/4.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/5.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/6.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/7.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/8.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/9.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/10.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/6.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/5.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/6.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/1.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/4.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/5.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/6.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/7.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/8.jpg" alt="" /></div>
			</div>	
			<div class="tags" style="display: none">
				<div class="container">
					<div class="row">
						<div>
							<div class="tagbox">
								<div>
								<img class="visible" src="<%=request.getContextPath() %>/img/gallery/tag2.png" alt="" />
								<span class="desc">#音乐</span>
								</div>
								<div class="hidden">
									<a href="">加关注</a>
								</div>
							</div>
							<div class="tagbox">
								<div>
									<img src="<%=request.getContextPath() %>/img/gallery/tag3.png" alt="" />
									<span class="desc">#生活</span>
								</div>
								<div class="hidden">
									<a href="">加关注</a>
								</div>
							</div>
							<div class="tagbox">
								<div>
									<img src="<%=request.getContextPath() %>/img/gallery/tag4.png" alt="" />
									<span class="desc">#运动</span>
								</div>
								<div class="hidden">
									<a href="">加关注</a>
								</div>
							</div>
							<div class="tagbox">
								<img src="<%=request.getContextPath() %>/img/gallery/tag1.png" alt="" />
								<div class="hidden">
									<a href="">加关注</a>
								</div>
							</div>

							<div class="tagbox">
								<img src="<%=request.getContextPath() %>/img/gallery/tag2.png" alt="" />
								<div class="hidden">
									<a href="">加关注</a>
								</div>
							</div>
							<div class="tagbox">
								<img src="<%=request.getContextPath() %>/img/gallery/tag1.png" alt="" />
								<div class="hidden">
									<a href="">加关注</a>
								</div>
							</div>
							<div class="tagbox">
								<img src="<%=request.getContextPath() %>/img/gallery/tag2.png" alt="" />
								<div class="hidden">
									<a href="">加关注</a>
								</div>
							</div>
							<div class="tagbox">
								<img src="<%=request.getContextPath() %>/img/gallery/tag3.png" alt="" />
								<div class="hidden">
									<a href="">加关注</a>
								</div>
							</div>
							<div class="tagbox">
								<img src="<%=request.getContextPath() %>/img/gallery/tag4.png" alt="" />
								<div class="hidden">
									<a href="">加关注</a>
								</div>
							</div>
							<div class="tagbox">
								<img src="<%=request.getContextPath() %>/img/gallery/tag2.png" alt="" />
								<div class="hidden">
									<a href="">加关注</a>
								</div>
							</div>
							<div class="tagbox">
								<img src="<%=request.getContextPath() %>/img/gallery/tag1.png" alt="" />
							</div>
							<div class="tagbox">
								<img src="<%=request.getContextPath() %>/img/gallery/tag4.png" alt="" />
							</div>
							<div class="tagbox">
								<img src="<%=request.getContextPath() %>/img/gallery/tag2.png" alt="" />
							</div>
							<div class="tagbox">
								<img src="<%=request.getContextPath() %>/img/gallery/tag3.png" alt="" />
							</div>
							<div class="tagbox">
								<img src="<%=request.getContextPath() %>/img/gallery/tag4.png" alt="" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="users" style="display: none">
				<div class="container">
					<div class="row">
						<div class="userbox">
							<div class="header">
								<img class="avatar" src="<%=request.getContextPath() %>/img/gallery/tag2.png" alt="" />
								<div class="desc">两排杨树</div>
								<div class="ui tiny yellow button follow">+关注</div>
							</div>
							<div class="content">
								<div class="">
									<img src="<%=request.getContextPath() %>/img/gallery/tag3.png" alt="" />
								</div>
								<div class="">
									<img src="<%=request.getContextPath() %>/img/gallery/tag1.png" alt="" />
								</div>
								<div class="">
									<img src="<%=request.getContextPath() %>/img/gallery/tag2.png" alt="" />
								</div>
								<div class="">
									<img src="<%=request.getContextPath() %>/img/gallery/tag2.png" alt="" />
								</div>
							</div>
						
						</div>
					</div>
					<!-- end a row -->
					<div class="row">
						<div class="userbox">
							<div class="header">
								<img class="avatar" src="<%=request.getContextPath() %>/img/gallery/tag2.png" alt="" />
								<div class="desc">两排杨树</div>
								<div class="ui tiny  button follow">已关注</div>
							</div>
							<div class="content">
								<div class="">
									<img src="<%=request.getContextPath() %>/img/gallery/tag3.png" alt="" />
								</div>
								<div class="">
									<img src="<%=request.getContextPath() %>/img/gallery/tag1.png" alt="" />
								</div>
								<div class="">
									<img src="<%=request.getContextPath() %>/img/gallery/tag2.png" alt="" />
								</div>
								<div class="">
									<img src="<%=request.getContextPath() %>/img/gallery/tag2.png" alt="" />
								</div>
							</div>
						
						</div>
					</div>
					<div class="row">
						<div class="userbox">
							<div class="header">
								<img class="avatar" src="<%=request.getContextPath() %>/img/gallery/tag2.png" alt="" />
								<div class="desc">两排杨树</div>
								<div class="ui tiny yellow button follow">+关注</div>
							</div>
							<div class="content">
								<div class="">
									<img src="<%=request.getContextPath() %>/img/gallery/tag3.png" alt="" />
								</div>
								<div class="">
									<img src="<%=request.getContextPath() %>/img/gallery/tag1.png" alt="" />
								</div>
								<div class="">
									<img src="<%=request.getContextPath() %>/img/gallery/tag2.png" alt="" />
								</div>
								<div class="">
									<img src="<%=request.getContextPath() %>/img/gallery/tag2.png" alt="" />
								</div>
							</div>
						
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</body>
</html>