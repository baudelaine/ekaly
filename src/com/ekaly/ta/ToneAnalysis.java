package com.ekaly.ta;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;


public class ToneAnalysis {

	DocumentAnalysis documentTone = new DocumentAnalysis();
	List<SentenceAnalysis> sentencesTone = new ArrayList<SentenceAnalysis>();
	
	@JsonGetter("document_tone")
	public DocumentAnalysis getDocumentTone() {
		return documentTone;
	}
	@JsonSetter("document_tone")
	public void setDocumentTone(DocumentAnalysis documentTone) {
		this.documentTone = documentTone;
	}
	@JsonGetter("sentences_tone")
	public List<SentenceAnalysis> getSentencesTone() {
		return sentencesTone;
	}
	@JsonSetter("sentences_tone")
	public void setSentencesTone(List<SentenceAnalysis> sentencesTone) {
		this.sentencesTone = sentencesTone;
	}
	
}
