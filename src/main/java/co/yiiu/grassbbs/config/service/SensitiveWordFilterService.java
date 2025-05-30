package co.yiiu.grassbbs.config.service;

import co.yiiu.grassbbs.model.SensitiveWord;
import co.yiiu.grassbbs.service.ISensitiveWordService;
import co.yiiu.grassbbs.util.SensitiveWordUtil;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tomoya.
 * Copyright (c) 2018, All Rights Reserved.
 * https://atjiu.github.io
 */
@Component
@DependsOn("mybatisPlusConfig")
public class SensitiveWordFilterService {

    @Resource
    private ISensitiveWordService sensitiveWordService;

    // 初始化过滤器
    @PostConstruct
    public void init() {
        List<SensitiveWord> sensitiveWords = sensitiveWordService.selectAll();
        Set<String> sensitiveWordSet = new HashSet<>();
        for (SensitiveWord sensitiveWord : sensitiveWords) {
            sensitiveWordSet.add(sensitiveWord.getWord());
        }
        SensitiveWordUtil.init(sensitiveWordSet);
    }
}
