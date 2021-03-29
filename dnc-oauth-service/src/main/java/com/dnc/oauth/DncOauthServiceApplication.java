package com.dnc.oauth;

import java.security.Principal;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class DncOauthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DncOauthServiceApplication.class, args);
	}
	
	@GetMapping("/")
	public String message(Principal principal) {
		Map<String, Object> details = (Map<String,Object>)((OAuth2Authentication) principal).getUserAuthentication().getDetails();
		String username =(String) details.get("name");
		return "Hi " + username+ ", Welcome";
		
		//return "Hi " + principal.getName()+ ", Welcome";
	}

}
