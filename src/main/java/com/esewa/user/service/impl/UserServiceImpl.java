package com.esewa.user.service.impl;

import com.esewa.mapper.UserMappingService;
import com.esewa.security.auth.request.AuthenticationRequest;
import com.esewa.security.auth.response.AuthenticationResponse;
import com.esewa.security.config.service.JwtService;
import com.esewa.shared.LastModifiedDate;
import com.esewa.shared.enumcollection.Role;
import com.esewa.shared.enumcollection.Status;
import com.esewa.shared.exception.exceptionhandler.UsernamePasswordNotMatchException;
import com.esewa.shared.exception.exceptionhandler.exceptioncollection.UserAlreadyDeletedException;
import com.esewa.shared.exception.exceptionhandler.exceptioncollection.UserAlreadyRegisteredException;
import com.esewa.shared.exception.exceptionhandler.exceptioncollection.UserNotFoundException;
import com.esewa.shared.message.MessageConstants;
import com.esewa.user.dto.UserRequestDto;
import com.esewa.user.dto.UserResponseDto;
import com.esewa.user.entity.User;
import com.esewa.user.repository.UserRepository;
import com.esewa.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMappingService userMappingService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse registerUser(UserRequestDto userRequestDto) throws UserAlreadyRegisteredException {
        Optional<User> isEmailExisted = userRepository.findByEmail(userRequestDto.getEmail());
        if (isEmailExisted.isPresent()) {
            throw new UserAlreadyRegisteredException();
        }
        User user = userMappingService.mapUserDtoToUser(userRequestDto);
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);
        userRepository.save(user);
        return AuthenticationResponse.builder().token(jwtService.generateToken(user)).build();
    }


    @Override
    public AuthenticationResponse authenticateUser(AuthenticationRequest authenticationRequest) throws UserNotFoundException, UsernamePasswordNotMatchException {
        Optional<User> isUserExisted = userRepository.findUsersByEmail(authenticationRequest.getEmail());
        if (isUserExisted.isEmpty()) {
            throw new UserNotFoundException();
        }
        //throw this exception if the password is not matched with encrypted password
        if (!passwordEncoder.matches(authenticationRequest.getPassword(), isUserExisted.get().getPassword())) {
            throw new UsernamePasswordNotMatchException();
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        return AuthenticationResponse.builder().token(jwtService.generateToken(isUserExisted.get())).build();
    }


    @Override
    public User findUserById(int userId) throws UserNotFoundException, UserAlreadyDeletedException {

        Optional<User> isExistedUser = userRepository.findById(userId);
        if (isExistedUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        if (isExistedUser.get().getStatus() == Status.DELETED) {
            throw new UserAlreadyDeletedException();
        }
        return isExistedUser.get();
    }

    @Override
    public List<UserResponseDto> getAllUserList() {
        return userMappingService.mappedToUserDtoList(userRepository.findAll());
    }


    @Override
    public User updateUserById(int id) {
        return null;
    }

    @Override
    public String deleteUserById(int id) throws UserNotFoundException, UserAlreadyDeletedException {
        Optional<User> isUserExisted = userRepository.findById(id);
        if (isUserExisted.isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = isUserExisted.get();
        if (user.getStatus() == Status.DELETED) {
            throw new UserAlreadyDeletedException();
        }
        user.setStatus(Status.DELETED);
        user.setLastModifiedDate(LastModifiedDate.getLastModifiedDate());
        userRepository.save(user);
        return MessageConstants.SUCCESSFULLY_DELETED;
    }

    @Override
    public User recreateDeletedUserByUserId(int id) throws UserAlreadyRegisteredException, UserNotFoundException {
        Optional<User> isUserExisted = userRepository.findById(id);
        if (isUserExisted.isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = isUserExisted.get();
        if (user.getStatus() == Status.ACTIVE) {
            throw new UserAlreadyRegisteredException();
        }
        user.setStatus(Status.ACTIVE);
        user.setLastModifiedDate(LastModifiedDate.getLastModifiedDate());
        return userRepository.save(user);
    }
}
