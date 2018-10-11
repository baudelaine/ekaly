package com.ekaly.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Tools {

	public final static String toJSON(Object o){
		try{
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		StringWriter sw = new StringWriter();
		String jsonResult = null;
		mapper.writeValue(sw, o);
		sw.flush();
		jsonResult = sw.toString();
		sw.close();
		return jsonResult;
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
		return null;
	}
	
	public final static Map<String, Object> fromJSON(InputStream is){
		Map<String, Object>	map = new HashMap<String, Object>();
		
		try{
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			map = mapper.readValue(br, new TypeReference<Map<String, Object>>(){});
			return map;
		}
		catch(Exception e){
			e.printStackTrace(System.err);
		}
		
        return null;
	}
	
}
