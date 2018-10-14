package com.baudelaine.test;

import java.io.IOException;
import java.util.Map;

import com.baudelaine.tools.Cmd;
import com.baudelaine.tools.Tools;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Cmd cmd = new Cmd();
		cmd.setName("TA0_KEY");
		cmd.setCmd("/usr/local/bin/ibmcloud service key-show ta0 user0");
		cmd.setNr(4);
		cmd.setPath("/tmp");
		
		System.out.println(Tools.toJSON(cmd));
		
		try {
			Map<String, Object> output = Tools.fromJSON((String) cmd.run().get("OUTPUT"));
			
			System.out.println(output.get("password"));
			
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
