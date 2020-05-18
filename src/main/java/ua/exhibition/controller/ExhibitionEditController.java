package ua.exhibition.controller;

//import static ua.exhibition.controller.Constants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.exhibition.domain.entity.Exhibition;
import ua.exhibition.domain.entity.User;
import ua.exhibition.service.ExhibitionService;

@Controller
public class ExhibitionEditController {

    @Autowired
    private ExhibitionService exhibitionService;

    @GetMapping("/user-exhibitions/{user}")
    public String userExhibitions(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @RequestParam(required = false) Exhibition exhibition,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        model.addAttribute("exhibition", exhibition);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        model.addAttribute("userChannel", user);
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));

        model.addAttribute("url", "/user-exhibitions/" + user.getId());
        model.addAttribute("page", exhibitionService.findByAuthor(user, pageable));

        return "userExhibitions";
    }

    @GetMapping("/user-exhibitions/{user}/{exhibition}")
    public String editExhibition (
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @PathVariable Exhibition exhibition,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        model.addAttribute("exhibition", exhibition);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        model.addAttribute("userChannel", user);
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));

        model.addAttribute("url", "/user-exhibitions/" + user.getId() + "/" + exhibition.getId());
        model.addAttribute("page", exhibitionService.findByAuthor(user, pageable));
        model.addAttribute("deleteFactor", true);

        return "userExhibitions";
    }

    @PostMapping("/user-exhibitions/{user}")
    public String updateExhibition(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Exhibition exhibition,
            @RequestParam String name,
            @RequestParam String showroom,
            @RequestParam String description
    ) {
        if (exhibition.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(name)) {
                exhibition.setName(name);
            }

            if (!StringUtils.isEmpty(showroom)) {
                exhibition.setShowroom(showroom);
            }

            if (!StringUtils.isEmpty(description)) {
                exhibition.setShowroom(description);
            }

            exhibitionService.save(exhibition);
        }

        return "redirect:/user-exhibitions/" + user;
    }

    @PostMapping("/user-exhibitions/delete")
    public String deleteExhibition(
            @RequestParam("exhibitionId") Long id
    ) {
        exhibitionService.deleteById(id);

        return "redirect:/main";
    }
}
