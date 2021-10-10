package com.yinuo.base.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author liang
 */
public abstract class BaseDaoImpl<T, R extends BaseRepo<T>> implements BaseDao<T> {
    @Autowired
    protected R repo;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<T> get(String id) {
        return repo.findById(id);
    }

    @Override
    public T save(T t) {
        return repo.save(t);
    }

    @Override
    public T update(T t) {
        return repo.save(t);
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }

    @Override
    public List<T> list() {
        return repo.findAll();
    }

    @Override
    public Page<T> page(PageRequest p) {
        return repo.findAll(p);
    }


    @Override
    public Page<T> page(PageRequest p, T sample) {
        return repo.findAll(getCriteria(sample), p);
    }

    @Override
    public List<T> saveAll(List<T> list) {
        return repo.saveAll(list);
    }

    @Override
    public <S extends T> void batchSave(Iterable<S> data) {
        Iterator<S> iterator = data.iterator();
        while (iterator.hasNext()) {
            em.persist(iterator.next());
        }
        em.flush();
        em.clear();
    }

    public abstract Specification<T> getCriteria(T sample);
}
