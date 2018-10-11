package com.ekaly.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneInput;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

	InitialContext ic;
	String vcap_services;
	String realPath;
	Properties props = new Properties();
	ToneAnalyzer ta;
	ToneOptions to;
	
    /**
     * Default constructor. 
     */
    public ContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
       	try {
       		
    			ic = new InitialContext();
    			arg0.getServletContext().setAttribute("ic", ic);
    			realPath = arg0.getServletContext().getRealPath("/"); 
    	    	props.load(new FileInputStream(realPath + "/res/conf.properties"));
    			arg0.getServletContext().setAttribute("props", props);
    	    	
    			System.out.println("Context has been initialized...");
    			
    			initVCAP_SERVICES();
    			System.out.println("VCAP_SERVICES has been initialized...");

    			initTA();
    			System.out.println("TA has been initialized...");
				arg0.getServletContext().setAttribute("ta", ta);
				arg0.getServletContext().setAttribute("to", to);
    			
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	arg0.getServletContext().removeAttribute("ic");
		System.out.println("Context has been destroyed...");    	
    }
    
    public void initVCAP_SERVICES() throws FileNotFoundException, IOException{
    	
    	String value = props.getProperty("VCAP_SERVICES");
    	
    	if(value != null && !value.trim().isEmpty()){
			Path path = Paths.get(realPath + value);
			Charset charset = StandardCharsets.UTF_8;
			if(Files.exists(path)){
				vcap_services = new String(Files.readAllBytes(path), charset);
				System.out.println("VCAP_SERVICES read from " + value + ".");
			}
    	}
    	else{
    		vcap_services = System.getenv("VCAP_SERVICES");
			System.out.println("VCAP_SERVICES read from System ENV.");
    	}

    }
    
    @SuppressWarnings({ "unchecked", "unused" })
	public void initTA() throws JsonParseException, JsonMappingException, IOException{

    	String serviceName = props.getProperty("TA_NAME");
    	
		ObjectMapper mapper = new ObjectMapper();
		
		String url = "";
		String username = "";
		String password = "";
		String version = props.getProperty("TA_VERSION");
		
		Map<String, Object> input = mapper.readValue(vcap_services, new TypeReference<Map<String, Object>>(){});
		
		List<Map<String, Object>> l0s = (List<Map<String, Object>>) input.get(serviceName);
		
		for(Map<String, Object> l0: l0s){
			for(Map.Entry<String, Object> e: l0.entrySet()){
				if(e.getKey().equalsIgnoreCase("credentials")){
					System.out.println(e.getKey() + "=" + e.getValue());
					Map<String, Object> credential = (Map<String, Object>) e.getValue();
					url = (String) credential.get("url");
					username = (String) credential.get("username");
					password = (String) credential.get("password");
				}
			}
		}
		
		ta = new ToneAnalyzer(version, username, password);
		ta.setEndPoint(url);

		try {
			String tacl = props.getProperty("TA_CONTENT_LANGUAGE");
			Boolean tas = Boolean.valueOf(props.getProperty("TA_SENTENCES"));
			String taal = props.getProperty("TA_ACCEPT_LANGUAGE");
			ToneInput ti = new ToneInput.Builder().text("Salut les p'tits gars !!!").build();
			ToneOptions to = new ToneOptions.Builder()
					  .contentLanguage(tacl)
					  .sentences(tas)
					  .acceptLanguage(taal)
					  .toneInput(ti)
					  .build();			
			
			System.out.println("to.toneInput()=" + to.toneInput());
			System.out.println(ta.tone(to).execute());
			
		}
		catch(Exception e) {
			System.err.println("Warning: ToneOptions to was not build successfully !!!");
			
		}
		
		
		
		System.out.println(ta.getName() + " " + ta.getEndPoint());
		
		return;
    }    
    
}
