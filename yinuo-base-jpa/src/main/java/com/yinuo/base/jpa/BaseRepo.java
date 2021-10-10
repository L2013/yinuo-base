package com.yinuo.base.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author liang
 */
public interface BaseRepo<T> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {
}
