package life.majiang.community.community.exception;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-25 20:47
 **/
public enum CustomizeErrorCode implements  ICustomizeErrorCode {



    QUSSTION_NOT_FOUND("你找的问题不在了，要不换个试试 >_<.....");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
