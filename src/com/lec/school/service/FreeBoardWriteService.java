package com.lec.school.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.school.dao.FreeBoardDao;
import com.lec.school.dto.StudentDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FreeBoardWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 자유게시판 글 작성하는 서비스단
		
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
				String path = request.getRealPath("fileUp");
				int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
				MultipartRequest mRequest = null;
				String fFileName = "";
				try {
					mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
					Enumeration<String> params = mRequest.getFileNames();
					String param = params.nextElement();
					fFileName = mRequest.getFilesystemName(param);
					
					// mId, fTitle, fContent,  fileName, fIp
					HttpSession httpSession = request.getSession();
					StudentDto student = (StudentDto)httpSession.getAttribute("student");
					
					if(student != null) {
						String sId = student.getsId(); // 로그인 한 사람의 Id
						String fTitle = mRequest.getParameter("fTitle");
						String fContent = mRequest.getParameter("fContent");
						String fIp = request.getRemoteAddr();	
						FreeBoardDao fboardDao = FreeBoardDao.getInstance();
						int result = fboardDao.writeBoard(sId, fTitle, fContent, fFileName, fIp);
						// joinMember결과에 따라 적절히 request.setAttribute
						
						if(result == FreeBoardDao.SUCCESS) { // 글 쓰기 진행
							request.setAttribute("fboaredResult", "글쓰기 성공");
						}else {
							request.setAttribute("fboaredResult", "글쓰기 실패");
						}
					}else {
						request.setAttribute("fboaredResult", "로그인 한 사람만 글쓸 수 있어요");
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
					request.setAttribute("fboaredResult", "글쓰기 실패 + exception");
				}
				// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
				if(fFileName!=null) {
					InputStream  is = null;
					OutputStream os = null;
					try {
						File serverFile = new File(path+"/"+fFileName);
						is = new FileInputStream(serverFile);
						os = new FileOutputStream("C:\\Users\\dbswj\\Desktop\\WebProgramming\\Source\\00_semiProject\\School\\WebContent\\fileUp\\"+fFileName);
						byte[] bs = new byte[(int)serverFile.length()];
						
						while(true) {
							int nByteCnt = is.read(bs);
							if(nByteCnt == -1) break;
							os.write(bs, 0, nByteCnt);
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					} finally {
						try {
							if(os!=null) os.close();
							if(is!=null) is.close();
						} catch (Exception e) {
							System.out.println(e.getMessage() + "파일 복사 실패");
						}
					} // try
				}// 파일 복사 if
	}

}
