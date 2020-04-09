package life.majiang.community.community.dto;

import lombok.Data;

/**
 * @program: community
 * @author: liumq
 * @create: 2020-04-09 09:31
 **/
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;

}
