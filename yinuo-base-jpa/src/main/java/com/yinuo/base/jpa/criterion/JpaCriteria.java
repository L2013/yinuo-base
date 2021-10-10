package com.yinuo.base.jpa.criterion;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liang
 */
public class JpaCriteria<T> implements Specification<T> {
    private static final long serialVersionUID = 1L;
    private List<JpaCriterion> criterions = new ArrayList<>();

    /**
     * 增加简单条件表达式
     *
     * @Methods Name add
     * @Create In 2012-2-8 By lee
     */
    public void add(JpaCriterion criterion) {
        if (criterion != null) {
            criterions.add(criterion);
        }
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (!criterions.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            for (JpaCriterion c : criterions) {
                predicates.add(c.toPredicate(root, query, builder));
            }
            // 将所有条件用 and 联合起来
            if (predicates.size() > 0) {
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }
        return builder.conjunction();
    }
}
