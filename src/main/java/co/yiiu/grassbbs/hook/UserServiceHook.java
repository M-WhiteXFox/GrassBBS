package co.yiiu.grassbbs.hook;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by tomoya.
 * Copyright (c) 2018, All Rights Reserved.
 * https://atjiu.github.io
 */
public class UserServiceHook {

    @Pointcut("execution(public * co.yiiu.grassbbs.service.IUserService.selectByUsername(..))")
    public void selectByUsername() {
    }

    @Pointcut("execution(public * co.yiiu.grassbbs.service.IUserService.selectByToken(..))")
    public void selectByToken() {
    }

    @Pointcut("execution(public * co.yiiu.grassbbs.service.IUserService.selectById(..))")
    public void selectById() {
    }

    @Pointcut("execution(public * co.yiiu.grassbbs.service.IUserService.delRedisUser(..))")
    public void delRedisUser() {
    }

}
