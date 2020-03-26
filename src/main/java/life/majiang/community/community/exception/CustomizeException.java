package life.majiang.community.community.exception;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-25 20:36
 **/
public class CustomizeException  extends  RuntimeException {
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode)
    {
        this.message=errorCode.getMessage();
        this.code=errorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
