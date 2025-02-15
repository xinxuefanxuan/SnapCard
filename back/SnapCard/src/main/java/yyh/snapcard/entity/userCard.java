package yyh.snapcard.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user_card")
public class userCard {
    /**
     * 主键ID
     */

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    @TableId(value = "user_id")
    private Long userId;
    /**
     * 卡片ID
     */
    @TableId(value = "card_id")
    private Long cardId;
}
