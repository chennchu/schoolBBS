package org.me.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.user.entity.Reply;
import org.me.user.entity.Teacher;
import org.me.user.entity.Topic;
import org.me.user.entity.TopicCreteria;
import org.me.user.entity.User;
import org.me.user.service.ReplyService;
import org.me.user.service.TopicService;
import org.me.user.service.UserService;

public class ReplyServlet extends HttpServlet {

	
	
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
		Class<? extends ReplyServlet> c = this.getClass();

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
	 * 教师用户的回复存储去数据库存储
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void replyPost2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取该帖的id
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获取当前帖子id
		String str = "";
		String topicid = request.getParameter("topicid");
		//System.out.println("测试是否可以进后台--------------");
		//获取回帖人的用户名，通过用户名去查询该用户的头像
		// 获取发帖人的信息
		Teacher user = (Teacher) request.getSession().getAttribute("teacher");
		//System.out.println(user  +"能获取到吗");
		//获取发帖人头像
		String path =user.getAvatar();
		//获取发帖人用户名
		String username = user.getTeachername();
		//获取编辑器里的内容
		String content = request.getParameter("editorValue");
		//System.out.println(content);
		//TopicService topservice = new TopicService();
		if(content==null){
			//发表失败
			str = "内容为空，不能发表！";
			request.setAttribute("msg", str);
			request.getRequestDispatcher("/postfail.jsp").forward(request, response);
		}else{
			//封装回复类
			Reply reply = new Reply(Integer.parseInt(topicid), username, path, content);
			ReplyService service = new ReplyService();
			//添加新的回复
			int i = service.addReply(reply);
			if(i>0){
				str = "评论成功！";
				
			}else{
				str = "评论成功！";
			}
			request.setAttribute("msg", str);
			request.getRequestDispatcher("/postmessage2.jsp").forward(request, response);
		}
	}
	/**
	 * 学生用户的回复存储去数据库存储
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void replyPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取该帖的id
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获取当前帖子id
		String str = "";
		String topicid = request.getParameter("topicid");
		//System.out.println("测试是否可以进后台--------------");
		//获取回帖人的用户名，通过用户名去查询该用户的头像
		// 获取发帖人的信息
		User user = (User) request.getSession().getAttribute("user");
		//System.out.println(user  +"能获取到吗");
		String path =user.getAvatar();
		String username = user.getUsername();
		//获取编辑器里的内容
		String content = request.getParameter("editorValue");
		//System.out.println(content);
		//TopicService topservice = new TopicService();
		if(content==null){
			//发表失败
			str = "内容为空，不能发表！";
			request.setAttribute("msg", str);
			request.getRequestDispatcher("/postfail.jsp").forward(request, response);
		}else{
			//封装回复类
			Reply reply = new Reply(Integer.parseInt(topicid), username, path, content);
			ReplyService service = new ReplyService();
			//添加新的回复
			int i = service.addReply(reply);
			if(i>0){
				str = "评论成功！";
				
			}else{
				str = "评论成功！";
			}
			request.setAttribute("msg", str);
			request.getRequestDispatcher("/postmessage.jsp").forward(request, response);
			
			}
			
	
}
	
	

}
