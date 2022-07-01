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

import com.lec.school.dto.LunchBoardDto;

public class LunchBoardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static LunchBoardDao instance = new LunchBoardDao();
	public static LunchBoardDao getInstance() {
		return instance;
	}
	private LunchBoardDao() {}
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
	// 메뉴 등록
	public int lunchIn(LunchBoardDto dto) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO LUNCH (LNO, LDATE, AMPM, MENU, CALORIE, PHOTO, day) "  + 
				"    VALUES (LUNCH_SEQ.NEXTVAL, ?, ?, ?, ?, ?, " + 
				"    to_char(to_date(?),'dd'))";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, dto.getLdate());
			pstmt.setString(2, dto.getAmpm());
			pstmt.setString(3, dto.getMenu());
			pstmt.setDouble(4, dto.getCalorie());
			pstmt.setString(5, dto.getPhoto());
			pstmt.setDate(6, dto.getLdate());
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? dto.getLdate() + "메뉴 입력 성공": dto.getLdate() + "메뉴 입력 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage() + dto.getLdate() + "메뉴입력 실패");
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return result;
	}
	// 특정 년월 메뉴들
	public ArrayList<LunchBoardDto> selectMenu(String year, String month){
		ArrayList<LunchBoardDto> dtos = new ArrayList<LunchBoardDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM LUNCH WHERE TO_CHAR(LDATE, 'YYYY-MM') =  ?||'-'||? ORDER BY DAY, AMPM DESC";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, year);
			pstmt.setString(2, month);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int lNo = rs.getInt("lno");
				Date ldate = rs.getDate("ldate");
				String ampm = rs.getString("ampm");
				String menu = rs.getString("menu");
				double calorie = rs.getDouble("calorie");
				String photo = rs.getString("photo");
				int  day = rs.getInt("day");
				dtos.add(new LunchBoardDto(lNo, ldate, ampm, menu, calorie, photo, day));
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
	// 특정날짜 메뉴
	public LunchBoardDto todayMenu(String year, String month, String day, String ampm){
		LunchBoardDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM LUNCH WHERE TO_CHAR(LDATE, 'YYYY-MM-DD') = ?|| '-' || ?||'-'||? AND AMPM=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, year);
			pstmt.setString(2, month);
			pstmt.setString(3, day);
			pstmt.setString(4, ampm);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int lNo = rs.getInt("lNo");
				Date ldate = rs.getDate("ldate");
				//String ampm = rs.getString("ampm");
				String menu = rs.getString("menu");
				double calorie = rs.getDouble("calorie");
				String photo = rs.getString("photo");
				int  dayint = rs.getInt("day");
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
