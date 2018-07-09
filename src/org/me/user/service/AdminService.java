package org.me.user.service;

import org.me.user.dao.AdminDao;
import org.me.user.dao.UserDao;
import org.me.user.entity.Admin;
import org.me.user.entity.Teacher;
import org.me.user.entity.User;

public class AdminService {

	/**
	 * 去数据库查询是否存在该管理员
	 * @param name
	 * @param password
	 * @return
	 */
	public Admin getT(String name, String password) {
		AdminDao dao = new AdminDao();
		Admin adm = dao.getUserByNameAndPwd(name, password);
		return adm;
	}

	/**
	 * 验证用户名是否存在 verityName
	 * @param name
	 * @return
	 */
	public boolean verifyName(String name) {
		AdminDao dao = new AdminDao();
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
		Admin u = new Admin(0,regname, pwd);
		AdminDao dao = new AdminDao();
		int result = dao.add(u);
		
		return result;
	}

	

	
}
