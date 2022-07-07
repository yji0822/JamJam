package com.lec.school.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.service.ALoginService;
import com.lec.school.service.FreeBoardDeleteService;
import com.lec.school.service.FreeBoardListService;
import com.lec.school.service.FreeBoardModifyService;
import com.lec.school.service.FreeBoardModifyViewService;
import com.lec.school.service.FreeBoardReplyService;
import com.lec.school.service.FreeBoardReplyViewService;
import com.lec.school.service.FreeBoardWriteService;
import com.lec.school.service.FreeContentService;
import com.lec.school.service.LunchInService;
import com.lec.school.service.MainService;
import com.lec.school.service.NoticeBoardContentService;
import com.lec.school.service.NoticeBoardDeleteService;
import com.lec.school.service.NoticeBoardListService;
import com.lec.school.service.NoticeBoardModifyService;
import com.lec.school.service.NoticeBoardModifyViewService;
import com.lec.school.service.NoticeBoardWriteService;
import com.lec.school.service.SAllViewService;
import com.lec.school.service.SJoinService;
import com.lec.school.service.SLoginService;
import com.lec.school.service.SLogoutService;
import com.lec.school.service.SModifyService;
import com.lec.school.service.SemailConfirmService;
import com.lec.school.service.Service;
import com.lec.school.service.SidConfirmService;
import com.lec.school.service.TodayMenuService;

@WebServlet("*.do")
public class SchoolController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length()); // 들어온 요청
		String viewPage = null;
		Service service = null;

		/******************* 메인 로그인 회원가입 로그아웃 *************************/
		if (com.equals("/main.do")) { // 메인화면
			viewPage = "main/main.jsp";
		} else if (com.equals("/loginView.do")) { // 로그인 화면
			viewPage = "student/login.jsp";
		} else if (com.equals("/login.do")) { // 로그인 처리
			service = new SLoginService();
			service.execute(request, response);
			viewPage = "main.do";
		} else if (com.equals("/logout.do")) { // 로그아웃 - 세션 날리기
			service = new SLogoutService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		} else if (com.equals("/joinView.do")) { // 회원가입 창
			viewPage = "student/join.jsp";
		} else if (com.equals("/idConfirm.do")) { // 아이디 체크
			service = new SidConfirmService();
			service.execute(request, response);
			viewPage = "student/idConfirm.jsp";
		} else if (com.equals("/emailConfirm.do")) { // 이메일 체크
			service = new SemailConfirmService();
			service.execute(request, response);
			viewPage = "student/emailConfirm.jsp";
		} else if (com.equals("/join.do")) { // 회원가입 DB 처리
			service = new SJoinService(); // execute메소드 : mId중복체크 후 회원가입
			service.execute(request, response);
			viewPage = "loginView.do";
		} else if (com.equals("/modifyView.do")) { // 회원정보수정 - 페이지
			viewPage = "student/modify.jsp";
		} else if (com.equals("/modify.do")) { // DB에 회원 정보 수정 저장
			service = new SModifyService();
			service.execute(request, response);
			viewPage = "/main.do";
		}

		/******************* 학생 리스트 출력 *************************/
		else if (com.equals("/sAllView.do")) { // 회원목록가져오기
			service = new SAllViewService();
			service.execute(request, response);
			viewPage = "board/studentList.jsp";
		}

		/************** 관리자 admin ********************************/
		else if (com.equals("/adminLoginView.do")) {
			viewPage = "admin/adminLogin.jsp";
		} else if (com.equals("/adminLogin.do")) {
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "main.do";
		}

		/******************* 공지사항 notice Board ********************/
		else if (com.equals("/notice.do")) { // 공지사항 리스트
			service = new NoticeBoardListService();
			service.execute(request, response);
			viewPage = "board/noticeBoard.jsp";
		} else if (com.equals("/noticeContent.do")) { // 공지사항 상세보기
			service = new NoticeBoardContentService();
			service.execute(request, response);
			viewPage = "board/noticeBoardContent.jsp";
		} else if (com.equals("/noticeDelete.do")) { // 공지사항 글 삭제
			service = new NoticeBoardDeleteService();
			service.execute(request, response);
			viewPage = "notice.do";
		} else if (com.equals("/noticeModifyView.do")) { // 공지사항 글 수정 폼
			service = new NoticeBoardModifyViewService();
			service.execute(request, response);
			viewPage = "board/noticeBoardModify.jsp";
		} else if(com.equals("/noticeModify.do")) { //공지사항 글 수정 진행 
			service = new NoticeBoardModifyService(); 
			service.execute(request, response); 
			viewPage = "notice.do"; 
		} 
		/* 진행중 */
		else if (com.equals("/noticeWriteView.do")) { // 공지사항 게시글 폼
			viewPage = "board/noticeBoardWrite.jsp";
		} 
		else if (com.equals("/noticeWrite.do")) { // 공지사항 게시글 작성
			service = new NoticeBoardWriteService();
			service.execute(request, response);
			viewPage = "notice.do";
		}
		

		/***************** freeBoard 자유게시판 ***************************/
		else if (com.equals("/free.do")) { // 자유게시판 리스트
			service = new FreeBoardListService();
			service.execute(request, response);
			viewPage = "board/freeBoard.jsp";
		} else if (com.equals("/freeContent.do")) { // 자유게시판 상세보기
			service = new FreeContentService();
			service.execute(request, response);
			viewPage = "board/freeBoardContent.jsp";
		} else if (com.equals("/freeWriteView.do")) { // 자유게시판 글 쓰기 창
			viewPage = "board/freeBoardWrite.jsp";
		} else if (com.equals("/freeWrite.do")) { // 자유게시판 글 작성 성공
			service = new FreeBoardWriteService();
			service.execute(request, response);
			viewPage = "free.do";
		} else if (com.equals("/freeDelete.do")) { // 자유게시판 글 삭제
			service = new FreeBoardDeleteService();
			service.execute(request, response);
			viewPage = "free.do";
		} else if (com.equals("/freeReplyView.do")) { // 자유게시판 답글 페이지
			service = new FreeBoardReplyViewService();
			service.execute(request, response);
			viewPage = "board/freeBoardReply.jsp";
		} else if (com.equals("/freeReply.do")) { // 자유게시판 답글 작성 처리
			service = new FreeBoardReplyService();
			service.execute(request, response);
			viewPage = "free.do";
		} else if(com.equals("/freeModifyView.do")) { // 자유게시판 글 수정창 - 다시 확인하기(에러)
			 service = new FreeBoardModifyViewService(); 
			 service.execute(request, response); 
			 viewPage = "board/freeBoardModify.jsp"; 
		} else if (com.equals("/freeModify.do")) { // 자유게시판 글 수정 진행 - 다시 확인하기 (에러) service =
			 service = new FreeBoardModifyService(); 
			 service.execute(request, response); 
			 viewPage = "free.do"; 
		} 

		/****************** 점심 게시판 lunchBoard *****************/
		else if (com.equals("/lunch.do")) {
			service = new MainService();
			service.execute(request, response);
			viewPage = "lunch/lunchCalendar.jsp"; // 새창
		} else if (com.equals("/todayMenu.do")) {
			service = new TodayMenuService();
			service.execute(request, response);
			viewPage = "lunch/todayMenu.jsp";
		} 
		else if (com.equals("/lunchInView.do")) { // 급식 메뉴 입력하는 페이지
			viewPage = "lunch/lunchInput.jsp";
		}
		else if(com.equals("/lunchIn.do")) { // 급식메뉴 입력 진행 후 급식 메뉴판으로 들어가도록
			service = new LunchInService();
			service.execute(request, response);
			viewPage = "lunch.do";
		}


		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}
