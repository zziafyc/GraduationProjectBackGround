package com.zzia.graduation.base;

import java.lang.reflect.ParameterizedType;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseDaoBean<T> implements BaseDao<T> {
	//当前的T类型
	private Class<T> clazz=null;
	private SessionFactory sessionFactory;
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public BaseDaoBean(){
		ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
		clazz=(Class<T>) type.getActualTypeArguments()[0];
	}
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public void add(T o) {
		getSession().merge(o);
	}

	@Override
	public void update(T o) {
		getSession().merge(o);
	}

	@Override
	public void delete(String id) {
		getSession().delete(getSession().load(clazz, id));
	}
	@Override
	public T query(String id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public T queryAll() {
		Query query=getSession().createQuery("from "+clazz.getSimpleName()+"");
		if(query.list()!=null&&!query.list().isEmpty())
			return (T) query.list();
		else
			return null;
	}
}
