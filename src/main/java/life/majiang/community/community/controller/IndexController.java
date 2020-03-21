package life.majiang.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liumq on 2020/03/21
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String greeting() {
        return "index";
    }
}
