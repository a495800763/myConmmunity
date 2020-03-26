package life.majiang.community.community.controller;

import life.majiang.community.community.dto.CommentDTO;
import life.majiang.community.community.dto.ResultDTO;
import life.majiang.community.community.exception.CustomizeErrorCode;
import life.majiang.community.community.mapper.CommentMapper;
import life.majiang.community.community.model.Comment;
import life.majiang.community.community.model.User;
import life.majiang.community.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-26 09:45
 **/
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
             return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(DEFAULT_LIKE_COUNT);
        comment.setCommentator(1);
        commentService.insert(comment);
        return ResultDTO.okOf();

    }

    private static final Long DEFAULT_LIKE_COUNT = 0L;
}
