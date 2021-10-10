package com.yinuo.base.jpa;

import com.yinuo.base.dto.PageQuery;
import com.yinuo.base.dto.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;


/**
 * @author liang
 */
public interface BaseDao<T> {
    Optional<T> get(String id);

    T save(T t);

    T update(T t);

    void delete(String id);

    void deleteAll();

    List<T> list();

    Page<T> page(PageRequest p);

    Page<T> page(PageRequest p, T sample);

    List<T> saveAll(List<T> list);

    <S extends T> void batchSave(Iterable<S> data);
}
