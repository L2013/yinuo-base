package com.yinuo.base.exception;

import java.util.Collection;

/**
 * @author liang
 * similar to Preconditions in google guava, but much poor and throws BaseException
 */
public abstract class Assert {
    public static void isTrue(boolean expression, ResultCode rc) {
        if (!expression) {
            throw new BaseException(rc);
        }
    }

    public static void isFalse(boolean expression, ResultCode rc) {
        if (expression) {
            throw new BaseException(rc);
        }
    }

    public static void isTrue(boolean expression, ResultCodeEnum rc) {
        if (!expression) {
            throw new BaseException(rc);
        }
    }

    public static void isFalse(boolean expression, ResultCodeEnum rc) {
        if (expression) {
            throw new BaseException(rc);
        }
    }

    public static void notNull(Object object, ResultCodeEnum rc) {
        if (object == null) {
            throw new BaseException(rc);
        }
    }

    public static void notNull(Object object, ResultCode rc) {
        if (object == null) {
            throw new BaseException(rc);
        }
    }

    public static void notNull(Object object) {
        notNull(object, ResultCodeEnum.NULL);
    }

    public static void notEmpty(Collection<?> collection, ResultCodeEnum rc) {
        if (collection == null || collection.isEmpty()) {
            throw new BaseException(rc);
        }
    }

    public static void notEmpty(Collection<?> collection, ResultCode rc) {
        if (collection == null || collection.isEmpty()) {
            throw new BaseException(rc);
        }
    }

    public static void notEmpty(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new BaseException(ResultCodeEnum.EMPTY);
        }
    }

}
