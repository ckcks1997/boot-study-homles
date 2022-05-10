package com.example.bootstudyhomles.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.bootstudyhomles.model.WebChat;
import com.example.bootstudyhomles.service.ChatMybatisDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/socket/")
public class ChatController {

	HttpServletRequest request;
	Model m;
	HttpSession session;
	ChatMybatisDao dao;

	public ChatController(ChatMybatisDao dao) {
		this.dao = dao;
	}

	@ModelAttribute
	void init(HttpServletRequest request, Model m) {
		this.request = request;
		this.m = m;
		this.session = request.getSession();
	}

	@RequestMapping("chat")
	public String list(HttpServletRequest request, HttpServletResponse response) {

		// member의 group가입에 관한 테이블작업
		String boardnum = request.getParameter("boardnum");
		String memberNickname = (String) request.getSession().getAttribute("memberNickname");
		System.out.println(memberNickname + "==");
		// userid 세션으로 수정
		request.setAttribute("boardnum", boardnum);
		request.setAttribute("memberNickname", memberNickname);

		List<WebChat> li = dao.listWebChat(boardnum);
		for (WebChat webChat : li) {
			System.out.println(webChat);
		}

		request.setAttribute("li", li);

		return "/view/chat/websocket";
	}

	@ResponseBody
	@RequestMapping("upload")
	public String upload(@RequestParam("file") MultipartFile multipartFile) {

		String filename = "";
		String path = request.getServletContext().getRealPath("/studyupload");

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

		return filename;
	}
}
