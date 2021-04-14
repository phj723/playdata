package com.study.cookies;

import java.util.List;
import java.util.Map;

public interface CookiesMapper {
	
	int create(CookiesDTO dto);
	CookiesDTO read(String name);
	int update(CookiesDTO dto);
	int delete(String name);
	List<CookiesDTO> list(Map map);
	int list_total(Map map);

}
