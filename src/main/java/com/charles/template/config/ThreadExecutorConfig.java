package com.charles.template.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author charles tao
 */
@Component
@Slf4j
public class ThreadExecutorConfig {

    @Bean("fixedBlockingExecutor")
    public ExecutorService fixedBlockingExecutor() {
        return new ThreadPoolExecutor(16, 16,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(100));
    }

    /**
     * scheduler 多线程执行
     */
    @Bean("taskScheduler")
    public TaskScheduler scheduledThreadPool() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(16);
        taskScheduler.setErrorHandler(t -> log.error("taskScheduler:", t));
        return taskScheduler;
    }


    /**
     * listener 多线程去执行
     */

    @Bean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
    public ApplicationEventMulticaster initEventMulticaster(@Qualifier("fixedThreadPool") ExecutorService taskExecutor) {
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster();
        simpleApplicationEventMulticaster.setErrorHandler(t -> log.error("event:", t));
        simpleApplicationEventMulticaster.setTaskExecutor(taskExecutor);
        return simpleApplicationEventMulticaster;
    }
}
