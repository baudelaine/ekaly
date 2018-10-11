package com.ekaly.ta;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class ToneScore {
	
	double score = 0D;
	String toneId = "";
	String toneName = "";
	
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	@JsonGetter("tone_id")
	public String getToneId() {
		return toneId;
	}
	@JsonSetter("tone_id")
	public void setToneId(String toneId) {
		this.toneId = toneId;
	}
	@JsonGetter("tone_name")
	public String getToneName() {
		return toneName;
	}
	@JsonSetter("tone_name")
	public void setToneName(String toneName) {
		this.toneName = toneName;
	}

}
