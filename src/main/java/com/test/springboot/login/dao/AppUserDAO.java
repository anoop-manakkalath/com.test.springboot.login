package com.test.springboot.login.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.test.springboot.login.entity.AppUser;

@Repository
@Transactional
public class AppUserDAO {

	@Inject
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<AppUser> findUserAccount(String userName) {
		String jpq = "SELECT e FROM AppUser e WHERE lower(e.userName) = lower(:userName)";
		Query query = entityManager.createQuery(jpq, AppUser.class);
		query.setParameter("userName", userName);
		return query.getResultList();
	}
}
