package life.majiang.community.community.exception;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-25 20:47
 **/
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    //问题找不到
    QUSSTION_NOT_FOUND(2001, "你找的问题不在了，要不换个试试"),
    //为选中目标
    TARGET_PARAM_MOT_FOUND(2002, "未选中任何问题或者评论进行回复"),
    //未登录
    NO_LOGIN(2003, "当前操作需要登录，请登录后重试"),
    //系统出错
    SYS_ERROR(2004, "服务太烫了，等它冷静一下"),
    //类型出错
    TYPE_PARAM_WRONG(2005, "评论类型错误或者不存在"),
    //找不到评论
    COMMENT_NOT_FOUND(2006, "父级评论不存在，换个评论试试"),
    //空评论
    COMMENT_IS_EMPTY(2007, "输入内容不能为空"),
    //读取信息错误
    READ_NOTIFICATION_FAILD(2008, "兄弟你这是读别人的信息呢？"),
    //消息找不到了
    NOTIFICATION_NOT_FOUND(2009, "消息怕不是不翼而飞了？"),
    //文件上传失败
    FILE_UPLOAD_ERROR(2010,"文件上传失败");

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
