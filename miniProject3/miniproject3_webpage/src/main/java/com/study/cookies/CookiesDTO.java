package com.study.cookies;

import org.springframework.web.multipart.MultipartFile;

public class CookiesDTO {
	private String name;
	private String type;
	private String position;
	private String grade;
	private String cmt;
	private String fname;
	private MultipartFile fnameMF;
	private String skillname;
	private String skillcmt;
	private String sfname;
	private MultipartFile sfnameMF;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCmt() {
		return cmt;
	}

	public void setCmt(String cmt) {
		this.cmt = cmt;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public MultipartFile getFnameMF() {
		return fnameMF;
	}

	public void setFnameMF(MultipartFile fnameMF) {
		this.fnameMF = fnameMF;
	}
	
	

	public String getSkillname() {
		return skillname;
	}

	public void setSkillname(String skillname) {
		this.skillname = skillname;
	}

	public String getSkillcmt() {
		return skillcmt;
	}

	public void setSkillcmt(String skillcmt) {
		this.skillcmt = skillcmt;
	}

	public String getSfname() {
		return sfname;
	}

	public void setSfname(String sfname) {
		this.sfname = sfname;
	}

	public MultipartFile getSfnameMF() {
		return sfnameMF;
	}

	public void setSfnameMF(MultipartFile sfnameMF) {
		this.sfnameMF = sfnameMF;
	}

	@Override
	public String toString() {
		return "CookiesDTO [name=" + name + ", type=" + type + ", position=" + position + ", grade=" + grade
				+ ", comment=" + cmt + ", fname=" + fname + ", fnameMF=" + fnameMF + ", skillname=" + skillname
				+ ", skillcmt=" + skillcmt + ", sfname=" + sfname + ", sfnameMF=" + sfnameMF + "]";
	}





}