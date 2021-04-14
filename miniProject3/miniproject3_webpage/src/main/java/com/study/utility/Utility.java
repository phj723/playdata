package com.study.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class Utility {
	
	public static String getCodeValue(String code) {
		String typename = null;
		Map<String,String> codes = new HashMap<String, String>();
		
		codes.put("A01", "돌격형");
		codes.put("A02", "마법형");
		codes.put("A03", "방어형");
		codes.put("A04", "사격형");
		codes.put("A05", "지원형");
		codes.put("A06", "침투형");
		codes.put("A07", "폭발형");
		codes.put("A08", "회복형");

		typename = codes.get(code);
		
		return typename;
	}
	
	public static String checkNull(String str) {
		if (str == null) {
			str = "";
		}
		return str;
	}

	/**
	 * 
	 * @param totalRecord   전체 레코드수
	 * @param nowPage       현재 페이지
	 * @param recordPerPage 페이지당 레코드 수
	 * @param col           검색 컬럼
	 * @param word          검색어
	 * @return 페이징 생성 문자열
	 */
	public static String paging(int totalRecord, int nowPage, int recordPerPage, String col, String word, String grade) {
		int pagePerBlock = 5; // 블럭당 페이지 수
		int totalPage = (int) (Math.ceil((double) totalRecord / recordPerPage)); // 전체 페이지
		int totalGrp = (int) (Math.ceil((double) totalPage / pagePerBlock));// 전체 그룹
		int nowGrp = (int) (Math.ceil((double) nowPage / pagePerBlock)); // 현재 그룹
		int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // 특정 그룹의 페이지 목록 시작
		int endPage = (nowGrp * pagePerBlock); // 특정 그룹의 페이지 목록 종료

		StringBuffer str = new StringBuffer();
		str.append("<div style='text-align:center'>");
		str.append("<ul class='pagination'> ");
		int _nowPage = (nowGrp - 1) * pagePerBlock; // 10개 이전 페이지로 이동
		
		if (nowGrp >= 2) {
			if(col=="grade") {
				str.append("<li><a href='./list?grade=" + grade +"&nowPage=" + _nowPage + "'>이전</A></li>");
			}else {
				str.append("<li><a href='./list?col=" + col + "&word=" + word + "&nowPage=" + _nowPage + "'>이전</A></li>");
			}
		}

		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage) {
				break;
			}

			if (nowPage == i) {
				str.append("<li class='active'><a href=#>" + i + "</a></li>");
			} else {
				if(col=="grade") {
					str.append(
							"<li><a href='./list?grade=" + grade +"&nowPage=" + i + "'>" + i + "</A></li>");
				}else {
				str.append(
					"<li><a href='./list?col=" + col + "&word=" + word + "&nowPage=" + i + "'>" + i + "</A></li>");
				}
			}
		}

		_nowPage = (nowGrp * pagePerBlock) + 1; // 10개 다음 페이지로 이동
		if (nowGrp < totalGrp) {
			if(col=="grade") {
				str.append("<li><A href='./list?grade=" + grade + "&nowPage=" + _nowPage + "'>다음</A></li>");
			}else {
				str.append("<li><A href='./list?col=" + col + "&word=" + word + "&nowPage=" + _nowPage + "'>다음</A></li>");
			}
		}
		str.append("</ul>");
		str.append("</div>");

		return str.toString();
	}

	public static String saveFileSpring(MultipartFile mf, String basePath) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		String filename = "";
		long filesize = mf.getSize();
		String originalFilename = mf.getOriginalFilename();// 경로가 없는 순수 파일명
		try {
			if (filesize > 0) { // 파일이 존재한다면
				// 인풋 스트림을 얻는다.
				inputStream = mf.getInputStream();

				File oldfile = new File(basePath, originalFilename); // 현재 서버의 파일 시스템

				if (oldfile.exists()) {
					for (int k = 0; true; k++) {
						// 파일명 중복을 피하기 위한 일련 번호를 생성하여
						// 파일명으로 조합
						oldfile = new File(basePath, "(" + k + ")" + originalFilename);

						// 조합된 파일명이 존재하지 않는다면, 일련번호가
						// 붙은 파일명 다시 생성
						if (!oldfile.exists()) { // 존재하지 않는 경우
							filename = "(" + k + ")" + originalFilename;
							break;
						}
					}
				} else {
					filename = originalFilename;
				}
				// make server full path to save
				String serverFullPath = basePath + "\\" + filename;

				System.out.println("fileName: " + filename);
				System.out.println("serverFullPath: " + serverFullPath);

				outputStream = new FileOutputStream(serverFullPath);

				// 버퍼를 만든다.
				int readBytes = 0;
				byte[] buffer = new byte[8192];

				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
					// 업로드되어진 파일을 0부터 8192까지 read하겠다->read는 int를 리턴
					// -1이 되는 경우는 더이상 읽을 것이 없을 때
					outputStream.write(buffer, 0, readBytes);// storage에 파일 넣겠다
				}
				outputStream.close();
				inputStream.close();

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return filename;
	}

	public static void deleteFile(String upDir, String oldfile) {
		File file = new File(upDir, oldfile);
		if (file.exists())
			file.delete();
	}

}