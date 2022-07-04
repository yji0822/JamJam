package com.lec.school.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.dao.FreeBoardDao;
import com.lec.school.dto.FreeBoardDto;

public class FreeBoardModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 자유게시판 글 수정 서비스 진행 후 글 수정창으로 
		int fNo = Integer.parseInt(request.getParameter("fNo"));
		String fTitle = request.getParameter("fTitle");
		String fContent = request.getParameter("fContent");
		String fFileName = request.getParameter("fFileName");
		String fIp = request.getRemoteAddr();
		
		FreeBoardDao fboardDao = FreeBoardDao.getInstance();
		request.setAttribute("modifyResult", fboardDao.modifyBoard(fNo, fTitle, fContent, fFileName, fIp));
	}

}
