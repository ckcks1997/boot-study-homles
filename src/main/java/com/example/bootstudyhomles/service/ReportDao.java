package com.example.bootstudyhomles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.example.bootstudyhomles.model.Report;

@Component
public class ReportDao {

	private static final String NS = "report.";
	private Map<String, Object> map = new HashMap<>();
	
	SqlSession sqlSession;

	public ReportDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insertReport(Report report){
		
		try {
			return sqlSession.update(NS+"insertReport",report);			
		} catch(Exception e) {
			e.printStackTrace();
		} 
		return 0;
	}
	
	//닉네임리스트 가져와서 비교할때 사용
	public List<String> reportNickname(int board_num){
		
			return sqlSession.selectList(NS + "reportNickname",board_num);
		
	}
	
	//알림으로 신고사유 보여줄때 사용 
	public List<String> reportReason(int board_num){
		
			return sqlSession.selectList(NS + "reportReason",board_num);
		
	}
	
	//알림으로 신고 정보 보여줄때 사용 
	public Report reportOne(int board_num) {
		
			return sqlSession.selectOne(NS + "reportOne",board_num);
	
	}
	
	
	
} //end class
