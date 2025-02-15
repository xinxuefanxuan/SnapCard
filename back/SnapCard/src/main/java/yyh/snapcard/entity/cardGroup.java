package yyh.snapcard.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "tb_card_group_relation")
public class cardGroup {
    /**
     * 主键ID
     */

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 组别ID
     */
    @TableId(value = "group_id")
    private Long groupId;
    /**
     * 卡片ID
     */
    @TableId(value = "card_id")
    private Long cardId;
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
