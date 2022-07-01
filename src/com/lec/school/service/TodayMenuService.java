package com.lec.school.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.dao.LunchBoardDao;

public class TodayMenuService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		LunchBoardDao lunchDao = LunchBoardDao.getInstance();
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day =  request.getParameter("day");
		String ampm = request.getParameter("ampm");
		request.setAttribute("lunch", lunchDao.todayMenu(year, month, day, ampm));
	}

}
