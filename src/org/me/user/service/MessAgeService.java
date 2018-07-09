package org.me.user.service;

import org.me.user.dao.MessageDao;
import org.me.user.entity.Message;

public class MessAgeService {

	/**
	 * 更新数据库里的公告栏内容
	 * @param message
	 */
	public int updateMessage(Message message) {
		MessageDao dao = new MessageDao();
		int i = dao.update(message);
		return i;
	}

	/**
	 * 获取公告栏内容
	 * @return
	 */
	public Message getMessage() {
		MessageDao dao = new MessageDao();
		Message message = dao.getT(1);
		return message;
	}

}
