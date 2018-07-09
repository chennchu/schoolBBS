package org.me.user.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.me.base.dao.BaseDao;
import org.me.base.dao.Dao;
import org.me.base.user.util.Myutils;
import org.me.user.entity.Admin;


public class AdminDao extends BaseDao<Admin> implements Dao<Admin>{

	/**
	 * 添加文件夹名到数据库
	 * @param classId
	 * @return
	 */
	public int addTeam(String classId) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 通过用户名和密码查询数据库
	 * @param name
	 * @param password
	 * @return
	 */
	public Admin getUserByNameAndPwd(String name, String password) {
		String sql = "select id,adminName,password from admins where adminName=? and password=?";
		Admin t = this.getT(sql,name,password);
		return t;
	}

	/**
	 * 去数据库里查询是否存在这个用户
	 * @param name
	 * @return
	 */
	public String getName(String name){
		try {
			String sql = "select adminName from admins where adminName=?";
			Object e = this.getE(sql, name);
			String n = (String)e;
			return n;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public Admin getT(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> getAll(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Admin t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Admin t) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 添加一个管理员
	 */
	@Override
	public int add(Admin t) {
		Connection conn = null;
		try {
			//String getId = "";
			conn = Myutils.ds.getConnection();
			String sql = "insert into admins values(?,?,?)";
			int result = Myutils.qr.update(conn,sql,0,t.getAdminName(),t.getPassword());
			conn.close();
			return result; 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}



	
}
