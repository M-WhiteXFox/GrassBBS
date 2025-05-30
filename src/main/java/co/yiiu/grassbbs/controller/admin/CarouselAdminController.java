package co.yiiu.grassbbs.controller.admin;

import co.yiiu.grassbbs.model.Carousel;
import co.yiiu.grassbbs.service.ICarouselService;
import co.yiiu.grassbbs.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin/img")
public class CarouselAdminController extends BaseAdminController {

    @Resource
    private ICarouselService IcarouselService;

    /**
     * 轮播图管理页面 - 显示所有轮播图
     */
    @RequiresPermissions("imgs:list")
    @GetMapping("/imgs_show")
    public String list(Model model) {
        List<Carousel> carousels = IcarouselService.selectAll(); // 获取所有轮播图
        model.addAttribute("carousels", carousels);              // 传递给模板
        return "admin/img/imgs_show";                            // 显示页面
    }

    /**
     * 编辑轮播图（仅修改 image_url 和 web_url）
     */
    @RequiresPermissions("imgs:edit")
    @PostMapping("/edit")
    @ResponseBody
    public Result edit(@RequestBody Carousel carousel) {
        if (carousel.getId() == null) {
            return Result.error("轮播图 ID 不能为空");
        }
        IcarouselService.update(carousel); // 根据主键更新
        return Result.success();
    }
}
