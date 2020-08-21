package br.com.walterrx.casetwitter.integracao.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import br.com.walterrx.casetwitter.integracao.model.UserFollower;

public interface TopUserFollowersRepository  extends CrudRepository<UserFollower, String> {

	List<UserFollower> findAll(Sort sort);

}
