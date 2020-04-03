package life.majiang.community.community.service;

import life.majiang.community.community.dto.TagDTO;
import life.majiang.community.community.mapper.TagCategoryMapper;
import life.majiang.community.community.mapper.TagMapper;
import life.majiang.community.community.model.Tag;
import life.majiang.community.community.model.TagCategory;
import life.majiang.community.community.model.TagCategoryExample;
import life.majiang.community.community.model.TagExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-30 22:32
 **/
@Service
public class TagService implements TagServiceInter {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TagCategoryMapper tagCategoryMapper;

    @Override
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

    @Override
    public String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = getTagDTO();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalidTags = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));

        return invalidTags;
    }
}
