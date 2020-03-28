package life.majiang.community.community.exception;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-25 20:47
 **/
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUSSTION_NOT_FOUND(2001, "你找的问题不在了，要不换个试试 >_<....."),
    TARGET_PARAM_MOT_FOUND(2002, "未选中任何问题或者评论进行回复 >_<....."),
    NO_LOGIN(2003, "当前操作需要登录，请登录后重试 >_<....."),
    SYS_ERROR(2004, "服务太烫了，等它冷静一下 >_<....."),
    TYPE_PARAM_WRONG(2005, "评论类型错误或者不存在 >_<....."),
    COMMENT_NOT_FOUND(2006, "父级评论不存在，换个评论试试 >_<....."),
    COMMENT_IS_EMPTY(2007, "输入内容不能为空 >_<.....");

    private String message;

    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
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
