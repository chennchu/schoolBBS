package org.me.user.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.me.base.user.util.MyInputStream;
import org.me.base.user.util.Myutils;
import org.me.user.entity.FileCreteria;
import org.me.user.entity.Message;
import org.me.user.entity.MyFile;
import org.me.user.entity.Teacher;
import org.me.user.entity.TeacherCreteria;
import org.me.user.entity.Team;
import org.me.user.entity.Topic;
import org.me.user.entity.TopicCreteria;
import org.me.user.entity.User;
import org.me.user.entity.UserCreteria;
import org.me.user.service.FileService;
import org.me.user.service.MessAgeService;
import org.me.user.service.TeacherService;
import org.me.user.service.TeamService;
import org.me.user.service.TopicService;
import org.me.user.service.UserService;

public class TeacherServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TeacherServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
		Class<? extends TeacherServlet> c = this.getClass();

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
	 * 重置密码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toUpdateTeacherPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		//通过id对用户密码进行重置
		TeacherService service = new TeacherService();
		service.toUpdatUserPassword(id);
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		// 获取封装好的userCreteria
		TeacherCreteria teacherCreteria = service.getTeacherByqualified(keyword, pageNo, orderby);
		// 获取符合所有条件的用户信息
		List<Teacher> page = service.getPage(teacherCreteria);
		request.setAttribute("teachers", page);
		request.setAttribute("teacherCreteria", teacherCreteria);

		request.getRequestDispatcher("/admins/teaList.jsp").forward(request, response);
	}
	
	/**
	 * 更改用户状态
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateState(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取当前用户id
		String id = request.getParameter("id");
		// 获取当前状态信息 修改为禁用或正常
		String state = request.getParameter("state");
		TeacherService service = new TeacherService();
		// 通过id去修改用户状态信息
		service.updateState(id, state);
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		// 获取封装好的userCreteria
		TeacherCreteria teacherCreteria = service.getTeacherByqualified(keyword, pageNo, orderby);
		// 获取符合所有条件的用户信息
		List<Teacher> page = service.getPage(teacherCreteria);
		request.setAttribute("teachers", page);
		request.setAttribute("teacherCreteria", teacherCreteria);

		request.getRequestDispatcher("/admins/teaList.jsp").forward(request, response);
	}
	
	/**
	 * 验证用户状态是否可以进行登陆
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void verifyState(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		//String password = request.getParameter("password");
		String str = "";
		// 给密码MD5加密
		//password = Myutils.md5(password);
		TeacherService us = new TeacherService();
		// 去数据库查找是否存在该用户
		Teacher u = us.getUserByName(name);
		if(u!=null){
			Integer state = u.getState();
			if(state==0){
				str = "该用户可以登录！";
			}else if(state==1){
				str="该用户已被禁用，请联系管理员！";
			}else{
				str="该用户已被注销！";
			}
		}else{
			str="用户名不存在！请去注册";
		}
		//获取教师用户的状态信息
		PrintWriter out = response.getWriter();
		out.write(str);
		out.flush();
		out.close();
		
	}
	
	/**
	 * 验证用户密码是否正确
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
		TeacherService us = new TeacherService();
		// 去数据库查找是否存在该用户
		Teacher u = us.getT(name, password);
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
	 * 通过id删除该文件
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleFileByid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileid = request.getParameter("id");
		String classId = request.getParameter("classId");
		//去文件夹删  
		//去数据库删
		String msg = "";
		FileService service = new FileService();
		//去数据库查找该文件的路径
		MyFile file = service.getById(Integer.parseInt(fileid));
		boolean bol = false;
			String path = file.getPath();
			File f = new File(path);
			//删除文件
			bol = f.delete();
			//删除数据库里文件的记录
			int i = service.deleteById(file.getId());
			//获取classId
			//System.out.println(classId);
			// 获取全部文件信息
			//获取全部信息
			FileCreteria fileCreteria = service.getFileByqualified2("", "1", "orderby",classId);
			//System.out.println("无条件分页："+fileCreteria);
			//分页
			//System.out.println("fileCreteria:"+fileCreteria);
			List<MyFile> page = service.getPage2(fileCreteria,classId);
			//System.out.println(page);
			request.setAttribute("files", page);
			request.setAttribute("fileCreteria", fileCreteria);
			request.setAttribute("classid", classId);
			request.getRequestDispatcher("/teachers/teaDownLoad.jsp").forward(request, response);
	}
	
	/**
	 * 有条件分页查询(文件)
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listAllFiles(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String classid = request.getParameter("classid");
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == "" ? "" : request.getParameter("keyword");
		/*System.out.println("pageNo:"+pageNo);
		System.out.println("orderby:"+orderby);
		System.out.println("keyword:"+keyword);*/
		// 获取全部文件信息
		FileService service = new FileService();
		//FileCreteria fileCreteria = service.getFileByqualified(keyword, pageNo, orderby,classid);
		FileCreteria fileCreteria = service.getFileByqualified(keyword, pageNo, orderby, classid);
		//System.out.println(fileCreteria);
		// 分页
		List<MyFile> page = service.getPage(fileCreteria,classid);
		//System.out.println("有条件的:"+page);
		request.setAttribute("files", page);
		request.setAttribute("fileCreteria", fileCreteria);
		request.getRequestDispatcher("/teachers/teaDownLoad.jsp").forward(request, response);
		// response.sendRedirect(request.getContextPath()+"/stuList.jsp");
	}
	
	/**
	 * 无条件文件分页
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listAllFiles2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String classId = request.getParameter("classId");
		//System.out.println(classId);
		// 获取全部文件信息
		FileService service = new FileService();
		//获取全部信息
		FileCreteria fileCreteria = service.getFileByqualified2("", "1", "orderby",classId);
		//System.out.println("无条件分页："+fileCreteria);
		//分页
		//System.out.println("fileCreteria:"+fileCreteria);
		List<MyFile> page = service.getPage2(fileCreteria,classId);
		//System.out.println(page);
		request.setAttribute("files", page);
		request.setAttribute("fileCreteria", fileCreteria);
		request.setAttribute("classid", classId);
		request.getRequestDispatcher("/teachers/teaDownLoad.jsp").forward(request, response);
	}
	
	/**
	 *  用户上传头像
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void uploadAva(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Teacher user = (Teacher) request.getSession().getAttribute("teacher");
		ServletInputStream is = request.getInputStream();
		//为基于磁盘的文件项目创建工厂
		FileItemFactory factory = new DiskFileItemFactory();
		//创建新的文件上传处理程序
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			//解析请求
			//List <FileItem> items = upload.parseRequest(request);
			//List<FileItem> list = diskFileUpload.parseRequest(request);
			List <FileItem> items = upload.parseRequest(request);
			String fileName = null;
			String rep = null;
			//遍历这个items
			for(FileItem fi:items){
				//判断是否是一个表单域
				if(fi.isFormField()){
					fileName = fi.getFieldName(); 
					//不是表单
				}else{
					//图片
					InputStream is2 = fi.getInputStream();
					//获取所传文件名字
					fileName = fi.getName();
						//获取最后一次出现的"/"
						//int lastIndexOf = fileName.lastIndexOf("/");
						//获取'/'之后的内容
						//String fileNameS = fileName.substring(lastIndexOf+1);
						//System.out.println(fileNameS);
					byte[] b = new byte[1024*1024*10];
					int len = 0;
					String dir = "avatar";
					//当前项目的webRoot文件夹
					rep = request.getServletContext().getRealPath("/"+dir);
					//防止重复文件名
					fileName = System.currentTimeMillis()+(int)Math.random()*1000+fileName;
					//输出流的全路径，包括文件名
					String path = rep+"\\"+fileName;
					//运用输出流写入图片
					OutputStream os = new FileOutputStream(path);
					while((len=is2.read(b))!=-1){
						os.write(b,0,len);
					}
						os.close();
						is2.close();
					//Book book = new Book(Integer.parseInt(id),name, Double.parseDouble(price),corp, Integer.parseInt(stock),Integer.parseInt(sold), Myutils.isonsale, "图书详情", dir+"/"+fileName,type,new Date(System.currentTimeMillis()),null);
				    //System.out.println(book);
					TeacherService userService = new TeacherService();
					//修改头像
					//int i = 0;
					user.setAvatar(dir+"/"+fileName);
					//System.out.println("测试-----"+user);
					int i = userService.addUploadAva(user);
					//System.out.println(i);
					//int i = bs.addBook();
					String msg = "网络故障，请重试！";
					//判断是否成功删除
					if(i<1){
						msg = "添加失败！";
					}else{
						msg = "添加成功！";
					}
				   
					//绑定参数
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("/msg2.jsp").forward(request, response);
					/*
					 * 测试图片是否添加
					 */
//				    request.setAttribute("book", book);
//				    request.getRequestDispatcher("test.jsp").forward(request, response);
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		is.close();
	}
	
	/**
	 * 后台教师有条件分页查询
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listAllPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == "" ? "" : request.getParameter("keyword");
		// 获取全部学生信息
		TeacherService service = new TeacherService();
		TeacherCreteria teacherCreteria = service.getTeacherByqualified(keyword, pageNo, orderby);
		// 分页
		List<Teacher> page = service.getPage(teacherCreteria);
		request.setAttribute("teachers", page);
		request.setAttribute("teacherCreteria", teacherCreteria);

		request.getRequestDispatcher("/admins/teaList.jsp").forward(request, response);
		// response.sendRedirect(request.getContextPath()+"/stuList.jsp");
	}
	
	/**
	 * 后台教师无条件分页
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void teaListAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		String pageNo = request.getParameter("pno") == null ? "1" : request.getParameter("pno");
		String orderby = request.getParameter("orderby") == null ? "id asc" : request.getParameter("orderby");
		String keyword = request.getParameter("keyword") == "" ? "" : request.getParameter("keyword");
		// 获取全部教师信息
		TeacherService service = new TeacherService();
		//获取信息
		TeacherCreteria teacherCreteria = service.getTeacherByqualified2(keyword, pageNo, orderby);
		//分页
		List<Teacher> page = service.getPage2(teacherCreteria);
		request.setAttribute("teachers", page);
		request.setAttribute("teacherCreteria", teacherCreteria);

		request.getRequestDispatcher("/admins/teaList.jsp").forward(request, response);
	}


	/**
	 * 上传帖子
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void teaPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 获取发帖人的用户名
		Teacher user = (Teacher) request.getSession().getAttribute("teacher");
		String username = user.getTeachername();
		// 拿到帖子的题目
		String title = request.getParameter("postName");
		// 设置请求和响应的编码统一为UTF-8
		response.setCharacterEncoding("UTF-8");
		// 拿到编辑器的内容
		String content = request.getParameter("editorValue");
		String msg = "请检查您的网络！";
		// 如果不为空
		if (content != null) {
			// 把帖子的信息传入数据库
			// 封装
			Topic t = new Topic(username, null, title, content, 0);
			//System.out.println("帖子包含的内容：" + t);
			// 把帖子添加进数据库
			TopicService service = new TopicService();
			int i = service.add(t);
			if (i < 1) {
				msg = "请检查您的帖子内容是否违规！";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/msg2.jsp").forward(request, response);

			} else {
				msg = "发帖成功！等待审核";
				
			}
			
		} else {
			msg = "对不起，您输入的内容为空，不能发表";
			response.getWriter().append("内容为空!");
		}
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/postmessage2.jsp").forward(request, response);
	}

	/**
	 * 判断这个班级文件夹是否存在
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void verityClassId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		// 获取上传文件夹
		String classId = request.getParameter("classId");
		// System.out.println("--------------text:" + classId);
		TeamService service = new TeamService();
		Team t = service.getTeamById(classId);
		// System.out.println("------" + t);
		String msg = "网络故障，请检查网络连接！";
		if (t != null) {
			msg = "班级号存在!";
		} else {
			msg = "班级号不存在，请联系管理员添加！";
		}

		PrintWriter out = response.getWriter();
		out.write(msg);
		out.flush();
		out.close();
	}

	/**
	 * 教师上传文件
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void uploadHomework(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		RequestContext requestContext = new ServletRequestContext(request);
		if (FileUpload.isMultipartContent(requestContext)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置文件的缓存路径
			factory.setRepository(new File("E:/tmp/"));
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = new ArrayList();// 创建集合用于添加文件名字列表
			String msg = "";
			try {
				// 上传文件并解析出所有的普通字段和文件字段
				items = upload.parseRequest(request);
			} catch (FileUploadException e1) {
				// System.out.println("文件上传发生错误" + e1.getMessage());
				msg = "上传失败，请按照指定提示操作！";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/msg2.jsp").forward(request, response);
			}
			// 下面对每个字段进行处理，分普通字段和文件字段
			Iterator it = items.iterator();
			// 这个map 用于存放后端文本内容，key做文件名，value作为文件值
			Map<String, String> map = new HashMap<String, String>();

			File newFile = null;
			while (it.hasNext()) {
				FileItem fileItem = (FileItem) it.next();
				if (fileItem.isFormField()) {
					String name = fileItem.getFieldName();
					String value = fileItem.getString();
					map.put(name, value);

					// String value=map.get("key");
				} else {
					// 获取前台输入的班级号
					String path = map.get("classId");
					//System.out.println("============" + path);
					// 去数据库查询是否存在该文件夹
					TeamService service = new TeamService();
					Team t = service.getTeamById(path);
					//System.out.println("===========" + t);
					// 把缓存里的数据写到目标路径下
					if (fileItem.getName() != null && fileItem.getSize() != 0 && t != null) {
						//System.out.println("我能进来！");
						File fullFile = new File(fileItem.getName());

						newFile = new File("D:/myfile/" + path + "/" + fullFile.getName());
						MyFile myfile = new MyFile(null, fileItem.getName(), fileItem.getSize(), null,
								"D:/myfile/" + path + "/" + fullFile.getName(),path);
						//System.out.println("myfile:" + myfile);
						// 去数据库存储
						FileService service2 = new FileService();
						int add = service2.add(myfile);
						if (add > 0) {
							msg="上传成功！";
						} else {
							msg = "上传失败！请重新操作！";
						}
						request.setAttribute("msg", msg);
						request.getRequestDispatcher("/msg2.jsp").forward(request, response);
						// 将缓存文件放在我们需要的文件夹下边
						// System.out.println("文件的存放位置"+newFile);
						msg = "";
						try {
							fileItem.write(newFile);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						msg = "文件没有选择 或 文件内容为空";
						request.setAttribute("msg", msg);
						request.getRequestDispatcher("/msg2.jsp").forward(request, response);
						// System.out.println("文件没有选择 或 文件内容为空");
					}
				}
			}

			/*
			 * //打印Map System.out.println("打印map:"+map);
			 */
		}

	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateUserPwd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 从session里获取用户名
		Teacher user = (Teacher) request.getSession().getAttribute("teacher");
		String username = user.getTeachername();
		// 获取前台输入的新密码，确认是否符合修改规则
		String pwd1 = request.getParameter("password1");
		// System.out.println("=====pwd1="+pwd1);
		String pwd2 = request.getParameter("password2");
		// System.out.println("=====pwd2="+pwd2);
		String msg = "";
		TeacherService service = new TeacherService();
		if (pwd1 == null || pwd1.length() < 8 || pwd1 == "" || !pwd1.equals(pwd2)) {
			/*
			 * System.out.println(pwd1=null);
			 * System.out.println(pwd1.length()<8);
			 * System.out.println(pwd1=="");
			 * System.out.println(!pwd1.equals(pwd2));
			 */
			msg = "您输入的密码不符合条件，请根据输入框后提示内容操作。";
		} else {
			// 首先进行md5加密
			String md5 = Myutils.md5(pwd2);
			// 去数据库修改密码
			int i = service.updateUserPwd(username, md5);
			if (i < 1) {
				msg = "修改失败！";
			} else {
				msg = "修改成功！";
			}
		}
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/msg2.jsp").forward(request, response);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateUserInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String keyword = request.getParameter("keyword") == null ? "" :
		// request.getParameter("keyword");
		Teacher user = (Teacher) request.getSession().getAttribute("teacher");
		String username = user.getTeachername();
		String email2 = user.getEmail();
		String sex2 = user.getSex();
		String birthday2 = user.getBirthday();
		String phoneNum2 = user.getPhoneNum();
		Teacher u = new Teacher();
		// 获取用户修改后的email
		String email = request.getParameter("email") == "" ? email2 : request.getParameter("email");
		// 测试
		// System.out.println("=========email:"+email);
		// 获取用户修改过的性别
		String sex = request.getParameter("optionsRadios") == null ? sex2 : request.getParameter("optionsRadios");
		// 获取用户修改后的生日
		String birthday = request.getParameter("nowdate") == null ? birthday2 : request.getParameter("nowdate");
		// 测试
		// System.out.println("=============birthday:"+birthday);
		// 获取用户修改后的电话号码
		String phoneNum = request.getParameter("phoneNum") == "" ? phoneNum2 : request.getParameter("phoneNum");

		String msg = null;
		u.setTeachername(username);
		u.setEmail(email);
		u.setSex(sex);
		u.setBirthday(birthday);
		u.setPhoneNum(phoneNum);

		TeacherService service = new TeacherService();
		// 去数据库修改用户信息
		int i = service.updateUserInfo(u);

		if (i < 1) {
			msg = "修改失败！";
		} else {
			msg = "修改成功！";
			// 修改成功之后，把修改后的用户信息更新
			Teacher tea = service.getUserByName(username);
			// 测试 直接给session赋值
			// System.out.println("======修改之后的值："+stu);
			if (tea.getEmail() != null) {
				user.setEmail(tea.getEmail());
			}
			if (tea.getPhoneNum() != null) {
				user.setPhoneNum(tea.getPhoneNum());
			}
			if (tea.getSex() != null) {
				user.setSex(tea.getSex());
			}
			if (tea.getBirthday() != null) {
				user.setBirthday(tea.getBirthday());
			}
		}
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/msg2.jsp").forward(request, response);
	}

	/**
	 * 用户登陆
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取全部帖子信息
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		TopicService service = new TopicService();
		//查公告栏
		MessAgeService messService = new MessAgeService();
		Message message = messService.getMessage();
		TopicCreteria topicCreteria = service.getTopicByqualified2(null, "1", "id asc");
		// 无条件分页
		List<Topic> page = service.getPage2(topicCreteria);
		// 从前端获取用户登陆信息
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		// 给密码MD5加密
		password = Myutils.md5(password);
		TeacherService us = new TeacherService();
		// 去数据库查找是否存在该用户
		Teacher u = us.getT(name, password);
		if (u != null) {
			Integer state = u.getState();
			if(state.equals(0)){
				request.getSession().setAttribute("teacher", u);
				request.setAttribute("topics", page);
				request.setAttribute("message", message);
				request.setAttribute("topicCreteria", topicCreteria);
				request.getRequestDispatcher("/teachers/listAll2.jsp").forward(request, response);
			}else{
				response.sendRedirect(request.getContextPath() + "/logFail2.jsp");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/logFail2.jsp");
		}
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
			TeacherService us = new TeacherService();
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
		// 获取全部帖子信息
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		TopicService service = new TopicService();
		TopicCreteria topicCreteria = service.getTopicByqualified2(null, "1", "id asc");
		// 无条件分页
		List<Topic> page = service.getPage2(topicCreteria);
		TeacherService us = new TeacherService();
		String regname = request.getParameter("regname");
		if (regname == null || regname == "") {
			response.sendRedirect(request.getContextPath() + "/regFail2.jsp");
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
					response.sendRedirect(request.getContextPath() + "/regFail2.jsp");
				} else {
					Teacher t = (Teacher) us.getT(regname, pwd);
					// System.out.println(t);
					// 给用户绑定session
					request.getSession().setAttribute("teacher", t);
					request.setAttribute("topics", page);
					request.setAttribute("topicCreteria", topicCreteria);
					request.getRequestDispatcher("/teachers/listAll2.jsp").forward(request, response);
					//response.sendRedirect(request.getContextPath() + "/success2.jsp");
				}

			} else {
				response.sendRedirect(request.getContextPath() + "/tearegFail.jsp");

			}
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
		request.getSession().removeAttribute("teacher");
		response.sendRedirect(request.getContextPath() + "/success2.jsp");
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
