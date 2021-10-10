package com.yinuo.base.batch;

import java.util.Map;

/**
 * @author liangliang
 */
public interface BatchJob {
    void execute(Map<String, String> params) throws Exception;
}
