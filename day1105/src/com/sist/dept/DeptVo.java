package com.sist.dept;

public class DeptVo {
	private int no;
	private String name;
	private String city;
	public DeptVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeptVo(int no, String name, String city) {
		super();
		this.no = no;
		this.name = name;
		this.city = city;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	

}
