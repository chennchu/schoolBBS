package org.me.user.service;

import java.util.ArrayList;
import java.util.List;

import org.me.user.dao.ReplyDao;
import org.me.user.dao.TopicDao;
import org.me.user.entity.Topic;
import org.me.user.entity.TopicCreteria;

public class TopicService {

	/**
	 * 向数据库添加帖子信息
	 * @param t
	 * @return
	 */
	public int add(Topic t) {
		TopicDao dao = new TopicDao();
		int i = dao.add(t);
		return i;
	}

	/**
	 * 查询所有帖子题目
	 * @return
	 */
	public List<Topic> listAll() {
		List<Topic> topics = new ArrayList<Topic>();
		TopicDao dao = new TopicDao();
		topics = dao.getAll();
		return topics;
	}

	/**
	 * 通过id获取该帖子内容
	 * @param id
	 * @return
	 */
	public Topic listbyId(String id) {
		TopicDao dao = new TopicDao();
		int iid = Integer.parseInt(id);
		Topic t = dao.getT(iid);
		return t;
	}

	/**
	 * 有条件的查询所有用户需要的信息
	 * @param keyword
	 * @param pageNo
	 * @param orderby
	 * @return
	 */
	public TopicCreteria getTopicByqualified(String keyword, String pageNo, String orderby) {
		TopicDao dao = new TopicDao();
		//获取全部用户信息
		TopicCreteria topicCreteria = new TopicCreteria(Integer.parseInt(pageNo),orderby,keyword);
		int total = dao.getTopicByqualified(keyword);
		topicCreteria.setTotal(total);
		//计算总页数
		if(total%11==0){
			topicCreteria.setEnd(total/11);
		}else{
			topicCreteria.setEnd(total/11+1);
		}
		return topicCreteria;
	}
	/**
	 * 后台有条件的查询所有用户需要的信息
	 * @param keyword
	 * @param pageNo
	 * @param orderby
	 * @return
	 */
	public TopicCreteria admgetTopicByqualified(String keyword, String pageNo, String orderby) {
		TopicDao dao = new TopicDao();
		//获取全部用户信息
		TopicCreteria topicCreteria = new TopicCreteria(Integer.parseInt(pageNo),orderby,keyword);
		int total = dao.getTopicByqualified(keyword);
		topicCreteria.setTotal(total);
		//计算总页数
		if(total%11==0){
			topicCreteria.setEnd(total/11);
		}else{
			topicCreteria.setEnd(total/11+1);
		}
		return topicCreteria;
	}
	/**
	 * 无条件分页查找全部帖子信息
	 * @param object
	 * @param i
	 * @param string
	 */
	public TopicCreteria getTopicByqualified2(String keyword, String pageNo, String orderby) {
		TopicDao dao = new TopicDao();
		TopicCreteria topicCreteria = new TopicCreteria(Integer.parseInt(pageNo),orderby,keyword);
		int total = dao.getAllCount();
		topicCreteria.setTotal(total);
		//计算总页数
				if(total%11==0){
					topicCreteria.setEnd(total/11);
				}else{
					topicCreteria.setEnd(total/11+1);
				}
				return topicCreteria;
		
	}

	
	
	/**
	 * 后台分页
	 * @param topicCreteria
	 * @return
	 */
	public List<Topic> admgetPage(TopicCreteria topicCreteria) {
		TopicDao dao = new TopicDao();
		List<Topic> page = dao.admgetPage(topicCreteria);
		return page;
	}
	/**
	 * 前台分页
	 * @param topicCreteria
	 * @return
	 */
	public List<Topic> getPage(TopicCreteria topicCreteria) {
		TopicDao dao = new TopicDao();
		List<Topic> page = dao.getPage(topicCreteria);
		return page;
	}
	/**
	 * 前台无条件分页
	 * @param topicCreteria
	 * @return
	 */
	public List<Topic> getPage2(TopicCreteria topicCreteria) {
		TopicDao dao = new TopicDao();
		List<Topic> page = dao.getPage2(topicCreteria);
		return page;
	}

	/**
	 * 审核帖子
	 * @param topicid
	 */
	public int updateAduit(String topicid) {
		TopicDao dao = new TopicDao();
		int i = dao.updateAduit(topicid);
		return i;
		
	}

	/**
	 * 后台无条件分页查询总数量
	 * @param keyword
	 * @param pageNo
	 * @param orderby
	 * @return
	 */
	public TopicCreteria admgetTopicByqualified2(String keyword, String pageNo, String orderby) {
		TopicDao dao = new TopicDao();
		TopicCreteria topicCreteria = new TopicCreteria(Integer.parseInt(pageNo),orderby,keyword);
		int total = dao.admgetAllCount();
		topicCreteria.setTotal(total);
		//计算总页数
				if(total%11==0){
					topicCreteria.setEnd(total/11);
				}else{
					topicCreteria.setEnd(total/11+1);
				}
				return topicCreteria;
	}

	/**
	 * 后台无条件分页查询帖子
	 * @param topicCreteria
	 * @return
	 */
	public List<Topic> admgetPage2(TopicCreteria topicCreteria) {
		TopicDao dao = new TopicDao();
		List<Topic> page = dao.admgetPage2(topicCreteria);
		return page;
	}

	/**
	 * 删帖
	 * @param topicid
	 * @return
	 */
	public int deleteById(String topicid) {
		//删帖子
		TopicDao dao = new TopicDao();
		int i = dao.delete(Integer.parseInt(topicid));
		return i;
	}


}
