package npu.software.code.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkBill {
    private String idWb;
    private String uid;
    private String idPos;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer state;
}
