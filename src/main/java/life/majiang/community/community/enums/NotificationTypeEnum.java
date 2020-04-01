package life.majiang.community.community.enums;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-04-01 09:55
 **/
public enum NotificationTypeEnum {
    REPLY_QUESTION(1, "回复了问题"),
    REPLY_COMMENT(2, "回复了评论");
    private int type;
    private String name;

    NotificationTypeEnum(int status, String name) {
        this.type = status;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
}
