package co.yiiu.grassbbs.hook;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by tomoya.
 * Copyright (c) 2018, All Rights Reserved.
 * https://atjiu.github.io
 */
public class IndexedServiceHook {

    @Pointcut("execution(public * co.yiiu.grassbbs.service.IIndexedService.indexAllTopic(..))")
    public void indexAllTopic() {
    }

    @Pointcut("execution(public * co.yiiu.grassbbs.service.IIndexedService.indexTopic(..))")
    public void indexTopic() {
    }

    @Pointcut("execution(public * co.yiiu.grassbbs.service.IIndexedService.deleteTopicIndex(..))")
    public void deleteTopicIndex() {
    }

    @Pointcut("execution(public * co.yiiu.grassbbs.service.IIndexedService.batchDeleteIndex(..))")
    public void batchDeleteIndex() {
    }

}
