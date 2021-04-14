package com.study.homepage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.study.cookies.CookiesDTO;
import com.study.cookies.CookiesMapper;
import com.study.utility.Utility;

@Controller
public class CookiesController {

	@Autowired
	private CookiesMapper mapper;

	@GetMapping("/cookies/create")
	public String create() {
		return "/cookies/create";
	}
	
	@PostMapping("/cookies/create")
	public String create(CookiesDTO dto, HttpServletRequest request) {
		String upDir = request.getRealPath("/storage");
		
		String fname = Utility.saveFileSpring(dto.getFnameMF(), upDir);
		int size = (int) dto.getFnameMF().getSize();
		if (size > 0) {
			dto.setFname(fname);
		} else {
			dto.setFname("cookie.jpg");
		}
		
		String sfname = Utility.saveFileSpring(dto.getSfnameMF(), upDir);
		int sizes = (int) dto.getSfnameMF().getSize();
		if (sizes > 0) {
			dto.setSfname(sfname);
		} else {
			dto.setSfname("skill.jpg");
		}

		if (mapper.create(dto) > 0) {
			return "redirect:./list";
		} else {
			return "error";
		}
	}
	
	@GetMapping("/cookies/read")
	public String read(String name, Model model) {
		CookiesDTO dto = mapper.read(name);
		model.addAttribute("dto", dto);
		return "/cookies/read";
	}
	

	@GetMapping("/cookies/update")
	public String update(String name, Model model, HttpSession session) {
		model.addAttribute("dto", mapper.read(name));
		return "/cookies/update";
	}
	
	@PostMapping("/cookies/update")
	public String update(CookiesDTO dto, Model model, MultipartFile fnameMF, MultipartFile sfnameMF, 
			String oldfile, String oldsfile, HttpSession session, HttpServletRequest request) {
		
		String basePath = request.getRealPath("/storage");
		Utility.deleteFile(basePath, oldfile);
		Utility.deleteFile(basePath, oldsfile);
		String fname = Utility.saveFileSpring(fnameMF, basePath);
		String sfname = Utility.saveFileSpring(sfnameMF, basePath);
		dto.setFname(fname);
		dto.setSfname(sfname);
		
		int cnt = mapper.update(dto);

		if (cnt == 1) {
			model.addAttribute("name", dto.getName());
			return "redirect:./read";
		} else {
			return "error";
		}
	}
	
	
	@GetMapping("/cookies/deleteAjax")
	public String deleteAjax() {
		return "/cookies/deleteAjax";
	}
	
	@PostMapping(value = "/cookies/deleteAjax", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, String> deleteAjax(@RequestBody CookiesDTO dto, HttpServletRequest request) {

		String upDir = request.getRealPath("/storage");

		Utility.deleteFile(upDir, dto.getFname());
		Utility.deleteFile(upDir, dto.getSfname());
		
		int cnt = mapper.delete(dto.getName());

		Map<String, String> map = new HashMap<String, String>();

		if (cnt > 0) {
			map.put("str", "삭제 처리 되었습니다.");
		} else {
			map.put("str", "삭제 중 에러가 발생했습니다.");
		}
		return map;
	}

	
	@RequestMapping("/cookies/list")
	public String list(HttpServletRequest request, String grade) {
		// 검색관련------------------------
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));

		if (col.equals("total")) {
			word = "";
		}
		if (grade != null) {
			col = "grade";
			word = grade;
		}

		// 페이지관련-----------------------
		int nowPage = 1;// 현재 보고있는 페이지
		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		int recordPerPage = 10;// 한페이지당 보여줄 레코드갯수

		// DB에서 가져올 순번-----------------
		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		int total = mapper.list_total(map);

		String paging = Utility.paging(total, nowPage, recordPerPage, col, word, grade);

		List<CookiesDTO> list = mapper.list(map);	

		// request에 Model사용 결과 담는다
		request.setAttribute("list", list);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("col", col);
		request.setAttribute("paging", paging);
		if (col != "grade") {
			request.setAttribute("word", word);
		}
		return "/cookies/list";
	}
}
