package com.test.springboot.login.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncrytedPasswordUtils {
	
	private EncrytedPasswordUtils() {
		
	}

	// Encrypt Password with BCryptPasswordEncoder
	public static String encrytePassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
}
