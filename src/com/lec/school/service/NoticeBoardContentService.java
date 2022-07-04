package com.lec.school.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.dao.NoticeBoardDao;
import com.lec.school.dto.NoticeBoardDto;

public class NoticeBoardContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 공지사항 상세보기

		int nNo = Integer.parseInt(request.getParameter("nNo"));

		NoticeBoardDao nboardDao = NoticeBoardDao.getInstance();
		NoticeBoardDto nboard = nboardDao.contentView(nNo);

		request.setAttribute("nboard", nboard);
		System.out.println("setAttribute" + nboard);

	}

}
