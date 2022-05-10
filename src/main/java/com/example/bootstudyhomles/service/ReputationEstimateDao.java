package com.example.bootstudyhomles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.example.bootstudyhomles.model.ReputationEstimate;

@Component
public class ReputationEstimateDao {
	private static final String NS = "reputationestimate.";
	private Map<String, Object> map = new HashMap<>();

	SqlSession sqlSession;

	public ReputationEstimateDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insertReputation(ReputationEstimate rep) {
		try {
			return sqlSession.insert(NS + "insertReputation", rep);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return 0;
	}

	public List<ReputationEstimate> getReputation(String nick) {

		return sqlSession.selectList(NS + "getReputation", nick);

	}
}
