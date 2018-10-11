package com.ekaly.ta;

import java.util.ArrayList;
import java.util.List;

public class DocumentAnalysis {

	List<ToneScore> tones = new ArrayList<ToneScore>();
	String warning = "";
	
	public List<ToneScore> getTones() {
		return tones;
	}
	public void setTones(List<ToneScore> tones) {
		this.tones = tones;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	
}
