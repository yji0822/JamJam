package com.lec.school.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.school.dao.NoticeBoardDao;

public class NoticeBoardModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		// 공지사항 글 로직 수정 진행
		System.out.println("서비스 로직 넘어오기 성공");
		// nNo, aName, nTitle, nContenet

		/*
		 * int pageNum = Integer.parseInt(request.getParameter("pageNum")); 
		 * String aName = request.getParameter("aName"); String aId = request.getParameter("aId");
		 */

		int nNo = Integer.parseInt(request.getParameter("nNo"));
		String nTitle = request.getParameter("nTitle");
		String nContent = request.getParameter("nContent");

		NoticeBoardDao nboardDao = NoticeBoardDao.getInstance();
		int result = nboardDao.modifyBoard(nNo, nTitle, nContent);

		if (result == NoticeBoardDao.SUCCESS) { // 글 수정
			request.setAttribute("nboardResult", "글수정 성공");
		} else {
			request.setAttribute("nboardResult", "글수정 실패");
		}

	}

}
