package com.ansv.notificationsystem.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;

    private String username;

    private String code;

    private String fullName;

    private String email;

    private String phone_number;

    private String position;

    private String note;

    private Long roleId;

    private Long deparmentId;

    private String password;

}
