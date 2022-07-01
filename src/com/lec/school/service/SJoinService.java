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

import com.lec.school.dao.StudentDao;
import com.lec.school.dto.StudentDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class SJoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String path = request.getRealPath("fileUp");
		int maxSize = 1024*1024*3; // 사진 업로드 제한 용량 : 1M
		String sPhoto = ""; // 첨부된 파일이 저장된 파일 이름
		try {
			// mRequest 객체 생성 후 mPhoto 파일이름 얻어옴
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize,
											"utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			//while(params.hasMoreElements()) {
				String param = params.nextElement();
				sPhoto = mRequest.getFilesystemName(param);
			//}
			// mRequest을 이용하여 파라미터 받아와서 DB 저장
			String sId = mRequest.getParameter("sId");
			System.out.println("sid");
			String sPw = mRequest.getParameter("sPw");
			System.out.println("spw");
			String sName = mRequest.getParameter("sName");
			System.out.println(sName);
			int sNo = Integer.parseInt(mRequest.getParameter("sNo"));
			System.out.println("sno");
			String sTel = mRequest.getParameter("sTel");
			System.out.println("stel");
			String sEmail = mRequest.getParameter("sEmail");
			System.out.println("semail");
			String sGender = mRequest.getParameter("sGender");
			System.out.println("sgender");
			sPhoto = sPhoto == null ? "NOIMG.JPG" : sPhoto;
			String sBirthStr = mRequest.getParameter("sBirth");
			Date sBirth = null;
			if(!sBirthStr.equals("")) {
				sBirth = Date.valueOf(sBirthStr);
			} else {
				System.out.println("3");
			}
			
			StudentDao sDao = StudentDao.getInstance();
			StudentDto student = new StudentDto(sId, sPw, sName, sNo, sTel, sEmail, sGender, sBirth, sPhoto, 0);
			// 회원가입
			int result = sDao.join(student);
			if(result == StudentDao.SUCCESS) {
				HttpSession session = request.getSession(); // 세션은 request로 부터
				session.setAttribute("sId", sId);
				request.setAttribute("joinResult", "회원가입이 완료되었습니다");
			}else {
				request.setAttribute("joinErrorMsg", "정보가 너무 길어서 회원가입 실패");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
		// 서버에 업로드된 파일을 소스 폴더로 복사
		File serverFile = new File(path + "/" + sPhoto);
		if(serverFile.exists() && !sPhoto.equals("NOIMG.JPG")) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("C:\\Users\\dbswj\\Desktop\\WebProgramming\\Source\\00_semiProject\\School\\WebContent\\fileUp\\"+sPhoto);
				byte[] bs = new byte[(int) serverFile.length()];
				while(true) {
					int readByteCnt = is.read(bs);
					if(readByteCnt==-1) break;
					os.write(bs, 0, readByteCnt);
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				} catch (IOException e) {
					// TODO: handle exception
				}
			}
		}
	}


}
