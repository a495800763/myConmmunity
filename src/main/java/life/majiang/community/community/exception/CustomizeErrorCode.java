package life.majiang.community.community.exception;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-25 20:47
 **/
public enum CustomizeErrorCode implements  ICustomizeErrorCode {



    QUSSTION_NOT_FOUND(2001,"你找的问题不在了，要不换个试试 >_<....."),
    TARGET_PARAM_MOT_FOUND(2002,"未选中任何问题或者评论进行回复 >_<....."),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试 >_<....."),
    SYS_ERROR(2004,"服务太烫了，等它冷静一下 >_<.....");

    private String message;

    private Integer code;

    CustomizeErrorCode( Integer code,String message) {
        this.message = message;
        this.code=code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
