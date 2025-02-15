package yyh.snapcard.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "tb_card_group")
public class Group {
    /**
     * 主键ID
     */

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 组名
     */
    @TableId(value = "group_name")
    private String groupName;

    /**
     * 用户ID
     */
    @TableId(value = "user_id")
    private Long userID;

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
