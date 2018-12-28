package br.com.walterrx.casetwitter.integracao.dto;

import java.io.Serializable;

public class GetTwitterData implements Serializable {
	
	/**
	 * Obter todos os dados do banco de dados com as informações dos Tweets
	 */
	private static final long serialVersionUID = 1L;
	private Object obterDados;

	public GetTwitterData(Object data) {

		this.obterDados = data;
	}

	public Object getObterDados() {
		return obterDados;
	}

	public void setObterDados(Object obterDados) {
		this.obterDados = obterDados;
	}


	
}
