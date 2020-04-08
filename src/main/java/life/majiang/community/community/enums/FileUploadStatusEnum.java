package life.majiang.community.community.enums;

/**
 * @program: community
 * @author: liumq
 * @create: 2020-04-08 14:38
 **/
public enum FileUploadStatusEnum {
    UPLOAD_SUCCESS(1),
    UPLOAD_FAILD(0);

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    FileUploadStatusEnum(Integer status) {
        this.status = status;
    }
}
