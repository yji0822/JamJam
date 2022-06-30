package com.lec.school.dto;

import java.sql.Date;

public class StudentDto {
	private String sId;
	private String sPw;
	private String sName;
	private int    sNo;
	private String sTel;
	private String sEmail;
	private String sGender;
	private Date   sBirth;
	private String sPhoto;
	private int    pNo;
	
	public StudentDto() {
	}

	public StudentDto(String sId, String sPw, String sName, int sNo, String sTel, String sEmail, String sGender,
			Date sBirth, String sPhoto, int pNo) {
		super();
		this.sId = sId;
		this.sPw = sPw;
		this.sName = sName;
		this.sNo = sNo;
		this.sTel = sTel;
		this.sEmail = sEmail;
		this.sGender = sGender;
		this.sBirth = sBirth;
		this.sPhoto = sPhoto;
		this.pNo = pNo;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public String getsPw() {
		return sPw;
	}

	public void setsPw(String sPw) {
		this.sPw = sPw;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	public String getsTel() {
		return sTel;
	}

	public void setsTel(String sTel) {
		this.sTel = sTel;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsGender() {
		return sGender;
	}

	public void setsGender(String sGender) {
		this.sGender = sGender;
	}

	public Date getsBirth() {
		return sBirth;
	}

	public void setsBirth(Date sBirth) {
		this.sBirth = sBirth;
	}

	public String getsPhoto() {
		return sPhoto;
	}

	public void setsPhoto(String sPhoto) {
		this.sPhoto = sPhoto;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	@Override
	public String toString() {
		return "StudentDto [sId=" + sId + ", sPw=" + sPw + ", sName=" + sName + ", sNo=" + sNo + ", sTel=" + sTel
				+ ", sEmail=" + sEmail + ", sGender=" + sGender + ", sBirth=" + sBirth + ", sPhoto=" + sPhoto + ", pNo="
				+ pNo + "]";
	}

}
