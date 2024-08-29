package npu.software.code.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    private String idPos;
    private String name;
    private String description;
    private String image;
    private Integer dept;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer state;
    private LocalDateTime workTime;
    private Double salary;
}
