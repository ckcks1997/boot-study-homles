package com.example.bootstudyhomles.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.bootstudyhomles.model.Reply;
import com.example.bootstudyhomles.service.ReplyDao;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/reply/")
public class ReplyController {
	
	ReplyDao rd;

	public ReplyController(ReplyDao rd) {
		this.rd = rd;
	}
	
	HttpServletRequest request;
	Model m;
	HttpSession session;
	
	@ModelAttribute
	void init(HttpServletRequest request, Model m) {
		this.request = request;
		this.m = m;
		this.session = request.getSession();
	}
	
	@ResponseBody// 자바 객체를 http바디로 보냄 
	@RequestMapping(value="writeReply", produces = MediaType.APPLICATION_JSON_VALUE)
	public Reply writeReply(@RequestBody Map<String, String> rep ) { //RequestBody : http요청 바디내용을 자바객체로 받음
		
		Reply reply = new Reply(); //글 저장을 위한 객체 생성 
		reply.setNickname((String) session.getAttribute("memberNickname")); //닉네임
		reply.setContent(rep.get("reply_content"));
		reply.setBoard_num(Integer.parseInt(rep.get("board_num")));
		
		//reply_num은 댓글의 번호
		int reply_num = rd.replyNextNum(); 
		reply.setReply_num(reply_num);
		
		rd.insertReply(reply); //댓글 저장
		
		//원글의 replycnt 업데이트
		rd.comReplyCount(Integer.parseInt(rep.get("board_num")));

		return reply;	
	}
	
	@RequestMapping("deleteReply")
	public String deleteReply(@RequestBody Map<String, Integer> rep, Reply reply) {
		
		int reply_num = rep.get("reply_num");
		int board_num = rep.get("board_num");
		
		reply =  rd.replyOne(reply_num);
		m.addAttribute("reply", reply);
		
		rd.deleteReply(reply_num); //댓글 삭제
		
		//원글 replycnt 업데이트 
		rd.comReplyCount(board_num);
	
		return "view/alert";
	}
	
	

} // end class
