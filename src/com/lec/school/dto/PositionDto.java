package com.lec.school.dto;

public class PositionDto {
	private int pNo; 
	private String pName;
	public PositionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PositionDto(int pNo, String pName) {
		super();
		this.pNo = pNo;
		this.pName = pName;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	@Override
	public String toString() {
		return "PositionDto [pNo=" + pNo + ", pName=" + pName + "]";
	}
	
	
}
