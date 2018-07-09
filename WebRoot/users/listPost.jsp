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
<title>帖子展示区</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>scripts/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>scripts/css/bootstrap-responsive.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>kkpager-master/css/kkpager_orange.css">
<script type="text/javascript"
	src="<%=basePath%>scripts/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>scripts/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>kkpager-master/js/kkpager.min.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/js/docs.min.js"></script>
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
<script type="text/javascript">
	$(function() {
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


	});
</script>

</head>
<body>
	<nav class="navbar navbar-default top-navbar" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".sidebar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="adminPersonCenter.jsp">在线教学论坛系统</a>
	</div>

	<ul class="nav navbar-top-links navbar-right">
		<!-- 导航条右边 -->
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#" aria-expanded="false"> <i
				class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<li class="divider"></li>
				<!-- 退出 -->
				<li><a href="#"><i class="fa fa-sign-out fa-fw"></i>注销</a></li>
			</ul></li>
	</ul>
	</nav>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column"
				style="width:1000px;margin:0 auto;height:100px;">
				<div class="row clearfix top">
					<div class="col-md-10 column">
						<h2 align="center">${requestScope.topic.title }</h2>
						<h4 align="right">发帖人:${requestScope.topic.userName }</h4>
						<p align="center">${requestScope.topic.content }</p>

					</div>
					<div class="col-md-2 column">
						<a href="#replyPost">回复该贴</a>
					</div>
				</div>
				<div class="xian "
					style="width:1000px;margin:0 auto;padding:0 200px; border-top:1px solid #ddd"></div>
				<c:if test="${!empty requestScope.replys }">
					<c:forEach items="${requestScope.replys }" var="replys"
						varStatus="status">
						<div class="row clearfix">
							<div class="col-md-2 column">
								<!-- 回复者头像 -->
								<img alt="140x140" src="<%=basePath %>${replys.avatar }" class="img-thumbnail" />
								<div align="center">回帖人:${replys.userName }</div>
							</div>
							<div class="col-md-10 column">
								<p class="bg-primary text-white">${ status.index + 1}楼  </p>

								<p>${replys.content }</p>
							</div>
							
						</div>
						<div class="xian "
							style="width:1000px;margin:0 auto;padding:0 200px; border-top:1px solid #ddd"></div>
					</c:forEach>
				</c:if>
				<c:if test="${empty requestScope.replys }">
					<p>暂无评论，快来评论吧！</p>
				</c:if>
				<br><br>
				<form id="replyPost" class="form-horizontal" method="post"
					role="form"
					action="<%=basePath %>reply/replyPost?topicid=${requestScope.topic.id}">
					<!-- 回复的内容 -->
					备注：<br /> 1.支持的文件格式是jpg/png/jpeg/gif<br /> 2.文件大小应小于1M<br />
					3.请不要上传与版权/名誉相关的图片 <br /> <br />
					<div class="form-group">
						<div align="center">
							<script id="editor" type="text/plain"
								style="width:1024px;height:500px;"></script>
						</div>
					</div>
					<div class="form-group" align="center">
						<button type="submit" class="btn">确定</button>
					</div>
				</form>
				<!-- 添加回复的富文本框 -->

			</div>
		</div>
	</div>
</body>

</html>