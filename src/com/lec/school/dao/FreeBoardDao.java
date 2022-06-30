package com.lec.school.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lec.school.dto.FreeBoardDto;

public class FreeBoardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static FreeBoardDao instance = new FreeBoardDao();
	public static FreeBoardDao getInstance() {
		return instance;
	}
	private FreeBoardDao() {}
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	// (1) 글목록(startRow부터 endRow까지) - 글번호, 작성자이름, ...
	public ArrayList<FreeBoardDto> listBoard(int startRow, int endRow){
		
		ArrayList<FreeBoardDto> dtos = new ArrayList<FreeBoardDto>();
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM " + 
				"    (SELECT ROWNUM RN, A.* FROM " + 
				"    (SELECT F.*, SNAME FROM FREE_BOARD F, STUDENT S WHERE F.SID=S.SID " + 
				"                ORDER BY FREF DESC, FSTEP) A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 int fNo  = rs.getInt("fNo");
				 String sId = rs.getString("sId");
				 String fTitle = rs.getString("fTitle");
				 String fContent = rs.getString("fContent");
				 String fFilename = rs.getString("fFilename");
				 Date fRdate = rs.getDate("fRdate");
				 int fHit = rs.getInt("fHit");
				 int fRef = rs.getInt("fRef");
				 int fStep = rs.getInt("fStep");
				 int fIndent = rs.getInt("fIndent");
				 String fIp = rs.getString("fIp");
				 
				 dtos.add(new FreeBoardDto(fNo, sId, fTitle, fContent, 
						 		fFilename, fRdate, fHit, fRef, fStep, fIndent, fIp));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return dtos;
	}
	// (2) 글갯수
	public int getBoardTotCnt() {
		
		int cnt = 0;
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM FREE_BOARD";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return cnt;
	}
	// (3) 글쓰기(원글)
	public int writeBoard(String sId, String fTitle, String fContent, String fFileName,
			String fIp) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FREE_BOARD (FNO, SID, FTITLE, FCONTENT, FFILENAME, " + 
				"        FREF, FSTEP, fINDENT, FIP)" + 
				"    VALUES (F_SEQ.NEXTVAL, ?, ?, ?, ?, " + 
				"        F_SEQ.CURRVAL, 0, 0, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sId);
			pstmt.setString(2, fTitle);
			pstmt.setString(3, fContent);
			pstmt.setString(4, fFileName);
			pstmt.setString(5, fIp);
			
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "원글쓰기성공":"원글쓰기실패");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return result;
	}
	// (4) FHit 하나 올리기(1번글 조회수 하나 올리기)
	private void hitUp(int fId) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FREE_BOARD SET FHIT = FHIT +1 WHERE FNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
	}
	// (5) bId로 글 dto보기 : 글 상세보기(조회수 up + bid로 dto리턴)
	public FreeBoardDto contentView(int fId) {
		
		hitUp(fId); 
		FreeBoardDto dto = null;
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT F.*, SNAME FROM FREE_BOARD F, STUDENT S "
				+ "WHERE F.SID = S.SID AND FNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fId);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				String sId   = rs.getString("sId");
				String sName = rs.getString("sName"); // join해서 출력
				String fTitle= rs.getString("fTitle");
				String fContent= rs.getString("fContent");
				String fFileName= rs.getString("fFileName");
				Date   fRdate   = rs.getDate("fRdate");
				int    fHit    = rs.getInt("fHit");
				int    fGroup  = rs.getInt("fGroup");
				int    fStep   = rs.getInt("fStep");
				int    fIndent = rs.getInt("fIndent");
				String fIp     = rs.getString("fIp");
				dto = new FreeBoardDto(fId, sId, sName, fTitle, fContent, fFileName, 
						fRdate, fHit, fGroup, fStep, fIndent, fIp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return dto;
	}
	// (5) fNo로 글 dto보기 : 답변글 view + 수정 view (fno로 dto리턴)
	public FreeBoardDto modifyView_replyView(int fNo) {
		
		FreeBoardDto dto = null;
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		
		String sql = "SELECT F.*, SNAME FROM FREE_BOARD F, STUDENT S "
				+ "WHERE F.SID = S.SID AND FNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String sId   = rs.getString("sId");
				String sName = rs.getString("sName"); 
				String fTitle= rs.getString("fTitle");
				String fContent= rs.getString("fContent");
				String fFileName= rs.getString("fFileName");
				Date   fRdate   = rs.getDate("fRdate");
				int    fHit    = rs.getInt("fHit");
				int    fGroup  = rs.getInt("fGroup");
				int    fStep   = rs.getInt("fStep");
				int    fIndent = rs.getInt("fIndent");
				String fIp     = rs.getString("fIp");
				dto = new FreeBoardDto(fNo, sId, sName, fTitle, fContent, fFileName, 
						fRdate, fHit, fGroup, fStep, fIndent, fIp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return dto;
	}
	// (6) 글 수정하기(FId, FTitle, FContent, FILENAME,  FIp, FDATE)
	public int modifyBoard(int fNo, String fTitle, String fContent, String fFileName, String fIp) {
		
		int result = FAIL;
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE FREE_BOARD SET FTITLE = ?," + 
				"                    FCONTENT = ?," + 
				"                    FFILENAME = ?," + 
				"                    FIP = ?, " + 
				"                    FRDATE = SYSDATE " + 
				"            WHERE FNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fTitle);
			pstmt.setString(2, fContent);
			pstmt.setString(3, fFileName);
			pstmt.setString(4, fIp);
			pstmt.setInt(5, fNo);
			
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "글수정성공":"글수정실패");
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return result;
	}
	// (7) 글 삭제하기(bId로 삭제하기)
	public int deleteBoard(int fRef, int fStep, int fIndent) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM FREE_BOARD WHERE FREF = ? AND (FSTEP >= ? AND " + 
						"    FSTEP<(select NVL(MIN(FSTEP),9999) FROM FREE_BOARD WHERE FREF = ? "
						+ "  AND FSTEP > ? AND FINDENT <= ?))";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fRef);
			pstmt.setInt(2, fStep);
			pstmt.setInt(3, fRef);
			pstmt.setInt(4, fStep);
			pstmt.setInt(5, fIndent);
			
			result = pstmt.executeUpdate();
			System.out.println(result>=SUCCESS? "글삭제성공":"글삭제실패");
			result = result>=1? SUCCESS : FAIL;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return result;
	}
	// (8) 답변글 추가전 STEP a 수행
	private void preReplyStepA(int fRef, int fStep) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE FREE_BOARD SET FSTEP = FSTEP+1 " + 
				"    WHERE FREF = ? AND FSTEP > ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fRef);
			pstmt.setInt(2, fStep);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
	}
	// (9) 답변글 쓰기
	public int replyBoard(String sId, String fTitle, String fContent,
			String fFileName, String fIp,
			int fRef, int fStep, int fIndent) {
		preReplyStepA(fRef, fStep); // 답변글 저장전 step A 먼저 실행
		// bgroup, bstep, bindent 원글정보
		// bname, btitle, bcontent, bip 답변글 정보
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO FREE_BOARD (FNO, SID, FTITLE, FCONTENT, FFILENAME," + 
				"        FREF, FSTEP, FINDENT, FIP)" + 
				"    VALUES (F_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sId);
			pstmt.setString(2, fTitle);
			pstmt.setString(3, fContent);
			pstmt.setString(4, fFileName);
			pstmt.setInt(5, fRef);
			pstmt.setInt(6, fStep + 1);
			pstmt.setInt(7, fIndent + 1);
			pstmt.setString(8, fIp);
			
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "답변쓰기성공":"답변쓰기실패");
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return result;
	}
	
	// (10) 회원탈퇴 하려는 사람이 쓴 글 모두 삭제 후 탈퇴
		public void withdrawalDeleteBoard(String sId) {
			int result = FAIL;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "DELETE FROM FILEBOARD WHERE SID = ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, sId);
				pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(pstmt != null) pstmt.close();
					if(conn  != null) conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	
}
