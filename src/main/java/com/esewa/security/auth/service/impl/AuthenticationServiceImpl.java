package com.esewa.security.auth.service.impl;

import com.esewa.mapper.UserMappingService;
import com.esewa.security.auth.request.AuthenticationRequest;
import com.esewa.security.auth.response.AuthenticationResponse;
import com.esewa.security.auth.service.AuthenticationService;
import com.esewa.security.config.service.JwtService;
import com.esewa.shared.enumcollection.Role;
import com.esewa.shared.enumcollection.Status;
import com.esewa.user.dto.UserRequestDto;
import com.esewa.user.entity.User;
import com.esewa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMappingService userMappingService;

    @Override
    public AuthenticationResponse register(UserRequestDto userDto) {
        User user = userMappingService.mapUserDtoToUser(userDto);
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);
        userRepository.save(user);
        return AuthenticationResponse.builder().token(jwtService.generateToken(user)).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        return AuthenticationResponse.builder().token(jwtService.generateToken(user)).build();
    }

}
