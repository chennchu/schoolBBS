package org.me.user.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.me.base.dao.BaseDao;
import org.me.base.dao.Dao;
import org.me.base.user.util.Myutils;
import org.me.user.entity.Topic;
import org.me.user.entity.TopicCreteria;

public class TopicDao extends BaseDao<Topic> implements Dao<Topic> {

	/**
	 * 获取帖子具体内容
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Topic getT(int id) {
		String sql = "select id,userName,title,content from topics where id=?";
		Topic t = this.getT(sql, id);
		return t;
	}

	@Override
	public List<Topic> getAll(Object... args) {
		List<Topic> topics = new ArrayList<Topic>();
		String sql = "select id,userName,title from topics";
		topics = this.getAll(sql);
		return topics;
	}

	@Override
	public int update(Topic t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int t) {
		Connection conn = null;
		int i = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "delete from topics where id = ?";
			i = Myutils.qr.update(conn, sql, t);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 向数据库添加帖子内容
	 */
	@Override
	public int add(Topic t) {
		Connection conn = null;
		int i = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "insert into topics values(?,?,?,?,?)";
			i = Myutils.qr.update(conn, sql, null, t.getUserName(), t.getTitle(), t.getContent(), t.getAudit());
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int insert(Topic t) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 	前台获取有条件的帖子信息的数量
	 * 
	 * @param keyword
	 * @return
	 */
	public int getTopicByqualified(String keyword) {
		Connection conn = null;
		//String upkeyword = "'%" + keyword + "%'";
		int total = 0;
		// 查询所有含有关键字的帖子数量
		try {
			conn = Myutils.ds.getConnection();
			String sql = "select count(id) from topics where audit=1 and title like '%" + keyword + "%'";
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
	 * 	后台获取有条件的帖子信息的数量
	 * 
	 * @param keyword
	 * @return
	 */
	public int admgetTopicByqualified(String keyword) {
		Connection conn = null;
		String upkeyword = "'%" + keyword + "%'";
		int total = 0;
		// 查询所有含有关键字的帖子数量
		try {
			conn = Myutils.ds.getConnection();
			String sql = "select count(id) from topics where title like '%" + keyword + "%'";
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
	 * 前台无条件分页获取帖子总数量
	 * 
	 * @return
	 */
	public int getAllCount() {
		Connection conn = null;
		int total = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "select count(id) from topics where audit=1";

			Object obj = Myutils.qr.query(conn, sql, new ScalarHandler<Object>());
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
	 * 后台无条件分页获取帖子总数量
	 * 
	 * @return
	 */
	public int admgetAllCount() {
		Connection conn = null;
		int total = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "select count(id) from topics";
			
			Object obj = Myutils.qr.query(conn, sql, new ScalarHandler<Object>());
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
	 * 前台有条件的分页
	 * 
	 * @param topicCreteria
	 * @return
	 */
	public List<Topic> getPage(TopicCreteria topicCreteria) {
		List<Topic> topics = new ArrayList<Topic>();
		String sql = "select id,userName,title,audit" + " from topics where audit=1 and title like '%" + topicCreteria.getKeyword()
				+ "%' order by " + topicCreteria.getOrderby() + " limit " + topicCreteria.getFrom() + ","
				+ topicCreteria.getPageSize();
		topics = this.getAll(sql);
		return topics;
	}
	/**
	 * 后台有条件的分页
	 * 
	 * @param topicCreteria
	 * @return
	 */
	public List<Topic> admgetPage(TopicCreteria topicCreteria) {
		List<Topic> topics = new ArrayList<Topic>();
		String sql = "select id,userName,title,audit" + " from topics where title like '%" + topicCreteria.getKeyword()
		+ "%' order by " + topicCreteria.getOrderby() + " limit " + topicCreteria.getFrom() + ","
		+ topicCreteria.getPageSize();
		topics = this.getAll(sql);
		return topics;
	}

	/**
	 * 前台无条件的分页
	 * 
	 * @param topicCreteria
	 * @return
	 */
	public List<Topic> getPage2(TopicCreteria topicCreteria) {
		List<Topic> topics = new ArrayList<Topic>();
		String sql = "select id,userName,title,audit from topics where audit=1 limit ?,?";
		topics = this.getAll(sql, topicCreteria.getFrom(), topicCreteria.getPageSize());
		return topics;
	}
	/**
	 * 后台无条件的分页
	 * 
	 * @param topicCreteria
	 * @return
	 */
	public List<Topic> admgetPage2(TopicCreteria topicCreteria) {
		List<Topic> topics = new ArrayList<Topic>();
		String sql = "select id,userName,title,audit from topics limit ?,?";
		topics = this.getAll(sql, topicCreteria.getFrom(), topicCreteria.getPageSize());
		return topics;
	}

	/**
	 * 审核帖子
	 * @param topicid
	 * @return
	 */
	public int updateAduit(String topicid) {
		Connection conn = null;
		int i = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "update topics set audit=1 where id=?";
			i = Myutils.qr.update(conn, sql, topicid);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/*
	 * public int add(Topic t) { // TODO Auto-generated method stub return 0; }
	 */
}
