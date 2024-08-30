package npu.software.code.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkBill {
    /**
     * 工单id
     */
    private String idWb;

    /**
     * 学生用户id
     */
    private String uid;

    /**
     * 岗位id
     */
    private String idPos;

    /**
     * 工单创建时间
     */
    private LocalDateTime createTime;

    /**
     * 工单更新时间
     */
    private LocalDateTime updateTime;

    /**
     *  工单状态
     */
    private Integer state;
}
