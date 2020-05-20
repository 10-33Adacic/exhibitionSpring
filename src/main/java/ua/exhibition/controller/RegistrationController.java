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
import ua.exhibition.domain.entity.User;
import ua.exhibition.domain.dto.CaptchaResponseDto;
import ua.exhibition.service.RegistrationService;
import ua.exhibition.util.ControllerUtils;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller("/")
public class RegistrationController {

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

    @PostMapping("registration")
    public String addUser(
            @RequestParam String passwordConfirm,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        if (isConfirmEmpty) {
            model.addAttribute("passwordConfirmError", "Password confirmation cannot be empty");
        }

        boolean isConfirmInvalid = user.getPassword() != null && !user.getPassword().equals(passwordConfirm);
        if (isConfirmInvalid) {
            model.addAttribute("passwordError", "Passwords are different!");
        }

        String url = String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s", secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);
        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Fill captcha");
        }

        if (isConfirmEmpty
                || isConfirmInvalid
                || bindingResult.hasErrors()
                || !response.isSuccess()
        ) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "registration";
        }

        if (!registrationService.addUser(user)) {
            model.addAttribute("usernameError", "User already exists!");

            return "registration";
        }

        return "redirect:/login";
    }
}
