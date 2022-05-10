package com.example.bootstudyhomles.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.example.bootstudyhomles.model.Community;
import com.example.bootstudyhomles.model.Reply;
import com.example.bootstudyhomles.service.CommunityBoardDao;
import com.example.bootstudyhomles.service.ReplyDao;
import com.example.bootstudyhomles.service.ReportDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/community/")
public class CommunityController {

	HttpServletRequest request;
	Model m;
	HttpSession session;
	
	CommunityBoardDao cbd;
	ReplyDao rd;
	ReportDao report_d;
	
	public CommunityController(CommunityBoardDao cbd, ReplyDao rd, ReportDao report_d) {
		this.cbd = cbd;
		this.rd = rd;
		this.report_d = report_d;
	}

	@ModelAttribute
	void init(HttpServletRequest request, Model m) {
		this.request = request;
		this.m = m;
		this.session = request.getSession();
	}
	

	//나열
	@RequestMapping("comBoardList")
	public String comBoardList(String sort, String boardid, String part, String searchData ,String pageNum) {

		int pageInt = 1;
		int limit = 4;

		if (boardid != null) {
			session.setAttribute("boardid", boardid);
			session.setAttribute("pageNum", "1");
		}

		boardid = (String) session.getAttribute("boardid");
		if (boardid == null) {
			boardid = "1";
		}
		
		if (pageNum != null) {
			session.setAttribute("pageNum", pageNum);
		}
		 pageNum = (String) session.getAttribute("pageNum");
		
		if (pageNum == null) {
			pageNum = "1";
		}

		pageInt = Integer.parseInt(pageNum);

		int boardcount = cbd.comBoardCount(boardid);
		List<Community> list;
		
		if(sort != "" && sort != null && sort.equals("replycnt")) { // 댓글순 목록
			list = cbd.comBoardReply(pageInt, limit, boardcount, boardid);
			if(part != null && part != "" && searchData !=null && searchData != "") {  //검색어가 있으면
				boardcount = cbd.comSearchCount(boardid,part,searchData);
				list  = cbd.comSearchListReply(pageInt,limit,boardcount,boardid, part, searchData);
			}
			
		} else if(sort != "" && sort != null && sort.equals("readcnt")) { // 조회수순 목록
			list = cbd.comBoardRead(pageInt, limit, boardcount, boardid);
			if(part != null && part != "" && searchData !=null && searchData != "") { //검색어가 있으면
				boardcount = cbd.comSearchCount(boardid,part,searchData);
				list  = cbd.comSearchListRead(pageInt,limit,boardcount,boardid, part, searchData);
			}
			
			
		} else { // 최신순 목록
			list = cbd.comBoardList(pageInt, limit, boardcount, boardid);
			if(part != null && part != "" && searchData !=null && searchData != "") { //검색어가 있으면
				boardcount = cbd.comSearchCount(boardid, part, searchData);
				list = cbd.comSearchList(pageInt, limit, boardcount, boardid, part, searchData); 
			}
		}
		int boardnum = boardcount - limit * (pageInt - 1);
		int bottomLine = 3;
		int startPage = (pageInt - 1) / bottomLine * bottomLine + 1;
		int endPage = startPage + bottomLine - 1;
		int maxPage = (boardcount / limit) + (boardcount % limit == 0 ? 0 : 1);
		if (endPage > maxPage)
			endPage = maxPage;

		String boardName = "질문 & 답변";
		switch (boardid) {
		case "5": boardName = "문의사항";
			break;
		case "4": boardName = "공지";
			break;
		case "3": boardName = "정보를 나눠요";
			break;
		case "2": boardName = "자유";
			break;

		}
		m.addAttribute("boardName", boardName);
		m.addAttribute("pageInt", pageInt);
		m.addAttribute("boardid", boardid);
		m.addAttribute("boardcount", boardcount);
		m.addAttribute("list", list);
		m.addAttribute("boardnum", boardnum);
		m.addAttribute("startPage", startPage);
		m.addAttribute("bottomLine", bottomLine);
		m.addAttribute("endPage", endPage);
		m.addAttribute("maxPage", maxPage);
		m.addAttribute("sort",sort);
		m.addAttribute("part",part);
		m.addAttribute("searchData",searchData);
		
		return "view/community/comBoardList";
	}

	
	@RequestMapping("comBoardmyList1")
	public String comBoardmyList1() {
		
		String nickname = (String) session.getAttribute("memberNickname");
		String boardid = "";
		int pageInt = 1;
		int limit = 3;

		if (request.getParameter("boardid") != null) {
			session.setAttribute("boardid", request.getParameter("boardid"));
			session.setAttribute("pageNum", "1");
		}

		boardid = (String) session.getAttribute("boardid");
		if (boardid == null) {
			boardid = "1";
		}

		if (request.getParameter("pageNum") != null) {
			session.setAttribute("pageNum", request.getParameter("pageNum"));
		}

		String pageNum = (String) session.getAttribute("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		pageInt = Integer.parseInt(pageNum);

		int boardcount = cbd.myComCount(nickname);
		List<Community> list = cbd.comBoardmyList1(pageInt, limit, boardcount, nickname);
		int boardnum = boardcount - limit * (pageInt - 1);
		int bottomLine = 3;
		int startPage = (pageInt - 1) / bottomLine * bottomLine + 1;
		int endPage = startPage + bottomLine - 1;
		int maxPage = (boardcount / limit) + (boardcount % limit == 0 ? 0 : 1);
		if (endPage > maxPage)
			endPage = maxPage;

		String boardName = "내가 쓴 게시판";

		m.addAttribute("boardName", boardName);
		m.addAttribute("pageInt", pageInt);
		m.addAttribute("boardid", boardid);
		m.addAttribute("boardcount", boardcount);
		m.addAttribute("list", list);
		m.addAttribute("boardnum", boardnum);
		m.addAttribute("startPage", startPage);
		m.addAttribute("bottomLine", bottomLine);
		m.addAttribute("endPage", endPage);
		m.addAttribute("maxPage", maxPage);

		return "view/community/myList1";
	}

	// 글쓰기 페이지
	@RequestMapping("comWriteForm")
	public String comWriteForm() {

			return "view/community/comWriteForm";	
	}

	// 글쓰기
	@RequestMapping("comWritePro")
	public String comWritePro(Community com, RedirectAttributes redirect) {
		
		com.setNickname((String)session.getAttribute("memberNickname"));
		com.setIp(request.getLocalAddr());

		String boardid = (String) session.getAttribute("boardid");
		if (boardid == null) {
			boardid = "1";
		}
		com.setBoardid(boardid);
		com.setBoard_num(cbd.comNextNum());
		
		
		int num = cbd.comInsertBoard(com);

		String msg = "게시물이 등록되지 않습니다.";
		String url = "/community/comBoardList";
		if (num == 1) {
			msg = "게시물이 등록되었습니다.";
			url = "/community/comBoardList?pageNum=1";

		}
		//m.addAttribute("msg",msg); 대신 사용, url에 msg 노출 x
		redirect.addFlashAttribute("msg",msg);

		return "redirect:"+url;
	}

	// 게시글 상세보기
	@RequestMapping("comBoardInfo")
	public String comBoardInfo(Community com, int board_num,String sort, String part, String searchData) {
		
		//board_id와 pageNum은 session에 저장되어 있기 때문에 파라미터로 받아서 보내지 않아도 comBoardInfo.jsp의 목록보기에서 EL로 바로 받을 수 있습니다 
		com = cbd.comBoardOne(board_num);
		m.addAttribute("com", com);

		String msg;
		String url;

		String boardid = com.getBoardid();
		
		if (boardid.equals("5")) {// 문의 게시판이면
			if (session.getAttribute("memberNickname")==null) {// 비로그인 시 
				//(String)session.getAttribute("memberNickname")==null --ok 
				//(String)session.getAttribute("memberNickname").equals(null) -- 앞에꺼가 애초에 null이어서 equals를 못 부름 
				msg = "로그인을 먼저 해주세요";
				url = "/studymember/loginForm";

			}

			// 로그인 후 1)닉네임과 게시글 작성자가 같거나 2)관리자이면
			else if (com.getNickname().equals(session.getAttribute("memberNickname"))
					|| session.getAttribute("memberNickname").equals("관리자")) {

				// 조회수 올리기
				cbd.comReadCountUp(board_num);

				// 댓글보여주기
				List<Reply> reply_list = rd.replyWriteList(board_num);
				int reply_count = rd.replyCount(board_num);
				
				m.addAttribute("reply_list", reply_list);
				m.addAttribute("reply_count", reply_count);
				return "view/community/comBoardInfo"; 

			} else { //로그인은 되어있는데 글 작성자가 아닐 경우 
				msg = "글 작성자만 열람가능합니다"; 
				url = "/community/comBoardList"; 
			}
			m.addAttribute("msg", msg);

		} else { // 문의 게시판이 아닌 다른 게시판은 검증 거치지 않고 바로 열람 가능 
			// 조회수 올리기
			cbd.comReadCountUp(board_num);

			// 신고한 유저 닉네임 리스트 세팅
			List<String> nicknameList = report_d.reportNickname(board_num);
			m.addAttribute("nicknameList", nicknameList);

			// 댓글보여주기
			List<Reply> reply_list = rd.replyWriteList(board_num);
			int reply_count = rd.replyCount(board_num);

			m.addAttribute("reply_list", reply_list);
			m.addAttribute("reply_count", reply_count);
			m.addAttribute("sort",sort);
			m.addAttribute("part",part);
			m.addAttribute("searchData",searchData);
			return "view/community/comBoardInfo"; // 바로 jsp로 보내주기

		}
		return "redirect:"+url;

	}

	
	
	// 게시글 수정페이지
	@RequestMapping("comBoardUpdateForm")
	public String comBoardUpdateForm(Community com, int board_num) {
	
		com = cbd.comBoardOne(board_num);
		m.addAttribute("com", com);

		return "view/community/comBoardUpdateForm";
	}

	// 게시글 수정
	@RequestMapping("comBoardUpdatePro")
	public String comBoardUpdatePro(Community com) {
		
		String msg = "";
		String url = "";

		if (cbd.comBoardUpdate(com) > 0) {
			msg = "수정되었습니다";
			url = "/community/comBoardInfo?board_num=" + com.getBoard_num();

		} else {
			msg = "수정이 실패하였습니다";
			url = "/community/comBoardInfo?board_num=" + com.getBoard_num();
		}

		m.addAttribute("msg", msg);
		return "redirect:"+url;

	}

	// 게시글 삭제
	@RequestMapping("comBoardDelete")
	public String comBoardDelete(Community com, int board_num) {

		com = cbd.comBoardOne(board_num);
		m.addAttribute("com", com);

		String msg = "";
		String url = "";

		if (cbd.comBoardDelete(board_num) > 0) {

			msg = "게시글이 삭제되었습니다.";
			url = "/community/comBoardList";
		} else {
			msg = "삭제가 불가능합니다";
			url = "/community/comBoardInfo?board_num=" + com.getBoard_num();
		}

		m.addAttribute("msg", msg);

		return "redirect:"+url;

	}


	// 이미지 업로드 ajax가 보내는 url 받는 메서드
	@RequestMapping("comImageUpload")
	public String comImageUpload(@RequestParam("file") MultipartFile file ) { //("file")은 jsp에서 form-data의 key명과 동일하게 적어야합니다
		
		String path = request.getServletContext().getRealPath("/") + "comboardupload";
															
		File comBoardFile = new File(path); // comBoardFile은 path경로로 만들어지는 File객체입니다 
		if (!comBoardFile.exists()) { //comBoardFile이 아예 없으면 
			comBoardFile.mkdir(); // 새로 폴더를 생성합니다.
		}
		
		String filename = file.getOriginalFilename(); //filename은 param으로 받은 file의 이름을 받습니다 
		
		if(!file.isEmpty()) { //param으로 받은 file이 있으면
			File comBoardFile2 = new File(path, filename); // comBoardFile2로 File객체를 생성합니다.
			try {
				file.transferTo(comBoardFile2); // param으로 받은 file을 comBoardFile2로 
																//System.out.println("이미지 경로---"+comBoardFile2.getPath()); //확인
			} catch(IllegalStateException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
			
		m.addAttribute("filename", filename);
		return "single/comBoardPicture";

	}

	// 메인
	@RequestMapping("main")
	public String main() {

		return "view/main";
	}

}
