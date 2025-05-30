package co.yiiu.grassbbs.mapper;

import co.yiiu.grassbbs.model.AdminUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by tomoya.
 * Copyright (c) 2018, All Rights Reserved.
 * https://atjiu.github.io
 */
public interface AdminUserMapper extends BaseMapper<AdminUser> {

    List<Map<String, Object>> selectAll();
}
