package com.zzia.graduation.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseDaoBean<T> implements BaseDao<T> {
	// 当前的T类型
	private Class<T> clazz = null;

	private SessionFactory sessionFactory;

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public BaseDaoBean() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void add(T o) {
		getSession().merge(o);
	}
	
	@Override
	public void delete(int id) {
		getSession().delete(getSession().load(clazz, new Integer(id)));

	}
	@Override
	public void delete(String id) {
		getSession().delete(getSession().load(clazz, id));
	}
	@Override
	public int delete(Object column, Object value) {
		Query query = getSession()
				.createQuery("delete " + clazz.getSimpleName() + " where " + column + "=?");

		query.setParameter(0, value);
		int count = query.executeUpdate();
		return count;
	}

	@Override
	public void update(T o) {
		getSession().merge(o);
	}

	@Override
	public int updateOneColumn(Object column, Object value, Object updateColumn, Object updateValue) {

		int count;
		Query query = getSession()
				.createQuery("update " + clazz.getSimpleName() + " set " + updateColumn + "=? where " + column + "=? ");
		query.setParameter(0, updateValue);
		query.setParameter(1, value);
		count = query.executeUpdate();
		return count;
	}

	@Override
	public int updateOneColumnByTwoColumn(Object column, Object value, Object column2, Object value2,
			Object updateColumn, Object updateValue) {

		int count;
		Query query = getSession().createQuery("update " + clazz.getSimpleName() + " set " + updateColumn + "=? where "
				+ column + "=? and " + column2 + "=? ");
		query.setParameter(0, updateValue);
		query.setParameter(1, value);
		query.setParameter(2, value2);
		count = query.executeUpdate();
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T queryOne(Object column, Object value) {
		Query query = getSession().createQuery("from " + clazz.getSimpleName() + " where " + column + "=? ");
		query.setParameter(0, value);
		query.setMaxResults(1);
		List<T> list = query.list();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public T queryOne(Object column, Object value, Object column2, Object value2) {
		Query query = getSession()
				.createQuery("from " + clazz.getSimpleName() + " where " + column + "=? and " + column2 + "= ?");
		query.setParameter(0, value);
		query.setParameter(1, value2);
		query.setMaxResults(1);
		List<T> list = query.list();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryAll(Object column, Object value) {
		Query query = getSession().createQuery("from " + clazz.getSimpleName() + " where " + column + "=? ");
		query.setParameter(0, value);
		List<T> list = query.list();
		if (list != null && !list.isEmpty()) {
			return list;
		} else {
			return null;
		}

	}
	@Override
	public List<T> queryAllAsc(Object column, Object value, Object orderName) {
		Query query = getSession().createQuery("from " + clazz.getSimpleName() + " where " + column + " =? order by ? asc ");
		query.setParameter(0, value);
		query.setParameter(1, orderName);
		List<T> list = query.list();
		if (list != null && !list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}
	@Override
	public List<T> queryAllDesc(Object column, Object value, Object orderName) {
		Query query = getSession().createQuery("from " + clazz.getSimpleName() + " where " + column + " =? order by ? desc ");
		query.setParameter(0, value);
		query.setParameter(1, orderName);
		List<T> list = query.list();
		if (list != null && !list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryAll(Object column, Object value, Object column2, Object value2) {
		Query query = getSession()
				.createQuery("from " + clazz.getSimpleName() + " where " + column + "=? and " + column2 + "= ?");
		query.setParameter(0, value);
		query.setParameter(1, value2);
		List<T> list = query.list();
		if (list != null && !list.isEmpty()) {
			return list;
		} else {
			return null;
		}

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryAllPageDesc( Object orderName,int currentPage,int count) {
		Query query = getSession().createQuery(
				"from " + clazz.getSimpleName()  + " order by " + orderName + " desc");

		query.setFirstResult(count * (currentPage - 1));// 设置起始行
		query.setMaxResults(count);// 每页条数
		List<T> list = query.list();
		if (list != null && !list.isEmpty()) {
			return list;
		} else {
			return null;
		}

	}


	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryAllPageDesc(Object column, Object value, Object orderName,int currentPage,int count) {
		Query query = getSession().createQuery(
				"from " + clazz.getSimpleName() + " where " + column + "=? order by " + orderName + " desc");
		query.setParameter(0, value);
		query.setFirstResult(count * (currentPage - 1));// 设置起始行
		query.setMaxResults(count);// 每页条数
		List<T> list = query.list();
		if (list != null && !list.isEmpty()) {
			return list;
		} else {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryAllPageDesc(Object column, Object value, Object column2, Object value2, Object orderName,int currentPage,int count) {
		Query query = getSession().createQuery("from " + clazz.getSimpleName() + " where " + column + "=?  and "
				+ column2 + " = ? order by " + orderName + " desc");
		query.setParameter(0, value);
		query.setParameter(1, value2);
		query.setFirstResult(count * (currentPage - 1));// 设置起始行
		query.setMaxResults(count);// 每页条数
		List<T> list = query.list();
		if (list != null && !list.isEmpty()) {
			return list;
		} else {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryAllPageAsc(Object column, Object value, Object orderName,int currentPage,int count) {
		Query query = getSession().createQuery(
				"from " + clazz.getSimpleName() + " where " + column + "=? order by " + orderName + " asc");
		query.setParameter(0, value);
		query.setFirstResult(count * (currentPage - 1));// 设置起始行
		query.setMaxResults(count);// 每页条数
		List<T> list = query.list();
		if (list != null && !list.isEmpty()) {
			return list;
		} else {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryAllPageAsc(Object column, Object value, Object column2, Object value2, Object orderName,int currentPage,int count) {
		Query query = getSession().createQuery("from " + clazz.getSimpleName() + " where " + column + "=?  and "
				+ column2 + " = ? order by " + orderName + " asc");
		query.setParameter(0, value);
		query.setParameter(1, value2);
		query.setFirstResult(count * (currentPage - 1));// 设置起始行
		query.setMaxResults(count);// 每页条数
		List<T> list = query.list();
		if (list != null && !list.isEmpty()) {
			return list;
		} else {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public T queryAll() {
		Query query = getSession().createQuery("from " + clazz.getSimpleName() + "");
		if (query.list() != null && !query.list().isEmpty())
			return (T) query.list();
		else
			return null;
	}

	
	
}
