package player;

import java.sql.Date;
import java.util.ArrayList;

//vo.(value object) 
public class Player {
	private String id;
	private String pwd;
	private String nickName;
	private Date signup_date;
	private int points;

	public Player() {
	}

	public Player(String id, String pwd, String nickName, Date signup_date, int points) {
		this.id = id;
		this.pwd = pwd;
		this.nickName = nickName;
		this.signup_date = signup_date;
		this.points = points;
	}

	public Player(String nickName, int points) {
		this.nickName = nickName;
		this.points = points;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getSignup_date() {
		return signup_date;
	}

	public void setSignup_date(Date signup_date) {
		this.signup_date = signup_date;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "닉네임 : " + nickName + " | 가입일자 : " + signup_date + " | 포인트 : " + points;
	}
	
	public String getInfo() {
		return "닉네임 : " + nickName + " | 포인트 : " + points;
	}
	
}