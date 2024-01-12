package guest_book_02.model.vo;

import java.util.Date;

public class GuestBookVo {
	private String author;
	private String content;
	private Date reg_date;
	
	public GuestBookVo() {}
	
	public GuestBookVo(String author, String content, Date reg_date) {
		this.author = author;
		this.content = content;
		this.reg_date = reg_date;
	}
	
	// 외부에서 값을 받아와서 넣어주기
	
	public void setAuthor(String author) {
		this.author = author; // 외부에서 받아온 author을 넣어주겠다
	}                      
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setReg_date(Date reg_date) {
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
	
}
