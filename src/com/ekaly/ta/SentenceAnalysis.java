package com.ekaly.ta;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class SentenceAnalysis {

	Long sentenceId = 0L;
	String text = "";
	List<ToneScore> tones = new ArrayList<ToneScore>();
	
	@JsonGetter("sentence_id")
	public Long getSentenceId() {
		return sentenceId;
	}
	@JsonSetter("sentence_id")
	public void setSentenceId(Long sentenceId) {
		this.sentenceId = sentenceId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<ToneScore> getTones() {
		return tones;
	}
	public void setTones(List<ToneScore> tones) {
		this.tones = tones;
	}
	
}
