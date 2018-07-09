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
<title>Insert title here</title>
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
		var totalPage = "${requestScope.topicCreteria.end}";
		var totalRecords = "${requestScope.topicCreteria.total}";
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
			hrefFormer : '<%=basePath%>topic',
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
	<!-- 引入header文件 -->
	<jsp:include page="../header.jsp"></jsp:include>
	<!-- 整个页面 -->

	<div class="container">
		<div class="row clearfix" align="center">
			<div class="col-md-12 column" >
				<div class="carousel slide" id="carousel-938497">
					<ol class="carousel-indicators">
						<li class="active" data-slide-to="0"
							data-target="#carousel-938497"></li>
						<li data-slide-to="1" data-target="#carousel-938497"></li>
						<li data-slide-to="2" data-target="#carousel-938497"></li>
					</ol>
					<div class="carousel-inner" align="center">
						<div class="item active">
							<img alt=""
								src="<%=basePath%>tupian/1.jpg" />
							<div class="carousel-caption"></div>
						</div>
						<div class="item">
							<img alt="" src="<%=basePath%>tupian/2.jpg" />
							<div class="carousel-caption"></div>
						</div>
						<div class="item">
							<img alt=""
								src="<%=basePath%>tupian/3.jpg" />
							<div class="carousel-caption"></div>
						</div>
					</div>
					<a class="left carousel-control" href="#carousel-938497"
						data-slide="prev"><span
						class="glyphicon glyphicon-chevron-left"></span></a> <a
						class="right carousel-control" href="#carousel-938497"
						data-slide="next"><span
						class="glyphicon glyphicon-chevron-right"></span></a>
				</div>
			</div>
		</div>
		<br>
		<div class="row-fluid">
			<div class="span2">
				<h2>公告栏</h2>
				<div>${requestScope.message.content }</div>
			</div>
			<div class="span10">
				<!--页头  -->
				<form class="form-inline" action="<%=basePath%>topic/listAllPost"
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
				<br> <br>
				<table class="table table-hover">
					<!-- 标题 编号    tr行数 th表头 td内容 -->
					<tr>
						<th>编号</th>
						<th>帖子</th>
						<th>发帖人</th>
					</tr>
					<!-- 循环分页读取 -->
					<c:if test="${empty requestScope.topics }">
						<tr>
							<td></td>
							<td>暂无帖子</td>
						</tr>
					</c:if>
					<!-- 获取帖子内容 -->
					<c:if test="${!empty requestScope.topics }">
						<c:forEach items="${requestScope.topics }" var="topics">
							<tr>
								<!-- 获取帖子id和标题 -->
								<td>${topics.id }</td>
								<td><a
									href="<%=basePath %>topic/listbyId?id=${topics.id  }">${topics.title }</a></td>
								<td>${topics.userName }</td>
							</tr>
						</c:forEach>

					</c:if>

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