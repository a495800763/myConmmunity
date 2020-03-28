package life.majiang.community.community.dto;

import lombok.Data;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-26 10:39
 **/
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
