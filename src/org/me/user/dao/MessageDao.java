package org.me.user.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.me.base.dao.BaseDao;
import org.me.base.dao.Dao;
import org.me.base.user.util.Myutils;
import org.me.user.entity.Message;

public class MessageDao extends BaseDao<Message>implements Dao<Message>{

	@Override
	public Message getT(int id) {
		String sql = "select content from message where id = ?";
		Message t = this.getT(sql,id);
		return t;
	}

	@Override
	public List<Message> getAll(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 更新公告栏内容
	 */
	@Override
	public int update(Message t) {
		int i = 0;
		Connection conn = null;
		try {
			conn = Myutils.ds.getConnection();
			String sql = "update message set content=? where id=?";
			i = Myutils.qr.update(conn, sql, t.getContent(),t.getId());
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

	@Override
	public int add(Message t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Message t) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
