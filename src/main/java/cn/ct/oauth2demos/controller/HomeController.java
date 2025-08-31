package cn.ct.oauth2demos.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {
    @GetMapping("/")
    public String home() {
        return "欢迎访问，<a href='/oauth2/authorization/github'>使用 GitHub 登录</a>";
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal OAuth2User user) {
        return "Login user: " + user.getAttributes();
    }
}
