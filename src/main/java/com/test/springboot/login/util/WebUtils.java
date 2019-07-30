package com.test.springboot.login.util;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.CollectionUtils;

public class WebUtils {
	
	private WebUtils() {
		
	}

	public static String toString(User user) {
		StringBuilder sb = new StringBuilder();

		sb.append("UserName:").append(user.getUsername());

		Collection<GrantedAuthority> authorities = user.getAuthorities();
		if (CollectionUtils.isEmpty(authorities)) {
			sb.append(" (");
			AtomicBoolean first = new AtomicBoolean(true);
			authorities.forEach(a -> {
				if (first.get()) {
					sb.append(a.getAuthority());
					first.set(false);
				} else {
					sb.append(", ").append(a.getAuthority());
				}
			});
			sb.append(")");
		}
		return sb.toString();
	}
}
