package org.me.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.user.entity.Message;
import org.me.user.entity.MyFile;
import org.me.user.entity.Reply;
import org.me.user.entity.Topic;
import org.me.user.entity.TopicCreteria;
import org.me.user.entity.User;
import org.me.user.entity.UserCreteria;
import org.me.user.service.MessAgeService;
import org.me.user.service.ReplyService;
import org.me.user.service.TopicService;
import org.me.user.service.UserService;

public class TopicServlet extends HttpServlet {



	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getContextPath();
		String requestURI2 = request.getRequestURI();
		StringBuffer requestURL = request.getRequestURL();
		String servletPath = request.getServletPath();
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		int serverPort = request.getServerPort();
		// 获取请求的URI
		String requestURI = request.getRequestURI();
		// 获取最后一个"/"的位置
		int index = requestURI.lastIndexOf("/");
		// 截取最后的请求的URI作为方法名
		String methodName = requestURI.substring(index + 1);
		// 获取当前类的Class对象
		Class<? extends TopicServlet> c = this.getClass();

		try {
			// 从当前类中获取相应的方法
			Method m = c.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			// 调用方法。
			m.invoke(this, request, response);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	/**
	 * 通过id删帖
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//先删评论
		String topicid = request.getParameter("id");
		//先查再删
		 //去查询
		ReplyService replyService = new ReplyService();
		List<Reply>replys = replyService.listAll(topicid);
		for(Reply reply:replys){
			//删除
			int i = replyService.deleteById(reply.getId());
		}
		//再删帖子
		TopicService service = new TopicService();
		int i = service.deleteById(topicid);
		this.admtoplistAll(request, response);
	}
	/**
	 * 审核帖子
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateState(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//去数据库把audit改为1
		//帖子id
		String topicid = request.getParameter("id");
		TopicService service = new TopicService();
		service.updateAduit(topicid);
		this.admtoplistAll(request, response);
		//查出所有帖子
		//返回
	}
	
	/**
	 * 后台分页查询(有条件)
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void admlistAllPost2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == "" ? "" : request.getParameter("keyword");
		// 获取全部学生信息
		TopicService service = new TopicService();
		TopicCreteria topicCreteria = service.admgetTopicByqualified(keyword, pageNo, orderby);
		// 分页
		List<Topic> page = service.admgetPage(topicCreteria);
		request.setAttribute("topics", page);
		request.setAttribute("topicCreteria", topicCreteria);

		request.getRequestDispatcher("/admins/topList.jsp").forward(request, response);
		// response.sendRedirect(request.getContextPath()+"/stuList.jsp");
	}
	
	/**
	 * 后台分页查询 无条件
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void admtoplistAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("能进后台吗");
		/*int i = 1;
		System.out.println("能进来几次:"+i++);*/
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		// 获取全部帖子信息
		TopicService service = new TopicService();
		//获取全部信息
		TopicCreteria topicCreteria = service.admgetTopicByqualified2("", "1", "id asc");
		//分页
		List<Topic> page = service.admgetPage2(topicCreteria);
		
		request.setAttribute("topics", page);
		request.setAttribute("topicCreteria", topicCreteria);

		request.getRequestDispatcher("/admins/topList.jsp").forward(request, response);
	}
	
	/**
	 * 教师主页面分页查询有条件
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listAllPost2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("可以进来啊！");
		//User user = (User) request.getSession().getAttribute("user");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获取公告栏内容
		MessAgeService messageService = new MessAgeService();
		Message message = messageService.getMessage();
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		//获取全部帖子信息
		TopicService service = new TopicService();
		TopicCreteria topicCreteria =  service.getTopicByqualified(keyword,pageNo,orderby);
		//分页
		List<Topic> page = service.getPage(topicCreteria);
		request.setAttribute("message", message);
		request.setAttribute("topics", page);
		request.setAttribute("topicCreteria", topicCreteria);
		request.getRequestDispatcher("/teachers/listAll2.jsp").forward(request, response);
	}
	/**
	 * 学生主页面分页查询有条件
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listAllPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("可以进来啊！");
		//User user = (User) request.getSession().getAttribute("user");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获取公告栏内容
		MessAgeService messageService = new MessAgeService();
		Message message = messageService.getMessage();
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		//获取全部帖子信息
		TopicService service = new TopicService();
		TopicCreteria topicCreteria =  service.getTopicByqualified(keyword,pageNo,orderby);
		//分页
		List<Topic> page = service.getPage(topicCreteria);
		request.setAttribute("message", message);
		request.setAttribute("topics", page);
		request.setAttribute("topicCreteria", topicCreteria);
		request.getRequestDispatcher("/users/listAll.jsp").forward(request, response);
	}
	
	/**
	 * 学生 帖子无条件分页
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listAllPostNoth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("能进listAllPostNoth");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		MessAgeService messService = new MessAgeService();
		Message message = messService.getMessage();
		TopicService service = new TopicService();
		TopicCreteria topicCreteria =  service.getTopicByqualified("","1","id asc");
		//分页
		List<Topic> page = service.getPage(topicCreteria);
		//System.out.println("能进数据库");
		request.setAttribute("message", message);
		request.setAttribute("topics", page);
		request.setAttribute("topicCreteria", topicCreteria);
		request.getRequestDispatcher("/users/listAll.jsp").forward(request, response);
	}
	
	/**
	 * 教师 帖子无条件分页
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listAllPostNoth2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("能进listAllPostNoth");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		MessAgeService messService = new MessAgeService();
		Message message = messService.getMessage();
		TopicService service = new TopicService();
		TopicCreteria topicCreteria =  service.getTopicByqualified("","1","id asc");
		//分页
		List<Topic> page = service.getPage(topicCreteria);
		System.out.println("能进数据库");
		request.setAttribute("message", message);
		request.setAttribute("topics", page);
		request.setAttribute("topicCreteria", topicCreteria);
		request.getRequestDispatcher("/teachers/listAll2.jsp").forward(request, response);
	}
	
		
	
	
	/**
	 * 学生通过帖子id获取帖子内容
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listbyId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		TopicService service = new TopicService();
		ReplyService rs = new ReplyService();
		Topic t = service.listbyId(id);
		List<Reply> replys = rs.listAll(id);
		request.setAttribute("topic", t);
		request.setAttribute("replys", replys);
		request.getRequestDispatcher("/users/listPost.jsp").forward(request, response);
	}
	
	/**
	 * 教师通过帖子id获取帖子内容
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listbyId2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		TopicService service = new TopicService();
		ReplyService rs = new ReplyService();
		Topic t = service.listbyId(id);
		List<Reply> replys = rs.listAll(id);
		request.setAttribute("topic", t);
		request.setAttribute("replys", replys);
		request.getRequestDispatcher("/teachers/listPost2.jsp").forward(request, response);
	}

	
	
	

}
