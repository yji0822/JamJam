package com.lec.school.dto;

import java.sql.Date;

public class ReplyDto {
	private int rNo;
	private String sId;
	private String rContent;
	private Date rRdate;
	private String rIp;
	private int fNo;
	public ReplyDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReplyDto(int rNo, String sId, String rContent, Date rRdate, String rIp, int fNo) {
		super();
		this.rNo = rNo;
		this.sId = sId;
		this.rContent = rContent;
		this.rRdate = rRdate;
		this.rIp = rIp;
		this.fNo = fNo;
	}
	public int getrNo() {
		return rNo;
	}
	public void setrNo(int rNo) {
		this.rNo = rNo;
	}
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
	public Date getrRdate() {
		return rRdate;
	}
	public void setrRdate(Date rRdate) {
		this.rRdate = rRdate;
	}
	public String getrIp() {
		return rIp;
	}
	public void setrIp(String rIp) {
		this.rIp = rIp;
	}
	public int getfNo() {
		return fNo;
	}
	public void setfNo(int fNo) {
		this.fNo = fNo;
	}
	@Override
	public String toString() {
		return "ReplyDto [rNo=" + rNo + ", sId=" + sId + ", rContent=" + rContent + ", rRdate=" + rRdate + ", rIp="
				+ rIp + ", fNo=" + fNo + "]";
	}
	

}
