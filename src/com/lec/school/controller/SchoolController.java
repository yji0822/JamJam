package com.lec.school.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.service.ALoginService;
import com.lec.school.service.SJoinService;
import com.lec.school.service.SLoginService;
import com.lec.school.service.SLogoutService;
import com.lec.school.service.SemailConfirmService;
import com.lec.school.service.Service;
import com.lec.school.service.SidConfirmService;

@WebServlet("*.do")
public class SchoolController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com     = uri.substring(conPath.length()); // 들어온 요청
		String viewPage = null;
		Service service = null;
		
		if(com.equals("/main.do")) {
			viewPage="main/main.jsp";
		} else if(com.equals("/loginView.do")) {
			viewPage="student/login.jsp";
		} else if(com.equals("/login.do")) {
			service = new SLoginService();
			service.execute(request, response);
			viewPage = "board/noticeBoard.jsp";
		} else if(com.equals("/logout.do")) {//로그아웃 - 세션 날리기
			service = new SLogoutService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		} else if (com.equals("/joinView.do")) {
			viewPage = "student/join.jsp";
		}else if(com.equals("/idConfirm.do")) {
			service = new SidConfirmService();
			service.execute(request, response);
			viewPage = "member/idConfirm.jsp";
		}else if(com.equals("/emailConfirm.do")) {
			service = new SemailConfirmService();
			service.execute(request, response);
			viewPage = "member/emailConfirm.jsp";
		}else if(com.equals("/join.do")) { // 회원가입 DB 처리
			service = new SJoinService(); // execute메소드 : mId중복체크 후 회원가입
			service.execute(request, response);
			viewPage = "loginView.do";
			
			/**** admin *****/
		} else if(com.equals("/adminLoginView.do")) {
			viewPage = "admin/adminLogin.jsp";
		} else if(com.equals("/adminLogin.do")) {
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "board/noticeBoard.jsp";
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
