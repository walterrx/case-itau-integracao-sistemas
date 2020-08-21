package br.com.walterrx.casetwitter.integracao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TweetHashTagLanguage {
	
	 @Id
	private String hashTag;
	private String pais;
	private Long total;

	public TweetHashTagLanguage() {
		
	}

	public TweetHashTagLanguage(String hashTag, String pais, Long total) {
		this.hashTag = hashTag;
		this.pais = pais;
		this.total = total;
	}

	public String getHashTag() {
		return hashTag;
	}

	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}

	public String getPais() {
		return pais.toUpperCase();
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
