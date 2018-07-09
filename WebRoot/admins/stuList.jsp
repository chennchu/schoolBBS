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
<title>学生信息管理系统</title>
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
<script type="text/javascript">
	function getParameter(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return unescape(r[2]);
		return null;
	}

	//init
	$(function() {
		var totalPage = "${requestScope.userCreteria.end}";
		var totalRecords = "${requestScope.userCreteria.total}";
		var pageNo = getParameter('pno');
		var orderby = $("[name=orderby]").val();
		var keyword = $("[name=keyword]").val();
		if (!pageNo) {
			pageNo = 1;
		}
		//生成分页
		//有些参数是可选的，比如lang，若不传有默认值
		kkpager.generPageHtml({
			pno : pageNo,
			//总页码
			total : totalPage,
			//总数据条数
			totalRecords : totalRecords,
			//链接前部
			hrefFormer : '<%=basePath%>user',
			//链接尾部
			hrefLatter : '/listAllPost',
			getLink : function(n) {
				return this.hrefFormer + this.hrefLatter + "?pno=" + n + "&&orderby=" + orderby + "&&keyword=" + keyword;
			}
		});
	});
</script>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">在线教学论坛</a>
		</div>

		<!-- 导航栏 -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">


			<!-- 登录信息 -->

		</div>
	</div>
	</nav>

	<div class="container">
		<div class="row clearfix">
			<div align="center" class="item">
				<img alt="" src="<%=basePath%>tupian/4.jpg" />
				<div class="carousel-caption"></div>
			</div>
			<br>
			<div class="col-md-12 column">
				<ul class="nav nav-tabs">
					<li class="active"><a href="<%=basePath%>user/stulistAll">学生信息管理</a></li>
					<li><a href="<%=basePath%>teacher/teaListAll">老师信息管理</a></li>
					<li><a href="<%=basePath%>topic/admtoplistAll">帖子信息管理</a></li>
					<li><a href="<%=basePath%>admin/listTeam">文件夹信息管理</a></li>
					<li><a href="<%=basePath%>admin/fileListAll">公共资源信息管理</a></li>
					<li><a href="<%=basePath%>admins/messList.jsp">公告栏信息管理</a></li>
				</ul>
				<br>
				<form class="form-inline" action="<%=basePath%>user/listAllPost"
					method="post">
					<div class="form-group">
						<select name="orderby" class="form-control">
							<option value="id asc">请选择排序方式</option>
							<option value="id asc">按id升序排列</option>
							<option value="id desc">按id降序排列</option>
						</select>
					</div>
					<div class="form-group">
						<label for="keyword"></label><input type="text" name="keyword"
							class="form-control" id="keyword" placeholder="请输入要查询的关键字..." />

					</div>
					<button type="submit" class="btn">确定</button>
				</form>
				<table class="table">
					<thead>
						<tr>
							<th>ID</th>
							<th>用户名</th>
							<th>性别</th>
							<th>邮箱</th>
							<th>电话号码</th>
							<th>状态信息</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.users }" varStatus="status"
							var="users">
							<tr>

								<td>${status.index + 1 }</td>
								<td>${users.username }</td>
								<td>${users.sex }</td>
								<td>${users.email }</td>
								<td>${users.phoneNum }</td>
									<!-- 默认0为正常，1为已删除  state=3表示要注销-->
								<c:if test="${users.state eq 0 }">
									<td>正常</td>
									<td><a href="<%=basePath %>user/updateState?id=${users.id }&state=1">禁用</a> | <a href= "<%=basePath %>user/updateState?id=${users.id }&state=3">注销</a> | <a href="<%=basePath%>user/toUpdateUserPassword?id=${users.id}" >重置密码</a></td>
								</c:if>
								<c:if test="${users.state eq 1 }">
									<td>禁用</td>
									<td><a href="<%=basePath %>user/updateState?id=${users.id }&state=0">恢复</a> | <a href= "<%=basePath %>user/updateState?id=${users.id }&state=3">注销</a></td>
								</c:if>
								<c:if test="${users.state eq 3 }">
									<td>注销</td>
									<td>该用户已被注销,不能操作<td>
								</c:if>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div style="width:800px;margin:0 auto;">
					<div id="kkpager"></div>
				</div>
			</div>
		</div>
	</div>
</body>
<jsp:include page="../footer.jsp" />
</html>