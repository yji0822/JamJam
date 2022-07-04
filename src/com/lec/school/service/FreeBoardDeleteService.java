package com.lec.school.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.dao.FreeBoardDao;

public class FreeBoardDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 자유게시판 글 삭제 로직
		
		int fRef = Integer.parseInt(request.getParameter("fRef"));
		int fStep = Integer.parseInt(request.getParameter("fStep"));
		int fIndent = Integer.parseInt(request.getParameter("fIndent"));
		
		FreeBoardDao fboardDao = FreeBoardDao.getInstance();
		int result = fboardDao.deleteBoard(fRef, fStep, fIndent); // 글 삭제 로직
		
		if(result == FreeBoardDao.SUCCESS) {
			request.setAttribute("fboaredResult", "글 삭제 성공");
		}else {
			request.setAttribute("fboaredResult", "글 삭제 실패");
		}

	}

}
