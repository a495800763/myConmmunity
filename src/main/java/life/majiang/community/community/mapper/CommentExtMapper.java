package life.majiang.community.community.mapper;

import life.majiang.community.community.model.Comment;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-29 16:37
 **/
public interface CommentExtMapper {

    /**
     * 增加评论数
     * @param record
     * @return
     */
    Integer incComment (Comment record);
}
