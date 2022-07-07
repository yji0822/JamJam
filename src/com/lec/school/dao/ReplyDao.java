package com.lec.school.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lec.school.dto.ReplyDto;

public class ReplyDao {
	
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	
	private static ReplyDao instance = new ReplyDao();
	public static ReplyDao getInstance() {
		return instance;
	}
	
	private ReplyDao() {}
	
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

	
	//	 1. 댓글쓰기
	public int write(int rNo, String sId, String rContent) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO WEDDINGREPLY (WRENO, WMEMO, MID, WNO)" + 
				"VALUES (REPLY_SEQ.NEXTVAL, ?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rNo);
			pstmt.setString(2, sId);
			pstmt.setString(3, rContent);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "댓글쓰기성공":"댓글쓰기실패");
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
	
//	 2. 댓글 목록 출력하기 -- 페이징 처리(STARTROW, ENDROW)   // rownum 해주기
	/*
	 * public ArrayList<ReplyDto> listReply(int wno, int startRow, int endRow){
	 * ArrayList<ReplyDto> reply = new ArrayList<ReplyDto>(); Connection conn =
	 * null; PreparedStatement pstmt = null; ResultSet rs = null; String sql =
	 * "SELECT * FROM" + "    (SELECT ROWNUM RN, A.* FROM" +
	 * "    (SELECT * FROM WEDDINGREPLY WHERE WNO=?" +
	 * "                ORDER BY WRENO DESC) A)" + "    WHERE RN BETWEEN ? AND ?";
	 * try { conn = getConnection(); pstmt = conn.prepareStatement(sql);
	 * pstmt.setInt(1, wno); pstmt.setInt(2, startRow); pstmt.setInt(3, endRow); rs
	 * = pstmt.executeQuery(); while(rs.next()) { int rNo = rs.getInt("wreno");
	 * String sId = rs.getString("wmemo"); String rContent = rs.getString("mid");
	 * Date wredate = rs.getDate("wredate") ; reply.add(new ReplyDto(rNo, sId,
	 * rContent, rRdate, rIp, fNo)); } } catch (SQLException e) {
	 * System.out.println(e.getMessage()); }finally { try { if(rs !=null)
	 * rs.close(); if(pstmt!=null) pstmt.close(); if(conn !=null) conn.close(); }
	 * catch (SQLException e) { // TODO: handle exception } } return reply; }
	 */
	

	//3. 댓글 삭제하기 
	public int deleteReply(int wreno) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM WEDDINGREPLY WHERE WRENO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, wreno);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "댓글삭제성공":"댓글삭제실패");
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
	
	// rNo로 댓글 출력 
	public ReplyDto replyView(int rNo) {
		ReplyDto dto = null;
		Connection      conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM WEDDINGREPLY WHERE rNo=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String sId = rs.getString("sId");
				String rContent = rs.getString("rContent");
				Date rRdate = rs.getDate("rRdate");
				String rIp = rs.getString("rIp");
				int fNo = rs.getInt("fNo");
				dto = new ReplyDto(rNo, sId, rContent, rRdate, rIp, fNo);
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
}
