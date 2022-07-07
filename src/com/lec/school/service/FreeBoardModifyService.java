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

import com.lec.school.dao.FreeBoardDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FreeBoardModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		// 자유게시판 수정글 작성 후 완료 로직 수행
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("fileUp");
		int maxSize = 1024 * 1024 * 10; // 최대업로드 사이즈는 10M
		String fFilename = "";
		String dbFileName = null;

		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			fFilename = mRequest.getFilesystemName(param);
			dbFileName = mRequest.getParameter("dbFileName");
			
			if (fFilename == null) {
				fFilename = dbFileName;
			}
			
			// mId, fTitle, fContent, fileName, fIp
			int fNo = Integer.parseInt(mRequest.getParameter("fNo"));
			String fTitle = mRequest.getParameter("fTitle");
			String fContent = mRequest.getParameter("fContent");
			String fIp = request.getRemoteAddr();
			
			FreeBoardDao fboardDao = FreeBoardDao.getInstance();
			int result = fboardDao.modifyBoard(fNo, fTitle, fContent, fFilename, fIp);
			
			if (result == FreeBoardDao.SUCCESS) { // 자유게시판 글 수정 진행 확인
				request.setAttribute("fboardResult", "글수정 성공");
			} else {
				request.setAttribute("fboardResult", "글수정 실패");
			}
			// mRequest에서 넘어온 pageNum(mRequest를 사용하면 request의 파라미터들이 다 null이 됨)을 request에
			// set
			request.setAttribute("pageNum", mRequest.getParameter("pageNum"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("fboardResult", "글수정 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy (파일 수정을 안 했거나, 예외가 떨어질 경우 복사 안 함)
		if (dbFileName != null && !fFilename.equals(dbFileName)) {
			InputStream is = null;
			OutputStream os = null;
			try {
				File serverFile = new File(path + "/" + fFilename);
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("C:\\Users\\dbswj\\Desktop\\WebProgramming\\Source\\00_semiProject\\School\\WebContent\\fileUp\\" + fFilename);
				byte[] bs = new byte[(int) serverFile.length()];
				
				while (true) {
					int nByteCnt = is.read(bs);
					if (nByteCnt == -1)
						break;
					os.write(bs, 0, nByteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if (os != null)
						os.close();
					if (is != null)
						is.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} // try
		} // 파일 복사 if

	}

}
