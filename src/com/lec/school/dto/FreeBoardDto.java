package com.lec.school.dto;

import java.sql.Date;

public class FreeBoardDto {
	private int fNo;
	private String sId;
	private String sName; // 학생이름	join 해서 출력
	private String fTitle;
	private String fContent;
	private String fFilename;
	private Date fRdate;
	private int fHit;
	private int fRef;
	private int fStep;
	private int fIndent;
	private String fIp;
	public FreeBoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FreeBoardDto(int fNo, String sId, String fTitle, String fContent, String fFilename, Date fRdate, int fHit,
			int fRef, int fStep, int fIndent, String fIp) {
		super();
		this.fNo = fNo;
		this.sId = sId;
		this.fTitle = fTitle;
		this.fContent = fContent;
		this.fFilename = fFilename;
		this.fRdate = fRdate;
		this.fHit = fHit;
		this.fRef = fRef;
		this.fStep = fStep;
		this.fIndent = fIndent;
		this.fIp = fIp;
	}

	public FreeBoardDto(int fNo, String sId, String sName, String fTitle, String fContent, String fFilename,
			Date fRdate, int fHit, int fRef, int fStep, int fIndent, String fIp) {
		super();
		this.fNo = fNo;
		this.sId = sId;
		this.sName = sName;
		this.fTitle = fTitle;
		this.fContent = fContent;
		this.fFilename = fFilename;
		this.fRdate = fRdate;
		this.fHit = fHit;
		this.fRef = fRef;
		this.fStep = fStep;
		this.fIndent = fIndent;
		this.fIp = fIp;
	}
	public int getfNo() {
		return fNo;
	}
	public void setfNo(int fNo) {
		this.fNo = fNo;
	}
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getfTitle() {
		return fTitle;
	}
	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
	}
	public String getfContent() {
		return fContent;
	}
	public void setfContent(String fContent) {
		this.fContent = fContent;
	}
	public String getfFilename() {
		return fFilename;
	}
	public void setfFilename(String fFilename) {
		this.fFilename = fFilename;
	}
	public Date getfRdate() {
		return fRdate;
	}
	public void setfRdate(Date fRdate) {
		this.fRdate = fRdate;
	}
	public int getfHit() {
		return fHit;
	}
	public void setfHit(int fHit) {
		this.fHit = fHit;
	}
	public int getfRef() {
		return fRef;
	}
	public void setfRef(int fRef) {
		this.fRef = fRef;
	}
	public int getfStep() {
		return fStep;
	}
	public void setfStep(int fStep) {
		this.fStep = fStep;
	}
	public int getfIndent() {
		return fIndent;
	}
	public void setfIndent(int fIndent) {
		this.fIndent = fIndent;
	}
	public String getfIp() {
		return fIp;
	}
	public void setfIp(String fIp) {
		this.fIp = fIp;
	}
	@Override
	public String toString() {
		return "FreeBoardDto [fNo=" + fNo + ", sId=" + sId + ", sName=" + sName + ", fTitle=" + fTitle + ", fContent="
				+ fContent + ", fFilename=" + fFilename + ", fRdate=" + fRdate + ", fHit=" + fHit + ", fRef=" + fRef
				+ ", fStep=" + fStep + ", fIndent=" + fIndent + ", fIp=" + fIp + "]";
	}
	
	
}
