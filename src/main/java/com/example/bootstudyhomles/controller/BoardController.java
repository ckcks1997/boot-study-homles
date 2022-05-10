package com.example.bootstudyhomles.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.bootstudyhomles.model.Community;
import com.example.bootstudyhomles.service.CommunityBoardDao;
import com.example.bootstudyhomles.service.NoticeDao;
import com.example.bootstudyhomles.service.StudyMenuDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bootstudyhomles.model.MainRecentStudy;


@Controller 
@RequestMapping("/board/")
public class BoardController {

  /*
   * 메인 화면입니다.
   * */
	CommunityBoardDao cbd;
	NoticeDao nd;
	StudyMenuDao sd;
	
  public BoardController(CommunityBoardDao cbd, NoticeDao nd, StudyMenuDao sd) {
		this.cbd = cbd;
		this.nd = nd;
		this.sd = sd;
	}


@RequestMapping("main")
  public String main(HttpServletRequest request, Model model) {
 
    HttpSession s = request.getSession();
    String nick_id = (String) s.getAttribute("memberNickname");
  
//    커뮤니티 리스트 가져오기
  
    List<Community> list1 = cbd.comMainBoardList("4");
    List<Community> list2 = cbd.comMainBoardList("1");
    List<Community> list3 = cbd.comMainBoardList("2");
    model.addAttribute("list1", list1);
    model.addAttribute("list2", list2);
    model.addAttribute("list3", list3);
    List<MainRecentStudy> slist = sd.mainNewStudy3();
    model.addAttribute("slist", slist);
    
    
    return "view/main";
  }
  
  
  //ajax(head 알림상태 가져오기)
  @ResponseBody
  @RequestMapping("notice")
  public String notice(HttpServletRequest request) {
 
    HttpSession s = request.getSession();
    String nick_id = (String) s.getAttribute("memberNickname"); //세션에서 닉네임 가져옴
    int newNoticeCount = 0;
    if(nick_id != null) { 
      newNoticeCount = nd.noticeNew(nick_id); //알림이 없으면 0, 있으면 1~
    }

    return Integer.toString(newNoticeCount);
  }
  
  
  @RequestMapping("make")
  public String make( ) {
    return "view/make/make";
  }
  @RequestMapping("whoMade")
  public String whoMade( ) {
    return "view/make/whoMade";
  }
  
}
 