package com.esewa.user.controller;

import com.esewa.security.auth.request.AuthenticationRequest;
import com.esewa.security.auth.response.AuthenticationResponse;
import com.esewa.shared.endpoint.EndpointConstants;
import com.esewa.shared.exception.exceptionhandler.UsernamePasswordNotMatchException;
import com.esewa.shared.exception.exceptionhandler.exceptioncollection.UserAlreadyDeletedException;
import com.esewa.shared.exception.exceptionhandler.exceptioncollection.UserAlreadyRegisteredException;
import com.esewa.shared.exception.exceptionhandler.exceptioncollection.UserNotFoundException;
import com.esewa.user.dto.UserRequestDto;
import com.esewa.user.dto.UserResponseDto;
import com.esewa.user.entity.User;
import com.esewa.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointConstants.AUTH_BASE_URL)
public class UserController {
    private final UserService userService;

    @PostMapping(EndpointConstants.API_REGISTER)
    public AuthenticationResponse registerUser(@RequestBody UserRequestDto userDto) throws UserAlreadyRegisteredException {
        return userService.registerUser(userDto);
    }

    @PostMapping(EndpointConstants.API_AUTHENTICATE)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws UserNotFoundException, UsernamePasswordNotMatchException {
        return userService.authenticateUser(authenticationRequest);
    }

    @GetMapping(EndpointConstants.GET_ALL)
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUserList();
    }

    @GetMapping(EndpointConstants.GET_BY_ID)
    public User getUserById(@PathVariable int id) throws UserNotFoundException, UserAlreadyDeletedException {
        return userService.findUserById(id);
    }

    @DeleteMapping(EndpointConstants.DELETE_BY_ID)
    public String deleteUserById(@PathVariable int id) throws UserNotFoundException, UserAlreadyDeletedException {
        return userService.deleteUserById(id);
    }

    @PostMapping(EndpointConstants.RECREATED_DELETED_USER_BY_ID)
    public User recreateDeleteUserById(@PathVariable int id) throws UserNotFoundException, UserAlreadyRegisteredException {
        return userService.recreateDeletedUserByUserId(id);
    }

    @GetMapping(EndpointConstants.USER_PROFILE_WITH_JWT)
    public UserResponseDto findUserByJwt(@RequestHeader("Authorization") String jwt) throws UserNotFoundException, Exception {
        return userService.findUserByJwt(jwt);

    }

}
