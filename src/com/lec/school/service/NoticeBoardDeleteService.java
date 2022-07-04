package com.lec.school.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.dao.NoticeBoardDao;

public class NoticeBoardDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 공지사항 글 삭제 로직
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		
		NoticeBoardDao nboardDao = NoticeBoardDao.getInstance();
		int result = nboardDao.deleteBoard(nNo);
		
		if(result == NoticeBoardDao.SUCCESS) {
			request.setAttribute("nboaredResult", "공지글 삭제 성공");
		}else {
			request.setAttribute("nboaredResult", "공지글 삭제 실패");
		}

	}

}
