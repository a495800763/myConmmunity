package life.majiang.community.community.enums;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-04-01 10:08
 **/
public enum NotificationStatusEnum {
    //未读
    UNREAD(0),
    //已读
    READ(1);

    private int status;

    NotificationStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
