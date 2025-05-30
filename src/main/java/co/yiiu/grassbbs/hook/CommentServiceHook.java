package co.yiiu.grassbbs.hook;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by tomoya.
 * Copyright (c) 2018, All Rights Reserved.
 * https://atjiu.github.io
 */
public class CommentServiceHook {

    @Pointcut("execution(public * co.yiiu.grassbbs.service.ICommentService.selectByTopicId(..))")
    public void selectByTopicId() {
    }

    @Pointcut("execution(public * co.yiiu.grassbbs.service.ICommentService.insert(..))")
    public void insert() {
    }

    @Pointcut("execution(public * co.yiiu.grassbbs.service.ICommentService.update(..))")
    public void update() {
    }

    @Pointcut("execution(public * co.yiiu.grassbbs.service.ICommentService.vote(..))")
    public void vote() {
    }

    @Pointcut("execution(public * co.yiiu.grassbbs.service.ICommentService.delete(..))")
    public void delete() {
    }

}
