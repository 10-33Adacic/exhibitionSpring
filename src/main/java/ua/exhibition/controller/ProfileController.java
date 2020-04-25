package ua.exhibition.controller;

import static ua.exhibition.controller.Constants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.exhibition.domain.entity.User;
import ua.exhibition.service.ProfileService;

@Controller
@RequestMapping(USER_MAPPING)
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping(PROFILE_MAPPING)
    public String profile(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        model.addAttribute(USERNAME, user.getUsername());

        return PAGE_PROFILE;
    }

    @PostMapping(PROFILE_MAPPING)
    public String profile(
            @AuthenticationPrincipal User user,
            @RequestParam String username,
            @RequestParam String password
    ) {
        profileService.updateProfile(user, username, password);

        return "redirect:" + URL_USER_PROFILE;
    }
}
