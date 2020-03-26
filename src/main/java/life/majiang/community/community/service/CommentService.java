package life.majiang.community.community.service;

import life.majiang.community.community.enums.CommentTypeEnum;
import life.majiang.community.community.exception.CustomizeErrorCode;
import life.majiang.community.community.exception.CustomizeException;
import life.majiang.community.community.model.Comment;
import org.springframework.stereotype.Service;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-26 14:37
 **/
@Service
public class CommentService {
    public void insert(Comment comment) {

        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_MOT_FOUND);
        }
        if(comment.getType()==null|| CommentTypeEnum.isExist(comment.getType()))
        {

        }

    }
}
