package yyh.snapcard.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserUpdateRequest implements Serializable {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String userAvatar;

    private static final long serialVersionUID = 1L;

}
