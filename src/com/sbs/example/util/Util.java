package com.sbs.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
	public static String getJsonText(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String rs = "";
		
		try {
			rs = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
}
