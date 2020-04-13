package life.majiang.community.community.service;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.dto.QuestionQueryDTO;
import life.majiang.community.community.exception.CustomizeErrorCode;
import life.majiang.community.community.exception.CustomizeException;
import life.majiang.community.community.mapper.QuestionExtMapper;
import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.Question;
import life.majiang.community.community.model.QuestionExample;
import life.majiang.community.community.model.User;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService implements QuestionServiceInter {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Value("${image.url.match}")
    private String urlMatch;

    /**
     * 查询问题列表
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PaginationDTO list(String search, Integer page, Integer size) {

        if (StringUtils.isNotBlank(search)) {
            //得到每个tag的数组
            String[] tags = StringUtils.split(search, " ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));

        }
        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<>();
        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setSearch(search);
        Integer totalCount = questionExtMapper.countBySearch(questionQueryDTO);
        Integer totalPage;
        PageCountInfo pageInfo = getPageInfo(totalCount, size, page);
        totalPage = pageInfo.getTotalPage();
        page = pageInfo.getPage();
        paginationDTO.setPagination(totalPage, page);
        Integer offset = size * (page - 1);
        questionQueryDTO.setSize(size);
        questionQueryDTO.setPage(offset);
        List<Question> questions = questionExtMapper.selectBySearch(questionQueryDTO);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            if (question.getDescription().contains(urlMatch)) {
                questionDTO.setIndexDescription(INDEX_DESCRIPTION_STRING);
            } else {
                questionDTO.setIndexDescription(question.getDescription());
            }

            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        return paginationDTO;
    }

    /**
     * 根据用户id 返回该用户的关联问题
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @Override
    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<>();
        Integer totalPage;
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = questionMapper.countByExample(example);
        PageCountInfo pageInfo = getPageInfo(totalCount, size, page);
        totalPage = pageInfo.getTotalPage();
        page = pageInfo.getPage();
        paginationDTO.setPagination(totalPage, page);
        Integer offset = size * (page - 1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            if (question.getDescription() != null && question.getDescription().contains(urlMatch)) {
                questionDTO.setIndexDescription(INDEX_DESCRIPTION_STRING);
            } else {
                questionDTO.setIndexDescription(question.getDescription());
            }
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setData(questionDTOList);


        return paginationDTO;

    }

    /**
     * 通过id 返回对象
     *
     * @param id
     * @return
     */
    @Override
    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUSSTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    /**
     * 添加或者删除
     *
     * @param question
     */
    @Override
    public void creatOrUpdate(Question question) {
        if (question.getId() == null) {
            //创建新的问题
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(DEFAULT_COUNT);
            question.setCommentCount(DEFAULT_COUNT);
            question.setLikeCount(DEFAULT_COUNT);
            questionMapper.insert(question);

        } else {
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUSSTION_NOT_FOUND);
            }
        }
    }


    /**
     * 增加浏览（阅读）数
     *
     * @param id
     */
    @Override
    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(DEFAULT_INC_COUNT);
        questionExtMapper.incView(question);
    }


    /**
     * 得到相关问题
     *
     * @param questionDTO
     * @return
     */
    @Override
    public List<QuestionDTO> selectRelated(QuestionDTO questionDTO) {
        if (StringUtils.isBlank(questionDTO.getTag())) {
            return new ArrayList<>();
        } else {

            //得到每个tag的数组
            String[] tags = StringUtils.split(questionDTO.getTag(), ",");
            String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));

            Question question = new Question();
            question.setId(questionDTO.getId());
            question.setTag(regexpTag);
            List<Question> relatedQuestions = questionExtMapper.selectRelated(question);
            List<QuestionDTO> questionDTOS = relatedQuestions.stream().map(q -> {
                QuestionDTO dto = new QuestionDTO();
                BeanUtils.copyProperties(q, dto);
                return dto;
            }).collect(Collectors.toList());
            return questionDTOS;
        }
    }

    /**
     * 消灭零回复
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PaginationDTO listForNoneReply(Integer page, Integer size) {


        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<>();
        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();

        Integer totalCount = questionExtMapper.count4Unanswered();
        Integer totalPage;

        PageCountInfo pageInfo = getPageInfo(totalCount, size, page);
        totalPage = pageInfo.getTotalPage();
        page = pageInfo.getPage();
        paginationDTO.setPagination(totalPage, page);
        Integer offset = size * (page - 1);
        questionQueryDTO.setSize(size);
        questionQueryDTO.setPage(offset);
        List<Question> questions = questionExtMapper.select4Unanswered(questionQueryDTO);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            if (question.getDescription().contains(urlMatch)) {
                questionDTO.setIndexDescription(INDEX_DESCRIPTION_STRING);
            } else {
                questionDTO.setIndexDescription(question.getDescription());
            }

            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        return paginationDTO;
    }

    private static final Integer DEFAULT_COUNT = 0;

    private static final Integer DEFAULT_INC_COUNT = 1;

    private static final Integer DEFAULT_TOTAL_PAGE = 1;

    private static final Integer DEFAULT_CURRENT_PAGE = 1;

    private static final String INDEX_DESCRIPTION_STRING = "点击问题查看相关图片及描述";


    /**
     * 处理页码和总数信息
     *
     * @param totalCount
     * @param size
     * @param page
     * @return
     */
    private PageCountInfo getPageInfo(Integer totalCount, Integer size, Integer page) {
        PageCountInfo pageInfo = new PageCountInfo();

        if (totalCount % size == 0) {
            pageInfo.setTotalPage(totalCount / size);
        } else {
            pageInfo.setTotalPage(totalCount / size + 1);
        }
        if (pageInfo.getTotalPage() < DEFAULT_TOTAL_PAGE) {
            pageInfo.setTotalPage(DEFAULT_TOTAL_PAGE);
        }
        if (page < DEFAULT_CURRENT_PAGE) {
            pageInfo.setPage(DEFAULT_CURRENT_PAGE);
        } else if (page > pageInfo.getTotalPage()) {
            pageInfo.setPage(pageInfo.getTotalPage());
        } else {
            pageInfo.setPage(page);
        }
        return pageInfo;
    }
}
@Data
class PageCountInfo {
    Integer totalPage;
    Integer page;
}
