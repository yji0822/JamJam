package com.lec.school.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.dao.NoticeBoardDao;
import com.lec.school.dto.NoticeBoardDto;

public class NoticeBoardModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 공지사항 게시판 수정 화면으로 넘어가는 창 
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		NoticeBoardDao nboardDao = NoticeBoardDao.getInstance();
		request.setAttribute("nboard", nboardDao.modifyView_replyView(nNo));

	}

}
