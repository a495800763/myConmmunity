package life.majiang.community.community.enums;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-26 14:22
 **/
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public static boolean isExist(Integer type) {
        return true;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
