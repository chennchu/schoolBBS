package org.me.user.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.me.base.dao.BaseDao;
import org.me.base.dao.Dao;
import org.me.base.user.util.Myutils;
import org.me.user.entity.User;
import org.me.user.entity.UserCreteria;

public class UserDao extends BaseDao<User> implements Dao<User> {

	/**
	 * 去数据库里查询是否存在这个用户
	 * 
	 * @param name
	 * @return
	 */
	public String getName(String name) {
		try {
			String sql = "select username from users where username=?";
			Object e = this.getE(sql, name);
			String n = (String) e;
			return n;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getT(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 修改密码
	 * 
	 * @param username
	 * @return
	 */
	public int updateUserPwd(String username, String md5) {
		Connection conn = null;
		int i = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "update users set password=? where username=?";
			i = Myutils.qr.update(conn, sql, md5, username);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param u
	 * @return
	 */
	public int updateInfo(User u) {
		Connection conn = null;
		int i = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "update users set email=?,phoneNum=?,sex=?,birthday=? where username=?";
			i = Myutils.qr.update(conn, sql, u.getEmail(), u.getPhoneNum(), u.getSex(), u.getBirthday(),
					u.getUsername());
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 更新用户头像
	 */
	@Override
	public int update(User t) {
		Connection conn = null;
		int i = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "update users set avatar=? where username=?";
			i = Myutils.qr.update(conn, sql, t.getAvatar(), t.getUsername());
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
	 * 添加一个用户
	 */
	@Override
	public int add(User t) {
		Connection conn = null;
		try {
			// String getId = "";
			conn = Myutils.ds.getConnection();
			String sql = "insert into users values(?,?,?,?,?,?,?,?,?,?,?)";
			int result = Myutils.qr.update(conn, sql, null, t.getUsername(), t.getPassword(), t.getEmail(),
					t.getPhoneNum(), t.getCredit(), t.getAvatar(), t.getType(), t.getSex(), t.getBirthday(),
					t.getState());
			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insert(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 通过用户名和密码获取用户信息
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public User getUserByNameAndPwd(String name, String password) {
		String sql = "select id,username,password,email,phoneNum,avatar,type,credit,sex,birthday,state from users where username=? and password=?";
		User user = this.getT(sql, name, password);
		return user;
	}

	/**
	 * 通过用户名查找用户信息
	 * 
	 * @param username
	 * @return
	 */
	public User getStu(String username) {
		String sql = "select username,email,phoneNum,sex,birthday,state from users where username=?";
		User t = this.getT(sql, username);

		return t;
	}

	/**
	 * 有条件分页
	 * 
	 * @param keyword
	 * @return
	 */
	public int getUserByqualified(String keyword) {
		Connection conn = null;
		String upkeyword = "'%" + keyword + "%'";
		int total = 0;
		// 查询所有含有关键字的帖子数量
		try {
			conn = Myutils.ds.getConnection();
			String sql = "select count(id) from users where username like '%" + keyword + "%'";
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
			String sql = "select count(id) from users";
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
	public List<User> getPage(UserCreteria userCreteria) {
		List<User> users = new ArrayList<User>();
		String sql = "select id,username,email,phoneNum,credit,sex,birthday,state"
				+ " from users where username like '%" + userCreteria.getKeyword() + "%' order by "
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
	public List<User> getPage2(UserCreteria userCreteria) {
		List<User> users = new ArrayList<User>();
		String sql = "select id,username,email,phoneNum,credit,sex,birthday,state from users limit ?,?";
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
			String sql = "update users set state=1 where id=?";
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
			String sql = "update users set state=0 where id=?";
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
	 * @param parseInt
	 */
	public void deleteState(int id) {
		Connection conn = null;
		int i = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "update users set state=3 where id=?";
			i = Myutils.qr.update(conn, sql, id);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 重置密码
	 * 
	 * @param parseInt
	 */
	public void toUpdatUserPassword(int id) {
		Connection conn = null;
		String pwd = "11111111";
		String md5 = Myutils.md5(pwd);
		int i = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "update users set password=? where id=?";
			i = Myutils.qr.update(conn, sql, md5, id);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
