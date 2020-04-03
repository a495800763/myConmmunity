package life.majiang.community.community.service;

import life.majiang.community.community.dto.NotificationDTO;
import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.model.User;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-04-03 14:38
 **/
public interface NotificationServiceInter {
    PaginationDTO list(Long userId, Integer page, Integer size);

    public Long unreadCount(Long id);

    public NotificationDTO read(Long id, User user);

}
