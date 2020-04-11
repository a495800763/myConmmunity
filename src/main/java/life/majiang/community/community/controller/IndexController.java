package life.majiang.community.community.controller;
import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by liumq on 2020/03/21
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String greeting(Model model,
                           @RequestParam(name = "page", defaultValue = "1") Integer page,
                           @RequestParam(name = "size", defaultValue = "5") Integer size,
                           @RequestParam(name = "search", required = false) String search) {
        PaginationDTO pagination = questionService.list(search,page,size);
        model.addAttribute("pagination", pagination);
        model.addAttribute("search",search);
        Integer showSearch =0;
        if(search!=null&&StringUtils.isNotBlank(search))
        {
            showSearch=1;
        }
        model.addAttribute("showSearch",showSearch);
        model.addAttribute("section","index");
        return "index";
    }

    @GetMapping("/index/unanswered")
    public String eliminateNonoRespose (Model model,
                                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                                        @RequestParam(name = "size", defaultValue = "5") Integer size)
    {
        PaginationDTO pagination = questionService.listForNoneReply(page,size);
        model.addAttribute("pagination", pagination);
        model.addAttribute("search",null);
        Integer showSearch =0;
        model.addAttribute("showSearch",showSearch);
        model.addAttribute("section","unanswered");
        return "index";
    }
}
