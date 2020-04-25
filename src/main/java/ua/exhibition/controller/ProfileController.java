package ua.exhibition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.exhibition.model.domain.User;
import ua.exhibition.model.service.ProfileService;

@Controller
@RequestMapping("/user")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile")
    public String profile(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        model.addAttribute("username", user.getUsername());

        return "profile";
    }

    @PostMapping("/profile")
    public String profile(
            @AuthenticationPrincipal User user,
            @RequestParam String username,
            @RequestParam String password
    ) {
        profileService.updateProfile(user, username, password);

        return "redirect:/user/profile";
    }
}
