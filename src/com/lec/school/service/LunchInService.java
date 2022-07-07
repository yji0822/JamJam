package com.lec.school.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.school.dao.LunchBoardDao;
import com.lec.school.dto.AdminDto;
import com.lec.school.dto.LunchBoardDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class LunchInService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		LunchBoardDao lunchDao = LunchBoardDao.getInstance();

		String path = request.getRealPath("lunchPic"); // 서버에 저장될 폴더명은 lunchPic
		int maxSize = 1024*1024*5; // 최대업로드 사이즈는 5M
		MultipartRequest mRequest = null;
		String photo = "";
		
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			photo = mRequest.getFilesystemName(param);
		
			HttpSession httpSession = request.getSession();
			AdminDto admin = (AdminDto)httpSession.getAttribute("admin");
			
			if(admin != null) {
				Date ldate = null;
				if(!mRequest.getParameter("ldate").equals("")) {
					ldate = Date.valueOf(mRequest.getParameter("ldate"));
				}
				String ampm = mRequest.getParameter("ampm");
				String menu = mRequest.getParameter("menu");
				double calorie = Double.parseDouble(mRequest.getParameter("calorie"));
				
				LunchBoardDto dto = new LunchBoardDto(ldate, ampm, menu, calorie, photo);
				LunchBoardDao lboardDao = LunchBoardDao.getInstance();
				int result = lboardDao.lunchIn(dto);
				
				if( result == LunchBoardDao.SUCCESS) {
					request.setAttribute("lunchInResult", "메뉴 등록 성공");
				} else {
					request.setAttribute("lunchInResult", "메뉴 등록 실패");
				}
			} else {
				request.setAttribute("lunchInResult", "관리자만 등록이 가능합니다.");
			}
		
		} catch (IOException e) {
			System.out.println(e.getMessage() + "메뉴 등록 실패입니다. 확인 다시 하세요");
		} //try-catch
		
		
		
		// 서버에 올라간 fileUp photo파일을 소스폴더에 filecopy
				if(photo!=null) {
					InputStream  is = null;
					OutputStream os = null;
					try {
						File serverFile = new File(path+"/"+photo);
						is = new FileInputStream(serverFile);
						os = new FileOutputStream("C:\\Users\\dbswj\\Desktop\\WebProgramming\\Source\\00_semiProject\\School\\WebContent\\lunchPic\\"+photo);
						byte[] bs = new byte[(int)serverFile.length()];
						
						while(true) {
							int nByteCnt = is.read(bs);
							if(nByteCnt==-1) break;
							os.write(bs, 0, nByteCnt);
						}
					} catch (Exception e) {
						System.out.println(e.getMessage() + "파일복사 실패");
					} finally {
						try {
							if(os!=null) os.close();
							if(is!=null) is.close();
						} catch (Exception e) {
							System.out.println(e.getMessage() + "파일입출력 오류 close가 되지 않습니다.");
						}
					} // try
				}// 파일 복사 if
		
			
	}

}
