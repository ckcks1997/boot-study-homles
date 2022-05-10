package com.example.bootstudyhomles.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.example.bootstudyhomles.model.MainRecentStudy;
import com.example.bootstudyhomles.model.Search;
import com.example.bootstudyhomles.model.StudyMenu;

@Component
public class StudyMenuDao {
	private static final String NS = "studymenu.";
	private Map<String, Object> map = new HashMap<>();
 
	SqlSession sqlSession;
	 
	public StudyMenuDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int menuCount(String menuid) {
	
			return sqlSession.selectOne(NS + "menuCount", menuid);
	
	}
	
	public int myStudyCount(String nickname) {
	
			return sqlSession.selectOne(NS + "myStudyCount", nickname);
		
	}
	
	
	public int onAllCount(String nickname) {
	
			return sqlSession.selectOne(NS + "onAllCount");
	}
	
	public int offAllCount(String nickname) {
		
			return sqlSession.selectOne(NS + "offAllCount");
	}
	
	public int onoffAllCount(String nickname) {
		
			return sqlSession.selectOne(NS + "onoffAllCount");
	}

	public List<StudyMenu> menuList(int pageInt, int limit, int menucount, String menuid) {
		
		try {
			map.clear();
			map.put("menuid", menuid);
			map.put("start", (pageInt - 1) * limit + 1);
			map.put("end", pageInt * limit);
			return sqlSession.selectList(NS + "menuList", map);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	

	
	public int menuNextNum() {
		
		try {
			return sqlSession.selectOne(NS + "menuNextNum");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return 0;
	}

	
	
	public int insertMenu(StudyMenu studymenu) {
		
		try {
			return sqlSession.update(NS + "insertMenu", studymenu);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return 0;
	}
	
	
	
	public List<StudyMenu> studySearch(Search sh) {
		
		List<StudyMenu> list = null;
		try {
		list = sqlSession.selectList(NS + "studySearch", sh);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	
	
	 public StudyMenu menuBoardOne(int board_num) {
		 
			try {
				return sqlSession.selectOne(NS+"menuBoardOne",board_num);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	  }
 

	 public List<StudyMenu> list2(String list2) {

			
			
			List<StudyMenu> list = null;
			try {
				
			list = sqlSession.selectList(NS + "list2", list2);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;

		}
	  
	 
		/*-----------------------------------------------------------------------------------------------------------------		*/ 
	 public List<StudyMenu> studySearch(String part, String searchData, String menuid) {
		
			List<StudyMenu> searchlist = null;
			try {
				map.clear();
				map.put("part", part);
				map.put("searchData", "%" +searchData+ "%");
				map.put("menuid", menuid);
				searchlist = sqlSession.selectList(NS + "studySearch", map);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return searchlist;
		}
	 
	 
	 public int studySearchCount(String menuid, String part, String searchData) {
			
			try {
				map.clear();
				map.put("part", part);
				map.put("searchData", "%" +searchData+ "%");
				map.put("menuid", menuid);
				return sqlSession.selectOne(NS + "studySearchCount", map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
	 
	 public List<StudyMenu> studySearchList(int pageInt, int limit, int menucount, String menuid, String part, String searchData) {
		
			try {
				map.clear();
				map.put("menuid", menuid);
				map.put("start", (pageInt - 1) * limit + 1);
				map.put("end", pageInt * limit);
				map.put("part", part);
				map.put("searchData", "%" +searchData+ "%");
				return sqlSession.selectList(NS + "studySearchList", map);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return null;
		}
	 
	 /*-----------------------------------------------------------------------------------------------------------------		*/ 
	 public List<StudyMenu> onAllSearch(String part, String searchData, String menuid) {
			
			List<StudyMenu> searchlist = null;
			try {
				map.clear();
				map.put("part", part);
				map.put("searchData", "%" +searchData+ "%");
				map.put("menuid", menuid);
				searchlist = sqlSession.selectList(NS + "onAllSearch", map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return searchlist;
		}
	 
	 
	 public int onAllSearchCount(String menuid, String part, String searchData) {
			
			try {
				map.clear();
				map.put("part", part);
				map.put("searchData", "%" +searchData+ "%");
				map.put("menuid", menuid);
				return sqlSession.selectOne(NS + "onAllSearchCount", map);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return 0;
		}
	 
	 public List<StudyMenu> onAllSearchList(int pageInt, int limit, int menucount, String menuid, String part, String searchData) {
			
			try {
				map.clear();
				map.put("menuid", menuid);
				map.put("start", (pageInt - 1) * limit + 1);
				map.put("end", pageInt * limit);
				map.put("part", part);
				map.put("searchData", "%" +searchData+ "%");
				return sqlSession.selectList(NS + "onAllSearchList", map);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return null;
		}
	 	 	 
	 /*-----------------------------------------------------------------------------------------------------------------		*/ 
	 
	 public List<StudyMenu> offAllSearch(String part, String searchData, String menuid) {
		
			List<StudyMenu> searchlist = null;
			try {
				map.clear();
				map.put("part", part);
				map.put("searchData", "%" +searchData+ "%");
				map.put("menuid", menuid);
				searchlist = sqlSession.selectList(NS + "offAllSearch", map);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return searchlist;
		}
	 
	 public int offAllSearchCount(String menuid, String part, String searchData) {
		
			try {
				map.clear();
				map.put("part", part);
				map.put("searchData", "%" +searchData+ "%");
				map.put("menuid", menuid);
				return sqlSession.selectOne(NS + "offAllSearchCount", map);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return 0;
		}
	 
	 public List<StudyMenu> offAllSearchList(int pageInt, int limit, int menucount, String menuid, String part, String searchData) {
		
			try {
				map.clear();
				map.put("menuid", menuid);
				map.put("start", (pageInt - 1) * limit + 1);
				map.put("end", pageInt * limit);
				map.put("part", part);
				map.put("searchData", "%" +searchData+ "%");
				return sqlSession.selectList(NS + "offAllSearchList", map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	 	 	 
	 /*-----------------------------------------------------------------------------------------------------------------		*/ 
	 
	 public List<StudyMenu> onoffAllSearch(String part, String searchData, String menuid) {
			
			List<StudyMenu> searchlist = null;
			try {
				map.clear();
				map.put("part", part);
				map.put("searchData", "%" +searchData+ "%");
				map.put("menuid", menuid);
				searchlist = sqlSession.selectList(NS + "onoffAllSearch", map);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return searchlist;
		}
	 
	 
	 public int onoffAllSearchCount(String menuid, String part, String searchData) {
			
			try {
				map.clear();
				map.put("part", part);
				map.put("searchData", "%" +searchData+ "%");
				map.put("menuid", menuid);
				return sqlSession.selectOne(NS + "onoffAllSearchCount", map);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return 0;
		}
	 
	 public List<StudyMenu> onoffAllSearchList(int pageInt, int limit, int menucount, String menuid, String part, String searchData) {
			
			try {
				map.clear();
				map.put("menuid", menuid);
				map.put("start", (pageInt - 1) * limit + 1);
				map.put("end", pageInt * limit);
				map.put("part", part);
				map.put("searchData", "%" +searchData+ "%");
				return sqlSession.selectList(NS + "onoffAllSearchList", map);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return null;
		}
	 	 	 
	 /*-----------------------------------------------------------------------------------------------------------------		*/ 
	 
	 public List<StudyMenu> onallList(int pageInt, int limit, int menuAllCount, String menuid) {
		
			try {
				map.clear();
				map.put("menuid", menuid);
				map.put("start", (pageInt - 1) * limit + 1);
				map.put("end", pageInt * limit);
				return sqlSession.selectList(NS + "onallList", map);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return null;
	 }	 
	 

	 public List<StudyMenu> offallList(int pageInt, int limit, int menuAllCount, String menuid) {
			
			try {
				map.clear();
				map.put("menuid", menuid);
				map.put("start", (pageInt - 1) * limit + 1);
				map.put("end", pageInt * limit);
				return sqlSession.selectList(NS + "offallList", map);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return null;
	 }	
	 
	 public List<StudyMenu> onoffallList(int pageInt, int limit, int menuAllCount, String menuid) {
			
			try {
				map.clear();
				map.put("menuid", menuid);
				map.put("start", (pageInt - 1) * limit + 1);
				map.put("end", pageInt * limit);
				return sqlSession.selectList(NS + "onoffallList", map);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return null;
	 }	 
	 
	
	 public List<StudyMenu> mylist2(int pageInt, int limit, int menucount, String nickname) {
			
			try {
				map.clear();
				
				map.put("start", (pageInt - 1) * limit + 1);
				map.put("end", pageInt * limit);
				map.put("nickname",nickname);
				return sqlSession.selectList(NS + "mylist2", map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	 public int studyUpdate(StudyMenu sm) {

		  try {
		  return sqlSession.update(NS+"studyUpdate",sm);
		  } catch (Exception e) {
			  e.printStackTrace();
		  } 
		  
		  return 0;
		  
	  }
	 
	 public StudyMenu studyMenuOne(int board_num) {
		 
		return sqlSession.selectOne(NS+"studyMenuOne",board_num);
			
	  }
	 
	 public int studyDelete (int board_num) {
		 
		 try {
		  return sqlSession.update(NS+"studyDelete", board_num);
		 } catch(Exception e) {
			 e.printStackTrace();
		 } 
		  return 0;
	  }

	 
     public List<MainRecentStudy> mainNewStudy3() {

       List<MainRecentStudy> list = null;
       try {
       list = sqlSession.selectList(NS + "mainNewStudy3");
       return list;

       } catch (Exception e) {
           e.printStackTrace();
       } 
       return null;
}   

}