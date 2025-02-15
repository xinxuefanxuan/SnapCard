package yyh.snapcard.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import yyh.snapcard.common.ErrorCode;
import yyh.snapcard.constant.UserConstant;
import yyh.snapcard.dto.UserLoginRequest;
import yyh.snapcard.dto.UserRegisterRequest;
import yyh.snapcard.dto.UserUpdateRequest;
import yyh.snapcard.entity.User;
import yyh.snapcard.exception.BusinessException;
import yyh.snapcard.mapper.UserMapper;
import yyh.snapcard.service.UserService;
import yyh.snapcard.utils.PasswordUtil;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private HttpServletRequest request;

    @Resource
    private UserMapper userMapper;
    /**
     * 用户注册
     * @param userRegisterRequest
     * @return
     */
    @Override
    public User register(UserRegisterRequest userRegisterRequest) {
        //判断用户是否存在
        String userName = userRegisterRequest.getUserName();
        String password = userRegisterRequest.getPassword();
        String userAvatar = userRegisterRequest.getUserAvatar();
        String phoneNumber = userRegisterRequest.getPhoneNumber();
        //昵称密码电话号码的限制之后再说
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(phoneNumber), User::getPhoneNumber, phoneNumber);
        User user = userMapper.selectOne(queryWrapper);
        if(user != null){
            throw new BusinessException(ErrorCode.PARA_ERROR,"该用户已存在");
        }
        //用户不存在，向数据库中插入数据，同时要进行Base64编码存储密码
        String encryptedPassword = PasswordUtil.encryptPassword(password);
        user = new User();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        user.setUserName(userName);
        user.setPassword(encryptedPassword);
        user.setUserAvatar(userAvatar);
        user.setPhoneNumber(phoneNumber);
        user.setUserAccount(uuid.substring(0,16));
        user.setLastLoginTime(new Date());
        // 生成登录token
        String token = DigestUtils.md5DigestAsHex((user.getId() + RandomUtil.randomString(6)).getBytes());
        user.setLoginToken(token);
        // 记录用户的登录态
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, user);
        userMapper.insert(user);
        return user;
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {

        Integer loginType = userLoginRequest.getLoginType();
        String userAccount = userLoginRequest.getUserAccount();
        String password = userLoginRequest.getPassword();
        String code = userLoginRequest.getCode();
        String phoneNumber = userLoginRequest.getPhoneNumber();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        User user = null;

        // 根据不同登录类型处理
        switch (loginType) {
            case 0 -> {
                // 手机验证码登录
                if (StringUtils.isAnyEmpty(phoneNumber, code)) {
                    throw new BusinessException(ErrorCode.PARA_ERROR, "手机号或验证码为空");
                }
                // 从session中获取验证码
                String savedCode = (String) request.getSession().getAttribute(phoneNumber);
                if (StringUtils.isBlank(savedCode)) {
                    throw new BusinessException(ErrorCode.PARA_ERROR, "验证码已过期");
                }
                if (!code.equals(savedCode)) {
                    throw new BusinessException(ErrorCode.PARA_ERROR, "验证码错误");
                }

                // 验证手机号是否存在
                user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhoneNumber, phoneNumber));
                if (user == null) {
                    throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "用户不存在");
                }
            }
            case 1 -> {
                // 手机号密码登录
                if (StringUtils.isAnyBlank(phoneNumber, password)) {
                    throw new BusinessException(ErrorCode.PARA_ERROR, "手机号或密码为空");
                }
                LambdaQueryWrapper<User> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(StringUtils.isNotEmpty(phoneNumber),User::getPhoneNumber,phoneNumber);

                user = userMapper.selectOne(queryWrapper1);
                if (user == null) {
                    throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "用户不存在");
                }
                // 校验密码
                String encryptPassword = PasswordUtil.encryptPassword(password);
                if (!user.getPassword().equals(encryptPassword)) {
                    throw new BusinessException(ErrorCode.PARA_ERROR, "密码错误");
                }
            }
            case 2 -> {
                // 账号密码登录
                if (StringUtils.isAnyBlank(userAccount, password)) {
                    throw new BusinessException(ErrorCode.PARA_ERROR, "账号或密码为空");
                }
                user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserAccount, userAccount));
                if (user == null) {
                    throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "用户不存在");
                }
                // 校验密码
                String encryptPassword = PasswordUtil.encryptPassword(password);
                if (!user.getPassword().equals(encryptPassword)) {
                    throw new BusinessException(ErrorCode.PARA_ERROR, "密码错误");
                }
            }
            default -> throw new BusinessException(ErrorCode.PARA_ERROR, "登录类型错误");

        }
        // 更新登录信息
        user.setLastLoginTime(new Date());
        // 生成登录token
        String token = DigestUtils.md5DigestAsHex((user.getId() + RandomUtil.randomString(6)).getBytes());
        user.setLoginToken(token);
        userMapper.updateById(user);
        // 记录用户的登录态
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, user);
        return user;
    }

    @Override
    public User updateUserInfo(UserUpdateRequest userUpdateRequest) {
        //从数据库中将用户信息查出来，然后更新信息
        User user = (User)request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        Long id = user.getId();
        String userName = user.getUserName();
        String userAvatar = user.getUserAvatar();
        String password = user.getPassword();
        String encryptPassword = PasswordUtil.encryptPassword(password);
        User selectById = userMapper.selectById(id);
        if(selectById==null){
            throw new BusinessException(ErrorCode.PARA_ERROR,"用户不存在");
        }
        selectById.setUserName(userName);
        selectById.setUserAvatar(userAvatar);
        selectById.setPassword(encryptPassword);
        userMapper.updateById(selectById);
        return selectById;
    }


}
