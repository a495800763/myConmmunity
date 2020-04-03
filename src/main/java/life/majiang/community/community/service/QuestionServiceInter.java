package life.majiang.community.community.service;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.model.Question;

import java.util.List;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-04-03 14:39
 **/
public interface QuestionServiceInter {
    PaginationDTO list(Integer page, Integer size);

    PaginationDTO list(Long userId, Integer page, Integer size);

    QuestionDTO getById(Long id);

    void creatOrUpdate(Question question);

    void incView(Long id);

    List<QuestionDTO> selectRelated(QuestionDTO questionDTO);
}
