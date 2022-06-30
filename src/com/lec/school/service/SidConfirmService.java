package com.lec.school.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.dao.StudentDao;

public class SidConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String sId = request.getParameter("sId");
		StudentDao sDao = StudentDao.getInstance();
		// ID 중복체크
		int result = sDao.sIdConfirm(sId);
		if(result == StudentDao.NONEXISTENT) {
			
			request.setAttribute("idConfirmResult", "사용 가능한 ID");
		}else {
			request.setAttribute("idConfirmResult", "<b>중복된 ID</b>");
		}
	}

}
