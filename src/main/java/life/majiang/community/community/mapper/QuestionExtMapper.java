package life.majiang.community.community.mapper;

import life.majiang.community.community.model.Question;


public interface QuestionExtMapper {

    Integer incView (Question record);

    Integer incComment (Question record);
}