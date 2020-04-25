package ua.exhibition.controller;

import static ua.exhibition.controller.Constants.*;

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

    @GetMapping(BUY_TICKET_MAPPING)
    public String buyTicket(
            @PathVariable User user

    ) {
        return "redirect:/main";
    }

    @PostMapping(BUY_TICKET_MAPPING)
    public String buyAndUpdate(
            @PathVariable User user,
            @RequestParam(value = PARAM_MONEY, required = false) Long money,
            @RequestParam(value = "exhibitionId", required = false) Long id,
            Model model
    ) {
        if (money != null) {
            userService.updateUserBalance(user, money);
        }

        model.addAttribute(USERNAME, user.getUsername());
        model.addAttribute(BALANCE, user.getAccountMoney());

        if (id != null) {
            Exhibition exhibition = exhibitionService.findById(id);
            model.addAttribute("exhibition", exhibition);

            if (user.getBoughtTickets().contains(exhibition)) {
                model.addAttribute(BUY_ERROR, BUY_ERROR_MESSAGE);
            }

            return PAGE_SALES;
        }

        return PAGE_SALES;
    }

    @GetMapping(BOUGHT_TICKETS_MAPPING)
    public String salesUser(
            @PathVariable User user,
            Model model
    ){
        List<Exhibition> tickets = salesService.findUserTickets(user);
        model.addAttribute(TICKETS, tickets);

        return PAGE_SALES_USER;
    }


    @PostMapping(BOUGHT_TICKETS_MAPPING)
    public String salesUser(
            @PathVariable User user,
            @RequestParam Long ticketId,
            Model model
    ) {
        model.addAttribute(USER, user);

        try {

            salesService.addTicket(user, ticketId);

        } catch (BuyException e) {
            model.addAttribute(BUY_ERROR, BUY_ERROR_MONEY);
            model.addAttribute(USERNAME, user.getUsername());
            model.addAttribute(BALANCE, user.getAccountMoney());

            Exhibition exhibition = exhibitionService.findById(ticketId);
            model.addAttribute("exhibition", exhibition);

            return PAGE_SALES;
        }

        List<Exhibition> tickets = salesService.findUserTickets(user);

        model.addAttribute(TICKETS, tickets);

        return PAGE_SALES_USER;
    }
}
