package life.majiang.community.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-30 16:14
 **/
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
