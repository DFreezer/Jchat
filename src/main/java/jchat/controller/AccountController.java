package jchat.controller;

import jchat.db.dataSet.Group;
import jchat.db.dataSet.Role;
import jchat.db.dataSet.User;
import jchat.db.service.UserService;
import jchat.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.util.Calendar;

@Controller
@RequestMapping(value = "/auth", method = {RequestMethod.GET, RequestMethod.POST})
public class AccountController {

    private final UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String authPage() {
        return "unauth";
    }

    @GetMapping(value = "/signup")
    public ModelAndView signUpPage() {
        return new ModelAndView("signup", "user", new UserDTO());
    }

    @GetMapping(value = "/signin")
    public String signInPage() {
        return "signin";
    }

    @PostMapping(value = "/regUser")
    public String submitRegistration(@Valid @ModelAttribute("user") UserDTO user,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) return "signup";
        if (!user.checkPasswordConfirm()) {
            model.addAttribute("error", "Passwords aren't the same!");
            return "signup";
        }
        model.addAttribute("username", user.getUsername());
        model.addAttribute("password", user.getPassword());
        userService.createUser(user.getUsername(), user.getPassword(), user.getEmail(), new Date(Calendar.getInstance().getTimeInMillis()), false);
        return "signin";
    }
}
