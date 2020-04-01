package life.majiang.community.community.dto;

import life.majiang.community.community.model.User;
import lombok.Data;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-04-01 11:03
 **/
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private User notifier;
    private String  outerTitle;
    private String type;
}
