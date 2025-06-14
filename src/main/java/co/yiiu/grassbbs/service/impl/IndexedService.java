package co.yiiu.grassbbs.service.impl;

import co.yiiu.grassbbs.service.IIndexedService;
import org.springframework.stereotype.Service;

/**
 * Created by tomoya.
 * Copyright (c) 2018, All Rights Reserved.
 * https://atjiu.github.io
 */
@Service
public class IndexedService implements IIndexedService {

    // 索引全部话题
    @Override
    public void indexAllTopic() {
    }

    // 索引话题
    @Override
    public void indexTopic(String id, String title, String content) {
    }

    // 删除话题索引
    @Override
    public void deleteTopicIndex(String id) {
    }

    // 删除所有话题索引
    @Override
    public void batchDeleteIndex() {
    }
}
