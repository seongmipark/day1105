package com.sist.book;

public class BookVo {
	
	private int no;
	private String name;
	private String publisher;
	private int price;
	
	public BookVo(int no, String name, String publisher, int price) {
		super();
		this.no = no;
		this.name = name;
		this.publisher = publisher;
		this.price = price;
	}
	public BookVo() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}


}
