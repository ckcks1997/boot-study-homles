package com.example.bootstudyhomles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.bootstudyhomles.model.Attend;

@Repository
public class AttendDao {

	private static final String NS = "attend.";
	private Map<String, Object> map = new HashMap<>();
	SqlSession sqlSession;

	public AttendDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<Attend> attendGet(String id) {
		return sqlSession.selectList(NS + "attendGet", id);
	}

	public int attendInsert(Attend attend) {

		try {
			return sqlSession.insert(NS + "attendInsert", attend);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
