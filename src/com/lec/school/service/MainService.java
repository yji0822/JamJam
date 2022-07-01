package com.lec.school.service;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.dao.LunchBoardDao;
import com.lec.school.dto.LunchBoardDto;
import com.lec.school.util.CalendarPrinter;

public class MainService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String yearParam = request.getParameter("year");
		String monthParam = request.getParameter("month");
		int year,  month;
		
		if(yearParam==null && monthParam==null) {
			Calendar cal = Calendar.getInstance();
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH)+1;
			yearParam = String.valueOf(year);
			monthParam = month<10 ? "0"+month : String.valueOf(month);
		}else {
			year = Integer.parseInt(yearParam);
			month = Integer.parseInt(monthParam);
			monthParam = month<10 ? "0"+month : String.valueOf(month);
		}
		
		System.out.println(year+"년" +month);
		CalendarPrinter calPrint = new CalendarPrinter(year, month);
		
		LunchBoardDao lunchDao = LunchBoardDao.getInstance();
		ArrayList<LunchBoardDto> dtos = lunchDao.selectMenu(yearParam, monthParam);
		
		System.out.println(dtos);
		
		request.setAttribute("dtos", dtos);
		request.setAttribute("calPrint", calPrint);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
	}

}
