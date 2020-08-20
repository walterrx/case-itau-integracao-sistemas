package br.com.walterrx.casetwitter.integracao.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import br.com.walterrx.casetwitter.integracao.model.TweetHashTagLanguage;

public interface TweetHashTagLanguageRepository extends CrudRepository<TweetHashTagLanguage, String> {

	List<TweetHashTagLanguage> findAll(Sort sort);

}
