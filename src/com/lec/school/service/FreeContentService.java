package com.lec.school.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.dao.FreeBoardDao;
import com.lec.school.dto.FreeBoardDto;

public class FreeContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// BoardContentService.java
		
		int fNo = Integer.parseInt(request.getParameter("fNo"));
		System.out.println("fNo" + fNo);
		
		FreeBoardDao fboardDao = FreeBoardDao.getInstance();
		FreeBoardDto fboard = fboardDao.contentView(fNo);
		System.out.println("contentView");

		request.setAttribute("fboard", fboard);
		System.out.println("setAttribute" + fboard);

	}

}
