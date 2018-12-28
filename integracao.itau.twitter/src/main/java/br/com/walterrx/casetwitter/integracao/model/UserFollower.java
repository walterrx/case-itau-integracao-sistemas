package br.com.walterrx.casetwitter.integracao.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class UserFollower {
	
	@Id
	private String apelido;
	private Long quantidadeSeguidores;

	public UserFollower() {}
	
	public UserFollower(String apelido, Long quantidadeSeguidores) {
		this.apelido = apelido;
		this.quantidadeSeguidores = quantidadeSeguidores;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Long getQuantidadeSeguidores() {
		return quantidadeSeguidores;
	}

	public void setQuantidadeSeguidores(Long quantidadeSeguidores) {
		this.quantidadeSeguidores = quantidadeSeguidores;
	}

}
