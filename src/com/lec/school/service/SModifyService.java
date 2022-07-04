package com.lec.school.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

public class SModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String path = request.getRealPath("fileUp");
		System.out.println("fileup path");
		int maxSize = 1024*1024*5; // 최대 업로드 사이즈 : 5M
		String sPhoto = "";
		System.out.println("sPhoto");
		try {
			// mRequest 객체 생성한 후 파일 이름 받아오기
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, 
									"utf-8", new DefaultFileRenamePolicy());
			
			System.out.println("multipartRequest");
			Enumeration<String> params = mRequest.getFileNames(); 
			//while(params.hasMoreElements()) {
				String param = params.nextElement(); // mPhoto
				System.out.println("param + sPhoto 호출");
				sPhoto = mRequest.getFilesystemName(param);
				System.out.println("sPhoto 넣ㅎ어주기");
			//}

			// 파라미터값 다 받아오기 -> DB에 넣기
			String sId = mRequest.getParameter("sId");
			String sPw = mRequest.getParameter("sPw");
			String sName = mRequest.getParameter("sName");
			int sNo = Integer.parseInt(mRequest.getParameter("sNo"));
			String sTel = mRequest.getParameter("sTel");
			String sEmail = mRequest.getParameter("sEmail");
			String sGender = mRequest.getParameter("sGender");
			System.out.println("getParameter 호출 성공");
			
			// 정보수정시 사진파일을 첨부안하면 dbmPhoto(원래 사진파일)로 저장
			String dbsPhoto = mRequest.getParameter("dbsPhoto");
			sPhoto = (sPhoto==null)? dbsPhoto : sPhoto;
			
			//HttpSession session = request.getSession();
			//mPhoto = (mPhoto==null)? ((MemberDto)session.getAttribute("member")).getmPhoto() : mPhoto;
			String sBirthStr = mRequest.getParameter("sBirth");
			Date sBirth = null;
			if(!sBirthStr.equals("")) {
				sBirth = Date.valueOf(sBirthStr);
				System.out.println("생일 형변환 성공");
			}
			StudentDao sDao = StudentDao.getInstance();
			// 회원정보 수정
			StudentDto student = new StudentDto(sId, sPw, sName, sNo, sTel, sEmail, sGender, sBirth, sPhoto, 0);
			System.out.println("dto 호출 성공");
			int result = sDao.modify(student);
			System.out.println("수정진행 성공");
			
			if(result == StudentDao.SUCCESS) {// 수정 성공시 세션도 수정
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("student", student);
				request.setAttribute("modifyResult", "회원정보 수정 성공");
				System.out.println("정보 수정 성공 if");
			}else {
				// 수정 실패시 
				request.setAttribute("modifyResult", "회원정보 수정 실패");
				System.out.println("정보 수정 실패 else");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// 업로드된 파일을 소스폴더로 복사
		File serverFile = new File(path+"/"+sPhoto);
		if(!sPhoto.equals("noimg.png") && serverFile.exists()) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("C:\\Users\\dbswj\\Desktop\\WebProgramming\\Source\\00_semiProject\\School\\WebContent\\fileUp\\"+sPhoto);
				byte[] bs = new byte[(int)serverFile.length()];
				
				while(true) {
					int readbyteCnt = is.read(bs);
					if(readbyteCnt == -1) break;
					os.write(bs, 0, readbyteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				} catch (Exception e) {}
			}//try-catch-finally
		}
	}

}
