package br.com.walterrx.casetwitter.integracao.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import br.com.walterrx.casetwitter.integracao.dto.ProcessarTwitter;
import br.com.walterrx.casetwitter.integracao.service.DadosTratadosTwitterService;
import br.com.walterrx.casetwitter.integracao.service.TwitterService;
import br.com.walterrx.casetwitter.integracao.transform.ObterRetornoTratado;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(TwitterController.TWITTER_BASE_URI)
public class TwitterController {
	

	public final static String TWITTER_BASE_URI = "svc/v1";
	public final static String ERROR_404_PARAMETRO_VIEW = "O parâmetro view é obrigatório.";

	@Autowired
	private TwitterService twitterService;
	
	@Autowired
	private DadosTratadosTwitterService dadosTratadosTwitterService;
	
	
	/**
	 * @author WalterRx
	 *
	 * Método responsável por expor uma API POST. A ideia é enviar no Post a relação de Hashtags que serão processadas
	 * e inseridas na base de dados MYSQL.
	 * 
	 * @param request EntradaProcessarTwitter - recebe as hashtags a serem processadas e gravadas na base
	 * @return ResponseEntity - retorna a resposta e também o status code da API
	 */
	@RequestMapping(value = "tweets/processarTweets", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Object> processTweets(@RequestBody ProcessarTwitter request) {
		twitterService.getTweets(request.getHashtags());
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
	
	// URL para teste no Postman http://localhost:9090/svc/v1/tweets/processarTweets
	//http://localhost:9090/svc/v1/tweets
	
	/**
	 * @author WalterRx
	 * 
	 * Método responsável por expor uma API GET para consultar e retornar os dados dos tweets que ja foram processados
	 * 
	 * @param view String - parâmetro view (neste caso aceita apenas "indicadores")
	 * @return ResponseEntity - retorna a resposta e também o status code da API
	 */
	@RequestMapping(value = "tweets", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Object> consultarIndicadores(@RequestParam final String view) {
		if ("obterRetorno".equals(view)) {
			return new ResponseEntity<>(ObterRetornoTratado.processar(dadosTratadosTwitterService.findTopUserFollowers(),
					dadosTratadosTwitterService.findTweetHashTagLanguage(), dadosTratadosTwitterService.findTweetDayHour()), HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TwitterController.ERROR_404_PARAMETRO_VIEW);
		}
	}

	
/*	
	@RequestMapping(value= "{hashTag}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String GetTweets(@PathVariable final String hashTag) {

		
		
		List<Tweet> tweets = this.twitter.searchOperations().search(hashTag, 100).getTweets();

		if (tweets != null) {
			for (Tweet t : tweets) {
				tweetRepository.save(new br.com.walterrx.casetwitter.integracao.model.Tweet(t.getIdStr(), t.getText(),
						t.getUser().getName(), t.getFromUser(), String.valueOf(t.getUser().getFollowersCount()),
						t.getUser().getLocation(), t.getLanguageCode(), t.getCreatedAt(), hashTag));
				
				}
			}
		
		
		return "Carga realizada";
		
	}*/
	
	
}
	

