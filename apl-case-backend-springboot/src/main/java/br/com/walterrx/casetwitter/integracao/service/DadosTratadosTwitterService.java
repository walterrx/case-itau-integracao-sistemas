package br.com.walterrx.casetwitter.integracao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.walterrx.casetwitter.integracao.model.TweetDayHours;
import br.com.walterrx.casetwitter.integracao.model.TweetHashTagLanguage;
import br.com.walterrx.casetwitter.integracao.model.UserFollower;
import br.com.walterrx.casetwitter.integracao.repository.TopUserFollowersRepository;
import br.com.walterrx.casetwitter.integracao.repository.TweetDayHoursRepository;
import br.com.walterrx.casetwitter.integracao.repository.TweetHashTagLanguageRepository;


@Service
public class DadosTratadosTwitterService {	
	
	@Autowired
	private TweetDayHoursRepository tweetDayHoursRepository;
	
	@Autowired
	private TweetHashTagLanguageRepository tweetHashTagLanguageRepository;
	
	@Autowired
	private TopUserFollowersRepository topUserFollowersRepository;
	
	
	public List<UserFollower> findTopUserFollowers() {
		return topUserFollowersRepository.findAll(new Sort(Sort.Direction.DESC, "quantidadeSeguidores")).subList(0, 5);
	}

	public List<TweetHashTagLanguage> findTweetHashTagLanguage() {
		return tweetHashTagLanguageRepository.findAll(new Sort(Sort.Direction.ASC, "hashTag"));
	}

	public List<TweetDayHours> findTweetDayHour() {
		return (List<TweetDayHours>) tweetDayHoursRepository.findAll();
	}

}
