package administrator;

import java.sql.Date;

public class NoticeDTO {
	private int notice_number;
	private String writer;
	private Date regist_date;
	private String title;
	private String content;
	private int category;	// 1:일반공지, 2:이벤트공지, 3:FAQ

	public NoticeDTO() {
		super();
	}

	public NoticeDTO(int notice_number, String writer, Date regist_date, String title, String content, int category) {
		this.notice_number = notice_number;
		this.writer = writer;
		this.regist_date = regist_date;
		this.title = title;
		this.content = content;
		this.category = category;
	}

	public int getNotice_number() {
		return notice_number;
	}

	public void setNotice_number(int notice_number) {
		this.notice_number = notice_number;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRegist_date() {
		return regist_date;
	}

	public void setRegist_date(Date regist_date) {
		this.regist_date = regist_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
}
