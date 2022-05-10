package com.example.bootstudyhomles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.bootstudyhomles.model.GroupBoard;

@Repository 
public class GroupBoardDao {

	private static final String NS = "groupBoard.";
	private Map<String, Object> map = new HashMap<>();
	SqlSession sqlSession;

	public GroupBoardDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int groupBoardCount(String boardnum, String boardid) {
		try {
			map.clear();
			map.put("boardnum", boardnum);
			map.put("boardid", boardid);
			return sqlSession.selectOne(NS + "groupBoardCount", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<GroupBoard> groupBoardList(int pageInt, int limit, int boardcount, String boardid, String s_board_num) {

		map.clear();
		map.put("boardid", boardid);
		map.put("s_board_num", s_board_num);
		map.put("start", (pageInt - 1) * limit + 1);
		map.put("end", pageInt * limit);

		return sqlSession.selectList(NS + "groupBoardList", map);

	}

	public int groupInsertBoard(GroupBoard gbd) {

		try {
			return sqlSession.insert(NS + "groupInsertBoard", gbd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public GroupBoard groupBoardOne(String s_board_num, String boardid) {

		map.clear();
		map.put("boardid", boardid);
		map.put("s_board_num", s_board_num);

		return sqlSession.selectOne(NS + "groupBoardOne", map);

	}

	public GroupBoard groupBoardOne2(int board_num) {

		return sqlSession.selectOne(NS + "groupBoardOne2", board_num);

	}

	/* 조회수 증가 */
	public void groupReadCountUp(int board_num) {
		try {
			sqlSession.update(NS + "groupReadCountUp", board_num);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int groupBoardUpdate(GroupBoard gb) {

		try {
			return sqlSession.update(NS + "groupBoardUpdate", gb);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;

	}
}
