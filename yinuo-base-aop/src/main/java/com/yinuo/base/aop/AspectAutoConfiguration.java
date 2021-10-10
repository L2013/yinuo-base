package com.yinuo.base.aop;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author liang
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(CatchLogAspect.class)
    public CatchLogAspect catchLogAspect() {
        return new CatchLogAspect();
    }

    @Bean
    @ConditionalOnMissingBean(CostTimeAspect.class)
    public CostTimeAspect costTimeAspect() {
        return new CostTimeAspect();
    }
}
