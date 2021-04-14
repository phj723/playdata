package forum;

import java.sql.Date;

public class Forum {
	private int article_num;
	private String writer;
	private Date w_date;
	private String title;
	private String content;
	private String category;

	public Forum() {
	}

	public Forum(int article_num, String writer, Date w_date, String title, String content, String category) {
		this.article_num = article_num;
		this.writer = writer;
		this.w_date = w_date;
		this.title = title;
		this.content = content;
		this.category = category;
	}

	public int getArticle_num() {
		return article_num;
	}

	public void setArticle_num(int article_num) {
		this.article_num = article_num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getW_date() {
		return w_date;
	}

	public void setW_date(Date w_date) {
		this.w_date = w_date;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "게시글 번호 : " + article_num + " | 카테고리 : " + category + " | 작성자 : " + writer + " | 작성일 : " + w_date + " | 제목 : " + title
				+ " | 내용 : " + content;
	}

}
