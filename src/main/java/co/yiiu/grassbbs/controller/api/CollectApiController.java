package co.yiiu.grassbbs.controller.api;

import co.yiiu.grassbbs.exception.ApiAssert;
import co.yiiu.grassbbs.model.Collect;
import co.yiiu.grassbbs.model.User;
import co.yiiu.grassbbs.service.ICollectService;
import co.yiiu.grassbbs.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by tomoya.
 * Copyright (c) 2018, All Rights Reserved.
 * https://atjiu.github.io
 */
@RestController
@RequestMapping("/api/collect")
public class CollectApiController extends BaseApiController {

    @Resource
    private ICollectService collectService;

    // 收藏话题
    @PostMapping("/{topicId}")
    public Result get(@PathVariable Integer topicId) {
        User user = getApiUser();
        Collect collect = collectService.selectByTopicIdAndUserId(topicId, user.getId());
        ApiAssert.isNull(collect, "做人要知足，每人每个话题只能收藏一次哦！");
        collectService.insert(topicId, user);
        return success();
    }

    // 取消收藏
    @DeleteMapping("/{topicId}")
    public Result delete(@PathVariable Integer topicId) {
        User user = getApiUser();
        Collect collect = collectService.selectByTopicIdAndUserId(topicId, user.getId());
        ApiAssert.notNull(collect, "你都没有收藏这个话题，哪来的取消？");
        collectService.delete(topicId, user.getId());
        return success();
    }
}
