package com.jio.pe.app.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.jio.pe.app.dao.IUserMasterDao;
import com.jio.pe.app.exceptions.UserNotFoundException;
import com.jio.pe.app.model.UserMaster;
import com.jio.pe.app.request.AbstractRequest;

@Repository("iUserMasterDao")
public class UserMasterDaoImpl /* extends CustomHibernateDaoSupport */ implements IUserMasterDao {

	@Resource
	private SessionFactory sessionFactory;
	// private Session session;

	public List<UserMaster> findByCriteria(Session session, DetachedCriteria dc, int from, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public int countByCriteria(Session session, DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Cacheable(value = "users")
	public List<UserMaster> getList(AbstractRequest<UserMaster> abstractRequest) {
		// TODO Auto-generated method stub

		Session session = getASession();

		List<UserMaster> list = session.createQuery("from UserMaster").getResultList();

		System.out.println(list.size());

		session.close();

		if (!(list != null && list.size() > 0)) {
			// System.exit(-1);
		}

		return list;
	}

	@Cacheable(value = "users" , key="#entity.userId")
	public int update(UserMaster entity) {
		// TODO Auto-generated method stub

		Session session = getASession();

		session.update(entity);

		session.close();

		// getHibernateTemplate().update(entity);

		return 0;
	}

	@Cacheable(value = "users" , key="#entity.userId")
	public int add(UserMaster entity) {
		// TODO Auto-generated method stub

		Session session = null;
		try {
			session = getASession();
			session.save(entity);
		} finally {
			session.close();
		}
		return 0;
	}

	@Cacheable(value = "users" , key="#entity.userId")
	public int delete(UserMaster entity) {
		// TODO Auto-generated method stub
		// getHibernateTemplate().delete(entity);
		Session session = null;
		try {
			session = getASession();
			session.delete(entity);

		} finally {
			session.close();
		}
		return 0;
	}

	@Cacheable(value = "users", key = "#seq")
	public UserMaster getEntity(long seq) {
		// TODO Auto-generated method stub
		UserMaster userMaster = null;
		Session session = null;
		try {

			session = getASession();

			List<UserMaster> list = session.createQuery("from UserMaster where userId=" + seq).getResultList();

			System.out.println(list.size());

			if (list != null && list.size() > 0) {
				userMaster = list.get(0);
			}
			else
			{
				throw new UserNotFoundException("Requested User:"+seq +" not found in database");
			}
			
		} finally {

			session.close();
		}
		return userMaster;
	}

	private Session getASession() {
		Session session = sessionFactory.openSession();
		return session;
	}

}
