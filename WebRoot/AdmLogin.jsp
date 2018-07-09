<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登陆页面</title>
 <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="<%=basePath%>assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=basePath%>assets/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="<%=basePath%>assets/css/form-elements.css">
        <link rel="stylesheet" href="<%=basePath%>assets/css/style.css">
        
         <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="<%=basePath%>assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=basePath%>assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=basePath%>assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=basePath%>assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="<%=basePath%>assets/ico/apple-touch-icon-57-precomposed.png">
</head>
<body>
	
        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>在线教学论坛</strong> 管理员登陆</h1>
                            <div class="description">
                            	<p>
	                            	Hello,welcome everybody!<!-- This is a free responsive login form made with Bootstrap. 
	                            	Download it on <a href="#"><strong>AZMIND</strong></a>, customize and use it as you like! -->
                            	</p>
                            </div>
                        </div>
                    </div>
                    <!-- 登陆页面 -->
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>登陆您的用户</h3>
                            		<p>请在下面输入您的用户名与密码:</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-key"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
                            	<!-- 登陆表单 -->
			                    <form role="form" action="admin/login" method="post" class="login-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="name">用户名</label>
			                        	<input type="text" name="name" placeholder="用户名..." class="form-username form-control" id="name">
			                        </div>
			                        <div class="form-group">
									<p class="help-block" id="error1"></p>
									</div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="password">密码</label>
			                        	<input type="password" name="password" placeholder="密码..." class="form-password form-control" id="password">
			                        </div>
			                        <div class="form-group">
									<p class="help-block" id="error2"></p>
									</div>
			                        <button type="submit" class="btn">登陆</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 social-login">
                        	
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
       


        <!-- Javascript -->
        <script src="<%=basePath%>assets/js/jquery-1.11.1.min.js"></script>
        <script src="<%=basePath%>assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="<%=basePath%>assets/js/jquery.backstretch.min.js"></script>
        <script src="<%=basePath%>assets/js/scripts.js"></script>
        <script type="text/javascript">
        	$(function(){
    	//验证密码是否错误
        	$("#password").blur(function() {
        		var name = $("#name").val();
				var pwd = $("#password").val();
				var url = "<%=basePath%>admin/verifyPassword";
				//发送请求参数
				var args = {
					"name" : name,
					"password" : pwd,
					"time" : new Date()
				};
					//发送请求到页面
				$.post(url, args, function(data) {
					$("#error2").text(data);
				});
        	
        	})
        	
        	//点击提交按钮时验证用户是否存在
			$("#name").blur(function() {
				var name = $("#name").val();
				//ajax发送url请求
				var url = "<%=basePath%>admin/verifyName";
				//发送请求参数
				var args = {
					"name": name,
					"time" : new Date()
				};
				//发送请求到页面
				$.post(url,args,function(data){
				$("#error1").text(data);
				
				});
			});
    
    
    
    });
        
        </script>
        
        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->
</body>
</html>