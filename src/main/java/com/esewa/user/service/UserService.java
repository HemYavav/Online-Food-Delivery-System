package com.esewa.user.service;

import com.esewa.security.auth.request.AuthenticationRequest;
import com.esewa.security.auth.response.AuthenticationResponse;
import com.esewa.shared.exception.exceptionhandler.UsernamePasswordNotMatchException;
import com.esewa.shared.exception.exceptionhandler.exceptioncollection.UserAlreadyDeletedException;
import com.esewa.shared.exception.exceptionhandler.exceptioncollection.UserAlreadyRegisteredException;
import com.esewa.shared.exception.exceptionhandler.exceptioncollection.UserNotFoundException;
import com.esewa.user.dto.UserRequestDto;
import com.esewa.user.dto.UserResponseDto;
import com.esewa.user.entity.User;

import java.util.List;

public interface UserService {
    AuthenticationResponse registerUser(UserRequestDto userDto) throws UserAlreadyRegisteredException;
    AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest) throws UserNotFoundException, UsernamePasswordNotMatchException;

    String deleteUserById(int id) throws UserNotFoundException, UserAlreadyDeletedException;

    List<UserResponseDto> getAllUserList();

    User findUserById(int userId) throws UserNotFoundException, UserAlreadyDeletedException;

    User updateUserById(int id);

    User recreateDeletedUserByUserId(int id) throws UserAlreadyRegisteredException, UserNotFoundException;
}
