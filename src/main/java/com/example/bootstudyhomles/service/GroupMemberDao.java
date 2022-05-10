package com.example.bootstudyhomles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.example.bootstudyhomles.model.GroupMember;
import com.example.bootstudyhomles.model.group.GroupInList;

@Component
public class GroupMemberDao {

	private static final String NS = "groupmember.";
	private Map<String, Object> map = new HashMap<>();

	SqlSession sqlSession;

	public GroupMemberDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int groupCount(String id) {
		try {
			return sqlSession.selectOne(NS + "groupCount", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int groupInsert(GroupMember sm, int represent) {
		try {
			map.clear();
			map.put("board_num", sm.getBoardnum());
			map.put("nickname", sm.getNickname());
			map.put("represent", represent);
			return sqlSession.insert(NS + "groupInsert", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<GroupInList> groupInList(String nickname) {
		return sqlSession.selectList(NS + "groupInList", nickname);
	}

	public List<GroupMember> groupListByBoardnum(String id) {
		return sqlSession.selectList(NS + "groupListByBoardnum", id);
	}

	public int groupDelete(int boardnum, String nickname) {
		try {
			map.clear();
			map.put("boardnum", boardnum);
			map.put("nickname", nickname);
			return sqlSession.delete(NS + "groupDelete", map);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return 0;
	}

	public int isMemberInGroup(String boardid, String memid) {
 
		try {
			map.clear();
			map.put("boardnum", boardid);
			map.put("nickname", memid);
			return sqlSession.selectOne(NS + "isMemberInGroup", map);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return 0;
	}

}
