package npu.software.code.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    /**
     * 留言id
     */
    private String idMsg;

    /**
     * 学生用户id
     */
    private String uid;

    /**
     * 留言内容
     */
    private String content;

    /**
     * 留言创建时间
     */
    private LocalDateTime createTime;

    /**
     * 留言状态
     * 0：未回复
     * 1：已回复
     */
    private Integer state;
}
