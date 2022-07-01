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

import com.lec.school.dto.StudentDto;

public class StudentDao {
	public static final int EXISTENT    = 0;
	public static final int NONEXISTENT = 1;
	public static final int LOGIN_FAIL    =0;
	public static final int LOGIN_SUCCESS = 1;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static StudentDao instance = new StudentDao();
	public static StudentDao getInstance() {
		return instance;
	}
	private StudentDao() {}
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
	// (1) 로그인 체크
	public int loginCheck(String sId, String sPw) {
		int result = LOGIN_FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM STUDENT WHERE SID = ? AND SPW = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sId);
			pstmt.setString(2, sPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = LOGIN_SUCCESS;
			}else {
				result = LOGIN_FAIL;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// (2) mid로 dto가져오기(로그인 성공시 session에 넣기 위함)
	public StudentDto getMember(String sId) {
		StudentDto student = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM STUDENT WHERE SID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 // String sId;
				 String sPw = rs.getString("sPw");
				 String sName = rs.getString("sName");
				 int    sNo = rs.getInt("sNo");
				 String sTel = rs.getString("sTel");
				 String sEmail = rs.getString("sEmail");
				 String sGender = rs.getString("sGender");
				 Date   sBirth = rs.getDate("sBirth");
				 String sPhoto = rs.getString("sPhoto");
				 int    pNo = rs.getInt("pNo");
				 
				 student = new StudentDto(sId, sPw, sName, sNo, sTel, 
						 					sEmail, sGender, sBirth, sPhoto, pNo);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return student;
	}
	// (3) 회원id 중복체크 
	public int sIdConfirm(String sId) {
		int result = EXISTENT;
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		
		String sql = "SELECT * FROM STUDENT WHERE SID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = EXISTENT;
			}else {
				result = NONEXISTENT;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// (4) 회원가입 
	public int join(StudentDto student) {
		
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO STUDENT (SID, SPW, SNAME, SNO, STEL, SEMAIL, "
											+ "SGENDER, SBIRTH, SPHOTO)" + 
					"             VALUES (?, ?, ?, ?, ?,  ?,"
									+ " ?, TO_DATE(?, 'YYYY-MM-DD'), ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getsId());
			pstmt.setString(2, student.getsPw());
			pstmt.setString(3, student.getsName());
			pstmt.setInt(4, student.getsNo());
			pstmt.setString(5, student.getsTel());
			pstmt.setString(6, student.getsEmail());
			pstmt.setString(7, student.getsGender());
			pstmt.setDate(8, student.getsBirth());
			pstmt.setString(9, student.getsPhoto());
			
			result = pstmt.executeUpdate();
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
		return result;
	}
	// (5) 회원정보 수정 
	public int modify(StudentDto student) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE STUDENT SET SPW = ?," + 
				     "                   SNAME = ?," + 
				     "                   STEL = ?," + 
				     "                   SEMAIL = ?," + 
				     "                   SGENDER = ?," + 
				     "                   SBIRTH = ?," + 
				     "                   SPHOTO = ?" + 
			    	"                WHERE SID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getsPw());
			pstmt.setString(2, student.getsName());
			pstmt.setString(3, student.getsTel());
			pstmt.setString(4, student.getsGender());
			pstmt.setDate(5, student.getsBirth());
			pstmt.setString(6, student.getsPhoto());
			pstmt.setString(7, student.getsId());
			
			result = pstmt.executeUpdate();
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
		return result;
	}
	
	
	// (6) 회원리스트(top-N구문) -- 쿼리문 다시 확인해서 집어넣기
	public ArrayList<StudentDto> allList(int startRow, int endRow){
		
		ArrayList<StudentDto> students = new ArrayList<StudentDto>();
		
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		
		String sql = "SELECT *" + 
				"    FROM (SELECT ROWNUM RN, SID, SNAME, SNO, STEL, SEMAIL, SGENDER, SBIRTH, SPHOTO" + 
				"                FROM (SELECT * FROM STUDENT ORDER BY SNO))" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				 String sId = rs.getString("sId");
				 System.out.println("1 sid");

				 String sName = rs.getString("sName");
				 System.out.println("2, sname" + sName + "error");
				 int    sNo = rs.getInt("sNo");
				 System.out.println("2. sNo error");
				 String sTel = rs.getString("sTel");
				 System.out.println("3 sTel error" + sTel);
				 String sEmail = rs.getString("sEmail");
				 String sGender = rs.getString("sGender");
				 Date   sBirth = rs.getDate("sBirth");
				 String sPhoto = rs.getString("sPhoto");
				 System.out.println("4 sPhoto");
				 
				 students.add(new StudentDto(sId, null, sName, sNo, sTel, sEmail, sGender, sBirth, sPhoto, 0));
				 System.out.println("add dto");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "dao error");
		}finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				 
			}
		}
		return students;
	}
	// (7) 회원수
	public int getStudenttTotCnt() {
		int totCnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) TOTCNT FROM STUDENT";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totCnt = rs.getInt("totcnt");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return totCnt;
	}
	// (8) 회원탈퇴
	public int withdrawal(String sId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE STUDENT WHERE SID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sId);
			result = pstmt.executeUpdate();
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
		return result;
	}
	
	
	public int emailConfirm(String sEmail) {
		int result = EXISTENT;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM STUDENT WHERE SEMAIL = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sEmail);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = EXISTENT;
			}else {
				result = NONEXISTENT;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs    != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn  != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
}
