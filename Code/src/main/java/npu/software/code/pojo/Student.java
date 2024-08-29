package npu.software.code.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student{
    private String uid;
    private String name;
    private String account;
    private String password;
    private Integer dept;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String num;
    private Short gender;
    private LocalDateTime birthday;
    private LocalDateTime workTime;
    private Double salary;
}
