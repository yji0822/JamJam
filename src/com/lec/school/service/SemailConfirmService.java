package com.lec.school.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.dao.StudentDao;

public class SemailConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String sEmail = request.getParameter("sEmail");
		StudentDao sDao = StudentDao.getInstance();
		int result = sDao.emailConfirm(sEmail); // 이메일 중복체크
		
		if(result == StudentDao.NONEXISTENT) {
			request.setAttribute("emailConfirmResult", "사용 가능한 메일입니다");
		}else {
			request.setAttribute("emailConfirmResult", "<b>사용 불가한 중복된 메일</b>");
		}
	}

}
