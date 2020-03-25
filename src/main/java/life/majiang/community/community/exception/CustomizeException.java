package life.majiang.community.community.exception;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-25 20:36
 **/
public class CustomizeException  extends  RuntimeException {
    private String message;

    public CustomizeException(String message) {
        this.message = message;
    }


    public CustomizeException(ICustomizeErrorCode errorCode)
    {
        this.message=errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
