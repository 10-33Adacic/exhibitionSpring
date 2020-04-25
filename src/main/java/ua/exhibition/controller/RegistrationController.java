package ua.exhibition.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ua.exhibition.model.domain.User;
import ua.exhibition.model.domain.dto.CaptchaResponseDto;
import ua.exhibition.model.service.RegistrationService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller("/")
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    private final RegistrationService registrationService;
    private final RestTemplate restTemplate;

    public RegistrationController(RegistrationService registrationService, RestTemplate restTemplate) {
        this.registrationService = registrationService;
        this.restTemplate = restTemplate;
    }

    @Value("${recaptcha.secret}")
    private String secret;

    @GetMapping("registration")
    public String registration() {
        return "registration";
    }

    //TODO убрать много if
    @PostMapping("registration")
    public String addUser(
            @RequestParam String passwordConfirm,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Fill captcha");
        }

        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        boolean isConfirmInvalid = user.getPassword() != null && !user.getPassword().equals(passwordConfirm);

        if (isConfirmEmpty) {
            model.addAttribute("passwordConfirmError", "Password confirmation cannot be empty");
        }

        if (isConfirmInvalid) {
            model.addAttribute("passwordError", "Passwords are different!");
        }

        if (isConfirmEmpty || isConfirmInvalid || bindingResult.hasErrors() || !response.isSuccess()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "registration";
        }

        if (!registrationService.addUser(user)) {
            model.addAttribute("usernameError", "User is exists!");

            return "registration";
        }

        return "redirect:/login";
    }
}
