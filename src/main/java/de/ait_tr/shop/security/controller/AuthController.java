package de.ait_tr.shop.security.controller;


import de.ait_tr.shop.exception_handling.Response;
import de.ait_tr.shop.model.dto.UserRegisterDTO;
import de.ait_tr.shop.security.dto.LoginRequestDto;
import de.ait_tr.shop.security.dto.RefreshRequestDto;
import de.ait_tr.shop.security.dto.TokenResponseDto;
import de.ait_tr.shop.security.service.AuthService;
import de.ait_tr.shop.service.interfaces.UserService;
import jakarta.security.auth.message.AuthException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {

        try {
            return authService.login(loginRequestDto);
        } catch (AuthException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/refresh")
    public TokenResponseDto refreshAccessToken(@RequestBody RefreshRequestDto refreshRequestDto) {
        try {
            return authService.refreshAccessToken(refreshRequestDto);
        } catch (AuthException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/register")
    public Response register(@RequestBody UserRegisterDTO userRegisterDto) {
        userService.register(userRegisterDto);
        return new Response("Registration Complete. Please check your email");
    }
}
