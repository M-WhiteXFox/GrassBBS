package co.yiiu.grassbbs.service;

import co.yiiu.grassbbs.model.Code;

/**
 * Created by tomoya.
 * Copyright (c) 2018, All Rights Reserved.
 * https://atjiu.github.io
 */
public interface ICodeService {
    Code selectByCode(String _code);

    // 查询没有用过的code
    Code selectNotUsedCode(Integer userId, String email, String mobile);

    // 创建一条验证码记录
    Code createCode(Integer userId, String email, String mobile);

    // 验证邮箱验证码
    Code validateCode(Integer userId, String email, String mobile, String _code);

    // 发送邮件
    boolean sendEmail(Integer userId, String email, String title, String content);

    // 发送短信
    boolean sendSms(String mobile);

    void update(Code code);

    // 根据用户id删除评论记录
    void deleteByUserId(Integer userId);
}
