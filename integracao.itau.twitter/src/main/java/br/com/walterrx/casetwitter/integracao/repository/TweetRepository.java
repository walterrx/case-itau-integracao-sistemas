package br.com.walterrx.casetwitter.integracao.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.walterrx.casetwitter.integracao.model.Tweet;

// Será auto implementado pelo Spring dentro do Bean TweetRepository

public interface TweetRepository extends CrudRepository<Tweet, String> {

	 public List<Tweet> findByHashtag(String hashtag);

}
