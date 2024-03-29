package com.esewa.security.auth.controller;

import com.esewa.security.auth.request.AuthenticationRequest;
import com.esewa.security.auth.response.AuthenticationResponse;
import com.esewa.security.auth.service.AuthenticationService;
import com.esewa.shared.endpoint.EndpointConstants;
import com.esewa.user.dto.UserRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping(EndpointConstants.AUTH_BASE_URL)
public class AuthenticationController {
   /* private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(EndpointConstants.API_REGISTER)
    public AuthenticationResponse register(@RequestBody UserRequestDto request) {
        return authenticationService.register(request);
    }

    @PostMapping(EndpointConstants.API_AUTHENTICATE)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.authenticate(authenticationRequest);
    }*/
}
