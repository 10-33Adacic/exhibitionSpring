package ua.exhibition.controller;

import static ua.exhibition.controller.Constants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.exhibition.domain.entity.Exhibition;
import ua.exhibition.domain.entity.User;
import ua.exhibition.service.ExhibitionService;
import ua.exhibition.util.ControllerUtils;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private ExhibitionService exhibitionService;

    @GetMapping(MAIN_MAPPING)
    public String mainPage() {
        return PAGE_HOME;
    }

    @GetMapping(MAIN_FORM_MAPPING)
    public String mainForm(
            @RequestParam(required = false, defaultValue = "") String showroom,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ){
        Page<Exhibition> page;

        if(!showroom.isEmpty()) {
            page = exhibitionService.findByShowroom(showroom, pageable);
        } else {
            page = exhibitionService.findAll(pageable);
        }

        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        model.addAttribute(SHOWROOM, showroom);

        return PAGE_MAIN_PAGE;
    }

    @PostMapping(MAIN_FORM_MAPPING)
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Exhibition exhibition,
            BindingResult bindingResult,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        exhibition.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("exhibition", exhibition);
        } else {
            model.addAttribute("exhibition", null);
            exhibitionService.save(exhibition);
        }

        model.addAttribute("url", "/main");
        model.addAttribute("page", exhibitionService.findAll(pageable));

        return PAGE_MAIN_PAGE;
    }
}
