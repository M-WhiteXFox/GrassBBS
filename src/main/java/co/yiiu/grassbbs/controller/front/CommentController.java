package co.yiiu.grassbbs.controller.front;

import co.yiiu.grassbbs.model.Comment;
import co.yiiu.grassbbs.model.Topic;
import co.yiiu.grassbbs.service.ICommentService;
import co.yiiu.grassbbs.service.ITopicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by tomoya.
 * Copyright (c) 2018, All Rights Reserved.
 * https://atjiu.github.io
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {

    @Resource
    private ICommentService commentService;
    @Resource
    private ITopicService topicService;

    // 编辑评论
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Comment comment = commentService.selectById(id);
        Topic topic = topicService.selectById(comment.getTopicId());
        model.addAttribute("comment", comment);
        model.addAttribute("topic", topic);
        return render("comment/edit");
    }
}
