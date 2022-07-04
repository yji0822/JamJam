package com.lec.school.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.dao.FreeBoardDao;
import com.lec.school.dto.FreeBoardDto;

public class FreeBoardReplyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 자유게시판 - 답글 다는 게시판 로직
		
		int fNo = Integer.parseInt(request.getParameter("fNo")); // 글번호 전달받기
		
		FreeBoardDao fboardDao = FreeBoardDao.getInstance();
		FreeBoardDto originBoard = fboardDao.modifyView_replyView(fNo);
		
		request.setAttribute("originBoard", originBoard); // 저장하기 

	}

}
