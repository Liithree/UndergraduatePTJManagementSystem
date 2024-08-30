package npu.software.code.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluation {
    /**
     * 评价id
     */
    private String idEvl;

    /**
     * 学生用户id
     */
    private String uid;

    /**
     * 岗位id
     */
    private String idPos;

    /**
     * 评价星级
     */
    private Integer stars;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 评价创建时间
     */
    private LocalDateTime createTime;
}
