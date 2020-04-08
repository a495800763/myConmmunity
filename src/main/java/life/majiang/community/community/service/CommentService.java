package life.majiang.community.community.service;

import life.majiang.community.community.dto.CommentDTO;
import life.majiang.community.community.enums.CommentTypeEnum;
import life.majiang.community.community.enums.NotificationStatusEnum;
import life.majiang.community.community.enums.NotificationTypeEnum;
import life.majiang.community.community.exception.CustomizeErrorCode;
import life.majiang.community.community.exception.CustomizeException;
import life.majiang.community.community.mapper.*;
import life.majiang.community.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-26 14:37
 **/
@Service
public class CommentService implements CommentServiceInter {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentExtMapper commentExtMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    /**
     * 插入评论
     *
     * @param comment
     */
    //整个步骤通过作为一个事务
    @Override
    @Transactional
    public void insert(Comment comment, User commentator) {

        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_MOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (CommentTypeEnum.COMMENT.getType().equals(comment.getType())) {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            //拿到所回复的评论原本的问题
            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUSSTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            //增加parentComment 的评论数
            Comment parentComment = new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(DEFAULT_COMMENT_INC_COUNT);
            commentExtMapper.incComment(parentComment);
            //创建通知
            createNotifiy(comment, dbComment.getCommentator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_COMMENT, question.getId());


        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUSSTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(DEFAULT_INC_COUNT);
            questionExtMapper.incComment(question);

            //创建通知
            createNotifiy(comment, question.getCreator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_QUESTION, question.getId());

        }
    }

    /**
     * 创建通知
     *
     * @param comment              评论对象
     * @param receiver             通知接受者
     * @param notificationTypeEnum 通知类型
     */
    private void createNotifiy(Comment comment, Long receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationTypeEnum, Long outerId) {
        if (receiver.equals(comment.getCommentator())) {
            return;
        }
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationTypeEnum.getType());
        notification.setOuterid(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setOuterTitle(outerTitle);

        notification.setNotifierName(notifierName);
        notificationMapper.insert(notification);
    }

    /**
     * 根据目标类型去获得列表
     *
     * @param id
     * @param type
     * @return
     */
    @Override
    public List<CommentDTO> listByTargetid(Long id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        if (comments.size() == 0) {
            return new ArrayList<CommentDTO>();
        } else {
            //使用lambda获取去重的评论人
            Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());

            List<Long> userIds = new ArrayList<>();
            userIds.addAll(commentators);


            // 获取评论人并转换为map
            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdIn(userIds);
            List<User> users = userMapper.selectByExample(userExample);

            Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

            // 装换comment 为commentDTO  并且给user赋值
            List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
                CommentDTO commentDTO = new CommentDTO();
                BeanUtils.copyProperties(comment, commentDTO);
                commentDTO.setUser(userMap.get(comment.getCommentator()));
                return commentDTO;
            }).collect(Collectors.toList());
            return commentDTOS;
        }

    }

    private static final Integer DEFAULT_INC_COUNT = 1;
    private static final Long DEFAULT_COMMENT_INC_COUNT = 1L;
}
