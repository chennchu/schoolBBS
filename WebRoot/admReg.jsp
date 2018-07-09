<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册管理员</title>
<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet" href="<%=basePath%>assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=basePath%>assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=basePath%>assets/css/form-elements.css">
<link rel="stylesheet" href="<%=basePath%>assets/css/style.css">


<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="<%=basePath%>assets/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<%=basePath%>assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="<%=basePath%>assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="<%=basePath%>assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="<%=basePath%>assets/ico/apple-touch-icon-57-precomposed.png">
<!-- Javascript -->
<script src="<%=basePath%>scripts/js/jquery-3.1.1.min.js"></script>
<script src="<%=basePath%>assets/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>assets/js/jquery.backstretch.min.js"></script>
<script src="<%=basePath%>assets/js/retina-1.1.0.min.js"></script>
<script src="<%=basePath%>assets/js/scripts2.js"></script>
<script type="text/javascript">
	$(function() {
		$("#regname").blur(function() {

			var name = $(this).val();
			name = $.trim(name);
			var url = "admin/verityName";
			var args = {
				"time" : new Date(),
				"name" : name
			};
			$.get(url, args, function(data) {
				$("#regname-msg").text(data);
			});
		});
		$("#regist-btn").click(function() {
			var pwd = $("#password1").val();
			var pwd2 = $("#password2").val();
			if (pwd2.length < 8) {
				alert("密码不能小于8！");
				return false;
			}
			if (pwd != pwd2) {
				alert("两次密码不一致！");
				return false;
			}
		});
	});
</script>
</head>
<body>
	<!-- 注册框 -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>
							<strong>在线教学论坛</strong> 管理员注册
						</h1>
						<div class="description">
							<p>Hello,welcome everyone!</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">

						<form role="form" action="admin/regist" method="post"
							class="registration-form" id="regist">

							<fieldset>
								<div class="form-top">
									<div class="form-top-left">
										<h3>步骤 1</h3>
										<p>请告诉我们你是谁:</p>
									</div>
									<div class="form-top-right">
										<i class="fa fa-user"></i>
									</div>
								</div>
								<div class="form-bottom">
									<div class="form-group">
										<label class="sr-only" for="regname">用户名:</label> <input
											id="regname" type="text" name="regname"
											placeholder="你的用户名..." class="form-first-name form-control">
									</div>
									<div class="form-group">
										<label for="regname"
											id="regname-msg"></label>
									</div>


								</div>
							</fieldset>

							<fieldset>
								<div class="form-top">
									<div class="form-top-left">
										<h3>步骤 2</h3>
										<p>设置你的账户:</p>
									</div>
									<div class="form-top-right">
										<i class="fa fa-key"></i>
									</div>
								</div>
								<div class="form-bottom">
									<div class="form-group">
										<label class="sr-only" for="password1">密码:</label> <input
											id="password1" type="password" name="password1"
											placeholder="Password..." class="form-password form-control">
									</div>
									<div class="form-group">
										<label class="sr-only" for="form-repeat-password">请重复一次:</label>
										<input id="password2" type="password" name="password2"
											placeholder="Repeat password..."
											class="form-repeat-password form-control"> <label
											for="password2" class="col-sm-2 control-label"
											id="password2-msg"></label>
									</div>
									<button type="submit" form="regist" class="btn btn-primary"
										id="regist-btn">注册</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">取消</button>
								</div>
							</fieldset>
						</form>

					</div>
				</div>
			</div>
		</div>

	</div>




</body>
</html>