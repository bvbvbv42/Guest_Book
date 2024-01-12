package com.goodee.guestbook.model.vo;

import java.util.Date;

public class GuestBookVo {
	private String author;
	private String content;
	//private Date reg_date = new Date();

	private Date reg_date;
	
	public GuestBookVo() {
		super();
	}
	
	public GuestBookVo(String author, String content, Date reg_date) {
		this.author = author; //매개변수로 넘어온 author이다
		this.content = content;
		this.reg_date = reg_date;
	}

	public String getAuthor() {
		return author;
	}
	public String getContent() {
		return content;
	}
	public Date getReg_date() {
		return reg_date;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}

