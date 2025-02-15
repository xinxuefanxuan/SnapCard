package yyh.snapcard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import yyh.snapcard.annotation.LoginCheck;
import yyh.snapcard.common.BaseResponse;
import yyh.snapcard.common.ErrorCode;
import yyh.snapcard.common.ResultUtils;
import yyh.snapcard.constant.UserConstant;
import yyh.snapcard.dto.UserLoginRequest;
import yyh.snapcard.dto.UserRegisterRequest;
import yyh.snapcard.dto.UserUpdateRequest;
import yyh.snapcard.entity.User;
import yyh.snapcard.exception.BusinessException;
import yyh.snapcard.service.UserService;

@RestController
@RequestMapping("/user")
@Tag(name = "用户管理", description = "用户相关接口")
public class UserController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private UserService userService;

    @Operation(summary = "测试接口")
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @Operation(summary = "用户注册",description = "注册一个新用户")
    @PostMapping("/register")
    public BaseResponse<User> register(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userRegisterRequest == null){
            throw new BusinessException(ErrorCode.PARA_ERROR,"传入参数为空");
        }
        String userName = userRegisterRequest.getUserName();
        String password = userRegisterRequest.getPassword();
        String phoneNumber = userRegisterRequest.getPhoneNumber();
        String userAvatar = userRegisterRequest.getUserAvatar();
        if(StringUtils.isAnyEmpty(userName,password,phoneNumber)){
            throw new BusinessException(ErrorCode.PARA_ERROR,"传入参数为空");
        }
        User user = userService.register(userRegisterRequest);
        return ResultUtils.success(user);
    }

    @Operation(summary = "用户登录",description = "三种方式进行用户登录")
    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody UserLoginRequest userLoginRequest){
        if(userLoginRequest == null){
            throw new BusinessException(ErrorCode.PARA_ERROR,"传入参数为空");
        }
        User user = userService.login(userLoginRequest);
        return ResultUtils.success(user);
    }

    @Operation(summary = "退出登录")
    @LoginCheck
    @PostMapping("/logout")
    public BaseResponse<Boolean> logout(){
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);
        return ResultUtils.success(true);
    }

    @Operation(summary = "获取登陆用户信息")
    @GetMapping("/getLoginUser")
    public BaseResponse<User> getLoginUser(){
        Object loginUser = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        if(loginUser==null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR,"用户还未登录");
        }
        User user = (User) loginUser;
        return ResultUtils.success(user);
    }

    public BaseResponse<User> updateUserInfo(@RequestBody UserUpdateRequest userUpdateRequest){
        if(userUpdateRequest==null){
            throw new BusinessException(ErrorCode.PARA_ERROR,"传入的参数为空");
        }
        User user = userService.updateUserInfo(userUpdateRequest);
        return ResultUtils.success(user);
    }




}
