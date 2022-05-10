package com.example.bootstudyhomles.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.bootstudyhomles.model.Community;
import com.example.bootstudyhomles.model.Report;
import com.example.bootstudyhomles.service.CommunityBoardDao;
import com.example.bootstudyhomles.service.NoticeDao;
import com.example.bootstudyhomles.service.ReportDao;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/report/")
public class ReportController {
	
	HttpServletRequest request;
	Model m;
	HttpSession session;

	CommunityBoardDao cbd;
	ReportDao report_d;
	NoticeDao nd;
	
	public ReportController(CommunityBoardDao cbd, ReportDao report_d, NoticeDao nd) {
		this.cbd = cbd;
		this.report_d = report_d;
		this.nd = nd;
	}


	@ModelAttribute
	void init(HttpServletRequest request, Model m) {
		this.request = request;
		this.m = m;
		this.session = request.getSession();
	}
	
	@ResponseBody
	@RequestMapping(value = "sendReport", produces = MediaType.APPLICATION_JSON_VALUE)
	public Report sendReport(@RequestBody Map<String, String> rep, Community com) {
	
	Report report = new Report();
	int board_num = Integer.parseInt(rep.get("board_num"));
	String memberNickname = (String) session.getAttribute("memberNickname");
	
	report.setNickname(memberNickname);
	report.setReport_reason(rep.get("report_reason"));
	report.setBoard_num(board_num);
			//원 게시글 정보 report테이블에 넣기
			com = cbd.comBoardOne(board_num); 
	report.setWriter_nickname(com.getNickname());
	report.setBoard_num_title(com.getTitle());
	report.setBoard_num_regdate(com.getRegdate());
	
		//신고테이블에 등록하기 	
		int num = report_d.insertReport(report);
		
		if(num ==1) {
			System.out.println("신고 정상 등록");
		} else {
			System.out.println("신고 DB등록 에러");
		}
	
	
		//3번째 신고가 들어오면 알림주고 게시글 삭제 
		List<String> nicknameList = report_d.reportNickname(board_num);
		if(nicknameList.size()==3) {
			
			//board_num에 해당하는 원 게시글 정보 찾기
			com = cbd.comBoardOne(board_num);
			//원 게시글 정보에서 작성자 닉네임만 가져오기 
			String writer_nickname = com.getNickname();
																	
			String from = "관리자";
			String info = "report: 신고요청에 의한 게시물 삭제";
			//알림테이블에 등록하기
			nd.noticeReportWrite(info, from, writer_nickname,board_num); 
			cbd.comBoardDelete(board_num); //삭제하기
		} 
		
	return report;
		
	}

} //end class
