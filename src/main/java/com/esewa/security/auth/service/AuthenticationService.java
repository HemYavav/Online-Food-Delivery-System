package com.esewa.security.auth.service;

import com.esewa.security.auth.request.AuthenticationRequest;
import com.esewa.security.auth.response.AuthenticationResponse;
import com.esewa.user.dto.UserRequestDto;

public interface AuthenticationService {
//    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse register(UserRequestDto request);

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
