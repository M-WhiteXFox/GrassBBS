package co.yiiu.grassbbs.hook;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by tomoya.
 * Copyright (c) 2018, All Rights Reserved.
 * https://atjiu.github.io
 */
public class TopicServiceHook {

    @Pointcut("execution(public * co.yiiu.grassbbs.service.ITopicService.search(..))")
    public void search() {
    }

    @Pointcut("execution(public * co.yiiu.grassbbs.service.ITopicService.selectById(..))")
    public void selectById() {
    }

    @Pointcut("execution(public * co.yiiu.grassbbs.service.ITopicService.update(..))")
    public void update() {
    }

    @Pointcut("execution(public * co.yiiu.grassbbs.service.ITopicService.vote(..))")
    public void vote() {
    }

    @Pointcut("execution(public * co.yiiu.grassbbs.service.ITopicService.updateViewCount(..))")
    public void updateViewCount() {
    }

}
