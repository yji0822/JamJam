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
import com.lec.school.dto.NoticeBoardDto;

public class NoticeBoardDao {
	
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static NoticeBoardDao instance = new NoticeBoardDao();
	public static NoticeBoardDao getInstance() {
		return instance;
	}
	private NoticeBoardDao() {}
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
	} // getConnextion
	
	
	// (1) 글목록(startRow부터 endRow까지) - 글번호, 작성자이름, ...
		public ArrayList<NoticeBoardDto> listBoard(int startRow, int endRow){
			
			ArrayList<NoticeBoardDto> dtos = new ArrayList<NoticeBoardDto>();
			
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			
			String sql = "SELECT * FROM " + 
					"    (SELECT ROWNUM RN, A.* FROM " + 
					"    (SELECT N.*, ANAME FROM NOTICE_BOARD N, ADMIN AD WHERE N.AID = AD.AID " + 
					"                ORDER BY NNO DESC) A)" + 
					"    WHERE RN BETWEEN ? AND ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				rs = pstmt.executeQuery();
			
				while(rs.next()) {
					 int nNo = rs.getInt("nNo");
					 String aName = rs.getString("aName");
					 String nTitle = rs.getString("nTitle");
					 String nContent = rs.getString("nContent");
					 Date   nRdate = rs.getDate("nRdate");
					 int    nHit = rs.getInt("nHit");
					 
					 dtos.add(new NoticeBoardDto(nNo, null, aName, nTitle, nContent, nRdate, nHit));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(rs   !=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(conn !=null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage() + "catch에서 빠져버림 ㅠ");
					}
			}
			return dtos;
		}
		// (2) 글갯수
		public int getBoardTotCnt() {
			int cnt = 0;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT COUNT(*) FROM NOTICE_BOARD";
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
		public int writeBoard(String aId, String nTitle, String nContent) {
			
			int result = FAIL;
			
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			
			String sql = "INSERT INTO NOTICE_BOARD (NNO, AID, NTITLE, NCONTENT)" + 
					"    VALUES (N_SEQ.NEXTVAL, ?, ?, ?)";
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, aId);
				pstmt.setString(2, nTitle);
				pstmt.setString(3, nContent);
				
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
		private void hitUp(int nNo) {
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			
			String sql = "UPDATE NOTICE_BOARD SET NHIT = NHIT +1 WHERE NNO = ?";
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, nNo);
				
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
		
		// (5) bId로 글 dto보기
		public NoticeBoardDto contentView(int nNo) {
			
			hitUp(nNo);
			NoticeBoardDto dto = null;
			
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			
			String sql = "SELECT N.*, ANAME FROM NOTICE_BOARD N, ADMIN A WHERE N.AID = A.AID AND NNO = ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, nNo);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					 //int nNo = rs.getInt("mId");
					 String aId = rs.getString("aId");
					 String aName = rs.getString("aName");
					 String nTitle = rs.getString("nTitle");
					 String nContent = rs.getString("nContent");
					 Date   nRdate = rs.getDate("nRdate");
					 int    nHit = rs.getInt("nHit");
					 
					 dto = new NoticeBoardDto(nNo, aId, aName, nTitle, nContent, nRdate, nHit);
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

		// (6) 글 수정하기(nno, ntitle, ncontent, nrdate)
		public int modifyBoard(int nNo, String nTitle, String nContent) {
			
			int result = FAIL;
			
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			
			
			String sql = "UPDATE NOTICE_BOARD SET NTITLE = ?," + 
					"                             NCONTENT = ?," + 
					"                             NRDATE = SYSDATE" + 
					"            WHERE NNO = ?";

			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, nTitle);
				pstmt.setString(2, nContent);
				pstmt.setInt(5, nNo);
				
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
		public int deleteBoard(int nNo) {
			
			int result = FAIL;
			
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			
			String sql = "DELETE FROM NOTICE_BOARD WHERE NNO = ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(5, nNo);
				
				result = pstmt.executeUpdate();
				System.out.println(result >= SUCCESS? "글삭제성공":"글삭제실패");
			
				result = result >= 1? SUCCESS : FAIL;
			
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


}
