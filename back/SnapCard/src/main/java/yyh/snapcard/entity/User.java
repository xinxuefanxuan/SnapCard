/*
 * @Author: xinxuefanxuan 2809832579@qq.com
 * @Date: 2025-02-06 10:39:04
 * @LastEditors: xinxuefanxuan 2809832579@qq.com
 * @LastEditTime: 2025-02-07 16:00:03
 * @FilePath: \graduate\back\SnapCard\src\main\java\yyh\snapcard\entity\User.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package yyh.snapcard.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@TableName(value = "tb_user")
@Data
public class User {
    /**
     * 主键ID
     */

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户账号
     */
    @TableField(value = "user_account")
    private String userAccount;

    /**
     * 用户肖像
     */
    @TableField(value = "user_avatar")
    private String userAvatar;

    /**
     * 电话号码
     */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 登录token
     */
    @TableField(value = "login_token")
    private String loginToken;

    /**
     * 最后一次登陆时间
     */
    @TableField(value = "last_login_time")
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;



}
