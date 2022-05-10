package com.example.bootstudyhomles.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.bootstudyhomles.model.Attend;
import com.example.bootstudyhomles.service.AttendDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/attend/")
public class AttendCheckController  {

	HttpServletRequest request;
	Model m;
	HttpSession session;
	AttendDao ad;

	
	public AttendCheckController(AttendDao ad) {
		this.ad = ad;
	}

	@ModelAttribute
	void init(HttpServletRequest request, Model m) {
		this.request = request;
		this.m = m ;
		this.session = request.getSession();
		}
	
  @RequestMapping("check")
  public String ckeck(HttpServletRequest request) {
    
    String id = (String) request.getSession().getAttribute("memberID");
    
    String msg= "로그인이 필요합니다";
    String url= "main"; //main으로 보내기, alert.jsp파일 참고
    
    if(id != null) {
      
      List<Attend> result = ad.attendGet(id);
      m.addAttribute("result", result);
      return "/view/check/event";
    }
    
    m.addAttribute("msg", msg);
    m.addAttribute("url", url);
     
    return "/view/alert";
  }
 
  @RequestMapping("sendGift")
  public String sendGood(HttpServletRequest request) {
    
    String id = (String) request.getSession().getAttribute("memberID");
    String gift = request.getParameter("gift");
    System.out.println(gift);
     m.addAttribute("num", gift);
    return "/single/alert_ajax";
  }
  
}
