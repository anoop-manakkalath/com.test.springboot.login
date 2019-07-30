package com.test.springboot.login.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.test.springboot.login.dao.AppRoleDAO;
import com.test.springboot.login.dao.AppUserDAO;
import com.test.springboot.login.entity.AppUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Inject
	private AppUserDAO AppUserDAO;

	@Inject
	private AppRoleDAO appRoleDAO;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		List<AppUser> users = this.AppUserDAO.findUserAccount(userName);
		
		if (users.isEmpty()) {
			System.out.println("User not found! " + userName);
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}
		
		AppUser appUser = users.get(0);
		System.out.println("Found User: " + appUser);

		// [ROLE_USER, ROLE_ADMIN,..]
		List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());
		List<GrantedAuthority> grantList = Lists.newArrayList();
		roleNames.forEach(role -> grantList.add(new SimpleGrantedAuthority(role))); // ROLE_USER, ROLE_ADMIN,..

		UserDetails userDetails = new User(appUser.getUserName(), appUser.getEncrytedPassword(), grantList);

		return userDetails;
	}
}
