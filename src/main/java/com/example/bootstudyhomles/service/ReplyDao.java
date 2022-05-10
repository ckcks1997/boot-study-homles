package com.example.bootstudyhomles.service;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.example.bootstudyhomles.model.Reply;

@Component
public class ReplyDao {
	
	private static final String NS = "reply.";
	 // private Map<String, Object> map = new HashMap<>();

	SqlSession sqlSession;

	  public ReplyDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}


	/*댓글등록*/
	  public int insertReply(Reply reply) {
		 
			try {
			return sqlSession.update(NS+"insertReply", reply);	
			} catch(Exception e ) {
				e.printStackTrace();
			} 
			return 0;
			}
	  
	  
	  public int replyNextNum() {
		  
			  return sqlSession.selectOne(NS+"replyNextNum");
		  
	  }
	  
	  
	  
	  public List<Reply> replyWriteList(int board_num) {
		 
			  return sqlSession.selectList(NS+"replyWriteList", board_num);
		  
	  }
	  
	  
	  public int replyCount(int board_num) {
		  
		  	return sqlSession.selectOne(NS+"replyCount", board_num);
		  	  
		  
	  }
	  
	  public int deleteReply(int reply_num) {
		 
		  try {
			  return sqlSession.update(NS+"deleteReply",reply_num);
		  } catch(Exception e) {
				 e.printStackTrace();
		  } 
			  return 0;
		  
	  }
	  
	  public Reply replyOne(int reply_num) {
		
		  return sqlSession.selectOne(NS+"replyOne",reply_num);
		
	  }
	  
	  public int comReplyCount(int board_num) {
		  
		
		  try {
			  int communityTable = sqlSession.update(NS+"comReplyCount", board_num);
			  int groupTable = sqlSession.update(NS+"groupReplyCountUp", board_num);
			  return communityTable+groupTable;
		  } catch(Exception e) {
			  e.printStackTrace();
		  } 
		  return 0;		  
		  
	  }
	  
	  
		  
}
	 

