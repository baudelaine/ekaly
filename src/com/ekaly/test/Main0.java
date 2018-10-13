package com.ekaly.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ekaly.web.Tools;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Main0 {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		
		Path path = Paths.get("/opt/wks/ekaly/WebContent/res/historical.json");
		Map<String, Object> historical = null;
		List<Map<String, Object>> newHistorical = new ArrayList<Map<String, Object>>();
		
		if(Files.exists(path)) {
			historical = Tools.fromJSON(path.toFile());
		}
		
		if(historical != null) {
			for(Map.Entry<String, Object> e: historical.entrySet()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("TEXT", e.getKey());
				m.put("TONES", e.getValue());
				newHistorical.add(m);
			}
		}
		
		System.out.println(Tools.toJSON(newHistorical));
		
	}

}
