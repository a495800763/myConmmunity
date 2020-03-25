package life.majiang.community.community.advice;

import life.majiang.community.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: community
 * @description
 * @author: liumq
 * @create: 2020-03-25 18:30
 **/
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
   public  ModelAndView handle(HttpServletRequest request, Throwable e, Model model) {
       // HttpStatus status = getStatus(request);

        if(e instanceof CustomizeException)
        {
            model.addAttribute("message",e.getMessage());
        }
        else{
            model.addAttribute("message","服务太烫了，等它冷静一下>_<........");
        }



        return new ModelAndView("error");
    }

   /* private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }*/
}
