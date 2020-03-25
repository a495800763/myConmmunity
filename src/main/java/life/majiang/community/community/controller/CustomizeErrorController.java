package life.majiang.community.community.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-25 21:03
 **/
@Controller
@RequestMapping("/error")
public class CustomizeErrorController implements ErrorController {

    @Value("${error.path:/error}")
    private String path="/error";

    @Override
    public String getErrorPath() {
        return path;
    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request,
                                  Model model) {
        HttpStatus status = getStatus(request);

        if(status.is4xxClientError())
        {
            model.addAttribute("message","你这个请求不正确，要不然换个姿势?>_<........");
        }
        if(status.is5xxServerError())
        {
            model.addAttribute("message","服务太烫了，等它冷静一下>_<........");
        }
        return new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
