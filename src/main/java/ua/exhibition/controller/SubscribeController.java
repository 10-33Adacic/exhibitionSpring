package ua.exhibition.controller;

import static ua.exhibition.controller.Constants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.exhibition.domain.entity.User;
import ua.exhibition.service.SubscribeService;

@Controller
@RequestMapping(USER_MAPPING)
public class SubscribeController {

    @Autowired
    private SubscribeService subscribeService;

    @GetMapping(SUBSCRIBE_MAPPING)
    public String subscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user
    ) {
        subscribeService.subscribe(currentUser, user);

        return "redirect:/user-exhibitions/" + user.getId();
    }

    @GetMapping(UNSUBSCRIBE_MAPPING)
    public String unsubscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user
    ) {
        subscribeService.unsubscribe(currentUser, user);

        return "redirect:/user-exhibitions/" + user.getId();
    }

    @GetMapping(USER_LIST_MAPPING)
    public String userList(
            Model model,
            @PathVariable User user,
            @PathVariable String type
    ) {
        model.addAttribute("userChannel", user);
        model.addAttribute(TYPE, type);

        if (SUBSCRIPTIONS.equals(type)) {
            model.addAttribute(USERS, user.getSubscriptions());
        } else {
            model.addAttribute(USERS, user.getSubscribers());
        }

        return PAGE_SUBSCRIPTIONS;
    }
}
