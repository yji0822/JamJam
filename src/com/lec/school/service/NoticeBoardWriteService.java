package com.lec.school.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.school.dao.NoticeBoardDao;
import com.lec.school.dto.AdminDto;
import com.lec.school.dto.StudentDto;

public class NoticeBoardWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 공지사항 적는 게시판 - 파일첨부 x
		
		HttpSession session = request.getSession();
		AdminDto admin = (AdminDto) session.getAttribute("admin");

		if(admin != null) {
			String aId = admin.getaId();
			String nTitle = request.getParameter("nTitle");
			String nContent = request.getParameter("nContent");
			
			NoticeBoardDao nbDao = NoticeBoardDao.getInstance();
			int result = nbDao.writeBoard(aId, nTitle, nContent);
			
			if(result == NoticeBoardDao.SUCCESS) {
				request.setAttribute("nboardResult", "공지사항 작성 성공");
			} else {
				request.setAttribute("nboardResult", "공지사항 작성 실패");
			}
		} else {
			request.setAttribute("nboardResult", "관리자만 글 작성 가능합니다.");
		}

	}

}
