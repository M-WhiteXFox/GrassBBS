package co.yiiu.grassbbs.directive;

import co.yiiu.grassbbs.model.User;
import co.yiiu.grassbbs.service.IUserService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by tomoya.
 * Copyright (c) 2018, All Rights Reserved.
 * https://atjiu.github.io
 */
@Component
public class ScoreDirective implements TemplateDirectiveModel {

    @Resource
    private IUserService userService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody
            templateDirectiveBody) throws TemplateException, IOException {
        Integer limit = Integer.parseInt(map.get("limit").toString());
        if (limit > 100) limit = 100;
        List<User> users = userService.selectTop(limit);

        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28);
        environment.setVariable("users", builder.build().wrap(users));
        templateDirectiveBody.render(environment.getOut());
    }
}
