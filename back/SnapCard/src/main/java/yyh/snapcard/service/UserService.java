package yyh.snapcard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import yyh.snapcard.dto.UserLoginRequest;
import yyh.snapcard.dto.UserRegisterRequest;
import yyh.snapcard.dto.UserUpdateRequest;
import yyh.snapcard.entity.User;

@Service
public interface UserService extends IService<User> {
    /**
     * 注册功能
     * @param userRegisterRequest
     * @return
     */
    User register(UserRegisterRequest userRegisterRequest);

    /**
     * 登录功能
     * @param userLoginRequest
     * @return
     */
    User login(UserLoginRequest userLoginRequest);

    /**
     * 更新功能
     * @param userUpdateRequest
     * @return
     */
    User updateUserInfo(UserUpdateRequest userUpdateRequest);
}