package com.lec.school.dto;

import java.sql.Date;

public class LunchBoardDto {
	
	private int lNo;
	private Date ldate;
	private String ampm;
	private String menu;
	private double calorie;
	private String photo;
	private int  day;
	
	public LunchBoardDto() {}
	// 입력용
	public LunchBoardDto(Date ldate, String ampm, String menu, double calorie, String photo) {
		this.ldate = ldate;
		this.ampm = ampm;
		this.menu = menu;
		this.calorie = calorie;
		this.photo = photo;
	}
	
	public LunchBoardDto(int lNo, Date ldate, String ampm, String menu, double calorie, String photo, int day) {
		this.lNo = lNo;
		this.ldate = ldate;
		this.ampm = ampm;
		this.menu = menu;
		this.calorie = calorie;
		this.photo = photo;
		this.day = day;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getlNo() {
		return lNo;
	}
	public void setlNo(int lNo) {
		this.lNo = lNo;
	}
	public Date getLdate() {
		return ldate;
	}
	public void setLdate(Date ldate) {
		this.ldate = ldate;
	}
	public String getAmpm() {
		return ampm;
	}
	public void setAmpm(String ampm) {
		this.ampm = ampm;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public double getCalorie() {
		return calorie;
	}
	public void setCalorie(double calorie) {
		this.calorie = calorie;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "LunchBoardDto [lNo=" + lNo + ", ldate=" + ldate + ", ampm=" + ampm + ", menu=" + menu + ", calorie="
				+ calorie + ", photo=" + photo + ", day=" + day + "]";
	}
	
	
	
}
