package ma.abid.eductionPlatform.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
public class Profile {
    @GetMapping
    public Authentication getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }
}

