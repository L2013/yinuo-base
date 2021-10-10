package com.yinuo.base.batch;


import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author liangliang
 */
@Slf4j
public class BatchJobExecutor {
    public static void run(Map<String, String> params, BatchJob... job) throws Exception {
        run(params, Arrays.asList(job));
    }

    public static void run(BatchJob... jobs) throws Exception {
        run(null, Arrays.asList(jobs));
    }

    public static void run(Map<String, String> params, List<BatchJob> jobs) throws Exception {
        for (BatchJob job : jobs) {
            job.execute(params);
        }
        declareSuccess();
    }

    private static void declareSuccess() {
        String SUCCESS = "result:success";
        System.out.println(SUCCESS);
        log.info(SUCCESS);
    }
}
