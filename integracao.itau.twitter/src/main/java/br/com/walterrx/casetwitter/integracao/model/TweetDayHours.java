package br.com.walterrx.casetwitter.integracao.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TweetDayHours {
	
	@Id
	private Integer hora;
	private Long total;
	
	public TweetDayHours() {
	}

	public TweetDayHours(Integer hora, Long total) {
		this.hora = hora;
		this.total = total;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
