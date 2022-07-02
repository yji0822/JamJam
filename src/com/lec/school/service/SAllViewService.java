package com.lec.school.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.school.dao.StudentDao;
import com.lec.school.dto.StudentDto;

public class SAllViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		// pageNum받고, startRow, endRow 계산해서
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 3, BLOCKSIZE=5;
		
		int startRow = (currentPage-1)*PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		
		// dao의 list(startRow, endRow) 실행결과를 request.setAttribute
		StudentDao sDao = StudentDao.getInstance();
		ArrayList<StudentDto> students = sDao.allList(startRow, endRow);
		request.setAttribute("sAllView", students);
		
		// totCnt, pageCnt, startPage, endPage, BLOCKSIZE, pageNum
		//    => request.setAttribute
		int totCnt = sDao.getStudenttTotCnt();
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE +1;
		int endPage   = startPage + BLOCKSIZE -1 ;
		if(endPage > pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("pageNum", currentPage);
	}

}
