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

public class FreeBoardReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 답글 게시판 작성 후 처리 로직
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
				String path = request.getRealPath("fileUp");
				int maxSize = 1024*1024*5; // 최대업로드 사이즈는 5M
				String fFilename = "";
				try {
					MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
					Enumeration<String> params = mRequest.getFileNames();
					String param = params.nextElement();
					fFilename = mRequest.getFilesystemName(param);
					
					// mId, fTitle, fContent,  fileName, fIp
					HttpSession httpSession = request.getSession();
					String sId = ((StudentDto)httpSession.getAttribute("student")).getsId();
					
					String fTitle = mRequest.getParameter("fTitle");
					String fContent = mRequest.getParameter("fContent");
					String fIp = request.getRemoteAddr();
					int fRef = Integer.parseInt(mRequest.getParameter("fRef"));
					int fStep = Integer.parseInt(mRequest.getParameter("fStep"));
					int fIndent = Integer.parseInt(mRequest.getParameter("fIndent"));
					FreeBoardDao fboardDao = FreeBoardDao.getInstance();
					int result = fboardDao.replyBoard(sId, fTitle, fContent, fFilename, fIp, fRef, fStep, fIndent);
					
					// joinMember결과에 따라 적절히 request.setAttribute
					if(result == FreeBoardDao.SUCCESS) { // 댓글 게시판 작성 진행
						request.setAttribute("fboardResult", "답글 게시판 작성 성공");
					}else {
						request.setAttribute("fboardResult", "답글 게시판 작성 실패");
					}
					
					
					// mRequest에서 넘어온 pageNum(mRequest를 사용하면 request의 파라미터들이 다 null이 됨)을 request에 set
					request.setAttribute("pageNum", mRequest.getParameter("pageNum"));
				} catch (IOException e) {
					System.out.println(e.getMessage());
					request.setAttribute("boaredResult", "답글쓰기 실패");
				}
				// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
				if(fFilename!=null) {
					InputStream  is = null;
					OutputStream os = null;
					
					try {
						File serverFile = new File(path+"/"+fFilename);
						is = new FileInputStream(serverFile);
						os = new FileOutputStream("C:\\Users\\dbswj\\Desktop\\WebProgramming\\Source\\00_semiProject\\School\\WebContent\\fileUp\\"+fFilename);
						byte[] bs = new byte[(int)serverFile.length()];
						while(true) {
							int nByteCnt = is.read(bs);
							if(nByteCnt==-1) break;
							os.write(bs, 0, nByteCnt);
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					} finally {
						try {
							if(os!=null) os.close();
							if(is!=null) is.close();
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} // try
				}// 파일 복사 if
		

	}

}
