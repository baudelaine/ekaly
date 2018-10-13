package com.ekaly.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main0 {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		
		Path path = Paths.get("/opt/wks/ekaly/WebContent/res/taResp0.json");
		
		if(Files.exists(path)) {
			ObjectMapper mapper = new ObjectMapper();
	        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		}
		
	}

}
