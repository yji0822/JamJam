package com.lec.school.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.dao.LunchBoardDao;

public class LunchInService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		LunchBoardDao lunchDao = LunchBoardDao.getInstance();


	}

}
