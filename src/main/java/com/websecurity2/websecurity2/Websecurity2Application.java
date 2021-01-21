package com.websecurity2.websecurity2;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Websecurity2Application {

	public static void main(String[] args) {
		SpringApplication.run(Websecurity2Application.class, args);
	}

	//HTTP 는 8080 포트로 접속. 세션으로 인해 접속이 안될 수도 있음.
	@Bean
	public ServletWebServerFactory servletContainer(){
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(createStandardConnector());
		return tomcat;
	}
	private Connector createStandardConnector(){
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setPort(8080);
		return connector;
	}

}
