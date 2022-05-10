package com.example.bootstudyhomles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.example.bootstudyhomles.model.Notice;

@Component
public class NoticeDao {

	private static final String NS = "notice.";
	private Map<String, Object> map = new HashMap<>();

	SqlSession sqlSession;

	public NoticeDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int noticeNew(String id) {

		return sqlSession.selectOne(NS + "noticeNew", id);

	}

	public List<Notice> noticeGet(String id) {

		return sqlSession.selectList(NS + "noticeGet", id);

	}

	public int noticeRead(String id) {

		try {
			return sqlSession.update(NS + "noticeRead", id);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return 0;
	}

	public int noticeWrite(int boardid, String from, String to, String info) {

		map.clear();
		map.put("boardid", boardid);
		map.put("from", from);
		map.put("to", to);
		map.put("info", info);

		return sqlSession.insert(NS + "noticeWrite", map);

	}
	public int noticeWriteAll(String to, String from, String info, String info2) {

		map.clear();
		map.put("info2", info2);
		map.put("info", info);
		map.put("from", from);
		map.put("to", to);

		return sqlSession.insert(NS + "noticeWriteAll", map);

	}
	public int noticeReportWrite(String info, String from, String to, int board_num) {

		map.clear();
		map.put("info", info);
		map.put("from", from);
		map.put("to", to);
		map.put("board_num", board_num);

		return sqlSession.insert(NS + "noticeReportWrite", map);

	}

	public Notice noticeGetByNoticeNum(int noticeNum) {

		return sqlSession.selectOne(NS + "noticeGetByNoticeNum", noticeNum);

	}
	
	public int noticeDelete(int noticeNum) {
		int n=0;
		try {
			n=sqlSession.delete(NS + "noticeDelete", noticeNum);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return n;

	}

}
