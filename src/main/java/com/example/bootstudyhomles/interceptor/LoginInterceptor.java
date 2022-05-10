package com.example.bootstudyhomles.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor; 

public class LoginInterceptor implements HandlerInterceptor{
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	throws Exception{ 
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("memberNickname");
		
		if(login == null) {
			String msg = URLEncoder.encode("로그인이 필요합니다.","UTF-8");
			response.sendRedirect(request.getContextPath()+"/studymember/loginForm?msg="+msg);
		return false;
		}
		
		return true;
	}
}
