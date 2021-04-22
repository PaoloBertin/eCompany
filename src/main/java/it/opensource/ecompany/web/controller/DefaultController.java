package it.opensource.ecompany.web.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Profile("html")
@RequestMapping("/default")
@Controller
public class DefaultController {

    @GetMapping
    public String defaultAfterLogin(HttpServletRequest request) {

        if (request.isUserInRole("ADMIN")) {
            return "redirect:/admin";
        }
        return "redirect:/";
    }
}
