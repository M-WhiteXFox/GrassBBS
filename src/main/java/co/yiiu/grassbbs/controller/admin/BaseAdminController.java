package co.yiiu.grassbbs.controller.admin;

import co.yiiu.grassbbs.controller.api.BaseApiController;
import co.yiiu.grassbbs.model.AdminUser;
import co.yiiu.grassbbs.service.IAdminUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

/**
 * Created by tomoya.
 * Copyright (c) 2018, All Rights Reserved.
 * https://atjiu.github.io
 */
public class BaseAdminController extends BaseApiController {

    @Resource
    private IAdminUserService adminUserService;

    // 可以将传递到controller里的参数中Date类型的从String转成Date类型的对象
    // 这货没有想象中的好用，还不如我手动控制String转Date了
    //  @InitBinder
    //  public void initBinder(ServletRequestDataBinder binder) {
    //    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    //    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    //  }

    protected AdminUser getAdminUser() {
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        return adminUserService.selectByUsername(principal);
    }

}
