package com.example.demo.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.configurations.KonnectConfig;
import com.example.demo.dtos.PayementRequest;
import com.example.demo.dtos.PayementResponse;
import com.example.demo.dtos.PayementStatus;

@Service
public class KonnectPaymentService {
	 @Autowired
	    private KonnectConfig konnectConfig;

	    
	 private final RestTemplate restTemplate;


	    public KonnectPaymentService(RestTemplate restTemplate) {
	        this.restTemplate = restTemplate;
	    }
	
	public PayementResponse initiatePayment(PayementRequest paymentRequest) {
		String url = konnectConfig.getApiUrl() + "/payments/init-payment";
     HttpHeaders headers = new HttpHeaders();
     headers.set("x-api-key", konnectConfig.getApiKey());
     headers.setContentType(MediaType.APPLICATION_JSON);

     HttpEntity<PayementRequest> request = new HttpEntity<>(paymentRequest, headers);
     ResponseEntity<PayementResponse> response = restTemplate.postForEntity(url, request, PayementResponse.class);

     return response.getBody();
	}

	public PayementStatus getPaymentStatus(String paymentId) {
		 String url = konnectConfig.getApiUrl() + "/payments/" + paymentId;
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("x-api-key", konnectConfig.getApiKey());

	        HttpEntity<String> request = new HttpEntity<>(headers);
	        ResponseEntity<PayementStatus> response = restTemplate.exchange(url, HttpMethod.GET, request, PayementStatus.class);
	        return response.getBody();
	}
}
