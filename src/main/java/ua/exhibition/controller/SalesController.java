package ua.exhibition.controller;

//import static ua.exhibition.controller.Constants.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.exhibition.domain.entity.Exhibition;
import ua.exhibition.domain.entity.User;
import ua.exhibition.exception.BuyException;
import ua.exhibition.service.ExhibitionService;
import ua.exhibition.service.SalesService;
import ua.exhibition.service.UserService;

import java.util.List;

@Controller
public class SalesController {

    private SalesService salesService;

    private UserService userService;

    private ExhibitionService exhibitionService;

    public SalesController(SalesService salesService,
                           UserService userService,
                           ExhibitionService exhibitionService
    ) {
        this.salesService = salesService;
        this.userService = userService;
        this.exhibitionService = exhibitionService;
    }

    @GetMapping("/sales/{user}")
    public String buyTicket(
            @PathVariable User user

    ) {
        return "redirect:/main";
    }

    @PostMapping("/sales/{user}")
    public String buyAndUpdate(
            @PathVariable User user,
            @RequestParam(value = "money", required = false) Long money,
            @RequestParam(value = "exhibitionId", required = false) Long id,
            Model model
    ) {
        if (money != null) {
            userService.updateUserBalance(user, money);
        }

        model.addAttribute("username", user.getUsername());
        model.addAttribute("balance", user.getAccountMoney());

        if (id != null) {
            Exhibition exhibition = exhibitionService.findById(id);
            model.addAttribute("exhibition", exhibition);

            if (user.getBoughtTickets().contains(exhibition)) {
                model.addAttribute("buyError", "You have already bought this ticket");
            }

            return "sales";
        }

        return "sales";
    }

    @GetMapping("bought-tickets/{user}")
    public String salesUser(
            @PathVariable User user,
            Model model
    ){
        List<Exhibition> tickets = salesService.findUserTickets(user);
        model.addAttribute("tickets", tickets);

        return "salesUser";
    }


    @PostMapping("bought-tickets/{user}")
    public String salesUser(
            @PathVariable User user,
            @RequestParam Long ticketId,
            Model model
    ) {
        model.addAttribute("user", user);

        try {

            salesService.addTicket(user, ticketId);

        } catch (BuyException e) {
            model.addAttribute("buyError", "Not enough money!");
            model.addAttribute("username", user.getUsername());
            model.addAttribute("balance", user.getAccountMoney());

            Exhibition exhibition = exhibitionService.findById(ticketId);
            model.addAttribute("exhibition", exhibition);

            return "sales";
        }

        List<Exhibition> tickets = salesService.findUserTickets(user);

        model.addAttribute("tickets", tickets);

        return "salesUser";
    }
}
