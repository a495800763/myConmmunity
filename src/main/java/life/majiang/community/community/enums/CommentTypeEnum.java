package life.majiang.community.community.enums;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-26 14:22
 **/
public enum CommentTypeEnum {
    //评论问题
    QUESTION(1),
    //评论上级评论
    COMMENT(2);
    private Integer type;

    public static boolean isExist(Integer type) {

        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (commentTypeEnum.getType().equals(type) ) {
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
