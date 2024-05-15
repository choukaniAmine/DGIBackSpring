package com.example.demo.dtos;

public class UpdatePasswordDto {
private Long id;
private String passwordPrec;
private String nvPassword;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getPasswordPrec() {
	return passwordPrec;
}
public void setPasswordPrec(String passwordPrec) {
	this.passwordPrec = passwordPrec;
}
public String getNvPassword() {
	return nvPassword;
}
public void setNvPassword(String nvPassword) {
	this.nvPassword = nvPassword;
}

}
