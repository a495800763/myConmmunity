package life.majiang.community.community.cache;

import life.majiang.community.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-30 16:14
 **/
public class TagCache {
    public List<TagDTO>  get(){
       List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","c#","html","python","node","java","kotlin"));
        tagDTOS.add(program);
       return tagDTOS;
    }

}
