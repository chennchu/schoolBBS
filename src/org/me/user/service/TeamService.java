package org.me.user.service;

import java.util.List;

import org.me.user.dao.TeamDao;
import org.me.user.dao.TopicDao;
import org.me.user.entity.Team;
import org.me.user.entity.TeamCreteria;
import org.me.user.entity.Topic;
import org.me.user.entity.TopicCreteria;

public class TeamService {

	/**
	 * 添加文件夹名进数据库
	 * @param classId
	 * @return
	 */
	public int addTeam(String classId) {
		TeamDao dao = new TeamDao();
		int i = dao.addTeam(classId);
		return i;
	}
	

	/**
	 * 查询全部文件夹名
	 * @return
	 */
	public List<Team> getAll(){
		TeamDao dao = new TeamDao();
		List<Team> teams = dao.getAll();
		return teams;
	}
	
	/**
	 * 通过id删除文件夹记录
	 * @param classId
	 * @return
	 */
	public int deleteByClassId(String classId){
		TeamDao dao = new TeamDao();
		int i = dao.deleteByClassId(classId);
		return 0 ;
	}


	/**
	 * 通过id查询文件夹是否存在
	 * @param classId
	 * @return
	 */
	public Team getTeamById(String classId) {
		TeamDao dao = new TeamDao();
		Team t = dao.getTeamById(classId);
		return t;
	}

	/**
	 * 有条件的查询所有用户需要的信息
	 * @param keyword
	 * @param pageNo
	 * @param orderby
	 * @return
	 */
	public TeamCreteria getTeamByqualified(String keyword, String pageNo, String orderby) {
		TeamDao dao = new TeamDao();
		//获取全部用户信息
		TeamCreteria teamCreteria = new TeamCreteria(Integer.parseInt(pageNo),orderby,keyword);
		int total = dao.getTeamByqualified(keyword);
		teamCreteria.setTotal(total);
		//计算总页数
		if(total%11==0){
			teamCreteria.setEnd(total/11);
		}else{
			teamCreteria.setEnd(total/11+1);
		}
		return teamCreteria;
	}

	/**
	 * 无条件获取文件夹
	 * @param object
	 * @param string
	 * @param string2
	 * @return
	 */
	public TeamCreteria getTeamByqualified2(String keyword, String pageNo, String orderby) {
		TeamDao dao = new TeamDao();
		TeamCreteria teamCreteria = new TeamCreteria(Integer.parseInt(pageNo),orderby,keyword);
		int total = dao.getAllCount();
		teamCreteria.setTotal(total);
		//计算总页数
				if(total%11==0){
					teamCreteria.setEnd(total/11);
				}else{
					teamCreteria.setEnd(total/11+1);
				}
				return teamCreteria;
	}


	/**
	 * 无条件 开始分页
	 * @param teamCreteria
	 * @return
	 */
	public List<Team> getPage2(TeamCreteria teamCreteria) {
		TeamDao dao = new TeamDao();
		List<Team> page = dao.getPage2(teamCreteria);
		return page;
	}
	
	/**
	 * 分页
	 * @param teamCreteria
	 * @return
	 */
	public List<Team> getPage(TeamCreteria teamCreteria) {
		TeamDao dao = new TeamDao();
		List<Team> page = dao.getPage(teamCreteria);
		return page;
	}
}
