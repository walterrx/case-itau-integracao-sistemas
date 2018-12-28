package br.com.walterrx.casetwitter.integracao.dto;

import java.io.Serializable;
import java.util.List;

import br.com.walterrx.casetwitter.integracao.model.TweetDayHours;
import br.com.walterrx.casetwitter.integracao.model.TweetHashTagLanguage;
import br.com.walterrx.casetwitter.integracao.model.UserFollower;

public class GetTwitterAllInformation implements Serializable {
	
	/**
	 * Retornar todos os dados das entidades de TOp Seguidores dos usuarios, HashTags por pais e Tweets por hora
	 */
	private static final long serialVersionUID = 1L;
	
	private List<TweetDayHours> tweetDayHours;
	
	private List<TweetHashTagLanguage> tweetHashTagLanguage;
	
	private List<UserFollower> userFollower;
	

	public List<TweetDayHours> getTweetDayHours() {
		return tweetDayHours;
	}

	public void setTweetDayHours(List<TweetDayHours> tweetDayHours) {
		this.tweetDayHours = tweetDayHours;
	}

	public List<TweetHashTagLanguage> getTweetHashTagLanguage() {
		return tweetHashTagLanguage;
	}

	public void setTweetHashTagLanguage(List<TweetHashTagLanguage> tweetHashTagLanguage) {
		this.tweetHashTagLanguage = tweetHashTagLanguage;
	}

	public List<UserFollower> getUserFollower() {
		return userFollower;
	}

	public void setUserFollower(List<UserFollower> userFollower) {
		this.userFollower = userFollower;
	}
	
	
	
	
	
	

}
