package com.example.demo.services;

import java.io.UnsupportedEncodingException;

import com.example.demo.dtos.PasswordDto;
import com.example.demo.dtos.ResetPassword;
import com.example.demo.dtos.SignupRequest;
import com.example.demo.dtos.UserDto;

import jakarta.mail.MessagingException;

public interface  AuthService {
UserDto createCustomer(SignupRequest signupRequest);
boolean verify(String verficationCod);
void sendVerificationEmail(UserDto user) throws UnsupportedEncodingException, MessagingException;
UserDto validePassword(PasswordDto pd);
UserDto changeuser(String code);
public boolean sendUpdatePasswordEmail(String email)throws UnsupportedEncodingException, MessagingException;
public boolean resetPassword(ResetPassword rp);
}
