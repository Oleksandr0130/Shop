package de.ait_tr.shop.controller;

import de.ait_tr.shop.exception_handling.Response;
import de.ait_tr.shop.model.dto.UserRegisterDTO;
import de.ait_tr.shop.service.interfaces.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Response register(@RequestBody UserRegisterDTO userRegisterDTO) {
        userService.register(userRegisterDTO);
        return new Response("Registration complete. Please check your email.");
    }
}
