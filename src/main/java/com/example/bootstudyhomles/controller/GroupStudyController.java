package com.example.bootstudyhomles.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.bootstudyhomles.model.group.GroupInList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.bootstudyhomles.model.GroupBoard;
import com.example.bootstudyhomles.model.GroupMember;
import com.example.bootstudyhomles.model.Reply;
import com.example.bootstudyhomles.model.ReputationEstimate;
import com.example.bootstudyhomles.model.StudyMember;
import com.example.bootstudyhomles.model.StudyMenu;
import com.example.bootstudyhomles.service.GroupBoardDao;
import com.example.bootstudyhomles.service.GroupMemberDao;
import com.example.bootstudyhomles.service.ReplyDao;
import com.example.bootstudyhomles.service.ReportDao;
import com.example.bootstudyhomles.service.ReputationEstimateDao;
import com.example.bootstudyhomles.service.StudyMemberDao;
import com.example.bootstudyhomles.service.StudyMenuDao;

//group
@Controller
@RequestMapping("/group/")
public class GroupStudyController {

	HttpServletRequest request;
	Model m;
	HttpSession session;
	
	StudyMenuDao mud;
	GroupMemberDao gmd;
	ReplyDao rd;
	ReportDao report_d;
	ReputationEstimateDao red;
	StudyMemberDao sd;
	GroupBoardDao gbd;

	public GroupStudyController(StudyMenuDao mud, GroupMemberDao gmd, ReplyDao rd, ReportDao report_d,
			ReputationEstimateDao red, StudyMemberDao sd, GroupBoardDao gbd) {
		this.mud = mud;
		this.gmd = gmd;
		this.rd = rd;
		this.report_d = report_d;
		this.red = red;
		this.sd = sd;
		this.gbd = gbd;
	}

	@ModelAttribute
	void init(HttpServletRequest request, Model m) {
		this.request = request;
		this.m = m;
		this.session = request.getSession();
	}
	/*????????? ????????? ?????????*/
	@RequestMapping("studylist")
	public String studyList(Model model) {
		
		String nickname = (String) session.getAttribute("memberNickname");
		List<GroupInList> list = gmd.groupInList(nickname);
		model.addAttribute("list", list);
		return "/view/group/groupStudyList";
	}
	/*????????? ?????? ??????*/
	@RequestMapping("studyinfo")
	public String studyInfo(@RequestParam String boardnum, Model model) {

		String nickname = (String) session.getAttribute("memberNickname");

		List<GroupMember> groupMemberList = gmd.groupListByBoardnum(boardnum);
		int total = groupMemberList.size();

		StudyMenu smenu = mud.menuBoardOne(Integer.parseInt(boardnum));

		model.addAttribute("groupMemberList", groupMemberList);
		model.addAttribute("total", total);
		model.addAttribute("studyMenuInfo", smenu);
		return "/view/group/groupStudyInfo";

	}

	/* ?????? ????????? ????????? */
	@RequestMapping("groupexit")
	public String groupExit(@RequestParam String boardnum, Model model) {

		String nickname = (String) session.getAttribute("memberNickname");
		List<GroupMember> groupMemberList = gmd.groupListByBoardnum(boardnum);
		
		model.addAttribute("groupMemberList", groupMemberList);
		return "/view/group/groupStudyExit";
	}

	/* ?????? ??? ?????? ????????? */
	@RequestMapping("groupexitpro")
	public String groupExitPro(@RequestParam String boardnum, Model model) {

		String nickname = (String) session.getAttribute("memberNickname");
		String msg = "???????????? ?????????????????????.";
		String url = "redirect:/board/main";

		gmd.groupDelete(Integer.parseInt(boardnum), nickname);
		model.addAttribute("msg", msg);

		return url;
	}

	/* ajax ???????????? */
	@ResponseBody
	@RequestMapping("score")
	public String score(@RequestParam("nickname") String nickname_to, @RequestParam String info_value,
			@RequestParam("score_value") String score1, @RequestParam String boardnum, Model model) {

		String nickname = (String) request.getSession().getAttribute("memberNickname");
		int score = Integer.parseInt(score1);
		// ???????????? ??????
		ReputationEstimate re = new ReputationEstimate();
		re.setNickname_from(nickname);
		re.setNickname_to(nickname_to);
		re.setScore(score);
		re.setInfo(info_value);
		re.setGroup_num(Integer.parseInt(boardnum));
		red.insertReputation(re);

		// ??????????????? ?????? ??????
		StudyMember member = sd.studyMembeByNickname(nickname_to);
		int val = member.getPoint();
		val += score;

		return Integer.toString(sd.changePoint(val, nickname_to));

	}

	/*
	 * ?????? ?????????
	 * Study_menu(=group_member)??? board_num??? s_board_num?????? ???????????? ???????????? ????????? ???????????????..
	 */
	@RequestMapping("groupBoard")
	public String groupBoard(Model model, String boardnum, @RequestParam(defaultValue = "1") String boardid) {
		String nickname = (String) session.getAttribute("memberNickname");
		session.setAttribute("boardnum", boardnum);

		String msg = "????????? ????????????.";
		String url = "redirect:/board/main"; // main?????? ?????????, alert.jsp?????? ??????

		int res = gmd.isMemberInGroup(boardnum, nickname); // ????????? ????????? ??????, ????????? 1
		if (res > 0) { // ????????? ?????? ??? ??????

			int pageInt = 1;
			int limit = 5;

			if (request.getParameter("boardid") != null) {
				session.setAttribute("boardid", request.getParameter("boardid"));
				session.setAttribute("pageNum", "1");
			}

			boardid = (String) session.getAttribute("boardid");

			if (request.getParameter("pageNum") != null) {
				session.setAttribute("pageNum", request.getParameter("pageNum"));
			}

			String pageNum = (String) session.getAttribute("pageNum");
			if (pageNum == null) {
				pageNum = "1";
			}

			pageInt = Integer.parseInt(pageNum);

			int boardcount = gbd.groupBoardCount(boardnum, boardid);
			System.out.println("boardcount=" + boardcount);

			List<GroupBoard> list = gbd.groupBoardList(pageInt, limit, boardcount, boardid, boardnum);
			int boardListnum = boardcount - limit * (pageInt - 1);
			int bottomLine = 3;
			int startPage = (pageInt - 1) / bottomLine * bottomLine + 1;
			int endPage = startPage + bottomLine - 1;
			int maxPage = (boardcount / limit) + (boardcount % limit == 0 ? 0 : 1);
			if (endPage > maxPage)
				endPage = maxPage;

			String boardName = "?????? & ??????";
			switch (boardid) {
			case "1":
				boardName = "?????? ??????";
				break;
			case "2":
				boardName = "????????????";
				break;
			}
			model.addAttribute("boardName", boardName);
			model.addAttribute("pageInt", pageInt);
			model.addAttribute("boardid", boardid);
			model.addAttribute("boardcount", boardcount);
			model.addAttribute("list", list);
			model.addAttribute("boardListnum", boardListnum);
			model.addAttribute("startPage", startPage);
			model.addAttribute("bottomLine", bottomLine);
			model.addAttribute("endPage", endPage);
			model.addAttribute("maxPage", maxPage);

			return "/view/group/groupBoard";
		}

		msg = "????????? ????????????.";
		model.addAttribute("msg", msg);
		return url;
	}

	/* ?????????-????????? */
	@RequestMapping("groupBoardList")
	public String groupBoardList(Model model, String boardnum, String pageNum) {
		String nickname = (String) session.getAttribute("memberNickname");
		if (boardnum != null)
			session.setAttribute("boardnum", boardnum);

		boardnum = (String) session.getAttribute("boardnum");
		String msg = "????????? ????????????.";
		String url = "redirect:/board/main"; // main?????? ?????????, alert.jsp?????? ??????

		int res = gmd.isMemberInGroup(boardnum, nickname); // ????????? ????????? ??????, ????????? 1
		if (res > 0) { // ????????? ?????? ??? ??????

			String boardid = "";
			int pageInt = 1;
			int limit = 5;

			if (request.getParameter("boardid") != null) {
				session.setAttribute("boardid", request.getParameter("boardid"));
				session.setAttribute("pageNum", "1");
			}

			boardid = (String) session.getAttribute("boardid");
			if (boardid == null) {
				boardid = "1";
			}

			if (pageNum != null) {
				session.setAttribute("pageNum", request.getParameter("pageNum"));
			}

			pageNum = (String) session.getAttribute("pageNum");
			if (pageNum == null) {
				pageNum = "1";
			}

			pageInt = Integer.parseInt(pageNum);

			int boardcount = gbd.groupBoardCount(boardnum, boardid);

			List<GroupBoard> list = gbd.groupBoardList(pageInt, limit, boardcount, boardid, boardnum);
			int boardListnum = boardcount - limit * (pageInt - 1);
			int bottomLine = 3;
			int startPage = (pageInt - 1) / bottomLine * bottomLine + 1;
			int endPage = startPage + bottomLine - 1;
			int maxPage = (boardcount / limit) + (boardcount % limit == 0 ? 0 : 1);
			if (endPage > maxPage)
				endPage = maxPage;

			String boardName = "?????? & ??????";
			switch (boardid) {
			case "1":
				boardName = "?????? ??????";
				break;
			case "2":
				boardName = "????????????";
				break;
			}
			model.addAttribute("boardName", boardName);
			model.addAttribute("pageInt", pageInt);
			model.addAttribute("boardid", boardid);
			model.addAttribute("boardcount", boardcount);
			model.addAttribute("list", list);
			model.addAttribute("boardListnum", boardListnum);
			model.addAttribute("startPage", startPage);
			model.addAttribute("bottomLine", bottomLine);
			model.addAttribute("endPage", endPage);
			model.addAttribute("maxPage", maxPage);

			return "/view/group/groupBoard";
		}

		model.addAttribute("msg", msg);
		return url;
	}

	/* ????????? */
	@RequestMapping("groupBoardWriteForm")
	public String groupWriteForm(Model model, String boardid) {

		if (boardid != null) {
			session.setAttribute("boardid", boardid);
			return "/view/group/groupBoardWriteForm";
		}
		model.addAttribute("msg", "????????? ????????????.");
		return "redirect:/board/main";
	}

	/* ?????????-?????? */
	@RequestMapping("groupBoardWritePro")
	public String groupBoardWritePro(GroupBoard gb, Model model) {

		String msg = "????????? ????????????.";
		String url = "redirect:/board/main";

		int res1 = gmd.isMemberInGroup((String) session.getAttribute("boardnum"),
				(String) session.getAttribute("memberNickname")); // ????????? ????????? ??????, ????????? 1
		if (res1 > 0) {
			String boardid = (String) session.getAttribute("boardid"); // ???????????????
			String boardnum = (String) session.getAttribute("boardnum");// ????????????
			String memberNickname = (String) session.getAttribute("memberNickname");
			gb.setBoardid(boardid);
			gb.setS_board_num(Integer.parseInt(boardnum));
			gb.setNickname(memberNickname);

			int res = gbd.groupInsertBoard(gb);
			System.out.println("result=" + res);
			return "redirect:/group/groupBoard?boardnum=" + boardnum + "&pageNum=1";
		}

		model.addAttribute("msg", msg);
		return url;
	}

	// ????????? ????????????
	@RequestMapping("groupBoardInfo")
	public String groupBoardInfo(Model model, String board_num) {

		String s_board_num = (String) session.getAttribute("boardnum");
		int res1 = gmd.isMemberInGroup((String) session.getAttribute("boardnum"),
				(String) session.getAttribute("memberNickname")); // ????????? ????????? ??????, ????????? 1
		if (res1 > 0) {
			GroupBoard gb = gbd.groupBoardOne(s_board_num, board_num);
			gbd.groupReadCountUp(Integer.parseInt(board_num));

			// ??????????????????
			List<Reply> reply_list = rd.replyWriteList(Integer.parseInt(board_num));
			int reply_count = rd.replyCount(Integer.parseInt(board_num));

			model.addAttribute("reply_list", reply_list);
			model.addAttribute("reply_count", reply_count);
			model.addAttribute("groupBoard", gb);

			return "/view/group/groupBoardInfo";
		}

		String msg = "????????? ????????????.";
		model.addAttribute("msg", msg);
		return "redirect:/board/main";

	}

	/* ????????? ????????? */
	@ResponseBody
	@RequestMapping(value ="imageUpload",  produces = "application/text; charset=utf8" )
	public String imageUpload(@RequestParam("file") MultipartFile multipartFile) {

		String path = request.getServletContext().getRealPath("/") + "studyupload/";
		String filename = null;
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdir();
		}
		if (!multipartFile.isEmpty()) {
			File file = new File(path, multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(file);
				filename = multipartFile.getOriginalFilename();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(filename);
		return filename;
	}

	// ????????? ???????????????
	@RequestMapping("groupBoardUpdateForm")
	public String comBoardUpdateForm(@RequestParam int board_num, Model model) {

		String msg = "????????? ????????????.";
		String url = "redirect:/board/main";

		int res1 = gmd.isMemberInGroup((String) session.getAttribute("boardnum"),
				(String) session.getAttribute("memberNickname")); // ????????? ????????? ??????, ????????? 1
		if (res1 > 0) {
			GroupBoard gb = gbd.groupBoardOne2(board_num);
			if (gb.getNickname().equals(session.getAttribute("memberNickname"))) {
				request.setAttribute("gb", gb);
				return "/view/group/groupBoardUpdateForm";
			}
		}

		msg = "????????? ????????????.";
		request.setAttribute("msg", msg);
		return url;
	}

	// ????????? ??????
	@RequestMapping("groupBoardUpdatePro")
	public String comBoardUpdatePro(GroupBoard gb, int board_num, RedirectAttributes redirect) {

		gb.setBoard_num(board_num);

		String msg = "??????";
		String url = "redirect:/board/main";
		int res1 = gmd.isMemberInGroup((String) session.getAttribute("boardnum"),
				(String) session.getAttribute("memberNickname")); // ????????? ????????? ??????, ????????? 1
		
		if (res1 > 0) {
			if (gbd.groupBoardUpdate(gb) > 0) {
				msg = "?????????????????????";
				url = "/group/groupBoardInfo?board_num=" + gb.getBoard_num();
			} else {
				msg = "????????? ?????????????????????";
			}
			redirect.addFlashAttribute("msg", msg);
			return "redirect:/" + url;
		}

		return url;
	}

}
