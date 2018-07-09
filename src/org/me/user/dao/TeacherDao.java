package org.me.user.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.me.base.dao.BaseDao;
import org.me.base.dao.Dao;
import org.me.base.user.util.Myutils;
import org.me.user.entity.Teacher;
import org.me.user.entity.TeacherCreteria;
import org.me.user.entity.User;
import org.me.user.entity.UserCreteria;

public class TeacherDao extends BaseDao<Teacher> implements Dao<Teacher>{
	/**
	 * 去数据库里查询是否存在这个用户
	 * @param name
	 * @return
	 */
	public String getName(String name){
		try {
			String sql = "select teachername from teachers where teachername=?";
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
	public Teacher getT(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Teacher> getAll(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Teacher t) {
		Connection conn = null;
		int i = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "update teachers set avatar=? where teachername=?";
			i = Myutils.qr.update(conn, sql, t.getAvatar(), t.getTeachername());
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int delete(int t) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 添加用户
	 */
	@Override
	public int add(Teacher t) {
		Connection conn = null;
		int result = 0;
		try {
			//String getId = "";
			conn = Myutils.ds.getConnection();
			String sql = "insert into teachers values(?,?,?,?,?,?,?,?,?,?,?)";
			result = Myutils.qr.update(conn,sql,null,t.getTeachername(),t.getPassword(),t.getEmail(),t.getPhoneNum(),t.getCredit(),t.getAvatar(),t.getType(),t.getSex(),t.getBirthday(),t.getState());
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(Teacher t) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 通过用户名和密码获取用户信息
	 * @param name
	 * @param password
	 * @return
	 */
	public Teacher getUserByNameAndPwd(String name,String password){
		String sql = "select id,teachername,password,email,phoneNum,avatar,type,credit,sex,birthday,state from teachers where teachername=? and password=?";
		Teacher t = this.getT(sql,name,password);
		return t;
	}

	/**
	 * 修改教师信息
	 * @param u
	 * @return
	 */
	public int updateInfo(Teacher u) {
		Connection conn = null;
		int i = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "update teachers set email=?,phoneNum=?,sex=?,birthday=? where teachername=?";
			i = Myutils.qr.update(conn, sql, u.getEmail(),u.getPhoneNum(),u.getSex(),u.getBirthday(),u.getTeachername());
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
	}

	/**
	 * 通过用户名查找教师信息
	 * @param username
	 * @return
	 */
	public Teacher getStu(String username) {
		String sql = "select teachername,email,phoneNum,sex,birthday,state from teachers where teachername=?";
		Teacher t = this.getT(sql, username);
		
		return t;
	}

	/**
	 * 修改教师密码
	 * @param username
	 * @param md5
	 * @return
	 */
	public int updateUserPwd(String username, String md5) {
		Connection conn = null;
		int i = 0;
		try {
			 conn = Myutils.ds.getConnection();
			 String sql="update teachers set password=? where teachername=?";
			 i = Myutils.qr.update(conn, sql, md5,username);
			 conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 有条件分页
	 * 
	 * @param keyword
	 * @return
	 */
	public int getTeacherByqualified(String keyword) {
		Connection conn = null;
		String upkeyword = "'%" + keyword + "%'";
		int total = 0;
		// 查询所有含有关键字的帖子数量
		try {
			conn = Myutils.ds.getConnection();
			String sql = "select count(id) from teachers where teachername like '%" + keyword + "%'";
			Object obj = Myutils.qr.query(conn, sql, new ScalarHandler());
			Number n = (Number) obj;
			total = n.intValue();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 无条件 查找用户信息
	 * 
	 * @return
	 */
	public int getAllCount() {
		Connection conn = null;
		int total = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "select count(id) from teachers";
			Object obj = Myutils.qr.query(conn, sql, new ScalarHandler());
			Number n = (Number) obj;
			total = n.intValue();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 有条件分页
	 * 
	 * @param userCreteria
	 * @return
	 */
	public List<Teacher> getPage(TeacherCreteria userCreteria) {
		List<Teacher> users = new ArrayList<Teacher>();
		String sql = "select id,teachername,email,phoneNum,credit,sex,birthday,state"
				+ " from teachers where teachername like '%" + userCreteria.getKeyword() + "%' order by "
				+ userCreteria.getOrderby() + " limit " + userCreteria.getFrom() + "," + userCreteria.getPageSize();
		users = this.getAll(sql);
		return users;
	}

	/**
	 * 无条件分页
	 * 
	 * @param topicCreteria
	 * @return
	 */
	public List<Teacher> getPage2(TeacherCreteria userCreteria) {
		List<Teacher> users = new ArrayList<Teacher>();
		String sql = "select id,teachername,email,phoneNum,credit,sex,birthday,state from teachers limit ?,?";
		users = this.getAll(sql, userCreteria.getFrom(), userCreteria.getPageSize());
		return users;
	}

	/**
	 * 修改为禁用
	 * 
	 * @param parseInt
	 */
	public void updateState(int id) {
		Connection conn = null;
		int i = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "update teachers set state=1 where id=?";
			i = Myutils.qr.update(conn, sql, id);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 修改为正常
	 * 
	 * @param parseInt
	 */
	public void backToState(int id) {
		Connection conn = null;
		int i = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "update teachers set state=0 where id=?";
			i = Myutils.qr.update(conn, sql, id);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 修改为已注销
	 * 
	 */
	public void deleteState(int id) {
		Connection conn = null;
		int i = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "update teachers set state=3 where id=?";
			i = Myutils.qr.update(conn, sql, id);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	/**
	 * 管理员重置密码
	 * @param parseInt
	 */
	public void toUpdateTeacherPassword(int id) {
		Connection conn = null;
		String pwd = "11111111";
		String md5 = Myutils.md5(pwd);
		int i = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "update teachers set password=? where id=?";
			i = Myutils.qr.update(conn, sql, md5, id);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
		
	


}
