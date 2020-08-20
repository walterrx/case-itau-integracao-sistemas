package br.com.walterrx.casetwitter.integracao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication 
public class CaseTwitterIntegracaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseTwitterIntegracaoApplication.class, args);
	}
}
