package life.majiang.community.community.mapper;

import life.majiang.community.community.dto.QuestionQueryDTO;
import life.majiang.community.community.model.Question;

import java.util.List;


public interface QuestionExtMapper {

    Integer incView (Question record);

    Integer incComment (Question record);

    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}