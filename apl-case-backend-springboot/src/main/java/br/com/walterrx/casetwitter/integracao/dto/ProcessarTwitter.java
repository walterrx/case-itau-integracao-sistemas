package br.com.walterrx.casetwitter.integracao.dto;

import java.io.Serializable;
import java.util.List;

public class ProcessarTwitter implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> hashtags;

	public List<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
	}

}
