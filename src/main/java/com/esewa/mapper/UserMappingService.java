package com.esewa.mapper;

import com.esewa.user.dto.UserRequestDto;
import com.esewa.user.dto.UserResponseDto;
import com.esewa.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor

public class UserMappingService {
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    public User mapUserDtoToUser(UserRequestDto userDto) {
        log.info("Encrypting User Password");
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        log.info("Converting UserDto information to User");
        log.warn("Assigning UserDto entity info to User type");
        return modelMapper.map(userDto, User.class);
    }

    public UserResponseDto mapUserToUserDto(User user) {
        log.info("Converting User information to UserDto ");
        log.warn("Assigning User entity info to UserDto types");
        return modelMapper.map(user, UserResponseDto.class);
    }

    public List<UserResponseDto> mappedToUserDtoList(List<User> products) {
        log.debug("Converting list of User entities to UserDto list.");
        return products.stream()
                .sorted(Comparator.comparing(User::getCreatedDate).reversed())
                .map(this::mapUserToUserDto).collect(Collectors.toList());
    }
}
