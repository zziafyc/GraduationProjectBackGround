package com.zzia.graduation.base;

import java.util.List;

import org.hibernate.SessionFactory;

public interface BaseDao<T> {
	//基本的增删改查

	public void setSessionFactory(SessionFactory sessionFactory);

	public SessionFactory getSessionFactory();

	public void add(T o);
	
	public void delete(int id);

	public void delete(String id);
	
	public int delete(Object column, Object value);

	public void update(T o);
	
	public int updateOneColumn(Object column, Object value,Object updateColumn,Object updateValue);
	
	public int updateOneColumnByTwoColumn(Object column, Object value,Object column2, Object value2,Object updateColumn,Object updateValue);

	public T queryOne(Object column, Object value);
	
	public T queryOne(Object column, Object value,Object column2, Object value2);
	
	public List<T> queryAll(Object column, Object value);
	
	public List<T> queryAllAsc(Object column, Object value,Object orderName);
	
	public List<T> queryAllDesc(Object column, Object value,Object orderName);
	
	public List<T> queryAll(Object column, Object value,Object column2, Object value2);
	
	public List<T> queryAllPageDesc(Object orderName,int currentPage,int count);
	
	public List<T> queryAllPageDesc(Object column, Object value,Object orderName,int currentPage,int count);
	
	public List<T> queryAllPageDesc(Object column, Object value,Object column2, Object value2,Object orderName,int currentPage,int count);

	public List<T> queryAllPageAsc(Object column, Object value,Object orderName,int currentPage,int count);
	
	public List<T> queryAllPageAsc(Object column, Object value,Object column2, Object value2,Object orderName,int currentPage,int count);
	
	public T queryAll();


	
}
