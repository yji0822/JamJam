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
		System.out.println("1");
		if(pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		System.out.println("2");
		final int PAGESIZE = 3, BLOCKSIZE=5;
		
		int startRow = (currentPage-1)*PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		
		// dao의 list(startRow, endRow) 실행결과를 request.setAttribute
		StudentDao sDao = StudentDao.getInstance();
		System.out.println("2getinstance");
		ArrayList<StudentDto> students = sDao.allList(startRow, endRow);
		System.out.println("dao all list");
		request.setAttribute("sAllView", students);
		System.out.println("setattribute");
		
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
		System.out.println("pageCnt print" + pageCnt);
		request.setAttribute("startPage", startPage);
		System.out.println("startPage" + startPage);
		request.setAttribute("endPage", endPage);
		System.out.println("endPage" + endPage);
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		System.out.println("blockSize" + BLOCKSIZE);
		request.setAttribute("pageNum", currentPage);
	}

}
