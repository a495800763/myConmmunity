package life.majiang.community.community.service;

import life.majiang.community.community.dto.TagDTO;

import java.util.List;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-04-03 14:41
 **/
public interface TagServiceInter {
    List<TagDTO> getTagDTO();

    String filterInvalid(String tags);

}
