package org.me.user.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.me.base.dao.BaseDao;
import org.me.base.dao.Dao;
import org.me.base.user.util.Myutils;
import org.me.user.entity.Reply;

public class ReplyDao extends BaseDao<Reply> implements Dao<Reply>{

	@Override
	public Reply getT(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reply> getAll(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Reply t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int t) {
		Connection conn = null;
		int i = 0;
		try {
			 conn = Myutils.ds.getConnection();
			String sql = "delete from replys where id=?";
			i = Myutils.qr.update(conn, sql, t);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}
	
	/**
	 * 通过帖子id获取该帖子的全部回复
	 * @param topicid
	 * @return
	 */
	public List<Reply> getAll(int topicid) {
		List<Reply> replys = new ArrayList<Reply>();
		String sql = "select * from replys where topicid=?";
		replys = this.getAll(sql, topicid);
		return replys;
	}

	/**
	 * 添加评论
	 */
	@Override
	public int add(Reply t) {
		Connection conn = null;
		int i = 0;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "insert into replys value(?,?,?,?,?)";
			i = Myutils.qr.update(conn, sql,null,t.getTopicId(),t.getUserName(),t.getAvatar(),t.getContent());
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int insert(Reply t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
