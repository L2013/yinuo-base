package com.yinuo.base.jpa.criterion;

import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 条件构造器 用于创建条件表达式
 *
 * @Class Name Restrictions
 * @Author lee
 */
public class JpaRestrictions {

	/**
	 * 等于
	 */
	public static JpaSimpleExpression eq(String fieldName, Object value, boolean ignoreNull) {
		if (ignoreNull && StringUtils.isEmpty(value)) {
			return null;
		}
		return new JpaSimpleExpression(fieldName, value, JpaCriterion.Operator.EQ);
	}

	/**
	 * 集合包含某个元素
	 */
	public static JpaSimpleExpression hasMember(String fieldName, Object value, boolean ignoreNull) {
		if (ignoreNull && StringUtils.isEmpty(value)) {
			return null;
		}
		return new JpaSimpleExpression(fieldName, value, JpaCriterion.Operator.IS_MEMBER);
	}

	/**
	 * 不等于
	 */
	public static JpaSimpleExpression ne(String fieldName, Object value, boolean ignoreNull) {
		if (ignoreNull && StringUtils.isEmpty(value)) {
			return null;
		}
		return new JpaSimpleExpression(fieldName, value, JpaCriterion.Operator.NE);
	}

	/**
	 * 模糊匹配
	 */
	public static JpaSimpleExpression like(String fieldName, String value, boolean ignoreNull) {
		if (ignoreNull && StringUtils.isEmpty(value)) {
			return null;
		}
		return new JpaSimpleExpression(fieldName, value, JpaCriterion.Operator.LIKE);
	}

	/**
	 */
	// public static SimpleExpression like(String fieldName, String value,
	// MatchMode matchMode, boolean ignoreNull) {
	// if (StringUtils.isEmpty(value)) return null;
	// return null;
	// }

	/**
	 * 大于
	 */
	public static JpaSimpleExpression gt(String fieldName, Object value, boolean ignoreNull) {
		if (ignoreNull && StringUtils.isEmpty(value)) {
			return null;
		}
		return new JpaSimpleExpression(fieldName, value, JpaCriterion.Operator.GT);
	}

	/**
	 * 小于
	 */
	public static JpaSimpleExpression lt(String fieldName, Object value, boolean ignoreNull) {
		if (ignoreNull && StringUtils.isEmpty(value)) {
			return null;
		}
		return new JpaSimpleExpression(fieldName, value, JpaCriterion.Operator.LT);
	}

	/**
	 * 小于等于
	 */
	public static JpaSimpleExpression lte(String fieldName, Object value, boolean ignoreNull) {
		if (ignoreNull && StringUtils.isEmpty(value)) {
			return null;
		}
		return new JpaSimpleExpression(fieldName, value, JpaCriterion.Operator.GTE);
	}

	/**
	 * 大于等于
	 */
	public static JpaSimpleExpression gte(String fieldName, Object value, boolean ignoreNull) {
		if (ignoreNull && StringUtils.isEmpty(value)) {
			return null;
		}
		return new JpaSimpleExpression(fieldName, value, JpaCriterion.Operator.LTE);
	}

	/**
	 * 并且
	 */
	public static JpaLogicalExpression and(JpaCriterion... criterions) {
		return new JpaLogicalExpression(criterions, JpaCriterion.Operator.AND);
	}

	/**
	 * 或者
	 */
	public static JpaLogicalExpression or(JpaCriterion... criterions) {
		return new JpaLogicalExpression(criterions, JpaCriterion.Operator.OR);
	}

	/**
	 * 包含于
	 */
	@SuppressWarnings("rawtypes")
	public static JpaLogicalExpression in(String fieldName, Collection value, boolean ignoreNull) {
		if (ignoreNull && (value == null || value.isEmpty())) {
			return null;
		}
		JpaSimpleExpression[] ses = new JpaSimpleExpression[value.size()];
		int i = 0;
		for (Object obj : value) {
			ses[i] = new JpaSimpleExpression(fieldName, obj, JpaCriterion.Operator.EQ);
			i++;
		}
		return new JpaLogicalExpression(ses, JpaCriterion.Operator.OR);
	}

	/**
	 * 集合包含某几个元素，譬如可以查询User类中Set<String> set包含"ABC","bcd"的User集合，
	 * 或者查询User中Set<Address>的Address的name为"北京"的所有User集合
	 * 集合可以为基本类型或者JavaBean，可以是one to many或者是@ElementCollection
	 * 
	 * @param fieldName
	 *            列名
	 * @param value
	 *            集合
	 * @return expresssion
	 */
	public static JpaLogicalExpression hasMembers(String fieldName, Object... value) {
		JpaSimpleExpression[] ses = new JpaSimpleExpression[value.length];
		int i = 0;
		// 集合中对象是基本类型，如Set<Long>，List<String>
		JpaCriterion.Operator operator = JpaCriterion.Operator.IS_MEMBER;
		// 集合中对象是JavaBean
		if (fieldName.contains(".")) {
			operator = JpaCriterion.Operator.EQ;
		}
		for (Object obj : value) {
			ses[i] = new JpaSimpleExpression(fieldName, obj, operator);
			i++;
		}
		return new JpaLogicalExpression(ses, JpaCriterion.Operator.OR);
	}
}