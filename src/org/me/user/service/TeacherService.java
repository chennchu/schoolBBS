package org.me.user.service;

import java.util.List;

import org.me.user.dao.TeacherDao;
import org.me.user.dao.UserDao;
import org.me.user.entity.Teacher;
import org.me.user.entity.TeacherCreteria;
import org.me.user.entity.User;
import org.me.user.entity.UserCreteria;

public class TeacherService {

	/**
	 * 验证用户名是否存在 verityName
	 * @param name
	 * @return
	 */
	public boolean verifyName(String name) {
		TeacherDao dao = new TeacherDao();
		String n = dao.getName(name);
		if(n==null){
			return false;
		}else{
			return true;
  		}
	}

	/**
	 * 注册用户
	 * @param regname
	 * @param pwd
	 * @return
	 */
	public int regist(String regname, String pwd) {
		//先增加用户
		Teacher u = new Teacher(regname, pwd, null, null, null, "avatar/timg.jpg", null,"男",null,0);
		TeacherDao dao = new TeacherDao();
		int result = dao.add(u);
		
		return result;
	}

	/**
	 * 从数据库获取用户信息
	 * 
	 * @param name
	 * @param password
	 */
	public Teacher getT(String regname, String pwd) {
		TeacherDao us = new TeacherDao();
		Teacher u = us.getUserByNameAndPwd(regname, pwd);
		return u;
	}

	/**
	 * 修改教师信息
	 * @param u
	 * @return
	 */
	public int updateUserInfo(Teacher u) {
		TeacherDao dao = new TeacherDao();
		int i = dao.updateInfo(u);
		return i;
	}

	/**
	 * 通过用户名查找教师信息
	 * @param username
	 * @return
	 */
	public Teacher getUserByName(String username) {
		TeacherDao dao = new TeacherDao();
		Teacher u = dao.getStu(username);
		return u;
	}

	/**
	 * 修改教师密码
	 * @param username
	 * @param md5
	 * @return
	 */
	public int updateUserPwd(String username, String md5) {
		TeacherDao dao = new TeacherDao();
		int i = dao.updateUserPwd(username,md5);
		return i;
	}

	/**
	 * 有条件的分页查询
	 * @param keyword
	 * @param pageNo
	 * @param orderby
	 * @return
	 */
	public TeacherCreteria getTeacherByqualified(String keyword, String pageNo, String orderby) {
		TeacherDao dao = new TeacherDao();
		//获取全部用户信息
		TeacherCreteria teacherCreteria = new TeacherCreteria(Integer.parseInt(pageNo),orderby,keyword);
		int total = dao.getTeacherByqualified(keyword);
		teacherCreteria.setTotal(total);
		//计算总页数
		if(total%11==0){
			teacherCreteria.setEnd(total/11);
		}else{
			teacherCreteria.setEnd(total/11+1);
		}
		return teacherCreteria;
	}

	/**
	 * 无条件分页查找全部帖子信息
	 * @param object
	 * @param i
	 * @param string
	 */
	public TeacherCreteria getTeacherByqualified2(String keyword, String pageNo, String orderby) {
		TeacherDao dao = new TeacherDao();
		TeacherCreteria teacherCreteria = new TeacherCreteria(Integer.parseInt(pageNo),orderby,keyword);
		int total = dao.getAllCount();
		teacherCreteria.setTotal(total);
		//计算总页数
				if(total%11==0){
					teacherCreteria.setEnd(total/11);
				}else{
					teacherCreteria.setEnd(total/11+1);
				}
				return teacherCreteria;
		
	}

	
	/**
	 * 有条件的分页
	 * @param userCreteria
	 * @return
	 */
	public List<Teacher> getPage(TeacherCreteria userCreteria) {
		TeacherDao dao = new TeacherDao();
		List<Teacher> page = dao.getPage(userCreteria);
		return page;
	}
	
	/**
	 * 无条件分页
	 * @param topicCreteria
	 * @return
	 */
	public List<Teacher> getPage2(TeacherCreteria userCreteria) {
		TeacherDao dao = new TeacherDao();
		List<Teacher> page = dao.getPage2(userCreteria);
		return page;
	}

	/**
	 * 修改教师头像
	 * @param user
	 * @return
	 */
	public int addUploadAva(Teacher user) {
		TeacherDao dao = new TeacherDao();
		int i = dao.update(user);
		return i;
	}

	/**
	 * 更改教师用户信息
	 * @param id
	 * @param state
	 */
	public void updateState(String id, String state) {
		//默认0为正常，1为已禁用  state=3表示要注销
				TeacherDao dao = new TeacherDao();
				if(("1").equals(state)){
					//修改为禁用
					dao.updateState(Integer.parseInt(id));
				}else if(("0").equals(state)){
					//修改为正常
					dao.backToState(Integer.parseInt(id));
				}else if(("3").equals(state)){
					//修改为注销
					dao.deleteState(Integer.parseInt(id));
				}
	}

	/**
	 * 重置密码
	 * @param id
	 */
	public void toUpdatUserPassword(String id) {
		TeacherDao dao = new TeacherDao();
		dao.toUpdateTeacherPassword(Integer.parseInt(id));
		
	}
}
