package com.example.demo.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class KonnectConfig {
	 @Value("${konnect.api.url}")
	    private String apiUrl;

	    @Value("${konnect.api.key}")
	    private String apiKey;

	    @Value("${konnect.wallet.id}")
	    private String walletId;
	    @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
		public String getApiUrl() {
			return apiUrl;
		}
		public void setApiUrl(String apiUrl) {
			this.apiUrl = apiUrl;
		}
		public String getApiKey() {
			return apiKey;
		}
		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}
		public String getWalletId() {
			return walletId;
		}
		public void setWalletId(String walletId) {
			this.walletId = walletId;
		}
	    
}
