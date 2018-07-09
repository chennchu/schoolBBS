package org.me.user.service;

import java.util.List;

import org.me.user.dao.UserDao;
import org.me.user.entity.User;
import org.me.user.entity.UserCreteria;

public class UserService {
	/**
	 * 验证用户名是否存在 verityName
	 * @param name
	 * @return
	 */
	public boolean verifyName(String name) {
		UserDao dao = new UserDao();
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
		User u = new User(regname, pwd, null, null, null, "avatar/timg.jpg", null,"男",null,0);
		UserDao dao = new UserDao();
		int result = dao.add(u);
		
		return result;
	}

	/**
	 * 从数据库获取用户信息
	 * 
	 * @param name
	 * @param password
	 */
	public User getT(String regname, String pwd) {
		UserDao us = new UserDao();
		User u = us.getUserByNameAndPwd(regname, pwd);
		return u;
	}

	/**
	 * 去数据库存储头像路径信息
	 * @param str
	 * @return
	 */
	public int addUploadAva(User t) {
		UserDao dao = new UserDao();
		int i = dao.update(t);
		return i;
	}

	/**
	 * 修改用户信息
	 * @param u
	 * @return
	 */
	public int updateUserInfo(User u) {
		UserDao dao = new UserDao();
		int i = dao.updateInfo(u);
		return i;
	}

	/**
	 * 通过用户名查找用户
	 * @param username
	 */
	public User getUserByName(String username) {
		UserDao dao = new UserDao();
		User u = dao.getStu(username);
		return u;
	}

	/**
	 * 修改密码
	 * @param username
	 * @return
	 */
	public int updateUserPwd(String username,String md5) {
		UserDao dao = new UserDao();
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
	public UserCreteria getUserByqualified(String keyword, String pageNo, String orderby) {
		UserDao dao = new UserDao();
		//获取全部用户信息
		UserCreteria userCreteria = new UserCreteria(Integer.parseInt(pageNo),orderby,keyword);
		int total = dao.getUserByqualified(keyword);
		userCreteria.setTotal(total);
		//计算总页数
		if(total%11==0){
			userCreteria.setEnd(total/11);
		}else{
			userCreteria.setEnd(total/11+1);
		}
		return userCreteria;
	}

	/**
	 * 无条件分页查找全部帖子信息
	 * @param object
	 * @param i
	 * @param string
	 */
	public UserCreteria getUserByqualified2(String keyword, String pageNo, String orderby) {
		UserDao dao = new UserDao();
		UserCreteria userCreteria = new UserCreteria(Integer.parseInt(pageNo),orderby,keyword);
		int total = dao.getAllCount();
		userCreteria.setTotal(total);
		//计算总页数
				if(total%11==0){
					userCreteria.setEnd(total/11);
				}else{
					userCreteria.setEnd(total/11+1);
				}
				return userCreteria;
		
	}

	
	/**
	 * 有条件的分页
	 * @param userCreteria
	 * @return
	 */
	public List<User> getPage(UserCreteria userCreteria) {
		UserDao dao = new UserDao();
		List<User> page = dao.getPage(userCreteria);
		return page;
	}
	
	/**
	 * 无条件分页
	 * @param topicCreteria
	 * @return
	 */
	public List<User> getPage2(UserCreteria userCreteria) {
		UserDao dao = new UserDao();
		List<User> page = dao.getPage2(userCreteria);
		return page;
	}

	/**
	 * 修改状态
	 * @param id
	 * @param state
	 */
	public void updateState(String id, String state) {
		//默认0为正常，1为已禁用  state=3表示要注销
		UserDao dao = new UserDao();
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
		UserDao dao = new UserDao();
		dao.toUpdatUserPassword(Integer.parseInt(id));
		
	}

	
}
