package org.me.user.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.me.base.dao.BaseDao;
import org.me.base.dao.Dao;
import org.me.base.user.util.Myutils;
import org.me.user.entity.MyFile;
import org.me.user.entity.Team;
import org.me.user.entity.TeamCreteria;
import org.me.user.entity.Topic;
import org.me.user.entity.TopicCreteria;

public class TeamDao extends BaseDao<Team> implements Dao<Team>{

	/**
	 * 添加文件夹名到数据库
	 * @param classId
	 * @return
	 */
	public int addTeam(String classId) {
		Connection conn = null;
		try {
			//String getId = "";
			conn = Myutils.ds.getConnection();
			String sql = "insert into team values(?,?)";
			int result = Myutils.qr.update(conn,sql,null,classId);
			conn.close();
			return result; 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Team getT(int id) {
		// TODO Auto-generated method stub
		return null;
	}
		
	/**
	 * 获取所有文件夹名
	 */
	@Override
	public List<Team> getAll(Object... args) {
		List<Team> teams = new ArrayList<Team>();
		String sql = "selest * from team";
		teams = this.getAll(sql);
		return teams;
	}

	@Override
	public int update(Team t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(Team t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Team t) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 通过id删除文件夹名
	 * @param classId
	 * @return
	 */
	public int deleteByClassId(String classId) {
		Connection conn = null;
		int i = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "delete from team where classId=?";
			i = Myutils.qr.update(conn, sql,classId);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}

	/**
	 * 通过classId查询文件夹是否存在
	 * @param classId
	 * @return
	 */
	public Team getTeamById(String classId) {
		Connection conn = null;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "select * from team where classId=?";
			Team t = this.getT(sql, classId);
			conn.close();
			return t;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 获取有条件的文件夹的数量
	 * 
	 * @param keyword
	 * @return
	 */
	public int getTeamByqualified(String keyword) {
		Connection conn = null;
		//String upkeyword = "'%" + keyword + "%'";
		int total = 0;
		// 查询所有含有关键字的帖子数量
		try {
			conn = Myutils.ds.getConnection();
			String sql = "select count(id) from team where classId like '%" + keyword + "%'";
			Object obj = Myutils.qr.query(conn, sql,new ScalarHandler<Object>());
			Number n = (Number) obj;
			total = n.intValue();
			conn.close();
			//System.out.println("有条件分页帖子总数" + total);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 无条件分页获取帖子总数量
	 * 
	 * @return
	 */
	public int getAllCount() {
		Connection conn = null;
		int total = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "select count(id) from team";

			Object obj = Myutils.qr.query(conn, sql, new ScalarHandler<Object>());
			Number n = (Number) obj;
			total = n.intValue();
			conn.close();
			//System.out.println("无条件分页帖子总数" + total);
			/*
			 * ScalarHandler<BigDecimal> sh = new ScalarHandler<BigDecimal>();
			 * BigDecimal bdtotal = Myutils.qr.query(conn, sql, sh);
			 * 
			 * total = bdtotal.intValue();
			 */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 有条件的分页
	 * 
	 * @param topicCreteria
	 * @return
	 */
	public List<Team> getPage(TeamCreteria teamCreteria) {
		List<Team> teams = new ArrayList<Team>();
		String sql = "select classId from team where classId like '%" + teamCreteria.getKeyword()
				+ "%' order by " + teamCreteria.getOrderby() + " limit " + teamCreteria.getFrom() + ","
				+ teamCreteria.getPageSize();
		teams = this.getAll(sql);
		return teams;
	}

	/**
	 * 无条件的分页
	 * 
	 * @param topicCreteria
	 * @return
	 */
	public List<Team> getPage2(TeamCreteria teamCreteria) {
		List<Team> teams = new ArrayList<Team>();
		String sql = "select classId from team limit ?,?";
		teams = this.getAll(sql, teamCreteria.getFrom(), teamCreteria.getPageSize());
		return teams;
	}


	

	
}
