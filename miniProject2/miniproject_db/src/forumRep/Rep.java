package forumRep;

import java.sql.Date;

public class Rep {
	private int rep_num;
	private int art_num;
	private String writer;
	private Date rep_date;
	private String content;

	public Rep() {
	}

	public Rep(int rep_num, int art_num, String writer, Date rep_date, String content) {
		this.rep_num = rep_num;
		this.art_num = art_num;
		this.writer = writer;
		this.rep_date = rep_date;
		this.content = content;
	}

	public int getRep_num() {
		return rep_num;
	}

	public void setRep_num(int rep_num) {
		this.rep_num = rep_num;
	}

	public int getArt_num() {
		return art_num;
	}

	public void setArt_num(int art_num) {
		this.art_num = art_num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getRep_date() {
		return rep_date;
	}

	public void setRep_date(Date rep_date) {
		this.rep_date = rep_date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "  └─── 작성자 : " + writer + " | 작성일 : " + rep_date + " | 내용 : " + content;
	}

}
