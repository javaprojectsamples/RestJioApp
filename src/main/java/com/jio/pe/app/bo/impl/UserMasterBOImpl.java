package com.jio.pe.app.bo.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jio.pe.app.bo.IUserMasterBO;
import com.jio.pe.app.dao.IUserMasterDao;
import com.jio.pe.app.model.UserMaster;
import com.jio.pe.app.request.AbstractRequest;

@Service("iUserMasterBO")
public class UserMasterBOImpl implements IUserMasterBO {

	private IUserMasterDao iUserMasterDao = null;
	
	@Autowired
	public void setiUserMasterDao(IUserMasterDao iUserMasterDao) {
		this.iUserMasterDao = iUserMasterDao;
	}

	public List<UserMaster> findByCriteria(Session session, DetachedCriteria dc, int from, int size) {
		// TODO Auto-generated method stub
		return iUserMasterDao.findByCriteria(session, dc, from, size);
	}

	public int countByCriteria(Session session, DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return iUserMasterDao.countByCriteria(session, dc);
	}

	public List<UserMaster> getList(AbstractRequest<UserMaster> abstractRequest) {
		// TODO Auto-generated method stub
		return iUserMasterDao.getList(abstractRequest);
	}

	public int update(UserMaster entity) {
		// TODO Auto-generated method stub
		return iUserMasterDao.update(entity);
	}

	public int add(UserMaster entity) {
		// TODO Auto-generated method stub
		return iUserMasterDao.add(entity);
	}

	public int delete(UserMaster entity) {
		// TODO Auto-generated method stub
		return iUserMasterDao.delete(entity);
	}

	public UserMaster getEntity(long seq) {
		// TODO Auto-generated method stub
		return iUserMasterDao.getEntity(seq);
	}

}
