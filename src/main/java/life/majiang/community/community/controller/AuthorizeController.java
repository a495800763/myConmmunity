package life.majiang.community.community.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import life.majiang.community.community.dto.AccessTokenDTO;
import life.majiang.community.community.dto.GithubUser;
import life.majiang.community.community.model.User;
import life.majiang.community.community.provider.GithubProvider;
import life.majiang.community.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Administrator
 */
@Controller
@Slf4j
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserService userService;

    @NacosValue(value = "${github.client.id}",autoRefreshed = true)
    private String clientId;
    @NacosValue(value = "${github.client.secret}" ,autoRefreshed = true)
    private String clientSecret;
    @NacosValue(value = "${github.redirecturi}",autoRefreshed = true)
    private String redirectUri;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUseruser = githubProvider.getUser(accessToken);
        System.out.println(githubUseruser.getName());

        if (githubUseruser != null && githubUseruser.getId() != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUseruser.getName());
            user.setAccountId(String.valueOf(githubUseruser.getId()));
            user.setAvatarUrl(githubUseruser.getAvatarUrl());

            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
            //登录成功 写cookie 和session
        } else {
             log.error("callback get github error,{}",githubUseruser);
            return "redirect:/";
            //登录失败，重新登录
        }
    }


    @GetMapping("/logout")
    public String logout (HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return "redirect:/";
    }
}
