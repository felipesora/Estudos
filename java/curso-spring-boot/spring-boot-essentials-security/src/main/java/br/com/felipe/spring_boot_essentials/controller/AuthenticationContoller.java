package br.com.felipe.spring_boot_essentials.controller;

import br.com.felipe.spring_boot_essentials.dto.LoginRequestDto;
import br.com.felipe.spring_boot_essentials.dto.RegisterRequestDto;
import br.com.felipe.spring_boot_essentials.dto.TokenResponseDto;
import br.com.felipe.spring_boot_essentials.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationContoller {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequestDto dto) {
        authenticationService.register(dto);
    }

    @PostMapping("/login")
    public TokenResponseDto register(@RequestBody @Valid LoginRequestDto dto) {
        return authenticationService.login(dto);
    }
}
