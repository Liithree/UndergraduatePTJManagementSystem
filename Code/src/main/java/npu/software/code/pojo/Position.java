package npu.software.code.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    /**
     * 岗位id
     */
    private String idPos;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 岗位描述
     */
    private String description;

    /**
     * 岗位宣传图片
     */
    private String image;

    /**
     * 学院/部门
     */
    private Integer dept;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 岗位状态
     */
    private Integer state;

    /**
     * 工作时间
     */
    private LocalDateTime workTime;

    /**
     * 工作薪资
     */
    private Double salary;
}
