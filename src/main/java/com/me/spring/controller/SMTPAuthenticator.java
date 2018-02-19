package com.me.spring.controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

class SMTPAuthenticator extends Authenticator
{
public PasswordAuthentication getPasswordAuthentication()
{
    String username = "shahlabdhi0601@gmail.com";
    String password = "Neuaedspring16";
    return new PasswordAuthentication(username, password);
}
}