package com.lec.school.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.school.dao.StudentDao;
import com.lec.school.dto.StudentDto;

public class SLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String sId = request.getParameter("sId");
		String sPw = request.getParameter("sPw");
		
		StudentDao sDao = StudentDao.getInstance();
		int result = sDao.loginCheck(sId, sPw);
		
		if(result == StudentDao.LOGIN_SUCCESS) { // 로그인 성공
			HttpSession session = request.getSession();
			StudentDto student = sDao.getMember(sId);
			session.setAttribute("student", student);
		}else { // 로그인 실패
			request.setAttribute("loginErrorMsg", "아이디, 비번을 확인하세요!");
		}

	}

}
