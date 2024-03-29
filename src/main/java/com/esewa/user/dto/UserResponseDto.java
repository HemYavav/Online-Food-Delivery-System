package com.esewa.user.dto;

import com.esewa.shared.enumcollection.Role;
import com.esewa.shared.enumcollection.Status;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String username;
    private String email;
    private String gender;
    private String phone;
    private String address;
    private Role role;
    private Status status;
    private String lastModifiedDate;
    private String createdDate;


}
