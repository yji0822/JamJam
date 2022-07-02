package com.lec.school.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.dao.NoticeBoardDao;
import com.lec.school.dto.NoticeBoardDto;

public class NoticeContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 공지사항 상세보기
		
		int nNo = Integer.parseInt(request.getParameter("nNo"));
		System.out.println("nNo" + nNo);
		
		NoticeBoardDao nboardDao = NoticeBoardDao.getInstance();
		NoticeBoardDto nboard = nboardDao.contentView(nNo);
		System.out.println("contentView");

		request.setAttribute("nboard", nboard);
		System.out.println("setAttribute" + nboard);

	}

}
