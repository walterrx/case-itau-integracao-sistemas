package br.com.walterrx.casetwitter.integracao.transform;

import java.util.List;

import br.com.walterrx.casetwitter.integracao.dto.GetTwitterAllInformation;
import br.com.walterrx.casetwitter.integracao.model.TweetDayHours;
import br.com.walterrx.casetwitter.integracao.model.TweetHashTagLanguage;
import br.com.walterrx.casetwitter.integracao.model.UserFollower;

public class ObterRetornoTratado {
	
	/**
	 * @author walterrx
	 * @param topUsers List<TopUsers>
	 * @param tweetsHashTag List<TweetsHashTag>
	 * @param tweetsHorasDia List<TweetsHorasDia>
	 * @return SaidaIndicadoresTweets
	 * 
	 * Método responsável por transformar e retornar as informações que serao chamadas pelo Controller
	 * 
	 */
	public static GetTwitterAllInformation processar(List<UserFollower> topUserFollowers, List<TweetHashTagLanguage> tweetsHashTagLanguage, List<TweetDayHours> tweetsDayHours) {
		GetTwitterAllInformation retorno = new GetTwitterAllInformation();
		retorno.setUserFollower(topUserFollowers);
		retorno.setTweetHashTagLanguage(tweetsHashTagLanguage);
		retorno.setTweetDayHours(tweetsDayHours);
		return retorno;
	}

}
