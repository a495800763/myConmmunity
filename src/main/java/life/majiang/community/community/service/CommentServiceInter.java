package life.majiang.community.community.service;

import life.majiang.community.community.dto.CommentDTO;
import life.majiang.community.community.enums.CommentTypeEnum;
import life.majiang.community.community.model.Comment;
import life.majiang.community.community.model.User;

import java.util.List;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-04-03 14:36
 **/
public interface CommentServiceInter {
    void insert(Comment comment, User commentator);

    List<CommentDTO> listByTargetid(Long id, CommentTypeEnum type);
}
