package com.example.kassa3.dao.impl;


import com.example.kassa3.dao.abstracts.ReadWriteDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class ReadWriteDaoImpl<T, K> implements ReadWriteDao<T, K> {
    private final Class<T> clazz;

    @PersistenceContext
    protected EntityManager em;

    @SuppressWarnings("unchecked")
    public ReadWriteDaoImpl() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public void persist(T entity) {
        em.persist(entity);
    }

    @Override
    public void update(T entity) {
        em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    @Override
    public void deleteByIdCascadeEnable(K id) {
        em.remove(em.find(clazz, id));
    }

    @Override
    public void deleteByIdCascadeIgnore(K id) {
        em.createQuery("DELETE FROM " + clazz.getName() + " u WHERE u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public boolean existsById(K id) {
        return  em.find(clazz, id) != null;
    }

    @Override
    public T findById(K id) {
        return em.find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("select u from " + clazz.getName() + " u", clazz)
                .getResultList();
    }

    @Override
    public Page<T> getAll(Pageable pageable) {
        Query query =  em.createQuery("select u from " + clazz.getName() + "u", clazz);
        int pageNumber = pageable.getPageNumber();
        int pageSize =pageable.getPageSize();
        query.setFirstResult((pageNumber) * pageSize);
        query.setMaxResults(pageSize);

        List<T> list = query.getResultList();

        Query queryCount = em.createQuery("Select count(i.id) From" +  clazz.getName()+ "i", clazz);
        long count = (long) queryCount.getSingleResult();

        return  new PageImpl<T>(list, pageable,count);
    }
}
