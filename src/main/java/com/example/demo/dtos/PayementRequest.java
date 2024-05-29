package com.example.demo.dtos;

import java.util.List;

public class PayementRequest {
	 private String receiverWalletId;
	    private String token;
	    private int amount;
	    private String type;
	    private String description;
	    private List<String> acceptedPaymentMethods;
	    private int lifespan;
	    private boolean checkoutForm;
	    private boolean addPaymentFeesToAmount;
	    private String firstName;
	    private String lastName;
	    private String phoneNumber;
	    private String email;
	    private String orderId;
	    private String webhook;
	    private boolean silentWebhook;
	    private String successUrl;
	    private String failUrl;
	    private String theme;
		public String getReceiverWalletId() {
			return receiverWalletId;
		}
		public void setReceiverWalletId(String receiverWalletId) {
			this.receiverWalletId = receiverWalletId;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public List<String> getAcceptedPaymentMethods() {
			return acceptedPaymentMethods;
		}
		public void setAcceptedPaymentMethods(List<String> acceptedPaymentMethods) {
			this.acceptedPaymentMethods = acceptedPaymentMethods;
		}
		public int getLifespan() {
			return lifespan;
		}
		public void setLifespan(int lifespan) {
			this.lifespan = lifespan;
		}
		public boolean isCheckoutForm() {
			return checkoutForm;
		}
		public void setCheckoutForm(boolean checkoutForm) {
			this.checkoutForm = checkoutForm;
		}
		public boolean isAddPaymentFeesToAmount() {
			return addPaymentFeesToAmount;
		}
		public void setAddPaymentFeesToAmount(boolean addPaymentFeesToAmount) {
			this.addPaymentFeesToAmount = addPaymentFeesToAmount;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getOrderId() {
			return orderId;
		}
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}
		public String getWebhook() {
			return webhook;
		}
		public void setWebhook(String webhook) {
			this.webhook = webhook;
		}
		public boolean isSilentWebhook() {
			return silentWebhook;
		}
		public void setSilentWebhook(boolean silentWebhook) {
			this.silentWebhook = silentWebhook;
		}
		public String getSuccessUrl() {
			return successUrl;
		}
		public void setSuccessUrl(String successUrl) {
			this.successUrl = successUrl;
		}
		public String getFailUrl() {
			return failUrl;
		}
		public void setFailUrl(String failUrl) {
			this.failUrl = failUrl;
		}
		public String getTheme() {
			return theme;
		}
		public void setTheme(String theme) {
			this.theme = theme;
		}
	    
	    
}
