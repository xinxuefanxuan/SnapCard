package yyh.snapcard.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {
    private String userAvatar;

    private String userName;

    private String phoneNumber;

    private String password;
}
