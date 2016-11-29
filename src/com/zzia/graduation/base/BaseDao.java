package com.zzia.graduation.base;

import org.hibernate.SessionFactory;

public interface BaseDao<T> {
	public void setSessionFactory(SessionFactory sessionFactory);
	public void add(T o);
	public void update(T o);
	public void delete(String id);
	public T query(String id);
	public T queryAll();
}
