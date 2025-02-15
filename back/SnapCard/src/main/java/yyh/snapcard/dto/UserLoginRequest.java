package yyh.snapcard.dto;

import lombok.Data;

@Data
public class UserLoginRequest {
    /**
     * 登录类型
     *  0 手机验证码
     *  1 手机密码
     *  2 账号密码
     */
    private Integer loginType;

    private String phoneNumber;

    private String userAccount;

    private String password;

    private String code;
}
