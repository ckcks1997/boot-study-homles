package com.example.bootstudyhomles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.bootstudyhomles.model.WebChat;

@Repository 
public class ChatMybatisDao {

	private static final String NS = "webchat.";
	private Map<String, Object> map = new HashMap<>();
	SqlSession sqlSession;

	public ChatMybatisDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int nextNum() {
		return sqlSession.selectOne(NS + "nextNum", map);
	}

	public int insertWebChat(WebChat webchat) {

		try {
			return sqlSession.insert(NS + "insertWebChat", webchat);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return 0;
	}

	public List<WebChat> listWebChat(String groupId) {
		return sqlSession.selectList(NS + "listWebChat", groupId);
	}

}
