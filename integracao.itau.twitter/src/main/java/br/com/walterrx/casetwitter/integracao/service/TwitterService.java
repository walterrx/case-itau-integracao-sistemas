package br.com.walterrx.casetwitter.integracao.service;

import java.util.List;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import br.com.walterrx.casetwitter.integracao.model.TweetDayHours;
import br.com.walterrx.casetwitter.integracao.model.TweetHashTagLanguage;
import br.com.walterrx.casetwitter.integracao.model.UserFollower;
import br.com.walterrx.casetwitter.integracao.repository.TopUserFollowersRepository;
import br.com.walterrx.casetwitter.integracao.repository.TweetHashTagLanguageRepository;
import br.com.walterrx.casetwitter.integracao.repository.TweetDayHoursRepository;
import br.com.walterrx.casetwitter.integracao.repository.TweetRepository;

@Service
public class TwitterService {

	
	@Autowired
	private Twitter twitter;

	@Autowired
	private TweetRepository tweetRepository;
	
	@Autowired
	private TopUserFollowersRepository topUsersFollowersRepository;
	
	@Autowired
	private TweetHashTagLanguageRepository tweetHashTagLanguageRepository;
	
	@Autowired
	private TweetDayHoursRepository tweetDayHoursRepository;
	
	public void getTweets(List<String> hashtags) {	
		
		tweetRepository.deleteAll();
	
		for (String hashtag : hashtags) {
			
			//consultar API do Twitter que retorna os posts por determinada hashtag
			List<Tweet> tweets = this.twitter.searchOperations().search(hashtag, 100).getTweets();
	
			// extrair informações necessárias do Tweet e salvar na base de dados
			if (tweets != null) {
				for (Tweet t : tweets) {
					tweetRepository.save(new br.com.walterrx.casetwitter.integracao.model.Tweet(t.getIdStr(), t.getText(),
							t.getUser().getName(), t.getFromUser(), String.valueOf(t.getUser().getFollowersCount()),
							t.getUser().getLocation(), t.getLanguageCode(), t.getCreatedAt(), hashtag));
				}
			}
		}
		
		processTopUsersFollowers();
		processHashTagLanguage();
		processDayHour();
	}
	


	private void processTopUsersFollowers() {
		// TODO Auto-generated method stub
		
		// limpar indicadores de top users
		topUsersFollowersRepository.deleteAll();
		
		//consultar tweets
		List<br.com.walterrx.casetwitter.integracao.model.Tweet> tweets = (List<br.com.walterrx.casetwitter.integracao.model.Tweet>) tweetRepository.findAll();
		Map<String, Long> usersTweets = new HashMap<String, Long>();
		
		// lógica para classificar os top users
		for(br.com.walterrx.casetwitter.integracao.model.Tweet t : tweets) {
			if(!usersTweets.containsKey(t.getIdUsuario())) {
				usersTweets.put(t.getIdUsuario(), Long.valueOf(t.getQuantidadeSeguidores()));
			}
		}
		
		// salvar indicadores na base
		for(String chave : usersTweets.keySet()) {
			UserFollower userFollowers = new UserFollower();
			userFollowers.setApelido(chave);
			userFollowers.setQuantidadeSeguidores(usersTweets.get(chave));
			
			topUsersFollowersRepository.save(userFollowers);
		}
	}
	
	private void processHashTagLanguage() {
		// TODO Auto-generated method stub
		
		// remover indicadores anteriores da base de dados
		tweetHashTagLanguageRepository.deleteAll();
		
		// consultar tweets da base de dados
		List<br.com.walterrx.casetwitter.integracao.model.Tweet> tweets = (List<br.com.walterrx.casetwitter.integracao.model.Tweet>) tweetRepository.findAll();
		Map<String, Long> hashTagPaises = new HashMap<String, Long>();
		
		// lógica para gerar indicadores de hashtags por idioma
		for(br.com.walterrx.casetwitter.integracao.model.Tweet t : tweets) {
			if(hashTagPaises.containsKey(t.getHashtag().concat("_").concat(t.getIdioma()))) {
				hashTagPaises.put(t.getHashtag().concat("_").concat(t.getIdioma()), hashTagPaises.get(t.getHashtag().concat("_").concat(t.getIdioma())) + 1L);
			} else {
				hashTagPaises.put(t.getHashtag().concat("_").concat(t.getIdioma()), 1L);
			}
		}
		
		// salvar na base de dados os indicadores
		for(String chave : hashTagPaises.keySet()) {
			String hashtag = "";
			String pais = "Outros";
			
			try {
				hashtag = chave.split("_")[0];
			} catch(Exception e) {
				
			}
			
			try {
				pais = chave.split("_")[1];
			} catch(Exception e) {
				
			}
			
			TweetHashTagLanguage tweetHashTagLanguage = new TweetHashTagLanguage();
			tweetHashTagLanguage.setHashTag(hashtag);
			tweetHashTagLanguage.setPais(pais);
			tweetHashTagLanguage.setTotal(hashTagPaises.get(chave));
			
			tweetHashTagLanguageRepository.save(tweetHashTagLanguage);
		}
		
	}
	
	private void processDayHour() {
		// TODO Auto-generated method stub
		
	tweetDayHoursRepository.deleteAll();
		
		List<br.com.walterrx.casetwitter.integracao.model.Tweet> tweets = (List<br.com.walterrx.casetwitter.integracao.model.Tweet>) tweetRepository.findAll();
		Map<Integer, Long> tweetsDayHours = new HashMap<Integer, Long>();
		
		for(br.com.walterrx.casetwitter.integracao.model.Tweet t : tweets) {
			Calendar postDate = Calendar.getInstance();
			postDate.setTime(t.getData());
			int horaDoDia = postDate.get(Calendar.HOUR_OF_DAY);
			
			if(tweetsDayHours.containsKey(horaDoDia)) {
				tweetsDayHours.put(horaDoDia, tweetsDayHours.get(horaDoDia) + 1);
			} else {
				tweetsDayHours.put(horaDoDia, 1L);
			}
		}
		
		for(Integer chave : tweetsDayHours.keySet()) {
			TweetDayHours tweetsHoraDiaResumo = new TweetDayHours();
			tweetsHoraDiaResumo.setHora(chave);
			tweetsHoraDiaResumo.setTotal(tweetsDayHours.get(chave));
			
			tweetDayHoursRepository.save(tweetsHoraDiaResumo);
		}
		
	}
}
