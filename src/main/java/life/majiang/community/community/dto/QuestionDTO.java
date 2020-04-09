package life.majiang.community.community.dto;

import life.majiang.community.community.model.User;
import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String indexDescription;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
