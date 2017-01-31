package com.jio.pe.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.jio.pe.app.request.AbstractRequest;

public interface IGenericDao<T> {
	public List<T> findByCriteria(Session session, DetachedCriteria dc,
			int from, int size);
	
	public int countByCriteria(Session session, DetachedCriteria dc);
	
	public List<T> getList(AbstractRequest<T> abstractRequest);
	
	public int update(T entity);
	
	public int add(T entity);
	
	public int delete(T entity);
	
	public T getEntity(long seq);

}
