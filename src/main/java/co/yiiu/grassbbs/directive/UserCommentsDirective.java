package co.yiiu.grassbbs.directive;

import co.yiiu.grassbbs.model.User;
import co.yiiu.grassbbs.service.ICommentService;
import co.yiiu.grassbbs.service.IUserService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * Created by tomoya.
 * Copyright (c) 2018, All Rights Reserved.
 * https://atjiu.github.io
 */
@Component
public class UserCommentsDirective implements TemplateDirectiveModel {

    @Resource
    private ICommentService commentService;
    @Resource
    private IUserService userService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody
            templateDirectiveBody) throws TemplateException, IOException {
        String username = String.valueOf(map.get("username"));
        Integer pageNo = Integer.parseInt(map.get("pageNo").toString());
        Integer pageSize = map.get("pageSize") == null ? null : Integer.parseInt(map.get("pageSize").toString());
        User user = userService.selectByUsername(username);
        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28);
        environment.setVariable("comments", builder.build().wrap(commentService.selectByUserId(user.getId(), pageNo,
                pageSize)));
        templateDirectiveBody.render(environment.getOut());
    }
}
