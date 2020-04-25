package ua.exhibition.controller;

import static ua.exhibition.controller.Constants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ua.exhibition.domain.entity.Role;
import ua.exhibition.domain.entity.User;
import ua.exhibition.service.UserService;

import java.util.Map;

@Controller
@RequestMapping(USER_MAPPING)
@PreAuthorize("hasAuthority('SUPER_ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String userList(
            Model model
    ) {
        model.addAttribute(USERS, userService.findAll());

        return PAGE_USER_LIST;
    }

    @GetMapping(USER_EDIT_MAPPING)
    public String userEdit(
            @PathVariable User user,
            Model model
    ) {
        model.addAttribute(USER, user);
        model.addAttribute(ROLES, Role.values());

        return PAGE_USER_EDIT;
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam(PARAM_USER_ID) User user
    ) {
        userService.saveUser(username, form, user);

        return "redirect:" + URL_USER;
    }

}
