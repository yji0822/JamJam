package com.lec.school.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.service.ALoginService;
import com.lec.school.service.FreeBoardListService;
import com.lec.school.service.FreeContentService;
import com.lec.school.service.LunchInService;
import com.lec.school.service.MainService;
import com.lec.school.service.NoticeBoardListService;
import com.lec.school.service.NoticeContentService;
import com.lec.school.service.SAllViewService;
import com.lec.school.service.SJoinService;
import com.lec.school.service.SLoginService;
import com.lec.school.service.SLogoutService;
import com.lec.school.service.SemailConfirmService;
import com.lec.school.service.Service;
import com.lec.school.service.SidConfirmService;
import com.lec.school.service.TodayMenuService;

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
		
		
		/* 학생 관련 리스트 출력*/
		if(com.equals("/main.do")) { // 메인화면
			viewPage="main/main.jsp";
		} else if(com.equals("/loginView.do")) { // 로그인 화면
			viewPage="student/login.jsp";
		} else if(com.equals("/login.do")) { // 로그인 처리
			service = new SLoginService();
			service.execute(request, response);
			viewPage = "board/noticeBoard.jsp";
		} else if(com.equals("/logout.do")) { //로그아웃 - 세션 날리기
			service = new SLogoutService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		} else if (com.equals("/joinView.do")) { // 회원가입 창
			viewPage = "student/join.jsp";
		}else if(com.equals("/idConfirm.do")) { // 아이디 체크 
			service = new SidConfirmService();
			service.execute(request, response);
			viewPage = "student/idConfirm.jsp";
		}else if(com.equals("/emailConfirm.do")) { // 이메일 체크
			service = new SemailConfirmService();
			service.execute(request, response);
			viewPage = "student/emailConfirm.jsp";
		}else if(com.equals("/join.do")) { // 회원가입 DB 처리
			service = new SJoinService(); // execute메소드 : mId중복체크 후 회원가입
			service.execute(request, response);
			viewPage = "loginView.do";
		} 
		/******************* 메뉴 보드 Board *************************/
		else if(com.equals("/sAllView.do")) { // 회원목록가져오기
			service = new SAllViewService();
			service.execute(request, response);
			viewPage = "board/studentList.jsp";
		} 
		else if(com.equals("/notice.do")) { // 공지사항 리스트
			service = new NoticeBoardListService();
			service.execute(request, response);
			viewPage = "board/noticeBoard.jsp";
		} else if(com.equals("/noticeContent.do")) { // 공지사항 상세보기
			service = new NoticeContentService();
			service.execute(request, response);
			viewPage = "board/noticeBoardContent.jsp";
		} 
		else if(com.equals("/free.do")) { // 자유게시판
			service = new FreeBoardListService();
			service.execute(request, response);
			viewPage = "board/freeBoard.jsp";
		} else if(com.equals("/freeContent.do")) {
			service = new FreeContentService();
			service.execute(request, response);
			viewPage = "board/freeBoardContent.jsp";
		}
		/************** 관리자 admin ****************/
		else if(com.equals("/adminLoginView.do")) {
			viewPage = "admin/adminLogin.jsp";
		} else if(com.equals("/adminLogin.do")) {
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "board/noticeBoard.jsp";
		}  
		/******** 점심 게시판 lunchBoard ************/
		else if(com.equals("/lunch.do")) {
			service = new MainService();
			service.execute(request, response);
			viewPage = "lunch/lunchCalendar.jsp"; // 새창
		} else if(com.equals("/todayMenu.do")) {
			service = new TodayMenuService();
			service.execute(request, response);
			viewPage = "lunch/todayMenu.jsp";
		} else if(com.equals("/lunchIn.do")) {
			service = new LunchInService();
			service.execute(request, response);
			viewPage = "";
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
