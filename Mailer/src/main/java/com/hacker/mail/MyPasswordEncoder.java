package com.hacker.mail;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
    	System.out.println(charSequence.toString() +"priya");
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
    	System.out.println("password:"+s+ "other:"+charSequence);
        return charSequence.toString().equals(s);
    }
}