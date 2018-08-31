package com.example.demo.exception;

import org.apache.shiro.authc.AuthenticationException;

public class RepateLoginException extends AuthenticationException{
	private static final long serialVersionUID = -1789347196095690999L;
	
	public RepateLoginException(String message) {
		super(message);
	}
}
