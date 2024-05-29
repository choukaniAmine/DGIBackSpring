package com.example.demo.dtos;

public class PayementResponse {
	 private String payUrl;
	    private String paymentRef;
		public String getPayUrl() {
			return payUrl;
		}
		public void setPayUrl(String payUrl) {
			this.payUrl = payUrl;
		}
		public String getPaymentRef() {
			return paymentRef;
		}
		public void setPaymentRef(String paymentRef) {
			this.paymentRef = paymentRef;
		}
	    
}
