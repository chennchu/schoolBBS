<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生上传作业</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="keywords" content="" />

<script type="application/x-javascript">addEventListener("load", function() {
		setTimeout(hideURLbar, 0);
	}, false);
	function hideURLbar() {
		window.scrollTo(0, 1);
	}
</script>





<!-- Bootstrap Core CSS -->
<link href="<%=basePath%>css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="<%=basePath%>css/style.css" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<!-- font-awesome icons -->
<link href="<%=basePath%>css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome icons -->
<!-- js-->
<script src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
<script src="<%=basePath%>js/modernizr.custom.js"></script>
<!--webfonts-->
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Condensed:400,300,300italic,400italic,700,700italic'
	rel='stylesheet' type='text/css'>
<!--//webfonts-->
<!--animate-->
<link href="<%=basePath%>css/animate.css" rel="stylesheet" type="text/css"
	media="all">
<script src="<%=basePath%>js/wow.min.js"></script>
<script>
	new WOW().init();
</script>
<!--//end-animate-->
<!-- Metis Menu -->
<script src="<%=basePath%>js/metisMenu.min.js"></script>
<script src="<%=basePath%>js/custom.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>utf8-jsp/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>utf8-jsp/kityformula-plugin/addKityFormulaDialog.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>utf8-jsp/kityformula-plugin/getKfContent.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>utf8-jsp/kityformula-plugin/defaultFilterFix.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>utf8-jsp/lang/zh-cn/zh-cn.js"></script>

<!-- <style type="text/css">
        div{
            width:100%;
        } -->
</style>

<link href="<%=basePath%>css/custom.css" rel="stylesheet">
<!--//Metis Menu -->

</head>
<body class="cbp-spmenu-push">
	<div class="main-content">
		<!--left-fixed -navigation-->
		<div class=" sidebar" role="navigation">
			<div class="navbar-collapse">
				<nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left"
					id="cbp-spmenu-s1">
				<ul class="nav" id="side-menu">
					<li><a href="PersonCenter.jsp"><i
							class="fa fa-home nav_icon"></i>个人中心</a></li>

					<li><a href="stuInfo.jsp"><i class="fa fa-table nav_icon"></i>个人用户信息
							</a></li>
					<li><a href="#"><i class="fa fa-check-square-o nav_icon"></i>基本操作<span
							class="fa arrow"></span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="UpdateStuInfo.jsp">修改个人信息 </a></li>
							<li><a href="updateStuPwd.jsp">修改密码</a></li>
							<li><a href="updateAvatar.jsp">修改头像</a></li>
						</ul> <!-- //nav-second-level --></li>
					<li><a href="#"><i class="fa fa-envelope nav_icon"></i>师生互动<span
							class="fa arrow"></span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="stuuploadHomework.jsp">上传作业</a></li>
							<li><a href="studownloadHomework.jsp">下载作业</a></li>
							<li><a href="stuPost.jsp">发表帖子</a></li>
						</ul> <!-- //nav-second-level --></li>

				</ul>
				<div class="clearfix"></div>
				<!-- //sidebar-collapse --> </nav>
			</div>
		</div>
		<!--left-fixed -navigation-->
		<!-- header-starts -->
		<div class="sticky-header header-section ">
			<div class="header-left">
				<!--toggle button start-->
				<button id="showLeftPush">
					<i class="fa fa-bars"></i>
				</button>
				<!--toggle button end-->
				<!--logo -->
				<div class="logo">
					<a href="PersonCenter.jsp">
						<h1>个人中心</h1> <span>UsersPanel</span>
					</a>
				</div>
				<!--//logo-->
				<!--search-box-->

				<div class="clearfix"></div>
			</div>
			<div class="header-right">
				<div class="profile_details_left">
					<!--notifications of menu start -->

					<div class="clearfix"></div>
				</div>
				<!--notification menu end -->
				<div class="profile_details">
					<ul>
						<li class="dropdown profile_details_drop"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"
							aria-expanded="false">
								<div class="profile_img">
									<%-- <span class="prfil-img">
									
									<img
										src="<%=basePath %>${sessionScope.user.avatar }"  alt="#">
									
									</span> --%>
									<div class="user-name">
										<p>${sessionScope.user.username }</p>
										<span>个人中心，欢迎您！</span>
									</div>

									<div class="clearfix"></div>
								</div>
						</a></li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>
		<!-- //header-ends -->
		<!-- main content start-->
		<div id="page-wrapper">
			<div class="main-page">
				<div class="row">
					<!-- 发表帖子 -->
					<h3 class="title1">请发表您的帖子:</h3>
					<div class="form-three widget-shadow">
						<form class="form-horizontal" method="post" role="form"
							action="<%=basePath %>user/stuPost">
							<!-- 输入帖子题目 -->
							<div class="form-group">
								<label for="postName" class="col-sm-2 control-label">帖子题目</label>
								<div class="col-sm-8">
									<input type="text" class="form-control1" name="postName"
										id="postName" placeholder="请输入帖子题目...">
								</div>
							</div>
							<!-- 帖子内容 -->
							<div class="form-group">
								<div align="center">
									<script id="editor" type="text/plain"
										style="width:1024px;height:500px;"></script>
								</div>
							</div>

							<div class="form-group" align="center">
								<button type="submit" class="btn">上传</button>
							</div>
						</form>
					</div>

				</div>

			</div>
		</div>
		<!--footer-->
		<div class="footer">
			<p>
				Copyright &copy; 2017.Company name All rights reserved.<a
					target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>
			</p>
		</div>
		<!--//footer-->
	</div>
	<!-- Classie -->
	<script src="<%=basePath%>js/classie.js"></script>
	<script>
		var menuLeft = document.getElementById('cbp-spmenu-s1'),
			showLeftPush = document.getElementById('showLeftPush'),
			body = document.body;
	
		showLeftPush.onclick = function() {
			classie.toggle(this, 'active');
			classie.toggle(body, 'cbp-spmenu-push-toright');
			classie.toggle(menuLeft, 'cbp-spmenu-open');
			disableOther('showLeftPush');
		};
	
		function disableOther(button) {
			if (button !== 'showLeftPush') {
				classie.toggle(showLeftPush, 'disabled');
			}
		}
	</script>
	<!--scrolling js-->
	<script src="<%=basePath%>js/jquery.nicescroll.js"></script>
	<script src="<%=basePath%>js/scripts.js"></script>
	<!--//scrolling js-->
	<!-- Bootstrap Core JavaScript -->
	<script src="<%=basePath%>js/bootstrap.js"> </script>
	<script type="text/javascript">
		$(function() {
			$("#classId").blur(function() {
				var classId = $(this).val();
				classId = $.trim(classId);
				var url = "teacher/verityClassId";
				var args = {
					"time" : new Date(),
					"classId" : classId
				};
				$.get(url, args, function(data) {
					$("#msg").text(data);
				});
			});
	
	
		});
	
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		var ue = UE.getEditor('editor');
		var ue = UE.getEditor('editor', {
			toolbars : [ [
				'fullscreen', 'source', '|',
				'bold', 'italic', 'underline', '|', 'fontsize', '|', 'kityformula', 'preview'
			] ]
		});
	
	
		function isFocus(e) {
			alert(UE.getEditor('editor').isFocus());
			UE.dom.domUtils.preventDefault(e)
		}
		function setblur(e) {
			UE.getEditor('editor').blur();
			UE.dom.domUtils.preventDefault(e)
		}
		function insertHtml() {
			var value = prompt('插入html代码', '');
			UE.getEditor('editor').execCommand('insertHtml', value)
		}
		function createEditor() {
			enableBtn();
			UE.getEditor('editor');
		}
		function getAllHtml() {
			alert(UE.getEditor('editor').getAllHtml())
		}
		function getContent() {
			var arr = [];
			arr.push("使用editor.getContent()方法可以获得编辑器的内容");
			arr.push("内容为：");
			arr.push(UE.getEditor('editor').getContent());
			alert(arr.join("\n"));
		}
		function getPlainTxt() {
			var arr = [];
			arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
			arr.push("内容为：");
			arr.push(UE.getEditor('editor').getPlainTxt());
			alert(arr.join('\n'))
		}
		function setContent(isAppendTo) {
			var arr = [];
			arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
			UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
			alert(arr.join("\n"));
		}
		function setDisabled() {
			UE.getEditor('editor').setDisabled('fullscreen');
			disableBtn("enable");
		}
	
		function setEnabled() {
			UE.getEditor('editor').setEnabled();
			enableBtn();
		}
	
		function getText() {
			//当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
			var range = UE.getEditor('editor').selection.getRange();
			range.select();
			var txt = UE.getEditor('editor').selection.getText();
			alert(txt)
		}
	
		function getContentTxt() {
			var arr = [];
			arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
			arr.push("编辑器的纯文本内容为：");
			arr.push(UE.getEditor('editor').getContentTxt());
			alert(arr.join("\n"));
		}
		function hasContent() {
			var arr = [];
			arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
			arr.push("判断结果为：");
			arr.push(UE.getEditor('editor').hasContents());
			alert(arr.join("\n"));
		}
		function setFocus() {
			UE.getEditor('editor').focus();
		}
		function deleteEditor() {
			disableBtn();
			UE.getEditor('editor').destroy();
		}
		function disableBtn(str) {
			var div = document.getElementById('btns');
			var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
			for (var i = 0, btn; btn = btns[i++];) {
				if (btn.id == str) {
					UE.dom.domUtils.removeAttributes(btn, [ "disabled" ]);
				} else {
					btn.setAttribute("disabled", "true");
				}
			}
		}
		function enableBtn() {
			var div = document.getElementById('btns');
			var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
			for (var i = 0, btn; btn = btns[i++];) {
				UE.dom.domUtils.removeAttributes(btn, [ "disabled" ]);
			}
		}
	
		function getLocalData() {
			alert(UE.getEditor('editor').execCommand("getlocaldata"));
		}
	
		function clearLocalData() {
			UE.getEditor('editor').execCommand("clearlocaldata");
			alert("已清空草稿箱")
		}
	</script>

</body>
</html>