package life.majiang.community.community.service;

import life.majiang.community.community.dto.TagDTO;
import life.majiang.community.community.mapper.TagCategoryMapper;
import life.majiang.community.community.mapper.TagMapper;
import life.majiang.community.community.model.Tag;
import life.majiang.community.community.model.TagCategory;
import life.majiang.community.community.model.TagCategoryExample;
import life.majiang.community.community.model.TagExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-30 22:32
 **/
@Service
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TagCategoryMapper tagCategoryMapper;

    public List<TagDTO> getTagDTO() {
        List<Tag> tags = tagMapper.selectByExample(new TagExample());
        List<TagCategory> tagCategories = tagCategoryMapper.selectByExample(new TagCategoryExample());

        List<TagDTO> tagDTOS = tagCategories.stream().map(q -> {
            TagDTO tagDTO = new TagDTO();
            tagDTO.setCategoryName(q.getCategoryName());

            // tags.stream().map(p -> p.getCategoryId().equals(q.getId()))
            List<String> collect = tags.stream().filter(p -> p.getCategoryId().equals(q.getId())).collect(Collectors.toList())
                    .stream().map(k -> k.getTagName()).collect(Collectors.toList());


            tagDTO.setTags(collect);
            return tagDTO;
        }).collect(Collectors.toList());

        return tagDTOS;
    }
}
