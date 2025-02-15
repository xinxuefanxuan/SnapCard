package yyh.snapcard.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "tb_card")
public class Card {
    /**
     * 主键ID
     */

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableId(value = "user_name")
    private String userName;

    /**
     * 公司名称
     */
    @TableId(value = "company_name")
    private String companyName;

    /**
     * 职位
     */
    @TableId(value = "position")
    private String position;

    /**
     * 地址
     */
    @TableId(value = "address")
    private String address;

    /**
     * 电话号码
     */
    @TableId(value = "phone_number")
    private String phoneNumber;

    /**
     * 邮件
     */
    @TableId(value = "email")
    private String email;

    /**
     * 介绍
     */
    @TableId(value = "introduction")
    private String introduction;

    /**
     * 标签
     */
    private String tag;

    /**
     * 二维码
     */
    @TableId(value = "QRCode")
    private String QRCode;

    /**
     * 创建时间
     */
    @TableId(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableId(value = "update_time")
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableId(value = "is_deleted")
    private Integer isDeleted;
}
