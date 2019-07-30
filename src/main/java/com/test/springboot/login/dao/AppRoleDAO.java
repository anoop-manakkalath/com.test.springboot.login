package com.test.springboot.login.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AppRoleDAO {

	@Inject
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<String> getRoleNames(Long userId) {
		String jpq = "SELECT r.appRole.roleName FROM UserRole r WHERE r.appUser.userId = :userId ";
		Query query = this.entityManager.createQuery(jpq, String.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}
}
