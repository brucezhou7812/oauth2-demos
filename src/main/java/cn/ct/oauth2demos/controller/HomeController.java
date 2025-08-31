package cn.ct.oauth2demos.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController

public class HomeController {
    @GetMapping("/")
    public String home() {
        return "<h1>Welcome!</h1>" +
                "<a href='/user'>View User Info</a><br/>" +
                "<a href='/logout'>Logout</a>";
    }

    @GetMapping("/user")
    public Map<String,Object> user(@AuthenticationPrincipal Object user) {
        if (user instanceof OidcUser oidcUser) {
            return Map.of(
                    "Source", "Google-OIDC",
                    "Claims", oidcUser.getClaims()
            );
        } else if (user instanceof OAuth2User oauth2User) {
            return Map.of(
                    "Source", "GitHub-OAuth2",
                    "Attributes", oauth2User.getAttributes()
            );
        }
        return Map.of("error", "Unkonwn user type" + user.getClass());
    }
}
