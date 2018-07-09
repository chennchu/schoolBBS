package org.me.user.service;

import java.util.List;

import org.me.user.dao.ReplyDao;
import org.me.user.entity.Reply;

public class ReplyService {

	/**
	 * 添加回复
	 * @param reply
	 * @return
	 */
	public int addReply(Reply reply) {
		ReplyDao dao = new ReplyDao();
		int i = dao.add(reply);
		return i;
	}

	/**
	 * 获取指定帖子的全部回复
	 * @param topicid
	 * @return
	 */
	public List<Reply> listAll(String topicid) {
		ReplyDao dao = new ReplyDao();
		List<Reply> replys = dao.getAll(Integer.parseInt(topicid));
		return replys;
	}

	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	public int deleteById(Integer id) {
		ReplyDao dao = new ReplyDao();
		int i = dao.delete(id);
		return i;
	}

	

}
