package org.me.user.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.me.base.user.util.Myutils;
import org.me.base.user.util.MyInputStream;

import org.me.user.entity.Admin;
import org.me.user.entity.FileCreteria;
import org.me.user.entity.Message;
import org.me.user.entity.MyFile;
import org.me.user.entity.Teacher;
import org.me.user.entity.Team;
import org.me.user.entity.TeamCreteria;
import org.me.user.entity.Topic;
import org.me.user.entity.TopicCreteria;
import org.me.user.entity.User;
import org.me.user.service.AdminService;
import org.me.user.service.FileService;
import org.me.user.service.MessAgeService;
import org.me.user.service.TeacherService;
import org.me.user.service.TeamService;
import org.me.user.service.TopicService;
import org.me.user.service.UserService;


public class AdminServlet extends HttpServlet {

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
		Class<? extends AdminServlet> c = this.getClass();

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
	 * 删除公共资源里的上传文件
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deletefilebyId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String fileid = request.getParameter("id");
		String msg = "";
		//通过id找到文件路径
		FileService fileService = new FileService();
		MyFile myfile = fileService.getById(Integer.parseInt(fileid));
		String path = myfile.getPath();
		File file = new File(path);
		boolean delete = file.delete();
		if(delete = true){
			//去数据库删除数据
			fileService.deleteById(Integer.parseInt(fileid));
			this.fileListAll(request, response);
		}else{
			msg="删除失败！";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/msg3.jsp").forward(request, response);
		}
	}
	/**
	 * 删除文件夹
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deletebyId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//先删除文件夹里的内容，在删除文件夹 最后去数据库删记录
		//查找文件夹里的内容
		request.setCharacterEncoding("UTF-8");
		String classid = request.getParameter("id");
		String msg = "";
		FileService service = new FileService();
		TeamService teamService = new TeamService();
		List<MyFile> files = service.getAll(classid);
		if(files!=null){
			for(MyFile myfile:files){
				//删除文件夹里的文件
				String path = myfile.getPath();
				File file = new File(path);
				boolean delete = file.delete();
				if(delete==true){
					int i = service.deleteById(myfile.getId());
				}else{
					msg="文件删除失败！";
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("/msg3.jsp").forward(request, response);
				}
			}
			Myutils.delFolder("D:\\myfile\\"+classid);
			//去数据库里删记录
			teamService.deleteByClassId(classid);
		}else{
			Myutils.delFolder("D:\\myfile\\"+classid);
			//去数据库里删记录
			teamService.deleteByClassId(classid);
		}
		this.listTeam(request, response);
		
	}
	
	 
	/**
	 * 验证用户名是否存在
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void verifyName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String str = "";
		// 给密码MD5加密
		AdminService us = new AdminService();
		// 去数据库查找是否存在该用户
		
		boolean boo = us.verifyName(name);
		if(boo){
			//如果 boo是true，用户存在
			str="";
		}else{
			str = "该管理员不存在，请注册！";
		}

		PrintWriter out = response.getWriter();
		out.write(str);
		out.flush();
		out.close();
	}
	
	/**
	 * 验证管理员密码是否正确
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void verifyPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String str = "";
		// 给密码MD5加密
		password = Myutils.md5(password);
		AdminService us = new AdminService();
		// 去数据库查找是否存在该用户
		Admin u = us.getT(name, password);
		if (u != null) {
			str = "密码正确。";
		} else {
			str = "密码错误，请修改！";
		}

		PrintWriter out = response.getWriter();
		out.write(str);
		out.flush();
		out.close();
	}
	
	/**
	 * 更新公告栏内容
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String content = request.getParameter("editorValue");
		//去数据库存储
		MessAgeService service = new MessAgeService();
		Message message = new Message(1, content);
		service.updateMessage(message);
		//更新完成之后后台中心
		UserServlet us = new UserServlet();
		us.stulistAll(request, response);
	}
	
	/**
	 * 后台无条件查询公共资源
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void fileListAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//String classId = request.getParameter("classId");
		String classId = "upload";
		//System.out.println(classId);
		// 获取全部学生信息
		FileService service = new FileService();
		//获取全部信息
		FileCreteria fileCreteria = service.getFileByqualified2("", "1", "orderby",classId);
		//System.out.println("无条件分页："+fileCreteria);
		//分页
		//System.out.println("fileCreteria:"+fileCreteria);
		List<MyFile> page = service.getPage2(fileCreteria,classId);
		//System.out.println(page);
		//System.out.println(page);
		request.setAttribute("files", page);
		request.setAttribute("fileCreteria", fileCreteria);
		request.getRequestDispatcher("/admins/fileList.jsp").forward(request, response);
	}
	
	/**
	 * 后台有条件查询公共资源
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void fileListAll2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//String classid = request.getParameter("classid");
		String classid = "upload";
		//System.out.println("classid："+classid);
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == "" ? "" : request.getParameter("keyword");
		// 获取全部文件信息
		FileService service = new FileService();
		FileCreteria fileCreteria = service.getFileByqualified(keyword, pageNo, orderby, classid);
		// 分页
		List<MyFile> page = service.getPage(fileCreteria,classid);
		//System.out.println("有条件的:"+page);
		request.setAttribute("files", page);
		request.setAttribute("fileCreteria", fileCreteria);
		request.getRequestDispatcher("/admins/fileList.jsp").forward(request, response);
	}

	/**
	 * 学生前台无条件查询公共资源
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listAllFileG2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//String classId = request.getParameter("classId");
		String classId = "upload";
		//System.out.println(classId);
		// 获取全部学生信息
		FileService service = new FileService();
		//获取全部信息
		FileCreteria fileCreteria = service.getFileByqualified2("", "1", "orderby",classId);
		//System.out.println("无条件分页："+fileCreteria);
		//分页
		//System.out.println("fileCreteria:"+fileCreteria);
		List<MyFile> page = service.getPage2(fileCreteria,classId);
		//System.out.println(page);
		//System.out.println(page);
		request.setAttribute("files", page);
		request.setAttribute("fileCreteria", fileCreteria);
		request.getRequestDispatcher("/admins/downLoadFilelist.jsp").forward(request, response);
	}
	/**
	 * 教师前台无条件查询公共资源
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listAllFileG3(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//String classId = request.getParameter("classId");
		String classId = "upload";
		//System.out.println(classId);
		// 获取全部学生信息
		FileService service = new FileService();
		//获取全部信息
		FileCreteria fileCreteria = service.getFileByqualified2("", "1", "orderby",classId);
		//System.out.println("无条件分页："+fileCreteria);
		//分页
		//System.out.println("fileCreteria:"+fileCreteria);
		List<MyFile> page = service.getPage2(fileCreteria,classId);
		//System.out.println(page);
		//System.out.println(page);
		request.setAttribute("files", page);
		request.setAttribute("fileCreteria", fileCreteria);
		request.getRequestDispatcher("/admins/downLoadFilelist2.jsp").forward(request, response);
	}
	
	
	/**
	 * 学生前台有条件查询公共资源
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listAllFileG(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//String classid = request.getParameter("classid");
		String classid = "upload";
		//System.out.println("classid："+classid);
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == "" ? "" : request.getParameter("keyword");
		// 获取全部文件信息
		FileService service = new FileService();
		FileCreteria fileCreteria = service.getFileByqualified(keyword, pageNo, orderby, classid);
		// 分页
		List<MyFile> page = service.getPage(fileCreteria,classid);
		//System.out.println("有条件的:"+page);
		request.setAttribute("files", page);
		request.setAttribute("fileCreteria", fileCreteria);
		request.getRequestDispatcher("/admins/downLoadFilelist.jsp").forward(request, response);
	}
	/**
	 * 教师前台有条件查询公共资源
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listAllFileG4(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//String classid = request.getParameter("classid");
		String classid = "upload";
		//System.out.println("classid："+classid);
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == "" ? "" : request.getParameter("keyword");
		// 获取全部文件信息
		FileService service = new FileService();
		FileCreteria fileCreteria = service.getFileByqualified(keyword, pageNo, orderby, classid);
		// 分页
		List<MyFile> page = service.getPage(fileCreteria,classid);
		//System.out.println("有条件的:"+page);
		request.setAttribute("files", page);
		request.setAttribute("fileCreteria", fileCreteria);
		request.getRequestDispatcher("/admins/downLoadFilelist2.jsp").forward(request, response);
	}
	
	/**
	 * 学生上传公共资源文件
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void uploadFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 添加文件到数据库
		// 建立一个工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//设置最大上传文件大小
		factory.setSizeThreshold(1024 * 10);
		// 设置缓冲区目录
		factory.setRepository(new File("e:\\temp"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> fileItems;
		String msg= "";
		try {
			fileItems = upload.parseRequest(request);
			// 把这个文件域解析 通过文件域写入数据库
			for (FileItem fi : fileItems) {
				if (fi.isFormField()) {
					System.out.println("该文件为表单！");
				} else {
					// 接收文件
					String fileName = fi.getName();
					// 获取文件大小 单位kb
					long sizekb = fi.getSize();
					// 转换为mb
					long size = sizekb / 1024;

					// .在正则表达式中有特殊含义 需要用\\转译
					String c = "\\.";
					String[] n = fileName.split(c);
					// 取最后一个.后的内容
					int i = n.length - 1;
					String type = n[i];
					// 把这个str传回页面，作为文件格式
					// System.out.println("---------"+n+"---------");
					// System.out.println("---------"+n[1]+"---------------");
					// 上传的文件保存的位置
					InputStream is = fi.getInputStream();
					MyInputStream mis = new MyInputStream(is);
					MyInputStream inClo;
					try {
						inClo = (MyInputStream) mis.clone();
						// System.out.println(is == inClo.is);
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 解决重名文件上传会覆盖之前文件的问题
					String rep = "d:\\myfile\\upload\\";
					String path = rep + System.currentTimeMillis() + (int) (Math.random() * 1000) + fileName;
					// 上传到指定文件夹
					OutputStream os = new FileOutputStream(path);
					byte[] bytes = new byte[1024 * 1024 * 5];
					int len;
					while ((len = is.read(bytes)) != -1) {
						os.write(bytes, 0, len);
					}
					is.close();
					os.close();

					// 去数据库添加

					// 封装一个MyFile对象
					MyFile myFile = new MyFile(null, fileName, size, type, path, "upload");
					FileService fileService = new FileService();
					int add = fileService.add(myFile);
					// 去数据库查找全部文件信息 并转发去页面
					if(add>0){
						msg="上传成功！";
					}else{
						msg="上传失败！";
					}
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("/postmessage.jsp").forward(request, response);

				}
			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 学生上传公共资源文件
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void uploadFile2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 添加文件到数据库
		// 建立一个工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//设置最大上传文件大小
		factory.setSizeThreshold(1024 * 10);
		// 设置缓冲区目录
		factory.setRepository(new File("e:\\temp"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> fileItems;
		String msg= "";
		try {
			fileItems = upload.parseRequest(request);
			// 把这个文件域解析 通过文件域写入数据库
			for (FileItem fi : fileItems) {
				if (fi.isFormField()) {
					System.out.println("该文件为表单！");
				} else {
					// 接收文件
					String fileName = fi.getName();
					// 获取文件大小 单位kb
					long sizekb = fi.getSize();
					// 转换为mb
					long size = sizekb / 1024;
					
					// .在正则表达式中有特殊含义 需要用\\转译
					String c = "\\.";
					String[] n = fileName.split(c);
					// 取最后一个.后的内容
					int i = n.length - 1;
					String type = n[i];
					// 把这个str传回页面，作为文件格式
					// System.out.println("---------"+n+"---------");
					// System.out.println("---------"+n[1]+"---------------");
					// 上传的文件保存的位置
					InputStream is = fi.getInputStream();
					MyInputStream mis = new MyInputStream(is);
					MyInputStream inClo;
					try {
						inClo = (MyInputStream) mis.clone();
						// System.out.println(is == inClo.is);
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 解决重名文件上传会覆盖之前文件的问题
					String rep = "d:\\myfile\\upload\\";
					String path = rep + System.currentTimeMillis() + (int) (Math.random() * 1000) + fileName;
					// 上传到指定文件夹
					OutputStream os = new FileOutputStream(path);
					byte[] bytes = new byte[1024 * 1024 * 5];
					int len;
					while ((len = is.read(bytes)) != -1) {
						os.write(bytes, 0, len);
					}
					is.close();
					os.close();
					
					// 去数据库添加
					
					// 封装一个MyFile对象
					MyFile myFile = new MyFile(null, fileName, size, type, path, "upload");
					FileService fileService = new FileService();
					int add = fileService.add(myFile);
					// 去数据库查找全部文件信息 并转发去页面
					if(add>0){
						msg="上传成功！";
					}else{
						msg="上传失败！";
					}
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("/postmessage2.jsp").forward(request, response);
					
				}
			}
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	/**
	 * 无条件查看文件夹
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listTeam(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 去后台查找作业文件夹，即班级文件夹
		// 获取全部文件夹信息
		TeamService service = new TeamService();
		TeamCreteria teamCreteria = service.getTeamByqualified2(null, "1", "id asc");
		// System.out.println("帖子总数："+teamCreteria.getTotal());
		// 无条件分页
		List<Team> teams = service.getPage2(teamCreteria);
		request.setAttribute("teamCreteria", teamCreteria);
		request.setAttribute("teams", teams);
		request.getRequestDispatcher("/admins/teamList.jsp").forward(request, response);
	}

	/**
	 * 有条件查看文件夹
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listTeam2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 去后台查找作业文件夹，即班级文件夹
		// 获取全部文件夹信息
		TeamService service = new TeamService();
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == "" ? "" : request.getParameter("keyword");
		TeamCreteria teamCreteria = service.getTeamByqualified(keyword, pageNo, orderby);
		// 无条件分页
		List<Team> teams = service.getPage(teamCreteria);

		request.setAttribute("teams", teams);
		request.setAttribute("teamCreteria", teamCreteria);
		request.getRequestDispatcher("/admins/teamList.jsp").forward(request, response);
	}

	/**
	 * 验证用户名是否存在 verityName
	 */
	public void verityName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String str = "网络异常，请重试！";
		if (name == null || name == "") {
			str = "用户名不能为空！";
		} else {
			AdminService us = new AdminService();
			boolean isregisted = us.verifyName(name);
			if (isregisted) {
				str = "用户名已被占用！";
			} else {
				str = "用户名可以用！";
			}
		}
		PrintWriter out = response.getWriter();
		out.write(str);
		out.flush();
		out.close();
	}

	/**
	 * 注册用户
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 获取全部帖子信息
		TopicService service = new TopicService();
		TopicCreteria topicCreteria = service.getTopicByqualified2(null, "1", "id asc");
		// 无条件分页
		List<Topic> page = service.getPage2(topicCreteria);
		AdminService us = new AdminService();
		String regname = request.getParameter("regname");
		if (regname == null || regname == "") {
			response.sendRedirect(request.getContextPath() + "/regFail3.jsp");
		} else {
			// 后台验证一次
			boolean verifyName = us.verifyName(regname);
			// 如果没有这个用户，可以注册
			if (verifyName == false) {
				String pwd = request.getParameter("password1");

				// 运用md5加密
				pwd = Myutils.md5(pwd);
				// 传入service
				int i = us.regist(regname, pwd);
				if (i != 1) {
					response.sendRedirect(request.getContextPath() + "/regFail3.jsp");
				} else {
					Admin t = (Admin) us.getT(regname, pwd);
					request.getSession().setAttribute("admin", t);
					UserServlet uss = new UserServlet();
					uss.stulistAll(request, response);
					// System.out.println(t);
					// 给用户绑定session
					/*request.setAttribute("topics", page);
					request.setAttribute("topicCreteria", topicCreteria);
					request.getRequestDispatcher("/admins/stuList.jsp").forward(request, response);*/
					/*
					 * response.sendRedirect(request.getContextPath() +
					 * "/listAll3.jsp");
					 */
				}

			} else {
				response.sendRedirect(request.getContextPath() + "/admregFail.jsp");

			}
		}

	}

	/**
	 * 管理员登陆
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// 获取全部帖子信息
		TopicService service = new TopicService();
		TopicCreteria topicCreteria = service.getTopicByqualified2(null, "1", "id asc");
		// 无条件分页
		List<Topic> page = service.getPage2(topicCreteria);
		// 从前端获取用户登陆信息
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		// 给密码MD5加密
		password = Myutils.md5(password);
		AdminService us = new AdminService();
		// 去数据库查找是否存在该用户
		Admin u = us.getT(name, password);
		if (u != null) {
			UserServlet uss = new UserServlet();
			uss.stulistAll(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/logFail3.jsp");
		}
	}

	/**
	 * 注销登陆
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("admin");
		response.sendRedirect(request.getContextPath() + "/success3.jsp");
	}

	/**
	 * 添加文件夹
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addTeam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String classId = request.getParameter("classId");
		String msg = "";
		int i = 0;
		try {
			if (!(new File("D:/myfile/").isDirectory())) {
				new File("D:/myfile/").mkdir();
				new File("D:/myfile/" + classId).mkdir();
				msg = "文件夹创建成功！";
				// 把创建好的文件夹名存入数据库
				TeamService service = new TeamService();
				i = service.addTeam(classId);

			} else {
				new File("D:/myfile/" + classId).mkdir();
				// 把创建好的文件夹名存入数据库
				TeamService service = new TeamService();
				i = service.addTeam(classId);
				if (i > 0) {
					msg = "文件创建成功，存储成功！";
				} else {
					msg = "文件创建成功,数据库未储存！";
				}
			}
		} catch (SecurityException e) {
			msg = "文件夹创建失败！";
		}
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/msg3.jsp").forward(request, response);

	}

}
